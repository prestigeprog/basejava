package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractStorage implements Storage {
    protected Collection<Resume> collection;


    public AbstractStorage(Collection<Resume> collection) {
        this.collection = collection;
    }

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public void update(Resume resume) {
        Iterator<Resume> iterator = collection.iterator();
        if (!collection.contains(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            while (iterator.hasNext()) {
                if (iterator.next().getUuid().equals(resume.getUuid())) {
                    iterator.remove();
                }
            }
            collection.add(resume);
        }
    }

    @Override
    public void save(Resume resume) {
        if (collection.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            collection.add(resume);
        }

    }

    @Override
    public Resume get(String uuid) {
        Iterator<Resume> iterator = collection.iterator();
        Resume tmp = new Resume(uuid);
        if(!collection.contains(tmp)) {
            throw new NotExistStorageException(uuid);
        }else {
            while (iterator.hasNext()) {
                if (iterator.next().getUuid().equals(uuid)) {
                    tmp = iterator.next();
                }
            }
        }
        return tmp;
    }

    @Override
    public void delete(String uuid) {
        Resume tmp = new Resume(uuid);
        Iterator<Resume> iterator = collection.iterator();
        if (!collection.contains(tmp)) {
            throw new NotExistStorageException(uuid);
        } else {
            while (iterator.hasNext()) {
                if (iterator.next().getUuid().equals(uuid)) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public Resume[] getAll() {
        return collection.toArray(new Resume[collection.size()]);
    }

    @Override
    public int size() {
        return collection.size();
    }

}
