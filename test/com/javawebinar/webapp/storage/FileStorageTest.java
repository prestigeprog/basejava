package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.serializator.ObjectStreamSerializator;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializator()));
    }
}
