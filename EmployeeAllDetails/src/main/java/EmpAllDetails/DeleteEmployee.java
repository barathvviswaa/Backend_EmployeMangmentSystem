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

//separate code delete 

@WebServlet("/delete")
public class DeleteEmployee extends GenericServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String id = req.getParameter("eid");

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL Database
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kavish",   // database URL (replace samp with your DB name)
                    "root",                               // MySQL username
                    "Barath1425"                           // MySQL password
                );

            // DELETE SQL Query
            ps = con.prepareStatement("DELETE FROM workers WHERE id = ?");
            ps.setString(1, id);

            int k = ps.executeUpdate();

            if (k > 0) {
                pw.println("<h2>Employee Deleted Successfully!</h2>");
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


//<form action="delete" method="post">
//Enter Employee ID:
//<input type="number" name="eid" required>
//<button type="submit">Delete</button>
//</form>
