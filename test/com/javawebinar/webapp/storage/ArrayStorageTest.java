package com.javawebinar.webapp.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest{
    private static final Storage arrayStorage = new ArrayStorage();

    public ArrayStorageTest() {
        super(arrayStorage);
    }
}