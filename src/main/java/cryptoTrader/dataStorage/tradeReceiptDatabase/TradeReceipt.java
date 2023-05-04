package cryptoTrader.dataStorage.tradeReceiptDatabase;

/**
 * This class represents a Trade Receipt that is created upon performing a trade.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * A receipt will store the broker information, the trading strategy, the traded coin, the trade action, 
 * the quantity of coin traded, the price of traded coin, the date that the trade took place.
 */
public class TradeReceipt {
	private String broker;
	private String tradingStrategy; 
	private String coin; 
	private String action; 
	private Double quantity; 
	private Double price; 
	private String date;
	/**
	 * This method is a constructor method for the class which stores the data that is required to create a trade receipt.
	 * @param broker, broker information
	 * @param tradingStrategy, trading strategy
	 * @param coin, traded coin
	 * @param action, the trade action
	 * @param quantity, the quantity of coin that is traded
	 * @param price, the price of traded coin
	 * @param date, the data that the trade took place.
	 */
	public TradeReceipt(
			String broker, String tradingStrategy, String coin,
			String action, Double quantity, Double price, String date
			) {
		this.broker = broker; 
		this.tradingStrategy = tradingStrategy; 
		this.coin = coin; 
		this.action = action; 
		this.quantity = quantity; 
		this.price = price;
		this.date = date; 
	}
	/**
	 * Getter method  for broker
	 * @return returns broker
	 */
	public String getBroker() {
		return broker;
	}
	/**
	 * Setter method for the broker
	 * @param broker, the input broker will be set to be the broker on the receipt.
	 */
	public void setBroker(String broker) {
		this.broker = broker;
	}
	/**
	 * Getter method for trading strategy
	 * @return returns the trading strategy
	 */
	public String getTradingStrategy() {
		return tradingStrategy;
	}
	/**
	 * Setter method for the trading strategy
	 * @param tradingStrategy, the input strategy will be set to be the strategy on the receipt.
	 */
	public void setTradingStrategy(String tradingStrategy) {
		this.tradingStrategy = tradingStrategy;
	}
	/**
	 * Getter method for the coin
	 * @return returns the traded coin
	 */
	public String getCoin() {
		return coin;
	}
	/**
	 * Setter method for the traded coin
	 * @param coin, the input coin will be set to be the traded coin on the receipt.
	 */
	public void setCoin(String coin) {
		this.coin = coin;
	}
	/**
	 * Getter method for the trade action
	 * @return returns the trade action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * Setter method for the trade action
	 * @param action, the input action will be set to be the traded action on the receipt.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * Getter method for the coin quantity that is traded
	 * @return returns the quantity of coin that is traded.
	 */
	public Double getQuantity() {
		return quantity;
	}
	/***
	 * Setter method for the coin quantity that is traded.
	 * @param quantity, the input quantity will be set to be the coin quantity on the receipt.
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	/**
	 * Getter method for the coin price
	 * @return returns the coin price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * Setter method for the coin price
	 * @param price, the input price will be set to be the coin price on the receipt.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * Getter method for the trade date
	 * @return returns the trade date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Setter method for the trade date
	 * @param date, the input date will be set to be the trade date on the receipt.
	 */
	public void setDate(String date) {
		this.date = date; 
	} 
	
	
}
