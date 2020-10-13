package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    public ListStorage() {
        super(new ArrayList<Resume>());
    }
}
