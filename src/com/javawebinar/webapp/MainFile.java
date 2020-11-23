package com.javawebinar.webapp;

import java.io.File;

public class MainFile {

    public static void printDirectoryDeeply(File folder) {
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    System.out.println(entry.getName());
                    printDirectoryDeeply(entry);
                } else if (entry.isFile()){
                    System.out.println("   " + entry.getName());
                }
            }
        }
    }


    public static void main(String[] args) {
        File dir = new File("D:\\local\\basejava\\src\\com\\javawebinar\\webapp");
        printDirectoryDeeply(dir);
    }
}