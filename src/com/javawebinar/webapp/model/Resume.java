package com.javawebinar.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private final Map<ContactType, Section> contacts = new HashMap<>();

    private final Map<SectionType, Section> sections = new HashMap<>();

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null!");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Section getContact(ContactType type) {
        return contacts.get(type);
    }

    public void setContact(ContactType type, Section section) {
        contacts.put(type, section);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int result = this.fullName.compareTo(o.fullName);
        return result != 0 ? result : this.uuid.compareTo(o.uuid);
    }
}
