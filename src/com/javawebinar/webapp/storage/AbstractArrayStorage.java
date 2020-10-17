package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
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

    public void save(Resume resume) {
        if (isContains(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException(resume.getUuid(), "Storage is full!");
        } else {
            saveDiff(resume, getIndex(resume.getUuid()));
        }
    }

    protected Resume getDiff(Resume resume) {
        return storage[getIndex(resume.getUuid())];
    }

    protected boolean isContains(String uuid) {
        int index = getIndex(uuid);
        return index >= 0;
    }

    @Override
    protected void updateDiff(Resume resume) {
        storage[getIndex(resume.getUuid())] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
