package com.javawebinar.webapp.model;

import com.javawebinar.webapp.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.javawebinar.webapp.util.DateUtil.NOW;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Position> positions;
    private final Link link;

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link link, List<Position> positions) {
        Objects.requireNonNull(positions, "positions must not be null");
        this.positions = positions;
        this.link = link;
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

    public static class Position implements Serializable{
        private static final long serialVersionUID = 1L;

        private final String title;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String description;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
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

        public String getTitle() {
            return title;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }
    }
}
