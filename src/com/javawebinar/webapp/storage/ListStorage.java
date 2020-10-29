package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList();

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
        return storage.contains(searchKey);
    }

    @Override
    protected void updateDiff(Resume resume, Object searchKey) {
        storage.set(getSearchKey(resume.getUuid()), resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume tmp = new Resume(uuid);
        return storage.indexOf(tmp);
    }

    @Override
    protected void saveDiff(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteDiff(Object searchKey, String uuid) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getDiff(Object searchKey, String uuid) {
        return storage.get((Integer) searchKey);
    }
}
