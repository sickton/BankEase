package bankEase.account;

import java.util.Random;
import java.util.Scanner;

import bankEase.owner.Owner;

/**
 * Class Account to perform various functionalities on an account
 * like depositing, withdrawing, updating information, check balance,
 * transferring funds, etc.
 * @author Srivathsa Mantrala
 */
public class Account {

	/** Static variable to create an account with minimum balance */
	private static final double MIN_BALANCE = 1000.0;
	
	/** Field to store the account number */
	private int accNo;
	
	/** Field to store the owner of the account */
	private Owner owner;
	
	/** Field to store the joint owner of the account */
	private Owner jointOwner;
	
	/** Field to maintain balance in the account */
	private double balance;
	
	/** Field to check if the account is a joint account or not */
	private boolean isJoint;
	
	/** Field to track the current state of the account */
	private AccountState state;
	
	/** Final field for check account state */
	private final AccountState checkAccountState = new checkAccount();
	
	/** Final field for deposit state */
	private final AccountState depositState = new Deposit();
	
	/** Final field for withdraw state */
	private final Withdraw withdrawState = new Withdraw();
		
	/**
	 * Constructor to create an account with a single owner
	 * @param owner owner of the account
	 * @param address address of the owner
	 * @param minBalance minimum balance to create the account
	 * @param phone phone number of owner
	 */
	public Account(String owner, String address, int phone)
	{
		Random r = new Random();
		int random = r.nextInt(99999);
		this.accNo = random;
		this.balance = MIN_BALANCE;
		this.owner = new Owner(owner, phone, address);
		setJoint(false);
		this.state = checkAccountState;
	}

	/**
	 * Constructor to create a joint account with two owners
	 * @param ownerO primary owner name
	 * @param ownerJO secondary owner name
	 * @param addressJO secondary owner address
	 * @param addressO primary owner address
	 * @param minBalance minimum balance in account
	 * @param phoneO primary owner's phone number
	 * @param phoneJO secondary owner's phone number
	 */
	public Account(String ownerO, String ownerJO, String addressJO, String addressO, int phoneO, int phoneJO)
	{
		Random r = new Random();
		int random = r.nextInt(99999);
		this.accNo = random;
		this.balance = MIN_BALANCE;
		this.owner = new Owner(ownerO, phoneO, addressO);
		this.jointOwner = new Owner(ownerJO, phoneJO, addressJO);
		setJoint(true);
	}
	
	/**
	 * Method to set a flag if the account is joint or not
	 * @param flag true if the account is joint else false
	 */
	public void setJoint(boolean flag)
	{
		this.isJoint = flag;
	}
	
	/**
	 * Method to return the account number
	 * @return account number
	 */
	public int getAccountNumber()
	{
		return this.accNo;
	}
	
	/**
	 * Method to return the primary account holder details
	 * @return primary owner details
	 */
	public String getPrimaryOwnerDetails()
	{
		return this.owner.getOwnerDetails();
	}
	
	/**
	 * Method to return the secondary account holder details
	 * @return secondary owner details
	 */
	public String getSecondaryOwnerDetails()
	{
		return this.jointOwner.getOwnerDetails();
	}
	
	/**
	 * Method to return status of account
	 * @return true if it is a joint account else false
	 */
	public boolean joint()
	{
		return this.isJoint;
	}
	
	/**
	 * Method to return the account balance
	 * @return balance in the account
	 */
	public double getBalance()
	{
		return this.balance;
	}
	
	public void updateState(String command)
	{
		this.state.command(command);
	}
	
	private interface AccountState
	{
		void command(String command);
	}
	
	private abstract class Functionalities implements AccountState
	{
		public void depositMoney(double money) 
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter money to be deposited");
			money = sc.nextDouble();
			balance += money;
			sc.close();
		}
		
		public void withdrawMoney(double money)
		{
			Scanner sc = new Scanner(System.in);
			if(balance - money < MIN_BALANCE)
			{
				sc.close();
				throw new IllegalArgumentException("Cannot withdraw money.");
			}
			else
				balance -= money;
			sc.close();
		}
		
		public void showBalance()
		{
			System.out.println("Available balance: " + balance);
		}
	}
	private class checkAccount extends Functionalities
	{
		@Override
		public void command(String command)
		{
			if("Deposit".equals(command))
			{
				double money = 0;
				super.depositMoney(money);
				state = depositState;
			}
			else if("Withdraw".equals(command))
			{
				double money = 0;
				super.withdrawMoney(money);
				state = withdrawState;
			}
			else if("Check Balance".equals(command))
			{
				super.showBalance();
			}
			else if("Update Information".equals(command))
			{
				
			}
		}
	}
	
	private class Deposit implements AccountState
	{
		@Override
		public void command(String command)
		{
			
		}
	}
	
	private class Withdraw implements AccountState
	{
		@Override
		public void command(String command)
		{
			
		}
	}
}
