package com.javawebinar.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;
    private final String link;

    public Experience(String name, LocalDate startDate, LocalDate endDate, String description, String link) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience experience = (Experience) o;
        return name.equals(experience.name) &&
                startDate.equals(experience.startDate) &&
                endDate.equals(experience.endDate) &&
                description.equals(experience.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate, description);
    }

    @Override
    public String toString() {
        return "" + name + "\n" + startDate+ "\n" + endDate+ "\n" + description + "\n" + link;
    }
}
