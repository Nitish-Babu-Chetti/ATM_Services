package ATM_Services;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Balance {
	
	public int check_balance(String Uname ) throws Exception 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		PreparedStatement ps = con.prepareStatement("select balance from sbi where uname = ? ");
		ps.setString(1, Uname);
		ResultSet rs = ps.executeQuery();
		
		int amount =0 ;
		if(rs.next())
		{
			amount = rs.getInt("balance");
		}
		
		rs.close();
		ps.close();
		con.close();
		
		return amount;
	}

	public static void main(String[] args) throws Exception {
		
		Balance B = new Balance();
		int account_balance = B.check_balance("Krish");
		out.println("Your amount is : "+account_balance);
	}

}
