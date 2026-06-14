package EmpAllDetails;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/add")
public class Employee extends GenericServlet {

	public EmployeeBean eb = null;//which class have setter 

	public void init() throws ServletException {

		eb = new EmployeeBean();// Creating bean object
	
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		eb.setId(req.getParameter("id"));
		eb.setName(req.getParameter("name"));
		eb.setDes(req.getParameter("des"));
		eb.setExp(req.getParameter("exp"));
		eb.setSalary(Float.parseFloat(req.getParameter("salary")));	

		int k = new InsertDAO().insert(eb);
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		if (k > 0) {
			pw.println("Employe Added Successfully...<br>");
			RequestDispatcher rd = req.getRequestDispatcher("Employe.html");
			rd.include(req, res);
		}

	}

}


