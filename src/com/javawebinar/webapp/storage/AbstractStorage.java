package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateDiff(resume);
        }
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveDiff(resume, getIndex(resume.getUuid()));
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getDiff(uuid);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteDiff(new Resume(uuid), index);
        }
    }

    protected abstract void updateDiff(Resume resume);

    protected abstract int getIndex(String uuid);

    protected abstract Resume getDiff(String uuid);

    protected abstract void saveDiff(Resume resume, int index);

    protected abstract void deleteDiff(Resume resume, int index);
}
