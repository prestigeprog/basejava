package com.javawebinar.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    List<Position> list;
    private final Link link;

    public Organization(List<Position> list, String name, String url) {
        this.list = list;
        this.link = new Link(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return list.equals(that.list) &&
                link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, link);
    }

    @Override
    public String toString() {
        return list.toString() + "\n" + link;
    }
}
