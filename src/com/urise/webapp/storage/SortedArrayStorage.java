package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume resume) {
        storage[size] = resume;
        for (int i = 1; i < size; i++) {
            Resume x = storage[i];
            // Найти место для вставки с помощью бинарного поиска
            int j = (Arrays.binarySearch(storage, 0, i, x) + 1);
            // Смещение массива в одну позицию вправо
            System.arraycopy(storage, j, storage, j + 1, i - j);
            // Размещение элемента в правильном месте
            storage[j] = x;
        }

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
