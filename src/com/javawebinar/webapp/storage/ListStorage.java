package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private final ArrayList<Resume> storage = new ArrayList<>();

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
    public boolean isContains(Resume resume) {
        return storage.contains(resume);
    }

    @Override
    protected void updateDiff(Resume resume) {
        storage.remove(resume);
        storage.add(resume);
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void saveDiff(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void deleteDiff(Resume resume, int index) {
        storage.remove(resume);
    }

    @Override
    protected Resume getDiff(Resume resume) {
        return storage.get(storage.indexOf(resume));
    }
}
