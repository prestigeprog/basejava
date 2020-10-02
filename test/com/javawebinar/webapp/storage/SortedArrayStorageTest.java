package com.javawebinar.webapp.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    private static final Storage sortedArrayStorage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(sortedArrayStorage);
    }
}