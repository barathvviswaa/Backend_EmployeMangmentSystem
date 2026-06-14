package EmpAllDetails;


import java.sql.*;

public class InsertDAO {
	public int k = 0;

	public int insert(EmployeeBean eb) {
		try {
			Connection con = DBConnection.getCon();// Accessing

			PreparedStatement ps = con.prepareStatement("insert into workers values(?,?,?,?,?)");
			ps.setString(1, eb.getId());
			ps.setString(2, eb.getName());
			ps.setString(3, eb.getDes());
			ps.setString(4, eb.getExp());
			ps.setFloat(5, eb.getSalary());
			
			k = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
}