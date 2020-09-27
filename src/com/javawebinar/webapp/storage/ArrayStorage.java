package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveDiff(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteDiff(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
