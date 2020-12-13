package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeWithException(resume.getSections().entrySet(), dos, entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                // org -> listOrg-> section
                switch (type) {
                    case EDUCATION, EXPERIENCE -> writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                        Link link = organization.getLink();
                        dos.writeUTF(link.getName());
                        dos.writeUTF(link.getUrl() == null ? "no website" : link.getUrl());
                        writeWithException(organization.getPositions(), dos, position -> {
                            writeDate(position.getStartDate(), dos);
                            writeDate(position.getEndDate(), dos);
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription() == null ? "no description" : position.getDescription());
                        });

                    });
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((SimpleTextSection) section).getDescription());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeWithException(((BulletedListSection) section).getList(), dos, dos::writeUTF);
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
                //section -> listOrg-> org
            });
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case EDUCATION, EXPERIENCE -> {
                List<Organization> organizations = new ArrayList<>();
                readWithException(dis, () -> {
                    String name = dis.readUTF();
                    String url = dis.readUTF();
                    url = (url.equals("no website")) ? null : url;
                    Link link = new Link(name, url);
                    List<Organization.Position> positions = new ArrayList<>();
                    readWithException(dis, () -> {
                        LocalDate startD = readDate(dis);
                        LocalDate endD = readDate(dis);
                        String title = dis.readUTF();
                        String description = dis.readUTF();
                        description = (description.equals("no description")) ? null : description;
                        positions.add(new Organization.Position(startD, endD, title, description));
                    });
                    organizations.add(new Organization(link, positions));
                });
                OrganizationSection os = new OrganizationSection(organizations);
                return os;
            }
            case PERSONAL, OBJECTIVE -> {
                return new SimpleTextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                List<String> aq = new ArrayList<>();
                readWithException(dis, () -> aq.add(dis.readUTF()));
                return new BulletedListSection(aq);
            }
            default -> throw new IllegalStateException();
        }
    }


    private void writeDate(LocalDate date, DataOutputStream dos) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeUTF(date.getMonth().name());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        Month month = Month.valueOf(dis.readUTF());
        return DateUtil.of(year, month);
    }

    @FunctionalInterface
    private interface CustomWriter<T> {
        void write(T o) throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, CustomWriter<T> cw) throws IOException {
        dos.writeInt(collection.size());
        for (T object : collection) {
            cw.write(object);
        }
    }

    @FunctionalInterface
    private interface CustomReader {
        void read() throws IOException;
    }

    private void readWithException(DataInputStream dis, CustomReader cr) throws IOException {
        int size = dis.readInt();
        for (int j = 0; j < size; j++) {
            cr.read();
        }
    }
}