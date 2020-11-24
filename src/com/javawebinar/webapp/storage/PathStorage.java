package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.StorageException;
import com.javawebinar.webapp.model.Resume;
import com.javawebinar.webapp.serializator.Serializator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final Serializator serializator;

    protected PathStorage(String dir, Serializator serializator) {
        this.serializator = serializator;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null!");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable!");
        }
    }

    @Override
    protected List<Resume> getList() {
        try {
            return Files.list(directory).map(this::getDiff).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
    }

    @Override
    protected boolean isContains(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void updateDiff(Resume resume, Path path) {
        try {
            serializator.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected Resume getDiff(Path path) {
        try {
            return serializator.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void saveDiff(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path.getFileName(), null, e);
        }
        updateDiff(resume, path);
    }

    @Override
    protected void deleteDiff(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't delete path " + path.getFileName(), null, e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteDiff);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size()  {
        try {
            return (int)Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Can't count files in the path", null, e);
        }
    }
}
