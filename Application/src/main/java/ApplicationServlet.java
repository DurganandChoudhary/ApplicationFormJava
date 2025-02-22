import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String course = request.getParameter("course");
        String password = request.getParameter("password");

        // Hash password using SHA-256 (More secure than plaintext)
        String hashedPassword = hashPassword(password);

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Database Connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeDB?useSSL=false&serverTimezone=UTC", "root", "your_password");
                 PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO applications (full_name, email, phone, dob, gender, address, course, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, dob);
                ps.setString(5, gender);
                ps.setString(6, address);
                ps.setString(7, course);
                ps.setString(8, hashedPassword);

                int i = ps.executeUpdate();
                if (i > 0) {
                    out.println("Application Submitted Successfully!");
                } else {
                    out.println("Application Submission Failed.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
