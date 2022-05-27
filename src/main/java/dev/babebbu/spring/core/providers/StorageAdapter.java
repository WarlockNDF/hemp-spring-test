package dev.babebbu.spring.core.providers;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface StorageAdapter {

    String save(byte[] content) throws IOException;
    String save(byte[] content, String extension) throws IOException;

}
