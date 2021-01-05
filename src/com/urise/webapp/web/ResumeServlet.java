package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //response.setHeader("Content-Type", "text/html: charset=UTF-8");
        String uuid = request.getParameter("uuid");

        PrintWriter writer = response.getWriter();

        writer.println("<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th>UUID</th>\n" +
                "<th>Full Name</th>\n" +
                "</tr>");
        if (uuid.equals("f1fd0509-4882-44ff-8557-86443030dfe1") || uuid.equals("bfbd612e-53f0-40c7-9b41-96ed65daf933")
                || uuid.equals("e11434b9-1fbe-469a-bee9-de6d6ffd28a9")) {
            Resume resume = storage.get(uuid);
            writer.println("<tr>");
            writer.println("<td>" + resume.getUuid() + "</td>");
            writer.println("<td>" + resume.getFullName() + "</td>");
            writer.println("</tr>");
        } else {
            for (Resume r : storage.getAllSorted()) {
                writer.println("<tr>");
                writer.println("<td>" + r.getUuid() + "</td>");
                writer.println("<td>" + r.getFullName() + "</td>");
                writer.println("</tr>");
            }
        }
        writer.println("</tbody\n" +
                "</table>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
