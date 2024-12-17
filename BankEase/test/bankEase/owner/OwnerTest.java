package bankEase.owner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for owner class
 */
class OwnerTest {

	/**
	 * Tests the owner constructor
	 */
	@Test
	void testConstructor() {
		assertDoesNotThrow(() -> new Owner("Srivathsa", 1000000522, "Avent Ferry Rd"));
	}

	/**
	 * Tests for invalid inputs
	 */
	@Test
	public void testInvalid()
	{
		assertThrows(IllegalArgumentException.class, () -> new Owner("Name with special cha@racter", 1111231281, "random address, Rd 2"));
		assertThrows(IllegalArgumentException.class, () -> new Owner("Name", 11112381, "random address, Rd 2"));
	}
	
	/**
	 * Tests to check the getters
	 */
	@Test
	public void testGetters()
	{
		Owner o = new Owner("Svm", 1234567890, "Address, line 2, line 3.");
		assertEquals("Svm", o.getName());
		assertEquals(1234567890, o.getPhone());
		assertEquals("Address, line 2, line 3.", o.getAddress());
		String ownerDetails = "Name: Svm\nPhone: 1234567890\nAddress: Address, line 2, line 3.";
		assertEquals(ownerDetails, o.getOwnerDetails());
	}
}