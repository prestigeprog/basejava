package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (!isContains(resume.getUuid())) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateDiff(resume);
        }
    }

    public void save(Resume resume) {
        if (isContains(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else if (storageOverflow()) {
            throw new StorageException(resume.getUuid(), "Storage is full!");
        } else {
            saveDiff(resume, getIndex(resume.getUuid()));
        }

    }

    public Resume get(String uuid) {
        if (!isContains(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getDiff(new Resume(uuid));
        }
    }

    public void delete(String uuid) {
        if (!isContains(uuid)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteDiff(new Resume(uuid), getIndex(uuid));
        }
    }

    protected abstract boolean storageOverflow();

    protected abstract boolean isContains(String uuid);

    protected abstract void updateDiff(Resume resume);

    protected abstract int getIndex(String uuid);

    protected abstract Resume getDiff(Resume resume);

    protected abstract void saveDiff(Resume resume, int index);

    protected abstract void deleteDiff(Resume resume, int index);
}
