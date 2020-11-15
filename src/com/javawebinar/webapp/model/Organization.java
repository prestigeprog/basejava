package com.javawebinar.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    private final Link link;

    public Organization(String title, LocalDate startDate, LocalDate endDate, String description, String name, String url) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.link = new Link(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, description, link);
    }

    @Override
    public String toString() {
        return title + "\n" + startDate+ "\n" + endDate+ "\n" + description + "\n" + link;
    }
}
