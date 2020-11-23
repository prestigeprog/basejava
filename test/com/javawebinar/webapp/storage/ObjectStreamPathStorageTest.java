package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.serializator.ObjectStreamSerializator;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_PATH, new ObjectStreamSerializator()));
    }
}
