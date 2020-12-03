package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super(uuid, "Resume " + uuid + " not exist");
    }
}
