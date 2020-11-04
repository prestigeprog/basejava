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
    public List<Resume> getListForSort() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateDiff(Resume resume, Object searchKey) {
        storage.set((Integer) searchKey, resume);
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
    protected void saveDiff(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    protected void deleteDiff(Object searchKey, String uuid) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume getDiff(Object searchKey, String uuid) {
        return storage.get((Integer) searchKey);
    }
}
