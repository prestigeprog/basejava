package com.javawebinar.webapp;

import java.io.File;

public class MainFile {

    static int count = 0;

    public static void printDirectoryDeeply(File folder) {
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    count++;
                    for (int i = 0; i < count; i++) {
                        System.out.print("-");
                    }
                    System.out.println("-" + entry.getName());
                    printDirectoryDeeply(entry);
                } else if (entry.isFile()) {
                    for (int i = 0; i < count; i++) {
                        System.out.print(" ");
                    }
                    System.out.println(" " + entry.getName());
                }
            }
        }
    }


    public static void main(String[] args) {
        File dir = new File("D:\\local\\basejava\\src\\com\\javawebinar\\webapp");
        printDirectoryDeeply(dir);
    }
}