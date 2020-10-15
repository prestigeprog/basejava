package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume resume) {
        if (!storage.contains(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.remove(resume);
            storage.add(resume);
        }
    }

    @Override
    public void save(Resume resume) {
        if (storage.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storage.add(resume);
        }

    }

    @Override
    public Resume get(String uuid) {
        Resume tmp = new Resume(uuid);
        if (!storage.contains(tmp)) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage.get(storage.indexOf(tmp));
        }
    }

    @Override
    public void delete(String uuid) {
        Resume tmp = new Resume(uuid);
        if (!storage.contains(tmp)) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(tmp);
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
