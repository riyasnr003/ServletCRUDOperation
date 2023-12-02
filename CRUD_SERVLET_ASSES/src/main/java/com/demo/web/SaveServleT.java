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

@WebServlet(name = "SaveServleT",urlPatterns = {"/SaveServleT"})
public class SaveServleT extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve employee data from the HTML form
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        // Create an Emp object and set its attributes
        Emp e = new Emp();
        e.setName(name);
        e.setPassword(password);
        e.setEmail(email);
        e.setCountry(country);

        // Call the save method from EmpDao to save the employee record
        int status = EmpDao.save(e);

        if (status > 0) {
            out.print("<p>Record saved successfully!</p>");
            // Redirect to the index.html page after successfully saving the record
            request.getRequestDispatcher("index.html").include(request, response);
        } else {
            out.println("Sorry! Unable to save record");
        }

        out.close();
    }
}
