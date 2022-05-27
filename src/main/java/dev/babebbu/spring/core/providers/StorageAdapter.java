package dev.babebbu.spring.core.providers;

import org.springframework.stereotype.Service;

@Service
public interface StorageAdapter {

    String save(byte[] content);

}
