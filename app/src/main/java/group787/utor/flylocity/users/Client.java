package group787.utor.flylocity.users;

import java.util.ArrayList;
import group787.utor.flylocity.flights.Itinerary;

/**
 * A Client that can store personal information and interact 
 * with the application.
 */
public class Client {
	
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String cc_Number;
	private String cc_Expiry;
	private ArrayList<Itinerary> bookedItins = new ArrayList<Itinerary>();
	
	/**
	 * Create an instance of Client with all fields.
	 * @param first_name string of the first name of the client
	 * @param last_name string of the last name of the client
	 * @param email the string email of this client
	 * @param address string address of the client
	 * @param cc_number credit card number of this client
	 * @param cc_expiry security code of the credit card info of this client
	 */
	public Client(String last_name,String first_name, String email, 
			String address, String cc_number, String cc_expiry) {
		
		this.lastName = last_name;
		this.firstName = first_name;
		this.email = email;
		this.address = address;
		this.cc_Number = cc_number;
		this.cc_Expiry = cc_expiry;
	}

	/**
	 * Get the first name of this Client.
	 * @return the first name of this Client
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name of this Client.
	 * @param firstName the first name of this Client
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the last name of this Client.
	 * @return the last name of this Client
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name of this Client.
	 * @param lastName the last name of this Client
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return the email of this Client.
	 * @return the email of this Client.
	 */
	public String getEmail(){
		return this.email;
	}
	
	/**
	 * Changes the email of this Client.
	 * @param email of this Client.
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Returns the address of this client.
	 * @return the address of this client
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Changes the address of this Client.
	 * @param address the address of this Client
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Get the credit card number for this Client.
	 * @return the credit card number of this Client
	 */
	public String getCc_Number() {
		return cc_Number;
	}
	
	/**
	 * Set the credit card number for this Client.
	 * @param cc_Number
	 */
	public void setCc_Number(String cc_Number) {
		this.cc_Number = cc_Number;
	}
	
	/**
	 * Get the credit card expiry date for this Client.
	 * @return the credit card expiry date for this Client
	 */
	public String getCc_Expiry() {
		return cc_Expiry;
	}
	
	/**
	 * Set the credit card expiry date for this Client.
	 * @param cc_Expiry credit card expiry date for this Client
	 */
	public void setCc_Expiry(String cc_Expiry) {
		this.cc_Expiry = cc_Expiry;
	}
	
	/**
	 * Takes an itinerary and stores it in the clients booked itineraries
	 * @param itin the Itinerary to be booked by this user.
	 */
	public void bookItins(Itinerary itin){
		this.bookedItins.add(itin);
	}
	
	/**
	 * Return the ArrayList of booked Itineraries, for this user.
	 * @return booked Itineraries for this user
	 */
	public ArrayList<Itinerary> getBookedItins(){
		return this.bookedItins;
	}
	
	/**
	 * Return String representation of this Client in the format:
	 * 		LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
	 */
	@Override
	public String toString() {
		String formatString = "";
		formatString += this.lastName + ",";
		formatString += this.firstName + ",";
		formatString += this.email + ",";
		formatString += this.address + ",";
		formatString += this.cc_Number + ",";
		formatString += this.cc_Expiry;
		return formatString;
	}

}
