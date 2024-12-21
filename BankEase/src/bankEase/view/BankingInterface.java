package bankEase.view;

import java.util.Scanner;

import bankEase.account.Account;
import bankEase.bank.Bank;

public class BankingInterface {

	private static Scanner sc;

	public static void main()
	{
		sc = new Scanner(System.in);
		printMenu();
		while(true)
		{
			Bank bank = Bank.getInstance();
			System.out.print("Enter choice - ");
			int option = sc.nextInt();
			System.out.println();
			switch(option)
			{
				case 1:
				{
					bank.addAccountToBank();
				}
				break;
				case 2:
				{
					bank.removeAccount();
				}
				break;
				case 3:
				{
					System.out.print("Enter account number- ");
					int number = sc.nextInt();
					System.out.println();
					Account a = bank.getAccount(number);
					printAccountMenu();
					System.out.print("Enter choice - ");
					int choice = sc.nextInt();
					switch(choice)
					{
						case 1:
						{
							a.updateState("Deposit");
							System.out.println("-Do you want to check your account details ?");
							System.out.println("(Enter Yes to continue, to exit enter No)");
							System.out.println("------------------------------------------------------------------------");
							String s = sc.nextLine();
							if("Yes".equals(s))
								System.out.println(a.toString());
							else
								a.updateState("Exit");
						}
						break;
						case 2:
						{
							a.updateState("Withdraw");
							System.out.println("-Do you want to check your account details?");
							System.out.println("(Enter Yes to continue, to exit enter No)");
							System.out.println("------------------------------------------------------------------------");
							String s = sc.nextLine();
							if("Yes".equals(s))
								System.out.println(a.toString());
							else
								a.updateState("Exit");
						}
						break;
						case 3:
						{
							a.updateState("Update Information");
							System.out.println("-Do you want to check your account details?");
							System.out.println("(Enter Yes to continue, to exit enter No)");
							System.out.println("------------------------------------------------------------------------");
							String s = sc.nextLine();
							if("Yes".equals(s))
								System.out.println(a.toString());
							else
								a.updateState("Exit");
						}
						break;
						case 4:
						{
							System.out.println(a.toString());
							System.out.println("(Enter Exit to quit application)");
							System.out.println("------------------------------------------------------------------------");
							String s = sc.nextLine();
							if("Exit".equals(s))
								a.updateState("Exit");
						}
						break;
						case 5:
						{
							a.updateState("Check Balance");
							System.out.println("(Enter Exit to quit application)");
							System.out.println("------------------------------------------------------------------------");
							String s = sc.nextLine();
							if("Exit".equals(s))
								a.updateState("Exit");
						}
						break;
						case 6:
						{
							a.updateState("Exit");
						}
						break;
						default:
						{
							System.out.println("------------------------------------------------------------------------");
							System.out.println("Invalid option selected.\n Exiting the application");
							System.out.println("------------------------------------------------------------------------");
							System.exit(0);
						}
						break;
					}
				}
				break;
				case 4:
				{
					System.out.println("------------------------------------------------------------------------");
					System.out.println("Thank you for using the application!\nHave a nice day!");
					System.out.println("------------------------------------------------------------------------");
					System.exit(0);
				}
				break;
				default:
				{
					System.out.println("------------------------------------------------------------------------");
					System.out.println("Invalid option selected.\n Exiting the application");
					System.out.println("------------------------------------------------------------------------");
					System.exit(0);
				}
				break;
			}
		}
	}
	
	public static void printAccountMenu()
	{
		System.out.println("1.Deposit money in account");
		System.out.println("2.Withdraw money in account");
		System.out.println("3.Update Account Information");
		System.out.println("4.Display account details");
		System.out.println("5.Check Balance");
		System.out.println("6.Exit");
		System.out.println("------------------------------------------------------------------------");
	}
	
	public static void printMenu()
	{
		System.out.println("1.Create a bank account");
		System.out.println("2.Remove a bank account");
		System.out.println("3.Find an account");
		System.out.println("4.Exit");
	}
}