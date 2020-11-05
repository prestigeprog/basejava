package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static com.javawebinar.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void storageExceptionTest() {
        try {
            for (int i = 3; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i + 1, "fullName"));
            }
        } catch (StorageException e) {
            Assert.fail("Exception must be later!");
        }
        storage.save(new Resume("Storage", "Overflow!"));
    }
}