package group787.utor.flylocity.driver;

import android.support.v7.app.AppCompatActivity;

import group787.utor.flylocity.flights.Flight;
import group787.utor.flylocity.flights.Itinerary;
import group787.utor.flylocity.users.Client;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * The main controller of the Flight Application; it is responsible for reading
 * flight and client information and storing them as files. 
 */
public class AppEngine implements Serializable {

	//Main storage for flights
	protected HashMap<String, Flight> flights = new HashMap<String, Flight>();
	
	//Main storage for Clients
	private HashMap<String, Client> clients = new HashMap<String, Client>();
			
	/**
	 * Takes a csv file and converts each line to a Flight object,
	 * and adds it to the application of saved Flights.
	 * @param filePath the name of the file that is to be scanned in the form
	 * of a String.
	 */
	public void readFlights(String filePath){
		// Try to read this file, check if it is in proper format
		// and check if it is found
//		BufferedReader br;
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
//			br = new BufferedReader(reader);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] flightInfo = line.split(",");
				String[] departureDT = flightInfo[1].split(" ");
				String[] arrivalDT = flightInfo[2].split(" ");
				// If date/time are not in proper format throw exception
				if (departureDT.length != 2 || arrivalDT.length != 2){
					throw new IndexOutOfBoundsException
					("Date/Time not in proper format.");
				}
				Flight flight = new Flight(flightInfo[0], departureDT,
						arrivalDT, flightInfo[3], flightInfo[4],
						flightInfo[5], new BigDecimal(flightInfo[6]), 100);
				this.flights.put(flightInfo[0], flight);
			}
			br.close();
		}catch(IndexOutOfBoundsException e){
			System.out.println("File not in proper format, got an "
					+ "IndexOutOfBoundsException: " + e.getMessage());
		}catch(IOException e){
			System.out.print("File not found, got an IOException: " 
		+ e.getMessage());
		}
	}
	
	
	/**
	 * Writes current flight information into a file, located by 
	 * the String filePath.
	 * @param filePath the location of the text file, as a String
	 */
	public void writeFlights(String filePath){
		// Try to see if the file path exists and if it can be written on
		try{
			int size = this.flights.size();
			int counter = 0;
			FileWriter filewriter = new FileWriter(filePath);
			for (String key: this.flights.keySet()){
				String printString = this.flights.get(key).toString();
				filewriter.write(printString);
				if (counter < size - 1){
					filewriter.write("\n");
				}
				counter++;
			}
			filewriter.close();
		}catch (IOException e){
			System.out.println("File not found, got an IOException: " 
		+ e.getMessage());
		}
	}
	
	/**
	 * Read client information from a text file and stores it as 
	 * a Hashmap in the application, from the location
	 * defined by the String filepath.
	 * @param filePath the location of the text file as a String
	 */
	public void readClients(String filePath){
		// Try to read this file, check if it is in proper format
		// and check if it is found
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] clientInfo = line.split(",");
				Client client = new Client(clientInfo[0], clientInfo[1], 
						clientInfo[2],
						clientInfo[3], clientInfo[4], clientInfo[5]);
				this.clients.put(clientInfo[2], client);		
			}
			br.close();
		}catch(IndexOutOfBoundsException e){
			System.out.println("File not in proper format, got an "
					+ "IndexOutOfBoundsException: " + e.getMessage());
		}catch(IOException e){
			System.out.print("File not found, got an IOException: " 
		+ e.getMessage());
		}
	}
	
	/**
	 * Writes the current client information in to a file located
	 * by the String filePath.
	 * @param filePath the String location of the file to be written
	 */
	public void writeClients(String filePath){
		// Try to see if the file path exists and if it can be written on
		try{
			int size = this.clients.size();
			int counter = 0;
			FileWriter filewriter = new FileWriter(filePath);
			for (String key: this.clients.keySet()){
				String printString = this.clients.get(key).toString();
				filewriter.write(printString);
				if (counter < size - 1){
					filewriter.write("\n");
				}
				counter++;
			}
			filewriter.close();
		}catch (IOException e){
			System.out.println("File not found, got an IOException: " 
		+ e.getMessage());
		}
	}
	
	/**
	 * Returns the client with the given email if the client exists.
	 * @param email of the client to be returned
	 * @return a Client object with the given email
	 */
	public Client getClient(String email){
		return this.clients.get(email);
	}
	
	/**
	 * Returns the Flight with the specified flight number.
	 * @param flightNumber the flight number of the flight being returned
	 * @return the Flight object with specified with its flight number
	 */
	public Flight getFlight(String flightNumber){
		return this.flights.get(flightNumber);
	}
	
	/**
	 * Return all flights with given departure date, origin and destination
	 * in a string in the format: 
	 * 		Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination,Price
	 * @param date the departure date of the flight to search for
	 * @param origin the origin of the flight to look for
	 * @param destination the destination of the flight to look for
	 * @return the flight properly formatted in a String, in the format
	 * specified above
	 */
	public String searchFlights(String date, String origin, String destination){
		String returnString = "";
		int counter = 0;
		int size = this.flights.size();
		for (String key : this.flights.keySet()){
			Flight flight = this.flights.get(key);
			if (flight.getDepartureDateandTime()[0].equals(date) 
					&& flight.getOrigin().equals(origin) 
					&& flight.getDestination().equals(destination)){
				returnString += this.getFlight(key);
				if (counter < size - 1){
					returnString += "\n";
				}
			}
			counter++;
		}
		return returnString;
	}
	
	/**
	 * Uses Algorithm class to search for all itineraries on given date,
	 * origin and destination, Return the Itineraries in ArrayList<Itinerary>.
	 * @param date start date of the itineraries to search for
	 * @param origin of the itineraries to search for
	 * @param destination destination of the itineraries to search for
	 * @return the ArrayList<Itinerary> of all the Itineraries found
	 */
	public ArrayList<Itinerary> getItineraries(String date, String origin,
			String destination){
		
		// Create an instance and use the algorithm to get the list of list 
		//of flight
		Algorithm alg = new Algorithm();
		String[] calanderDate = date.split("-");
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt
				(calanderDate[0]), Integer.parseInt(calanderDate[1]), 
				Integer.parseInt(calanderDate[2]));
		ArrayList<ArrayList<Flight>> listFlights = alg.search(cal, origin, 
				destination, this.flights);
		
		// Create a new list of Itinerary based on this list of list of flight
		ArrayList<Itinerary> listItinerary = new ArrayList<Itinerary>();
		for (ArrayList<Flight> itinerary: listFlights){
			Itinerary newItinerary = new Itinerary();
			newItinerary.setItinerary(itinerary);
			listItinerary.add(newItinerary);
		}
		
		return listItinerary;
	}
	
	/**
	 * Returns all the Itineraries in the String format of:
	 * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 * Destination followed by total price (on its own line, exactly two
	 * decimal places), followed by total duration (on its own line, in
	 * format HH:MM).
	 * @param listItin the ArrayList<Itinerary> to be represented as a string
	 * @return the String representation of the List of Itinerary
	 */
	public String textListItineraries(ArrayList<Itinerary> listItin){
		// Format the return String based on the list of itineraries 
		String returnString = "";
		for (Itinerary itin: listItin){
			returnString += itin.toString();
		}
		return returnString;
	}
	
	/**
	 * Return the Itineraries sorted by cost, in non decreasing order as
	 * an ArrayList<Itinerary>. 
	 * @param listItinerary ArrayList<Itinerary> that will be sorted
	 * @return an ArrayList<Itinerary> that is sorted by cost
	 */
	public ArrayList<Itinerary> sortByCost(ArrayList<Itinerary> listItinerary){
		// Check if this list can be sorted
		if (listItinerary.size() < 2){
			return listItinerary;
		}else{
			for (int x = 0; x < listItinerary.size(); x++){
				for (int y = 0; y < listItinerary.size() - x - 1; y++){
					if (listItinerary.get(y).getTotalCost().compareTo(
							listItinerary.get(y+1).getTotalCost()) > 0){
						// Swap the elements
						Itinerary temp = listItinerary.get(y);
						listItinerary.set(y, listItinerary.get(y+1));
						listItinerary.set(y+1, temp);
					}
				}
			}
		}
		return listItinerary;
	}
	
	/**
	 * Return the Itineraries sorted by travel time, in non decreasing 
	 * order as an ArrayList<Itinerary>. 
	 * @param listItinerary ArrayList<Itinerary> that will be sorted
	 * @return an ArrayList<Itinerary> that is sorted by cost
	 */
	public ArrayList<Itinerary> sortByTime(ArrayList<Itinerary> listItinerary){
		// Check if this list can be sorted
		if (listItinerary.size() < 2){
			return listItinerary;
		}else{
			for (int x = 0; x < listItinerary.size(); x++){
				for (int y = 0; y < listItinerary.size() - x - 1; y++){
					int z = Double.compare
							(listItinerary.get(y).getDifferenceFlightTime(), 
							listItinerary.get(y+1).getDifferenceFlightTime());
					// If the Itinerary before is larger than the one in front
					// of it
					if (z > 0){
						// Swap the elements
						Itinerary temp = listItinerary.get(y);
						listItinerary.set(y, listItinerary.get(y+1));
						listItinerary.set(y+1, temp);
					}
				}
			}
		}
		return listItinerary;
	}
}












