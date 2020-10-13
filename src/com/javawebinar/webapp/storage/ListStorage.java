package com.javawebinar.webapp.storage;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    public ListStorage() {
        super(new ArrayList<>());
    }
}
