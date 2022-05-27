package dev.babebbu.spring.core.providers;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenstackSwiftStorageAdapter implements StorageAdapter {
    @Override
    public String save(byte[] content) {
        return null;
    }

    @Override
    public String save(byte[] content, String extension) throws IOException {
        return null;
    }
}
