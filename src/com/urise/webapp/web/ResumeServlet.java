package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "add" -> r = Resume.EMPTY;
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view" -> r = storage.get(uuid);
            case "edit" -> {
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = SimpleTextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = BulletedListSection.EMPTY;
                            }
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
                            OrganizationSection section1 = (OrganizationSection) section;
                            List<Organization> organizations = new ArrayList<>();
                            organizations.add(Organization.EMPTY);
                            if (section1 != null) {
                                for (Organization org : section1.getOrganizations()) {
                                    List<Organization.Position> positions = new ArrayList<>();
                                    positions.add(Organization.Position.EMPTY);
                                    positions.addAll(org.getPositions());
                                    organizations.add(new Organization(org.getLink(), positions));
                                }
                            }
                            section = new OrganizationSection(organizations);
                            break;
                    }
                    r.setSection(type, section);
                }
            }
            default -> throw new IllegalStateException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(("view".equals(action)) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullname");
        Resume r;
        if (uuid == null || uuid.length() == 0) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (!HtmlUtil.isEmpty(value)) {
                r.setContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value)) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE, PERSONAL -> r.setSection(type, new SimpleTextSection(value));
                    case ACHIEVEMENT, QUALIFICATIONS -> r.setSection(type, new BulletedListSection(value.split("\\n")));
                    case EDUCATION, EXPERIENCE -> {
                        List<Organization> orgs = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                List<Organization.Position> positions = new ArrayList<>();
                                String numType = type.name() + i;
                                String[] startDates = request.getParameterValues(numType + "startDate");
                                String[] endDates = request.getParameterValues(numType + "endDate");
                                String[] titles = request.getParameterValues(numType + "title");
                                String[] descriptions = request.getParameterValues(numType + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    if (titles[j] != null && titles[j].trim().length() != 0) {
                                        positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                orgs.add(new Organization(new Link(name, urls[i]), positions));
                            }
                        }
                        r.setSection(type, new OrganizationSection(orgs));
                    }
                }
            }
        }
        if(uuid == null || uuid.length() == 0){
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

}
