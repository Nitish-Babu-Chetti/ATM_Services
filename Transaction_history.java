package ATM_Services;
import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class Transaction_history 
{
	
	public void Transactions(String uName , int balance , String transaction_type , int Amount) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		PreparedStatement ps = con.prepareStatement("select acc_no from sbi where uname=?");
		ps.setString(1, uName);
		ResultSet rs = ps.executeQuery();
		
		long account_no=0;
		if(rs.next())
		{
			account_no = rs.getLong("acc_no");
		}
		
		String tableName = uName+"_transactions";
		String query = "insert into "+tableName+"(acc_no , transaction_type , amount , balance) "
				+ "values(? , ? , ? , ?)";
		
		PreparedStatement ps1 = con.prepareStatement(query);
		ps1.setLong(1, account_no);
		ps1.setString(2, transaction_type);
		ps1.setInt(3, Amount);
		ps1.setInt(4, balance);
		
		ps1.executeUpdate();
		
		ps1.close();
		con.close();
	}
	
	public void show_statement(String uName) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferb" , "root" , "nani");
		
		String tableName = uName+"_transactions";
		String query = "select * from "+tableName;
		
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		out.println("----------------------------------------------------------------------------------------------");
		out.printf("%-5s | %-20s | %-10s | %-15s | %-10s | %-15s%n",
                "Sno", "Date", "Acc_No", "Transaction_Type", "Amount", "Acc_Balance");
		out.println("----------------------------------------------------------------------------------------------");
		
		while(rs.next())
		{
			int sno = rs.getInt(1);
			Timestamp date = rs.getTimestamp(2);
			long account_no = rs.getLong(3);
			String Transaction_type = rs.getString(4);
			int amount = rs.getInt(5);
			int account_balance = rs.getInt(6);
			
			out.printf("%-5d | %-20s | %-10d | %-15s | %-10d | %-15d |%n",
                    sno, date, account_no, Transaction_type, amount, account_balance);
			
		}
	}

	public static void main(String[] args) throws Exception
	{
		Transaction_history TH = new Transaction_history();
		TH.Transactions("krish" , 6405 , "withdraw" , 350);
		TH.show_statement("krish");
	}

}
