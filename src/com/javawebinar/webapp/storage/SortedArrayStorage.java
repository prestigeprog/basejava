package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("ERROR: Storage contains resume with " + resume.getUuid() + "!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Storage is full!");
        } else {
            System.arraycopy(storage, Math.abs(index) - 1, storage, Math.abs(index), size + 1);
            storage[Math.abs(index) - 1] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
        } else {
            System.out.println("ERROR: Storage don't contains resume with " + uuid + "!");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
