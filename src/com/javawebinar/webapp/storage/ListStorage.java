package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getList() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isContains(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateDiff(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void saveDiff(Resume resume, Integer searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteDiff(Integer searchKey) {
        storage.remove((searchKey).intValue());
    }

    @Override
    protected Resume getDiff(Integer searchKey) {
        return storage.get(searchKey);
    }
}
