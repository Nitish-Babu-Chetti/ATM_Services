package ATM_Services;
import static java.lang.System.out;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Change_pin {
	
	public void change_password(String uName) throws Exception
	{
		int oldPassword = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		
		PreparedStatement ps = con.prepareStatement("select pwd from sbi where uname=?");
		ps.setString(1, uName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			oldPassword = rs.getInt("pwd");
		}
		
		out.println("Enter old pin : ");
		Scanner scan = new Scanner(System.in);
		int EnteredOldPwd = scan.nextInt();
		
		if(EnteredOldPwd == oldPassword)
		{
			out.println("Enter new pin : ");
			Scanner scan1 = new Scanner(System.in);
			int newPwd = scan1.nextInt();
			
			PreparedStatement ps1 = con.prepareStatement("update sbi set pwd=? where uname=?");
			ps1.setInt(1, newPwd);
			ps1.setString(2, uName);
			ps1.executeUpdate();
			out.println("Password Successfully changed");
			
			ps1.close();
			scan1.close();
		}
		else
		{
			out.println("You have entered a wrong previous password");
		}
		
		scan.close();
		ps.close();
		con.close();
	}

	public static void main(String[] args) {
		

	}

}
