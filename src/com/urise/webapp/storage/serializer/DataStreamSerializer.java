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
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                // org -> listOrg-> section
                switch (type) {
                    case EDUCATION, EXPERIENCE -> writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                        Link link = organization.getLink();
                        dos.writeUTF(link.getName());
                        if (link.getUrl() == null) {
                            dos.writeUTF("no website");
                        } else {
                            dos.writeUTF(link.getUrl());
                        }
                        writeWithException(organization.getPositions(), dos, position -> {
                            writeStartDate(position, dos);
                            writeEndDate(position, dos);
                            dos.writeUTF(position.getTitle());
                            if (position.getDescription() == null) {
                                dos.writeUTF("no description");
                            } else {
                                dos.writeUTF(position.getDescription());
                            }
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
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                //section -> listOrg-> org
                switch (sectionType) {
                    case EDUCATION, EXPERIENCE -> {
                        List<Organization> organizations = new ArrayList<>();
                        int orgSize = dis.readInt();
                        for (int j = 0; j < orgSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("no website")) {
                                url = null;
                            }
                            Link link = new Link(name, url);
                            List<Organization.Position> positions = new ArrayList<>();
                            int posSize = dis.readInt();
                            for (int p = 0; p < posSize; p++) {
                                LocalDate startD = readDate(dis);
                                LocalDate endD = readDate(dis);
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                if(description.equals("no description")) {
                                    description = null;
                                }
                                positions.add(new Organization.Position(startD, endD, title, description));
                            }
                            organizations.add(new Organization(link, positions));
                        }
                        OrganizationSection os = new OrganizationSection(organizations);
                        resume.setSection(sectionType, os);
                    }
                    case PERSONAL, OBJECTIVE -> {
                        SimpleTextSection sts = new SimpleTextSection(dis.readUTF());
                        resume.setSection(sectionType, sts);
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> aq = new ArrayList<>();
                        int aqSize = dis.readInt();
                        for (int o = 0; o < aqSize; o++) {
                            aq.add(dis.readUTF());
                        }
                        BulletedListSection bls = new BulletedListSection(aq);
                        resume.setSection(sectionType, bls);
                    }
                }
            }
            return resume;
        }
    }

    private void writeStartDate(Organization.Position pos, DataOutputStream dos) throws IOException {
        LocalDate date = pos.getStartDate();
        dos.writeInt(date.getYear());
        dos.writeUTF(date.getMonth().name());
    }

    private void writeEndDate(Organization.Position pos, DataOutputStream dos) throws IOException {
        LocalDate date = pos.getEndDate();
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
}