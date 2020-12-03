package com.javawebinar.webapp;

import java.io.File;

public class MainFile {

    static int count = 0;

    public static void printDirectoryDeeply(File folder, String offset) {
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    System.out.println(offset + "D: " + entry.getName());
                    printDirectoryDeeply(entry, offset + "  ");
                } else if (entry.isFile()) {
                    System.out.println(offset + "F: " + entry.getName());
                }
            }
        }
    }


    public static void main(String[] args) {
        File dir = new File("D:\\local\\basejava\\src\\com");
        printDirectoryDeeply(dir,"");
    }
}