package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void saveDiff(Resume resume, int index) {
        if (size != STORAGE_LIMIT) {
            insertElement(resume, index);
            size++;
        } else {
            throw new StorageException(resume.getUuid(), "Storage is full!");
        }
    }

    @Override
    public void deleteDiff(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateDiff(Resume resume, int index) {
        storage[index] = resume;
    }

    protected Resume getDiff(int index) {
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
