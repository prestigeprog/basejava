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
    protected void saveDiff(Resume resume, int index) {
        int posIndex = Math.abs(index);
        System.arraycopy(storage, posIndex-1, storage, posIndex, storage.length-1);
        storage[posIndex-1] = resume;
    }

    @Override
    protected void deleteDiff(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }
}
