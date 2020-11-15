package com.javawebinar.webapp;

import java.io.File;

public class MainFile {

    public static void processFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    processFilesFromFolder(entry);
                } else {
                    System.out.println(entry);
                }
            }
        }
    }
    public static void main(String[] args) {
        File dir = new File(".");
        processFilesFromFolder(dir);
    }
}