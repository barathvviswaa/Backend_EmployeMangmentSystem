package EmpAllDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/view")
public class ViewAllEmployee extends GenericServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kavish",   // database URL (replace samp with your DB name)
                    "root",                               // MySQL username
                    "Barath1425"                           // MySQL password
                );

            // SELECT query
            ps = con.prepareStatement("SELECT * FROM workers");
            rs = ps.executeQuery();

            pw.println("<h2>Employee Records</h2>");
            pw.println("<table border='1' cellpadding='10'>");
            pw.println("<tr>"
                    + "<th>EID</th>"
                    + "<th>Name</th>"
                    + "<th>Designation</th>"
                    + "<th>Salary</th>"
                    + "<th>Experience</th>"
                    + "</tr>");

            boolean recordFound = false;

            while (rs.next()) {
                recordFound = true;

                pw.println("<tr>");
                pw.println("<td>" + rs.getString("id") + "</td>");
                pw.println("<td>" + rs.getString("name") + "</td>");
                pw.println("<td>" + rs.getString("des") + "</td>");
                pw.println("<td>" + rs.getFloat("salary") + "</td>");
                pw.println("<td>" + rs.getString("exp") + "</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");

            if (!recordFound) {
                pw.println("<h3>No records found in workers table!</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h3>Error: " + e.getMessage() + "</h3>");

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}