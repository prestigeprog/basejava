package com.javawebinar.webapp.serializer;

import com.javawebinar.webapp.model.*;
import com.javawebinar.webapp.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case EDUCATION, EXPERIENCE:
                        List<Organization> orgList = ((OrganizationSection) section).getOrganizations();
                        dos.writeInt(orgList.size());
                        for (Organization organization : orgList) {
                            Link link = organization.getLink();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position pos : positions) {
                                LocalDate startD = pos.getStartDate();
                                dos.writeInt(startD.getYear());
                                dos.writeUTF(startD.getMonth().toString());
                                LocalDate endD = pos.getStartDate();
                                dos.writeInt(endD.getYear());
                                dos.writeUTF(endD.getMonth().toString());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription());
                            }
                        }
                        // org -> listOrg-> section
                        break;
                    case PERSONAL, OBJECTIVE:
                        dos.writeUTF(((SimpleTextSection) section).getDescription());
                        break;
                    case ACHIEVEMENT, QUALIFICATIONS:
                        List<String> textList = ((BulletedListSection) section).getList();
                        dos.writeInt(textList.size());
                        for (String content : textList) {
                            dos.writeUTF(content);
                        }
                        break;
                }
            }
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
                switch (sectionType) {
                    case EDUCATION, EXPERIENCE:
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < dis.readInt(); j++) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int p = 0; p < dis.readInt(); i++) {
                                int startYear = dis.readInt();
                                Month startMonth = Month.valueOf(dis.readUTF());
                                LocalDate startD = DateUtil.of(startYear,startMonth);
                                int endYear = dis.readInt();
                                Month endMonth = Month.valueOf(dis.readUTF());
                                LocalDate endD = DateUtil.of(endYear,endMonth);
                                positions.add(new Organization.Position(startD,endD,dis.readUTF(),dis.readUTF()));
                            }
                            organizations.add(new Organization(link, positions));
                        }

                        OrganizationSection os = new OrganizationSection(organizations);
                        resume.setSection(sectionType, os);
                        //section -> listOrg-> org
                        break;
                    case PERSONAL, OBJECTIVE:
                        SimpleTextSection sts = new SimpleTextSection(dis.readUTF());
                        resume.setSection(sectionType, sts);
                        break;
                    case ACHIEVEMENT, QUALIFICATIONS:
                        List<String> aq = new ArrayList<>();
                        for (int o = 0; o < dis.readInt(); o++) {
                            aq.add(dis.readUTF());
                        }
                        BulletedListSection bls = new BulletedListSection(aq);
                        resume.setSection(sectionType, bls);
                        break;
                }
            }

            return resume;
        }
    }
}