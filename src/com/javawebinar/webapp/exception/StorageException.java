package com.javawebinar.webapp.exception;

public class StorageException extends RuntimeException{

    private final String uuid;

    public StorageException(String uuid, String message){
        super(message);
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }
}
