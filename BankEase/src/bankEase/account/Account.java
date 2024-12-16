package bankEase.account;

import java.util.Random;

import bankEase.owner.Owner;

public class Account {

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
	
	/**
	 * Constructor to create an account with a single owner
	 * @param owner owner of the account
	 * @param address address of the owner
	 * @param minBalance minimum balance to create the account
	 * @param phone phone number of owner
	 */
	public Account(String owner, String address, double minBalance, int phone)
	{
		Random r = new Random();
		int random = r.nextInt(99999);
		this.accNo = random;
		setBalance(minBalance);
		this.owner = new Owner(owner, phone, address);
		setJoint(false);
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
	public Account(String ownerO, String ownerJO, String addressJO, String addressO, double minBalance, int phoneO, int phoneJO)
	{
		Random r = new Random();
		int random = r.nextInt(99999);
		this.accNo = random;
		setBalance(minBalance);
		this.owner = new Owner(ownerO, phoneO, addressO);
		this.jointOwner = new Owner(ownerJO, phoneJO, addressJO);
		setJoint(true);
	}
	
	/**
	 * Method to set minimum balance in the account
	 * @param balance minimum balance
	 * @throws IllegalArgumentException if the given balance is less than $1000
	 */
	public void setBalance(double balance)
	{
		if(balance < 1000)
			throw new IllegalArgumentException("Minimum Balance required in account- $1000");
		this.balance = balance;
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
}
