package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = getExistKey(resume.getUuid());
        updateDiff(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistKey(resume.getUuid());
        saveDiff(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistKey(uuid);
        return getDiff(searchKey, uuid);
    }

    public void delete(String uuid) {
        Object searchKey = getExistKey(uuid);
        deleteDiff(searchKey, uuid);
    }

    private Object getExistKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isContains(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isContains(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isContains(Object searchKey);

    protected abstract void updateDiff(Resume resume, Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getDiff(Object searchKey, String uuid);

    protected abstract void saveDiff(Resume resume, Object searchKey);

    protected abstract void deleteDiff(Object searchKey, String uuid);
}
