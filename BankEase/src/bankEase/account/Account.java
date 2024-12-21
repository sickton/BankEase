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
	private final AccountState checkAccountState = new foundAccount();
	
	/** Final field for deposit state */
	private final AccountState depositState = new Deposit();
	
	/** Final field for withdraw state */
	private final AccountState withdrawState = new Withdraw();
	
	/** Final field for update information state */
	private final AccountState updateInformationState = new updateInformation();
		
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
	public Account(String ownerO, String ownerJO, String addressO, String addressJO, int phoneO, int phoneJO)
	{
		Random r = new Random();
		int random = r.nextInt(99999);
		this.accNo = random;
		this.balance = MIN_BALANCE;
		this.owner = new Owner(ownerO, phoneO, addressO);
		this.jointOwner = new Owner(ownerJO, phoneJO, addressJO);
		this.state = checkAccountState;
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
	 * Method to return the primary owner object
	 * @return primary owner
	 */
	public Owner getPrimaryOwner()
	{
		return this.owner;
	}
	
	/**
	 * Method to return the secondary owner object
	 * @return secondary owner
	 */
	public Owner getSecondaryOwner()
	{
		return this.jointOwner;
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
	
	/**
	 * Method to update state with an initial command
	 * @param command initial command
	 */
	public void updateState(String command)
	{
		this.state.command(command);
	}
	
	/**
	 * Method to represent account details as a string
	 * @return account detail string
	 */
	@Override
	public String toString()
	{
		if(joint())
		{
			String s = "Primary Owner :-\n" + owner.getOwnerDetails() + "\nSecondary Owner :-\n" + jointOwner.getOwnerDetails() + "\n";
			s += "Available balance:- " + this.getBalance() + "\n";
			return s;
		}
		else
		{
			String s = owner.getOwnerDetails();
			s += "Available balance:- " + this.getBalance() + "\n";
			return s;
		}
	}
	
	/**
	 * Interface to handle command separately in each state
	 */
	private interface AccountState
	{
		void command(String command);
	}
	
	/**
	 * private class to call all similar functions in the states like
	 * withdraw, deposit, and updating information
	 */
	private abstract class Functionalities implements AccountState
	{
		
		/**
		 * Method to deposit amount into the account
		 * @param money to be deposited
		 */
		public void depositMoney(double money) 
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter money to be deposited");
			money = sc.nextDouble();
			balance += money;
			sc.close();
		}
		
		/**
		 * Method to withdraw money from the account
		 * @param money to be withdrawn
		 * @throws IllegalArgumentException if the money to be withdrawn reduces the
		 * account balance to less than the minimum balance
		 */
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
		
		/**
		 * Method to show the balance in the account
		 */
		public void showBalance()
		{
			System.out.println("Available balance: " + balance);
		}

		/**
		 * Method to update information in the account depending
		 * on the number of owners for the account
		 */
		public void updateInformation() 
		{
			Scanner sc = new Scanner(System.in);
			if(joint())
			{
				System.out.println("1.Update primary owner details");
				System.out.println("2.Update secondary owner details");
				System.out.println("3.Exit");
				int inp = sc.nextInt();
				switch(inp)
				{
					case 1:
						this.informationMenu();
						int opt = sc.nextInt();
						Owner priOwner = getPrimaryOwner();
						switch(opt)
						{
							case 1:
								System.out.println("Enter new Name: ");
								String newName = sc.nextLine();
								priOwner.setName(newName);
								break;
							case 2:
								System.out.println("Enter new phone number: ");
								int phone = sc.nextInt();
								priOwner.setPhone(phone);
								break;
							case 3:
								System.out.println("Enter new address: ");
								String add = sc.nextLine();
								priOwner.setAddress(add);
								break;
							default:
								System.out.println("Invalid option selected.");
								break;
						}
						break;
					case 2:
						this.informationMenu();
						int opt1 = sc.nextInt();
						Owner secOwner = getPrimaryOwner();
						switch(opt1)
						{
							case 1:
								System.out.println("Enter new Name: ");
								String newName = sc.nextLine();
								secOwner.setName(newName);
								break;
							case 2:
								System.out.println("Enter new phone number: ");
								int phone = sc.nextInt();
								secOwner.setPhone(phone);
								break;
							case 3:
								System.out.println("Enter new address: ");
								String add = sc.nextLine();
								secOwner.setAddress(add);
								break;
							default:
								System.out.println("Invalid option selected.");
								break;
						}
						break;
					case 3:
						exiting();
						break;
					default:
						System.out.println("Invalid option selected.");
						break;
				}
				sc.close();
			}
			else
			{
				this.informationMenu();
				int opt1 = sc.nextInt();
				Owner secOwner = getPrimaryOwner();
				switch(opt1)
				{
					case 1:
						System.out.println("Enter new Name: ");
						String newName = sc.nextLine();
						secOwner.setName(newName);
						break;
					case 2:
						System.out.println("Enter new phone number: ");
						int phone = sc.nextInt();
						secOwner.setPhone(phone);
						break;
					case 3:
						System.out.println("Enter new address: ");
						String add = sc.nextLine();
						secOwner.setAddress(add);
						break;
					default:
						System.out.println("Invalid option selected.");
						break;
				}
				sc.close();
			}
		}
		
		/**
		 * Method to display the options for updating information
		 */
		public void informationMenu()
		{
			System.out.println("1.Update Name");
			System.out.println("2.Update Phone Number");
			System.out.println("3.Update Address");
		}

		/**
		 * Message to display on exiting with appropriate information
		 */
		public void exiting() 
		{
			System.out.println("Thank you for using the application!");
			System.exit(0);
		}
	}
	
	/**
	 * Class to handle commands when the account is present in bank
	 */
	private class foundAccount extends Functionalities
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
				super.updateInformation();
				state = updateInformationState;
			}
			else if("Exit".equals(command))
			{
				super.exiting();
			}
		}
	}
	
	/**
	 * Class to handle commands after depositing the amount in account
	 */
	private class Deposit extends Functionalities
	{
		@Override
		public void command(String command)
		{
			if("Exit".equals(command))
			{
				super.exiting();
			}
		}
	}
	
	/**
	 * Class to handle commands after withdrawing the amount from account
	 */
	private class Withdraw extends Functionalities
	{
		@Override
		public void command(String command)
		{
			if("Exit".equals(command))
			{
				super.exiting();
			}
		}
	}
	
	/**
	 * Class to handle commands after updating the information of the account owners
	 */
	private class updateInformation extends Functionalities
	{
		@Override
		public void command(String command)
		{
			if("Exit".equals(command))
			{
				super.exiting();
			}
		}
	}
}