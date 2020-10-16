package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (!isContains(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateDiff(resume);
        }
    }

    public void save(Resume resume) {
        if (isContains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveDiff(resume, getIndex(resume.getUuid()));
        }

    }

    public Resume get(String uuid) {
        Resume tmp = new Resume(uuid);
        if (!isContains(tmp)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getDiff(tmp);
        }
    }

    public void delete(String uuid) {
        Resume tmp = new Resume(uuid);
        if (!isContains(tmp)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteDiff(tmp, getIndex(uuid));
        }
    }

    protected abstract boolean isContains(Resume resume);

    protected abstract void updateDiff(Resume resume);

    protected abstract int getIndex(String uuid);

    protected abstract Resume getDiff(Resume resume);

    protected abstract void saveDiff(Resume resume, int index);

    protected abstract void deleteDiff(Resume resume, int index);
}
