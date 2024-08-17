package ATM_Services;
import static java.lang.System.out;
import java.util.Scanner;

public class ATM_services 
{

	public void ATM(String userName) throws Exception
	{
		out.println("Enter 1 to Check Balance ");
		out.println("Enter 2 to Withdraw Money");
		out.println("Enter 3 to deposit money ");
		out.println("Enter 4 for Bank Statement");
		out.println("Enter 5 to change Password");
		out.println("Enter 0 to Cancel the Session ");
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		Balance C  = new Balance();
		int amount = 0;
		int balance = 0;
		int amountDorW = 0;
		String Transaction_type = "";
		
		switch(input)
		{
		
		case 0:
			out.println("Session Timeout");
			System.exit(0);
			break;
			
		case 1:
			Balance CB = new Balance();
			balance = CB.check_balance(userName);
			out.println("Your account Balance is : "+balance);
			break;
			
		case 2:
			Balance CB1 = new Balance();
			amount = CB1.check_balance(userName);
			
			Withdraw W = new Withdraw();
			W.withdraw(userName , amount);
			
			
			break;
			
		case 3:
			Balance CB2 = new Balance();
			amount = CB2.check_balance(userName);
			Deposit_money DM = new Deposit_money();
			DM.Deposit(userName , amount );
			break;
			
		case 4:
			Transaction_history TH1 = new Transaction_history();
			TH1.show_statement(userName);
			break;
			
		case 5:
			Change_pin CP = new Change_pin();
			CP.change_password(userName);
			break;
			
		default:
			out.println("You have entered a wrong input");
			Atm_login AL = new Atm_login();
			AL.Login();
			break;
		}
		
		scan.close();
	}

	public static void main(String[] args) throws Exception 
	{
		

	}

}
