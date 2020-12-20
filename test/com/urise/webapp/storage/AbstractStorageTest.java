package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.urise.webapp.ResumeTestData.createFilledResume;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume R1 = createFilledResume(UUID_1, "Barry Allen");

    private static final String UUID_2 = "uuid2";
    private static final Resume R2 = createFilledResume(UUID_2, "Lex Lutor");

    private static final String UUID_3 = "uuid3";
    private static final Resume R3 = createFilledResume(UUID_3, "Oliver Queen");

    private static final String UUID_4 = "uuid4";
    private static final Resume R4 = createFilledResume(UUID_4, "Diana Prince");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume testResume = createFilledResume(UUID_1, "Max Sarychev");
        storage.update(testResume);
        assertGet(testResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(createFilledResume("ff", "dummy"));
    }

    @Test
    public void save() {
        storage.save(R4);
        assertGet(R4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        Resume resume = createFilledResume(UUID_1, "Barry Allen");
        storage.save(resume);
    }

    @Test
    public void get() {
        assertGet(R1);
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
        List<Resume> expectedResumes = new ArrayList<>();
        expectedResumes.add(R1);
        expectedResumes.add(R2);
        expectedResumes.add(R3);
        expectedResumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        Assert.assertEquals(expectedResumes, storage.getAllSorted());
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
