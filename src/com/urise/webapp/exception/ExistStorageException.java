package com.urise.webapp.exception;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super(uuid, "Resume " + uuid + " already exist");
    }
}
