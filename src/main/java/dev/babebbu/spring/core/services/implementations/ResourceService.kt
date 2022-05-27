package dev.babebbu.spring.core.services.implementations

import dev.babebbu.spring.core.utils.SearchCriteria
import dev.babebbu.spring.core.utils.Sorting
import dev.babebbu.spring.core.models.entities.requests.Request
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit
import javax.persistence.criteria.Predicate

@Service
@RequiredArgsConstructor
class ResourceService(
    @PersistenceUnit
    private val entityManagerFactory: EntityManagerFactory,
    private val resourceHelperService: ResourceHelperService,
) {

    fun list(
        typeRef: Class<*>?,
        pageable: Pageable?,
        search: String?,
        criteria: MutableList<SearchCriteria>?,
        orders: MutableList<Sorting>?
    ): Any {
        val em = entityManagerFactory.createEntityManager()
        val cb = entityManagerFactory.criteriaBuilder;
        val query = entityManagerFactory.criteriaBuilder.createQuery(typeRef);
        val root = query.from(typeRef)
        if (search != null) {
            var predicate: Predicate? = null
            if (search.toIntOrNull() != null)
                predicate = cb.like(root.get<Any>("id").`as`(String::class.java), "%$search%")
//                predicate = cb.like(root.get<String>("id"), "%$search%")
//                predicate = resourceHelperService.findAllById(cb, root, search);
            criteria?.forEach { c ->
                run {
                    predicate =
                        if (predicate != null) cb.or(predicate, cb.like(root.get<String>(c.key), "%$search%"))
                        else cb.or(cb.like(root.get<String>(c.key), "%$search%"))
                }
            }
            if (predicate != null) {
                query.where(predicate)
            }
        }
        orders?.forEach { order ->
            run {
                if (order.reverse)
                    query.orderBy(cb.desc(root.get<String>(order.key)))
                else
                    query.orderBy(cb.asc(root.get<String>(order.key)))
            }
        }
        val result = em.createQuery(query)
            .setFirstResult(pageable!!.pageNumber * pageable.pageSize)
            .setMaxResults(pageable.pageSize)
            .resultList;
        return PageImpl(result, pageable, result.size.toLong());
    }

    fun get(typeRef: Class<*>?, id: Int): Any {
        return get(typeRef, id, entityManagerFactory.createEntityManager())
    }

    fun get(typeRef: Class<*>?, id: Int, entityManager: EntityManager): Any {
        val query = entityManager.criteriaBuilder.createQuery(typeRef);
        val root = query.from(typeRef)
        query.where(entityManager.criteriaBuilder.equal(root.get<String>("id"), id))
        return entityManager.createQuery(query).singleResult;
    }

    fun save(request: Any, responseTypeRef: Class<*>?): Any {
        val em = entityManagerFactory.createEntityManager()
        em.transaction.begin();
        em.persist(request);
        em.flush();
        em.transaction.commit();
        return get(responseTypeRef, (request as Request).id);
    }

    fun delete(typeRef: Class<*>?, id: Int): Any {
        val em = entityManagerFactory.createEntityManager()
        val entity = get(typeRef, id, em)
        em.transaction.begin()
        em.remove(entity)
        em.transaction.commit()
        return entity
    }

}