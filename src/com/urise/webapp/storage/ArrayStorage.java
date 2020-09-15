package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        if (isPresent(storage, r.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                }
            }
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
        }

    }

    public void save(Resume r) {
        if (!isPresent(storage, r.getUuid()) && size < storage.length+1) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR: Storage contains your resume or It is full!");
        }

    }

    public Resume get(String uuid) {
        if (isPresent(storage, uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
        }
        return null;
    }

    public void delete(String uuid) {
        if (isPresent(storage, uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    for (int j = i; j < size; j++) {
                        storage[i] = storage[j];
                    }
                }
            }
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

    public static boolean isPresent(Resume[] a, String target) {
        for (Resume s : a) {
            if (target.equals(s.getUuid())) {
                return true;
            }
        }
        return false;
    }
}
