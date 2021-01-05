package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            deleteContacts(conn, uuid);
            deleteSections(conn, uuid);
            saveContacts(conn, resume);
            saveSections(conn, resume);
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
            saveSections(conn, resume);
            return null;
        });
    }


    @Override
    public Resume get(String uuid) {
        return helper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, r);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, r);
                }
            }
            return r;
        });
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
        return helper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    resumes.put(uuid, resume);
                }
            }
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addContact(rs, r);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addSection(rs, r);
                }
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

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.setContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String content = rs.getString("content");
        SectionType type = SectionType.valueOf(rs.getString("type"));
        if (content != null) {
            switch (type) {
                case PERSONAL, OBJECTIVE -> {
                    SimpleTextSection textSection = new SimpleTextSection(content);
                    resume.setSection(type, textSection);
                }
                case QUALIFICATIONS, ACHIEVEMENT -> {
                    BulletedListSection listSection = new BulletedListSection(Arrays.asList(content.split("\n")));
                    resume.setSection(type, listSection);
                }
            }
        }
    }

    private void saveContacts(Connection conn, Resume resume) throws SQLException {
        if (resume.getContacts().size() != 0) {
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

    private void saveSections(Connection conn, Resume resume) throws SQLException {
        if (resume.getSections().size() != 0) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section(resume_uuid, type, content) VALUES (?,?,?)")) {
                for (Map.Entry<SectionType, AbstractSection> e : resume.getSections().entrySet()) {
                    ps.setString(1, resume.getUuid());
                    SectionType type = e.getKey();
                    ps.setString(2, type.name());
                    AbstractSection section = e.getValue();
                    switch (type) {
                        case PERSONAL, OBJECTIVE -> ps.setString(3, ((SimpleTextSection) section).getDescription());
                        case QUALIFICATIONS, ACHIEVEMENT -> {
                            List<String> list = ((BulletedListSection) section).getList();
                            String listItem = String.join("\n", list);
                            ps.setString(3, listItem);
                        }
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void deleteContacts(Connection conn, String uuid) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void deleteSections(Connection conn, String uuid) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }
}
