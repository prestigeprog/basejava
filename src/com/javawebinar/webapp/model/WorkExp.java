package com.javawebinar.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class WorkExp {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String description;

    public WorkExp(String name, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkExp workExp = (WorkExp) o;
        return name.equals(workExp.name) &&
                startDate.equals(workExp.startDate) &&
                endDate.equals(workExp.endDate) &&
                description.equals(workExp.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate, description);
    }

    @Override
    public String toString() {
        return "WorkExp{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}
