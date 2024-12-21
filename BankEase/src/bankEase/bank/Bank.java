package bankEase.bank;

import java.util.ArrayList;
import java.util.Scanner;

import bankEase.account.Account;

/**
 * Class to maintain a record of accounts in a bank
 * @author Srivathsa Mantrala
 */
public class Bank {

	/** Field to store just a single instance of the bank */
	private static Bank bankEase;
	
	/** Field to store the list of accounts in the bank */
	private ArrayList<Account> accounts;
	
	/**
	 * Constructor to initialize the fields
	 */
	private Bank()
	{
		accounts = new ArrayList<Account>();
	}
	
	/**
	 * Method to return a single instance of bank
	 * @return instance of bank
	 */
	public static Bank getInstance()
	{
		if(bankEase == null)
			bankEase = new Bank();
		return bankEase;
	}
	/**
	 * Method to add an account to the bank
	 */
	public void addAccountToBank()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Open regular account");
		System.out.println("2.Open joint account");
		System.out.print("Enter your choice: ");
		int opt = sc.nextInt();
		switch(opt)
		{
			case 1:
			{
				System.out.print("\n-Enter name of the account holder: ");
				String name = sc.nextLine();
				System.out.print("\n-Enter phone number of the account holder: ");
				int phone = sc.nextInt();
				System.out.println("\n-Enter the address of the account holder: ");
				String address = sc.nextLine();
				try
				{
					Account a = new Account(name, address, phone);
					accounts.add(a);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Invalid information entered.\nPlease try again!");
					System.exit(0);
				}
			}
			break;
			case 2:
			{
				System.out.print("\n-Enter name of the primary account holder: ");
				String priName = sc.nextLine();
				System.out.print("\n-Enter phone number of the primary account holder: ");
				int priPhone = sc.nextInt();
				System.out.println("\n-Enter the address of the primary account holder: ");
				String priAddress = sc.nextLine();
				System.out.print("\n-Enter name of the secondary account holder: ");
				String secName = sc.nextLine();
				System.out.print("\n-Enter phone number of the secondary account holder: ");
				int secPhone = sc.nextInt();
				System.out.println("\n-Enter the address of the secondary account holder: ");
				String secAddress = sc.nextLine();
				try
				{
					Account a = new Account(priName, secName, priAddress, secAddress, priPhone, secPhone);
					accounts.add(a);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Invalid information entered.\nPlease try again!");
					System.exit(0);
				}
			}
				break;
			default:
			{
				System.out.println("Invalid option!\nPlease try again later");
				System.exit(0);
			}
			break;
		}
		sc.close();
	}
	
	/**
	 * Method to remove an account from the bank
	 */
	public void removeAccount()
	{
		int index = this.getAccountIndex();
		if(index != -1)
		{
			accounts.remove(index);
			System.out.println("Account removed successfully\nThank you for using the application!");
			System.exit(0);
		}
		else
		{
			System.out.println("Account does not exist on the system!\nThank you for using the application!");
			System.exit(0);
		}
	}

	/**
	 * Helper method to return the index where the account is in the list
	 * @return index if account found else returns -1
	 */
	public int getAccountIndex()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter account number- ");
		int number = sc.nextInt();
		System.out.println();
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i).getAccountNumber() == number)
			{
				sc.close();
				return i;
			}
		}
		sc.close();
		return -1;
	}
	
	/**
	 * Method to return account if found
	 * @return account object if found else null
	 */
	public Account getAccount(int accno)
	{
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i).getAccountNumber() == accno)
			{
				return accounts.get(i);
			}
		}
		return null;
	}
	
	
}