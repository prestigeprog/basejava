package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (getIndexByUuid(resume.getUuid())!= 0) {
            storage[getIndexByUuid(resume.getUuid())] = resume;
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
        }

    }

    public void save(Resume resume) {
        if (getIndexByUuid(resume.getUuid())== 0 && size < storage.length) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Storage contains your resume or It is full!");
        }

    }

    public Resume get(String uuid) {
        if (getIndexByUuid(uuid)!= 0) {
            return storage[getIndexByUuid(uuid)];
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
            return null;
        }
    }

    public void delete(String uuid) {
        if (getIndexByUuid(uuid)!= 0) {
            storage[getIndexByUuid(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return 0;
    }
}
