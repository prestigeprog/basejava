package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveDiff(Resume resume, Object searchKey) {
        if (size != STORAGE_LIMIT) {
            insertElement(resume, (Integer) searchKey);
            size++;
        } else {
            throw new StorageException(resume.getUuid(), "Storage is full!");
        }
    }

    @Override
    public void deleteDiff(Object searchKey, String uuid) {
        fillDeletedElement((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateDiff(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected Resume getDiff(Object searchKey, String uuid) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(Arrays.copyOfRange(storage, 0,size));
        Collections.sort(list);
        return list;
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int searchKey);

    protected abstract Object getSearchKey(String uuid);
}
