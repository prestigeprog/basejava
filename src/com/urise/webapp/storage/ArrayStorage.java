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
        if (isPresent(storage, resume.getUuid())) {
            getResumeInStorage(resume.getUuid()) = resume;
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
        }

    }

    public void save(Resume resume) {
        if (!isPresent(storage, resume.getUuid()) && size < storage.length) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Storage contains your resume or It is full!");
        }

    }

    public Resume get(String uuid) {
        if (isPresent(storage, uuid)) {
            return getResumeInStorage(uuid);
        } else {
            System.out.println("ERROR: Storage don't contains your resume!");
            return null;
        }
    }

    public void delete(String uuid) {
        if (isPresent(storage, uuid)) {
            Resume deletedResume = getResumeInStorage(uuid);
            deletedResume = storage[size - 1];
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

    public static boolean isPresent(Resume[] a, String target) {
        for (Resume s : a) {
            if (target.equals(s.getUuid())) {
                return true;
            }
        }
        return false;
    }

    public Resume getResumeInStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
}
