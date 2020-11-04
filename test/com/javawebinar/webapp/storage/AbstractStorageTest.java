package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {

    final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String FULLNAME_1 = "Barry Allen";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULLNAME_2 = "Lex Lutor";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_3 = "Oliver Queen";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);

    private static final String UUID_4 = "uuid4";
    private static final String FULLNAME_4 = "Diana Prince";
    private static final Resume RESUME_4 = new Resume(UUID_4, FULLNAME_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        assertGet(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        Resume resume = new Resume(UUID_1);
        storage.save(resume);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = new ArrayList<>();
        list.add(new Resume(UUID_1, FULLNAME_1));
        list.add(new Resume(UUID_2, FULLNAME_2));
        list.add(new Resume(UUID_3, FULLNAME_3));
        list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        Assert.assertEquals(list, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}
