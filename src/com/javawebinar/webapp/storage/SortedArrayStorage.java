package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveDiff(Resume resume) {
        int index = getIndex(resume.getUuid());
        System.arraycopy(storage, Math.abs(index) - 1, storage, Math.abs(index), size + 1);
        storage[Math.abs(index) - 1] = resume;
    }

    @Override
    protected void deleteDiff(String uuid) {
        int index = getIndex(uuid);
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }
}
