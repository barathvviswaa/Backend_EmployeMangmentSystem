package EmpAllDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/update")
public class UpdateEmployee extends GenericServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String id= req.getParameter("id");
        String name = req.getParameter("name");
        String des = req.getParameter("des");
        float salary = Float.parseFloat(req.getParameter("salary"));
        String exp = req.getParameter("exp");

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect MySQL
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kavish",   // database URL (replace samp with your DB name)
                    "root",                               // MySQL username
                    "Barath1425"                           // MySQL password
                );
            // UPDATE query
            String sql = "UPDATE workers SET name=?, des=?, salary=?, exp=? WHERE id=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, des);
            ps.setFloat(3, salary);
            ps.setString(4, exp);
            ps.setString(5, id);

            int k = ps.executeUpdate();

            if (k > 0) {
                pw.println("<h2>Employee Updated Successfully!</h2>");
            } else {
                pw.println("<h3>No Employee Found With ID: " + id + "</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}