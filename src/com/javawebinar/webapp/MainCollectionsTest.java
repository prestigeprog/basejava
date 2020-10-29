package com.javawebinar.webapp;

import com.javawebinar.webapp.model.Resume;
import com.javawebinar.webapp.storage.MapStorage;
import com.javawebinar.webapp.storage.Storage;

public class MainCollectionsTest {
    private static final Storage STORAGE = new MapStorage();
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public static void main(String[] args) {

        STORAGE.save(RESUME_1);
        STORAGE.save(RESUME_2);
        STORAGE.save(RESUME_3);

        printAll();

        STORAGE.update(RESUME_1);
        STORAGE.update(RESUME_2);
        STORAGE.update(RESUME_3);

        System.out.println("Get r1: " + STORAGE.get(RESUME_1.getUuid()));
        System.out.println("Size: " + STORAGE.size());


        printAll();
        STORAGE.delete(RESUME_1.getUuid());
        printAll();
        STORAGE.clear();
        printAll();

        System.out.println("Size: " + STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
