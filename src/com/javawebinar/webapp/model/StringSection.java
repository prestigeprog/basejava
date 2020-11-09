package com.javawebinar.webapp.model;

import java.util.Objects;

public class StringSection extends Section{
    private final String description;

    public StringSection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "StringSection{" +
                "description='" + description + '\'' +
                '}';
    }
}
