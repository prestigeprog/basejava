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
            writeWithException(contacts.entrySet(), dos, entry ->{
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, entry -> {SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                // org -> listOrg-> section
                switch (type) {
                    case EDUCATION, EXPERIENCE -> writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                        if (organization.getLink() == null) {
                            dos.writeUTF("no web page");
                            dos.writeUTF("null");
                        } else {
                            Link link = organization.getLink();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            writeWithException(organization.getPositions(), dos, position -> {
                                writeDate(position, dos);
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());


                            });
                        }
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
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            List<Organization.Position> positions = new ArrayList<>();
                            int posSize = dis.readInt();
                            for (int p = 0; p < posSize; p++) {
                                LocalDate startD = readDate(dis);
                                LocalDate endD = readDate(dis);
                                positions.add(new Organization.Position(startD, endD, dis.readUTF(), dis.readUTF()));
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

    private void writeDate(Organization.Position pos, DataOutputStream dos) throws IOException {
        LocalDate startD = pos.getStartDate();
        dos.writeInt(startD.getYear());
        dos.writeUTF(startD.getMonth().name());
        LocalDate endD = pos.getEndDate();
        dos.writeInt(endD.getYear());
        dos.writeUTF(endD.getMonth().name());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        Month month = Month.valueOf(dis.readUTF());
        LocalDate date = DateUtil.of(year, month);
        return date;
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