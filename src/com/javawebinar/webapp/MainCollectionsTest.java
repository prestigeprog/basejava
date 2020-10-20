package com.javawebinar.webapp;

import com.javawebinar.webapp.model.Resume;
import com.javawebinar.webapp.storage.ListStorage;
import com.javawebinar.webapp.storage.Storage;

public class MainCollectionsTest {
    private static Storage storage = new ListStorage();
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public static void main(String[] args) {

        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);

        printAll();

        storage.update(RESUME_1);
        storage.update(RESUME_2);
        storage.update(RESUME_3);

        System.out.println("Get r1: " + storage.get(RESUME_1.getUuid()));
        System.out.println("Size: " + storage.size());


        printAll();
        storage.delete(RESUME_1.getUuid());
        printAll();
        storage.clear();
        printAll();

        System.out.println("Size: " + storage.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : storage.getAll()) {
            System.out.println(r);
        }
    }
}
