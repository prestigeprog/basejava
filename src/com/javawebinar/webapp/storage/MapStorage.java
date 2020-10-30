package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isContains(Object searchKey) {
        String key = (String) searchKey;
        return storage.containsKey(key);
    }

    @Override
    protected void updateDiff(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if (isContains(uuid)) {
            return uuid;
        } else {
            return null;
        }
    }

    @Override
    protected Resume getDiff(Object searchKey, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void saveDiff(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteDiff(Object searchKey, String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = storage.values().toArray(new Resume[0]);
        Arrays.sort(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
