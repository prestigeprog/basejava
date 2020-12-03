package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveDiff(Resume resume, Integer searchKey) {
        if (size != STORAGE_LIMIT) {
            insertElement(resume, searchKey);
            size++;
        } else {
            throw new StorageException(resume.getUuid(), "Storage is full!");
        }
    }

    @Override
    public void deleteDiff(Integer searchKey) {
        fillDeletedElement(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateDiff(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    @Override
    protected Resume getDiff(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected boolean isContains(Integer searchKey) {
        return searchKey >= 0;
    }

    public List<Resume> getList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int searchKey);

    protected abstract Integer getSearchKey(String uuid);
}
