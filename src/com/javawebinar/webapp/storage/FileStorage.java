package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;
import com.javawebinar.webapp.serializator.Serializator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    private final Serializator serializator;

    protected FileStorage(File directory, Serializator serializator) {
        this.serializator = serializator;
        Objects.requireNonNull(directory, "directory must not be null!");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory!");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable!");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getList() {
        File[] filesArray = getFilesList(directory);
        List<Resume> list = new ArrayList<>();
        for (File file : filesArray) {
            list.add(getDiff(file));
        }
        return list;
    }

    @Override
    protected boolean isContains(File file) {
        return file.exists();
    }

    @Override
    protected void updateDiff(Resume resume, File file) {
        try {
            serializator.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume getDiff(File file) {
        try {
            return serializator.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void saveDiff(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateDiff(resume, file);
    }

    @Override
    protected void deleteDiff(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    public void clear() {
        File[] filesArray = getFilesList(directory);
        for (File file : filesArray) {
            deleteDiff(file);
        }
    }

    @Override
    public int size() {
        File[] files = getFilesList(directory);
        return files.length;
    }

    private File[] getFilesList(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("directory is empty", null);
        }
        return files;
    }
}
