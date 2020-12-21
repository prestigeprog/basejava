package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SqlStorage implements Storage {
    SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        helper =new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        helper.doStatement("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        if (get(resume.getUuid()) != null) {
            helper.doStatement("UPDATE resume SET full_name=? WHERE uuid=?", ps -> {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                ps.execute();
                return null;
            });
        }
    }

    @Override
    public void save(Resume resume) {
        helper.doStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            try{
                ps.execute();
            } catch (SQLException e) {
                if (e.getSQLState().equals("23505")){
                    throw new ExistStorageException(null);
                }
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.doStatement("SELECT * FROM resume r WHERE r.uuid =?", (ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        }));
    }

    @Override
    public void delete(String uuid) {
        if (get(uuid) != null) {
            helper.doStatement("DELETE FROM resume WHERE uuid=?", ps -> {
                ps.setString(1, uuid);
                ps.execute();
                return null;
            });
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.doStatement("SELECT uuid, full_name FROM resume r", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid").trim();
                String fullName = rs.getString("full_name").trim();
                resumes.add(new Resume(uuid, fullName));
            }
            resumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
            return resumes;
        });
    }

    @Override
    public int size() {
        return helper.doStatement("SELECT COUNT(uuid) FROM resume", ps->{
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("Empty table");
            }
            int count = rs.getInt(1);
            return count;
        });
    }
}
