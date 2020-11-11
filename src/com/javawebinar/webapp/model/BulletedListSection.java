package com.javawebinar.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {
    private List<String> list = new ArrayList<>();

    public BulletedListSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulletedListSection that = (BulletedListSection) o;
        return list.equals(that.list);
    }

    @Override
    public String toString() {
        return "" + list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
