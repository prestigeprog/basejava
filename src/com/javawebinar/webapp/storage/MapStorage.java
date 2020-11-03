package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.List;

public class MapStorage extends AbstractStorage{

    @Override
    protected List<Resume> sortDiff() {
        return null;
    }

    @Override
    protected boolean isContains(Object searchKey) {
        return false;
    }

    @Override
    protected void updateDiff(Resume resume, Object searchKey) {

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected Resume getDiff(Object searchKey, String uuid) {
        return null;
    }

    @Override
    protected void saveDiff(Resume resume, Object searchKey) {

    }

    @Override
    protected void deleteDiff(Object searchKey, String uuid) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
