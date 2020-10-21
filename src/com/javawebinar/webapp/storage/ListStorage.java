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
    protected void updateDiff(Resume resume, int index) {
        storage.set(getIndex(resume.getUuid()), resume);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume tmp = new Resume(uuid);
        return storage.indexOf(tmp);
    }

    @Override
    protected void saveDiff(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void deleteDiff(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume getDiff(int index) {
        return storage.get(index);
    }
}
