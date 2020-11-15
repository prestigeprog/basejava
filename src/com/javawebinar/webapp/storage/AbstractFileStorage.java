package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null!");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory!");
        }
        if (!directory.canRead() || directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable!");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getList() {
        File[] filesArray = directory.listFiles();
        List<Resume> list = new ArrayList<>();
        if (filesArray != null) {
            for (File file : filesArray) {
                list.add(getDiff(file));
            }
        } else {
            throw new StorageException("directory is empty", null);
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
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume getDiff(File file) {
        return doRead(file);
    }

    @Override
    protected void saveDiff(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Error", file.getName(), e);
        }
    }

    @Override
    protected void deleteDiff(File file) {
        doDelete(file);
    }

    @Override
    public void clear() {
        File[] filesArray = directory.listFiles();
        if (filesArray != null) {
            for (File file : filesArray) {
                doDelete(file);
            }
        } else {
            throw new StorageException("directory is empty", null);
        }
    }

    @Override
    public int size() {
        String[] files = directory.list();
        if (files == null) {
            throw new StorageException("directory is empty",null);
        }
        return files.length;
    }

    protected abstract void doDelete(File file);

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file);
}
