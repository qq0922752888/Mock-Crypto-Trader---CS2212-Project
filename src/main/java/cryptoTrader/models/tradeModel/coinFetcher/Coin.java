package cryptoTrader.models.tradeModel.coinFetcher;

import java.time.LocalDate;
/**
 * This class represents a coin object.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * A coin has a name, price and date(to fetch the prince on that day).
 */
public class Coin {
	private String name; 
	private double price; 
	private String date; // DD-MM-YYYY
	/**
	 * This constructor method assigns the class attributes with the corresponding parameters.
	 * @param name, name of the coin.
	 * @param price, price of the coin.
	 * @param dating, current date.
	 */
	public Coin(String name, int price, LocalDate dating) {
		this.name = name; 
		this.price = price; 
		this.date = LocalDate.now().toString(); 
	}
	/**
	 * Second constructor to initialize the coin name, if no parameter is entered for the price and date.
	 * @param name, name of the coin.
	 */
	public Coin(String name) {
		this.name = name; 
		price = -1; 
		date = ""; 
	}
	/**
	 * Getter method for the name of the coin.
	 * @return name, name of the coin.
	 */
	public String getName() {
		return this.name; 
	}
	/**
	 * Getter method for the price of the coin.
	 * @return price, price of the coin.
	 */
	public double getPrice() {
		return this.price; 
	}
	/**
	 * Getter method for date.
	 * @return date
	 */ 
	public String getDate() {
		return this.date; 
	}
	/**
	 * Setter method for the coin name.
	 * @param name, coin name
	 */
	public void setName(String name) {
		this.name = name; 
	}
	/**
	 * Setter method for the coin price.
	 * @param price, coin price.
	 */
	public void setPrice(double price) {
		this.price = price; 
	}
	/**
	 * Setter method for the date.
	 * @param date
	 */
	public void setDate(String date) {
		this.date = LocalDate.now().toString();  
	}
	/**
	 * Equals method to check if two coins are the same.
	 * @param otherCoin, other coin inputted by user.
	 * @return boolean, true if two coins are same, false otherwise.
	 */
	public boolean equals(Coin otherCoin) {
		return this.name.equals(otherCoin.getName()); 
	}
}
