package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isContains(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void updateDiff(Resume resume, String searchKey) {
        storage.put(searchKey, resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume getDiff(String searchKey, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void saveDiff(Resume resume, String searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteDiff(String searchKey, String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
