package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    public static final BulletedListSection EMPTY = new BulletedListSection("");

    private List<String> list;

    public BulletedListSection() {
    }

    public BulletedListSection(String... list) {
        this(Arrays.asList(list));
    }

    public BulletedListSection(List<String> list) {
        Objects.requireNonNull(list, "list must not be null");
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
        return list.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
