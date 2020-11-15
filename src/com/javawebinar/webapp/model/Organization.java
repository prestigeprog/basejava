package com.javawebinar.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private List<Position> positions;
    private final Link link;

    public Organization(List<Position> positions, String name, String url) {
        Objects.requireNonNull(positions, "positions must not be null");
        this.positions = positions;
        this.link = new Link(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return positions.equals(that.positions) &&
                link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions, link);
    }

    @Override
    public String toString() {
        return positions.toString() + "\n" + link;
    }
}
