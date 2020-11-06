package com.javawebinar.webapp.storage;

import com.javawebinar.webapp.exception.ExistStorageException;
import com.javawebinar.webapp.exception.NotExistStorageException;
import com.javawebinar.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract List<Resume> getList();

    protected abstract boolean isContains(SK searchKey);

    protected abstract void updateDiff(Resume resume, SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume getDiff(SK searchKey, String uuid);

    protected abstract void saveDiff(Resume resume, SK searchKey);

    protected abstract void deleteDiff(SK searchKey, String uuid);

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistKey(resume.getUuid());
        updateDiff(resume, searchKey);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistKey(resume.getUuid());
        saveDiff(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistKey(uuid);
        return getDiff(searchKey, uuid);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistKey(uuid);
        deleteDiff(searchKey, uuid);
    }

    private SK getExistKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (!isContains(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SK getNotExistKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (isContains(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getList();
        list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }
}
