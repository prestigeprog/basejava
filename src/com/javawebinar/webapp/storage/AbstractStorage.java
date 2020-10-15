package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public abstract void update(Resume resume);

    public abstract void save(Resume resume);

    public abstract Resume get(String uuid);

    public abstract void delete(String uuid);

    public abstract Resume[] getAll();

    public abstract int size();

}
