package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void updateDiff(Resume resume, Object searchKey) {
        storage.set(getSearchKey(resume.getUuid()), resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void saveDiff(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteDiff(Object searchKey, String uuid) {
        int index = (Integer) searchKey;
        storage.remove(index);
    }

    @Override
    protected Resume getDiff(Object searchKey, String uuid) {
        return storage.get((Integer) searchKey);
    }
}
