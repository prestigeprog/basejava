package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("ERROR: Storage don't contains resume with " + resume.getUuid() + "!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("ERROR: Storage contains resume with " + resume.getUuid() + "!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full!");
        } else {
            saveDiff(resume, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Storage don't contains resume with " + uuid + "!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteDiff(index);
            size--;
        } else {
            System.out.println("ERROR: Storage don't contains resume with " + uuid + "!");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveDiff(Resume resume, int index);

    protected abstract void deleteDiff(int index);

}
