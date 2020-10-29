package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (!isContains(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateDiff(resume, searchKey);
        }
    }

    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (isContains(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveDiff(resume, searchKey);
        }
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isContains(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getDiff(searchKey, uuid);
        }
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isContains(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteDiff(searchKey, uuid);
        }
    }

    protected abstract boolean isContains(Object searchKey);

    protected abstract void updateDiff(Resume resume, Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getDiff(Object searchKey, String uuid);

    protected abstract void saveDiff(Resume resume, Object searchKey);

    protected abstract void deleteDiff(Object searchKey, String uuid);
}
