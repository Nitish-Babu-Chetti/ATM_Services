package ATM_Services;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Atm_login {
	
	public void Login() throws Exception
	{
		out.println("Enter your Username : ");
		Scanner scan = new Scanner(System.in);
		String uName = scan.nextLine();
		
		out.println("Enter your Password : ");
		Scanner scan1 = new Scanner(System.in);
		int password = scan1.nextInt();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		PreparedStatement ps = con.prepareStatement("select count(*) from sbi where uname=? and pwd=?");
		
		ps.setString(1,uName);
		ps.setInt(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		int count = rs.getInt(1);
		if(count == 1)
		{
			out.println("Valid Credentials");
			ATM_services AS = new ATM_services();
			AS.ATM(uName);
		}
		else
		{
			out.println("Wrong Credentials");
			Atm_login ATML = new Atm_login();
			ATML.Login();
			
		}
		
		
		scan.close();
		scan1.close();
	}

	public static void main(String[] args) throws Exception
	{
		Atm_login ATML = new Atm_login();
		ATML.Login();
		

	}

}
