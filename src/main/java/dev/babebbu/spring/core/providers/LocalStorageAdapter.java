package dev.babebbu.spring.core.providers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Date;

@Service
public class LocalStorageAdapter implements StorageAdapter {

    @Override
    public String save(byte[] content) throws IOException {
        String workingDirectory = getWorkingDirectory();
        String fileName = generateFileName();
        write(workingDirectory, fileName, content);
        return fileName;
    }

    @Override
    public String save(byte[] content, String extension) throws IOException {
        String workingDirectory = getWorkingDirectory();
        String fileName = generateFileName(extension);
        write(workingDirectory, fileName, content);
        return fileName;
    }

    private String getWorkingDirectory() {
        return Path.of("").toAbsolutePath().toString();
    }

    private String generateFileName() {
        Date date = new Date();
        long unixTimestamp = date.getTime();
        return "file-" + unixTimestamp +  "-" + RandomStringUtils.randomAlphanumeric(16);
    }

    private String generateFileName(String extension) {
        Date date = new Date();
        long unixTimestamp = date.getTime();
        return "file-" + unixTimestamp +  "-" + RandomStringUtils.randomAlphanumeric(16) + "." + extension;
    }

    private void write(String dir, String filename, byte[] content) throws IOException {
        File file = new File(dir + "/storage/" + filename);
        PrintStream out = new PrintStream(file);
        out.write(content);
        out.close();
    }
}
