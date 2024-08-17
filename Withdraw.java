package ATM_Services;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Withdraw {
	
	public void withdraw(String uName , int balance) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con  = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		PreparedStatement ps = con.prepareStatement("update sbi set balance=? where uname=?");
		
		out.println("Your account balance is : "+balance);
		Scanner scan = new Scanner(System.in);
		out.println("Enter the Amount Required");
		
		int EnteredAmount = scan.nextInt();
		String Transaction_type= "Withdraw";
		
		System.out.println("Transaction Being Pocessing .................");
		Thread.sleep(3000);
		
		if(EnteredAmount<=balance)
		{
			balance -= EnteredAmount;
			out.println("Transaction Successfull");
			ps.setString(2, uName);
			ps.setInt(1,balance);
			
			int rs = ps.executeUpdate();
			Balance B = new Balance();
			int Remaining_amount = B.check_balance(uName);
			out.println("Your Remainaing Account Balance is : "+Remaining_amount);
			
			Transaction_history TH = new Transaction_history();
			TH.Transactions(uName , balance , Transaction_type , EnteredAmount);
		}
		
		
		
		else 
		{
			out.println("You have insufficient Balance");
		}
		
		
		
		ps.close();
		con.close();
		
		scan.close();
	}

	public static void main(String args[]) throws Exception 
	{
//		Withdraw W = new Withdraw();
//		W.withdraw("Krish" , 6545);
		
	}

}
