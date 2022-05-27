package dev.babebbu.spring.core.services.implementations;

import dev.babebbu.spring.core.providers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final LocalStorageAdapter localStorageProvider;
    private final SftpStorageAdapter sftpStorageProvider;
    private final FtpStorageAdapter ftpStorageProvider;
    private final OpenstackSwiftStorageAdapter openstackSwiftStorageProvider;
    private final AwsSimpleStorageServiceStorageAdapter awsSimpleStorageServiceStorageProvider;

    public StorageAdapter local() {
        return localStorageProvider;
    }

    public StorageAdapter sftp() {
        return sftpStorageProvider;
    }

    public StorageAdapter ftp() {
        return ftpStorageProvider;
    }

    public StorageAdapter swift() {
        return openstackSwiftStorageProvider;
    }

    public StorageAdapter s3() {
        return awsSimpleStorageServiceStorageProvider;
    }

    public StorageAdapter adapter(String provider) {
        return switch (provider) {
            case "local" -> local();
            case "sftp" -> sftp();
            case "ftp" -> ftp();
            case "swift" -> swift();
            case "s3" -> s3();
            default -> null;
        };
    }

}
