package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public final SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        helper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        helper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        helper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }
            deleteContacts(conn, resume);
            saveContacts(conn, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        helper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            saveContacts(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.execute("" +
                        "SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "  ON r.uuid = c.resume_uuid " +
                        "   WHERE r.uuid =?",
                (ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContacts(rs, r);
                    } while (rs.next());
                    return r;
                }));
    }

    @Override
    public void delete(String uuid) {
        helper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.execute("SELECT * FROM resume r" +
                " LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                "  ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumes = new LinkedHashMap<>();
            while (rs.next()) {
                Resume resume = resumes.get(rs.getString("uuid"));
                if (resume == null) {
                    resume = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                    resumes.put(rs.getString("uuid"), resume);
                }
                addContacts(rs, resume);
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return helper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void addContacts(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.setContact(ContactType.valueOf(rs.getString("type")), value);
        }

    }

    private void saveContacts(Connection conn, Resume resume) throws SQLException {
        if (resume.getContacts() != null) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void deleteContacts(Connection conn, Resume resume) {
        helper.execute("DELETE FROM contact WHERE resume_uuid = ?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }
}
