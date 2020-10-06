package com.javawebinar.webapp;

import com.javawebinar.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume r = new Resume();
        Method method = r.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        System.out.println(method.invoke(r));
    }
}

