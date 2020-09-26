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
        if (getIndex(resume.getUuid()) == -1) {
            if (size < STORAGE_LIMIT) {
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
            } else {
                System.out.println("ERROR: Storage is full!");
            }
        } else {
            System.out.println("ERROR: Storage contains resume with " + resume.getUuid() + "!");
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
