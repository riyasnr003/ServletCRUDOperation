package com.demo.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.Emp;
import com.model.dao.EmpDao;

@WebServlet(name = "EditServlet2",urlPatterns = {"/EditServlet2"})
public class EditServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditServlet2() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve employee details from the HTML form
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        // Create an Emp object and set its attributes
        Emp e = new Emp();
        e.setId(id);
        e.setName(name);
        e.setPassword(password);
        e.setEmail(email);
        e.setCountry(country);

        // Call the update method from EmpDao to update the employee record
        int status = EmpDao.update(e);

        if (status > 0) {
            // Redirect to the ViewServlet after successfully updating the record
            response.sendRedirect("ViewServlet");
        } else {
            out.println("Sorry! Unable to update record");
        }

        out.close();
    }
}
