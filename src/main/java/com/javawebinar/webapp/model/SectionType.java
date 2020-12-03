package com.javawebinar.webapp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum SectionType {
    PERSONAL("Личные Качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    SectionType() {
    }

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
