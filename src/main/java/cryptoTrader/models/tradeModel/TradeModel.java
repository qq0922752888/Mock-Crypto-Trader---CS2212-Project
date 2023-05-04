package cryptoTrader.models.tradeModel;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import java.awt.Component;
import java.time.LocalDate;
import cryptoTrader.models.brokerModel.Broker;
import cryptoTrader.models.brokerModel.tradingStrategies.TradingStrategy;
import cryptoTrader.models.tradeModel.brokerDataFetcher.BrokerDataFetcher;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
import cryptoTrader.models.tradeModel.coinFetcher.DataFetcher;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.views.mainView.tradeView.TradeView;
/**
 * This class represents the TradeModel.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements UpdateTradeModel class as an interface.
 */
public class TradeModel implements UpdateTradeModel{
	
	private static TradeModel instance; 
	
	private BrokerDataFetcher brokerDataFetcher; 
	private DataFetcher dataFetcher; 
	HashMap<String, HashMap<String, Integer>> barValues;
	private HashMap<String, Double> coinPrices;
	
	/**
	 * If there is no instance created before, this method creates a new TradeModel object, and returns it as instance.
	 * @return instance, the TradeModel object.
	 */
	public static TradeModel getInstance() {
		if (instance == null)
			instance = new TradeModel();

		return instance;
	}
	/**
	 * This method initializes the class attributes by creating new objects.
	 * barValues is a dictionary that coins a string and another dictionary.
	 * This is created in order to store values that will represent the histogram.
	 */
	public TradeModel() {
		brokerDataFetcher = new BrokerDataFetcher(); 
		dataFetcher = new DataFetcher(); 
		barValues = new HashMap<String, HashMap<String, Integer>>(); 
	}
	/**
	 * This method starts the trade for each broker in currentBrokerList.
	 * After a trade is performed, it is displayed in the table and graph.
	 */
	@Override
	public void startTrading() {
		
		List<Broker> currentBrokerList = brokerDataFetcher.getBrokers(); 
		
		//New hashmap every time a new round of trading starts. This means old values will NEVER be used.
		coinPrices = new HashMap<String, Double>();
		
		//add condition where, if there is no broker, and press perform trade: ERROR!
		Component frame = null;
		if(currentBrokerList.size()<1) {
			
			JOptionPane.showMessageDialog(frame, "Error!, please fill in at least a broker before performing trade");
		}
		
		//if two broker have same name error 
		boolean repeat = false;
		for(int i = 0; i < currentBrokerList.size();i++) {
			for(int j = 0; j < currentBrokerList.size();j++) {
				if(i != j && currentBrokerList.get(i).getBrokerName().equals(currentBrokerList.get(j).getBrokerName()) ) {
					repeat = true;
					break;
				}
				if(repeat) { break;}}}
		if(repeat) {JOptionPane.showMessageDialog(frame, "Error!, detects a broker that is declared more than once");}
		
		
		
		// Update broker crypto price 
		for (Broker broker : currentBrokerList) 
		{
			updateBrokerCoinList(broker); 
			performIndividualTrade(broker);
		}
		TradeView.getInstance().displayTradeView();
	}
	/**
	 * This method performs a trade for one broker.
	 * @param broker, a trade will be performed for this broker.
	 */
	private void performIndividualTrade(Broker broker) { 
		
		
		
		if(!broker.getTradingStrategy().performTrade(broker))
			TradeView.getInstance().notifyFailTrade(broker.getBrokerName());
	}
	/**
	 * This method will update the broker coin list for the broker in the parameter.
	 * @param broker, broker list will be updated for this broker.
	 */
	private void updateBrokerCoinList(Broker broker) {
		
		for (Coin coin : broker.getCoinList())
		{
			// Checks if the coin value has already been fetched from coinGecko and is in hashmap.
			if (coinPrices.containsKey(coin.getName()))
			{
				coin.setPrice(Math.round(coinPrices.get(coin.getName())));
			}
			else
			{
				// Fetches coin data from CoinGecko and then adds the data to the hashmap
				coin.setPrice(Math.round(dataFetcher.getPriceForCoin(coin.getName(), getCurrentDate())));
				coinPrices.put(coin.getName(), coin.getPrice());
			}			
		}
	}
	/**
	 * This method gets the current date.
	 * @return current date
	 */
	private String getCurrentDate() {
		String tempDate = LocalDate.now().toString(); 
		
		String[] tmp = tempDate.split("-"); 
		
		// Changed line for date issue discussed with TA. **
		String date = tmp[2] + "-" + tmp[1] + "-" + tmp[0];  
		return date; 
	}	
}
