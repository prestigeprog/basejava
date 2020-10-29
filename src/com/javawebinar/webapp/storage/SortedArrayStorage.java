package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int posIndex = Math.abs(index) - 1;
        System.arraycopy(storage, posIndex, storage, posIndex + 1, size - posIndex);
        storage[posIndex] = resume;
    }

    @Override
    protected void fillDeletedElement(int searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey, size - searchKey - 1);
    }
}
