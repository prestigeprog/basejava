package com.javawebinar.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public Position(String title, LocalDate startDate, LocalDate endDate, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return title.equals(position.title) &&
                startDate.equals(position.startDate) &&
                endDate.equals(position.endDate) &&
                Objects.equals(description, position.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, endDate, description);
    }

    @Override
    public String toString() {
        return title + "\n" + startDate + "\n" + endDate + "\n" + description;
    }
}
