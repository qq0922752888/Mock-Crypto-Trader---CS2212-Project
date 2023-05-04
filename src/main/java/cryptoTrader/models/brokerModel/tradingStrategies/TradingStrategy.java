package cryptoTrader.models.brokerModel.tradingStrategies;

import java.time.LocalDate;
import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceipt;
import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceiptsDatabase;
import cryptoTrader.models.brokerModel.Broker;

/**
 * This class represents a TradingStrategy that a broker uses.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements Strategy as an interface.
 */
public abstract class TradingStrategy implements Strategy
{
	/**
	 * This method implements trading strategy logic to make decisions for purchasing and selling coins.
	 * @param broker The Broker object that is using the trading strategy.
	 * @return Returns true if the trade was successful, false otherwise.
	 */
	public abstract boolean performTrade(Broker broker);
	
	public abstract String toString(); 
	
	/**
	 * This method is used to create a trade receipt object representing a failed or successful trade.
	 * @param broker The Broker that performed the trade.
	 * @param tradingStrategy The trading strategy employed.
	 * @param coin The coin that was purchased or sold.
	 * @param action Describes whether the trade was a buy or sell.
	 * @param quantity The quantity of coins purchased or sold.
	 * @param price The price of the coin.
	 * @param date The date of the transaction.
	 * @return Returns the initialized trade receipt object.
	 */
	public TradeReceipt generateTradeReceipt 
			(
			String broker, String tradingStrategy,
			String coin, String action, Double quantity, Double price, String date
			) {
		
		TradeReceipt tradeReceipt = new TradeReceipt(
				broker, tradingStrategy, coin, action, quantity, price, date);
		
		TradeReceiptsDatabase.getInstance().addTradeReceipt(tradeReceipt);
		
		return tradeReceipt; 
	}
	
	/**
	 * This is a utility method for fetching the current date and formatting it as a String.
	 * @return Returns a string representing the current date.
	 */
	protected String getCurrentDate() {
		String tempDate = LocalDate.now().toString(); 
		
		String[] tmp = tempDate.split("-"); 
		
		String date = tmp[2] + "-" + tmp[1] + "-" + tmp[0]; 
		return date; 
		
	}	
}