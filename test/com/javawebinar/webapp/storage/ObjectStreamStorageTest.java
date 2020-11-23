package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.serializator.ObjectStreamSerializator;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializator()));
    }
}
