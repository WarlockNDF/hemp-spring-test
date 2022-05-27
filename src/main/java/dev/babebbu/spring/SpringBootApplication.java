package dev.babebbu.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;

@org.springframework.boot.autoconfigure.SpringBootApplication(exclude = {
    ElasticsearchRepositoriesAutoConfiguration.class,
    ElasticsearchDataAutoConfiguration.class,
    ElasticsearchRestClientAutoConfiguration.class
})
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}
