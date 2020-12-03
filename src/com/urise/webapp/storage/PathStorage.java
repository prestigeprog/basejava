package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        this.streamSerializer = streamSerializer;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null!");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable!");
        }
    }

    @Override
    protected List<Resume> getList() {
        return getPaths().map(this::getDiff).collect(Collectors.toList());
    }

    @Override
    protected boolean isContains(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void updateDiff(Resume resume, Path path) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
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
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileName(path), e);
        }
    }

    @Override
    protected void saveDiff(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path, getFileName(path), e);
        }
        updateDiff(resume, path);
    }

    @Override
    protected void deleteDiff(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't delete path " + path, getFileName(path), e);
        }
    }

    @Override
    public void clear() {
        getPaths().forEach(this::deleteDiff);
    }

    @Override
    public int size() {
        return (int) getPaths().count();
    }

    private Stream<Path> getPaths() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Can't do list from path", e);
        }
    }

    private String getFileName(Path path){
        return path.getFileName().toString();
    }
}
