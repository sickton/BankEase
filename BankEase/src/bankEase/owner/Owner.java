package bankEase.owner;

/**
 * Class to define an owner for the account with required details like name, address and phone
 * @author Srivathsa Mantrala
 */
public class Owner {

	/** Name of the owner */
	private String name;
	
	/** Address of the owner */
	private String address;
	
	/** Phone number of the owner */
	private int phone;
	
	/**
	 * Constructor to initialize owner details
	 * @param name name of the owner
	 * @param phone phone number of the owner
	 * @param address address of the owner
	 */
	public Owner(String name, int phone, String address)
	{
		setName(name);
		setPhone(phone);
		setAddress(address);
	}
	
	/**
	 * Method to set the owner name
	 * @param name name of owner
	 * @throws IllegalArgumentException if the name contains any characters
	 * apart from space and upper/lower case letters
	 */
	public void setName(String name)
	{
		for(int i = 0; i < name.length(); i++)
		{
			if(Character.isLetter(name.charAt(i)))
				continue;
			else if(name.charAt(i) == ' ')
				continue;
			else
				throw new IllegalArgumentException("Invalid owner name.");
		}
		this.name = name;
	}
	
	/**
	 * Method to set phone number of the owner
	 * @param phone phone number of the owner
	 * @throws IllegalArgumentException if the phone number
	 * does not contain 10 digits.
	 */
	public void setPhone(int phone)
	{
		int temp = phone;
		int digits = 0;
		while(temp != 0)
		{
			temp = temp / 10;
			digits++;
		}
		if(digits != 10)
		{
			throw new IllegalArgumentException("Invalid phone number.");
		}
		else
		{
			this.phone = phone;
		}
	}
	
	/**
	 * Method to set the address of the owner
	 * @param address of the owner
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	/**
	 * Method to return the owner name
	 * @return name of the owner
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method to return the phone number of owner
	 * @return phone number of the owner
	 */
	public int getPhone()
	{
		return this.phone;
	}
	
	/**
	 * Method to return the address of the owner
	 * @return address of owner
	 */
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * Method to get the owner details in a format
	 * @return name, phone and address of owner
	 */
	public String getOwnerDetails()
	{
		return "Name: "+ this.name + "\nPhone: " + this.phone + "\nAddress: " + this.address;
	}
}
