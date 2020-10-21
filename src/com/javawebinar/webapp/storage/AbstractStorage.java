package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateDiff(resume, index);
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveDiff(resume, index);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getDiff(index);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteDiff(index);
        }
    }

    protected abstract void updateDiff(Resume resume, int index);

    protected abstract int getIndex(String uuid);

    protected abstract Resume getDiff(int index);

    protected abstract void saveDiff(Resume resume, int index);

    protected abstract void deleteDiff(int index);
}
