package ATM_Services;
import static java.lang.System.out;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Deposit_money 
{
	
	public void Deposit(String uName , int amount ) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		PreparedStatement ps = con.prepareStatement("update sbi set balance=? where uname=?");
		
		
		
		out.println("Count and insert the amount : ");
		out.println("After insertion of amount , please press 1 to proceed otherwise press 0 : ");
		
		int Deposited_amount =  1500;
		String Transaction_type = "Deposit";
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		if(input ==1 )
		{
			out.println("Your total amount is 1500/- ");
			out.println("Press 1 to proceed otherwise press 0 ");
			
			Scanner scan1 = new Scanner(System.in);
			int input1 = scan1.nextInt();
			if(input1==1)
			{
				out.println("Transaction being processing............");
				amount += Deposited_amount;
				Thread.sleep(3000);
				ps.setString(2, uName);
				ps.setInt(1, amount);
				ps.executeUpdate();
				out.println("Transaction Successful");
				
				Transaction_history TH = new Transaction_history();
				TH.Transactions(uName , amount , Transaction_type , Deposited_amount);
			}
			else {
				out.println("Transaction Failed");
			}
		}
		else
		{
			out.println("Transaction Failed");
		}
		
		scan.close();
		ps.close();
		con.close();
	}

	public static void main(String[] args) throws Exception
	{
		

	}

}
