package com.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Emp;

public class EmpDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Hibernate";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "00001418";

    // Establishes a database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error loading MySQL JDBC Driver", e);
        }
    }

    // Saves an employee to the database
    public static int save(Emp e) {
        int status = 0;
        try (Connection con = EmpDao.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "insert into Employeee(name,password,email,country) values (?,?,?,?)")) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getCountry());

            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or handle the exception appropriately
        }

        return status;
    }

    // Updates an employee in the database
    public static int update(Emp e) {
        int status = 0;
        try (Connection con = EmpDao.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "update Employeee set name=?,password=?,email=?,country=? where id=?")) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getCountry());
            ps.setInt(5, e.getId());

            status = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or handle the exception appropriately
        }

        return status;
    }

    // Deletes an employee from the database by ID
    public static int delete(int id) {
        int status = 0;
        try (Connection con = EmpDao.getConnection();
             PreparedStatement ps = con.prepareStatement("delete from Employeee where id=?")) {

            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }

        return status;
    }

    // Retrieves an employee from the database by ID
    public static Emp getEmployeeById(int id) {
        Emp e = new Emp();

        try (Connection con = EmpDao.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from Employeee where id=?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e.setId(rs.getInt(1));
                    e.setName(rs.getString(2));
                    e.setPassword(rs.getString(3));
                    e.setEmail(rs.getString(4));
                    e.setCountry(rs.getString(5));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or handle the exception appropriately
        }

        return e;
    }

    // Retrieves all employees from the database
    public static List<Emp> getAllEmployees() {
        List<Emp> list = new ArrayList<>();

        try (Connection con = EmpDao.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from Employeee");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Emp e = new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setCountry(rs.getString(5));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }

        return list;
    }
}
