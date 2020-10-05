package com.javawebinar.webapp;

import com.javawebinar.webapp.model.Resume;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        System.out.println(r.getClass().toString());
    }
}
