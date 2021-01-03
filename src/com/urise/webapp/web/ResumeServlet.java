package com.urise.webapp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //response.setHeader("Content-Type", "text/html: charset=UTF-8");
        String name = request.getParameter("name");

        Writer out = response.getWriter();
        out.write("<html><head><style>");
        out.write("</TITLE></HEAD><BODY>");
        out.write("<h2>HTML Table</h2><table style=\"width:100%\">");
        out.write("<tr>\n" +
                "    <th>Firstname</th>\n" +
                "    <th>Lastname</th>\n" +
                "    <th>Age</th>\n" +
                "  </tr>");
        out.write("<tr>\n" +
                "    <td>Jill</td>\n" +
                "    <td>Smith</td>\n" +
                "    <td>50</td>\n" +
                "  </tr>");
        out.write("<tr>\n" +
                "    <td>Eve</td>\n" +
                "    <td>Jackson</td>\n" +
                "    <td>94</td>\n" +
                "  </tr>");
        out.write("</table></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
