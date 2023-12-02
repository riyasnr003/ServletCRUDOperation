package com.demo.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.model.Emp;
import com.model.dao.EmpDao;

@WebServlet(name = "ViewServlet",urlPatterns = {"/ViewServlet"})
public class ViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='index.html'>Add New Employee</a>");
        out.println("<h1>Employees List</h1>");

        // Retrieve the list of all employees from the database
        List<Emp> list = EmpDao.getAllEmployees();
        
        
        out.println("<style>");
        out.println("body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
        out.println(".container { width: 80%; margin: auto; background: #fff; padding: 20px; margin-top: 50px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
        out.println("h1 { text-align: center; color: #333; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        out.println("table, th, td { border: 1px solid #ddd; text-align: left; padding: 12px; }");
        out.println("th { background-color: #4caf50; color: white; }");
        out.println("a { text-decoration: none; color: blue; }");
        out.println("a:hover { color: #45a049; }");
        out.println("</style>");
        
        out.print("<table border='1' width='100%'");
        out.print(
                "<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");
        for (Emp e : list) {
            out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getPassword() + "</td><td>"
                    + e.getEmail() + "</td><td>" + e.getCountry() + "</td><td><a href='EditServlet?id=" + e.getId()
                    + "'>edit</a></td><td><a href='DeleteServlet?id=" + e.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
