package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("Электронная почта"),
    LINKEDIN("Linked in"),
    GITHUB("Github"),
    STACKOVERFLOW("StackOverFlow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType() {
    }

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
