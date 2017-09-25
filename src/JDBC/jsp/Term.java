package JDBC.jsp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Term{
	public Date term_date(int cust_id, int pol_no) {
		Date prem_term = null;
		String sql1 = "select prem_pdate from customer_policy where cust_id=? AND pol_no=?";
		String sql2= "select DATE_SUB(?, INTERVAL 1 MONTH)";
		try {
			Connection conn = new Connect().myDBConnect();
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt1.setInt(1, cust_id);
			stmt1.setInt(2, pol_no);
			ResultSet rs1 = stmt1.executeQuery();
			rs1.next();
			Date p_date= rs1.getDate("prem_pdate");
			//System.out.println("Date:"+p_date);
			stmt2.setDate(1, p_date);
			ResultSet rs2= stmt2.executeQuery();
			rs2.next();
			prem_term=rs2.getDate(1);
		} catch (Exception e) {
			System.out.println(e);
		}

		return prem_term;

	}
	
	public static void main(String args[])
	{
		int key=new Premium().date(1,897456123);
		System.out.println("Key="+key);
	}
	
}
