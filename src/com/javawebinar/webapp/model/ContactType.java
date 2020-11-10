package com.javawebinar.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("Электронная почта"),
    LINKEDIN("Linked in"),
    GITHUB("Github"),
    STACKOVERFLOW("StackOverFlow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
