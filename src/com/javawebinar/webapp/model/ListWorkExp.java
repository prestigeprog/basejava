package com.javawebinar.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListWorkExp extends Section{
    private List<WorkExp> list;

    public ListWorkExp(List<WorkExp> list) {
        this.list = list;
    }

    public List<WorkExp> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListWorkExp that = (ListWorkExp) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "ListWorkExp{" +
                "list=" + list +
                '}';
    }
}
