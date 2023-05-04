package cryptoTrader.models.brokerModel.tradingStrategies;

import java.util.ArrayList;
import java.util.List;

import cryptoTrader.models.brokerModel.Broker;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
/**
 * This class represents Trading Strategy C that we will use to perform a trade.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class extends TradingStrategy class.
 */
public class TradingStrategyC extends TradingStrategy{


	
	//Deafult conditions (fail), display data
	private String coinUsed = "Null";  
	private Double price = null;  
	private String date = ""; 
	private List<Coin> coinList; 
	private Double tetherPrice, moneroPrice,litePrice; 
	
	/**
	 * This method creates a coin list that will be used in Trading Strategy C.
	 * Trading Strategy C will perform a trade with tether, monero and litecoin.
	 */
	public TradingStrategyC() {
		coinList = new ArrayList<Coin>(); 
		coinList.add(new Coin("tether")); 
		coinList.add(new Coin("monero"));
		coinList.add(new Coin("litecoin"));
	}
	
	/**
	 * This method returns the name of the strategy.
	 */
	@Override
	public String toString() 
	{
		return "Strategy-C";
	}
	/**
     * This method performs a trade.
     * First, the list of entered coins are checked to proceed to start the trade.
     * If tether, monero and litecoin prices are as in the Strategy C, then the trade is performed successfully.
     * When a trade is not performed, "fail" message is shown in trading actions' table.
     * @return boolean, returns true if a trade is performed successfully, false otherwise.
     */
	@Override
	public boolean performTrade(Broker broker) {
		
		boolean hasTether = false;
		boolean hasmon = false; 
		boolean haslite = false;
		
		date = getCurrentDate();
		 
		List<Coin> brokerCoinList = broker.getCoinList(); 

		//Retrieval of coin prices from main coin list whenever coin prices entered is a subset of main coin list
		for(Coin coin : brokerCoinList) {
			if(coin.equals(coinList.get(0))){
				hasTether = true; 
				tetherPrice = coin.getPrice(); 
			}
			if(coin.equals(coinList.get(1))) {
				hasmon = true; 
				moneroPrice = coin.getPrice(); 
			}
			if(coin.equals(coinList.get(2))) {
				haslite = true;
				litePrice = coin.getPrice();
			}
		  }
		
		
		//if all coins entered condition
		if(hasTether && hasmon && haslite) {
			
			if(tetherPrice < 1.0 && moneroPrice > 250.0 && litePrice >120.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "tether", "Buy", 300.0, tetherPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else if(tetherPrice > 1.1 && moneroPrice < 220.0 && litePrice > 130.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "bnb", "Buy", 20.0, moneroPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else if(tetherPrice > 1.15 && moneroPrice > 165.0 && litePrice < 105.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "terra", "Buy", 15.0, litePrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else if(tetherPrice > 1.3 && moneroPrice > 215.0 && litePrice > 110.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "tether", "Sell", 150.0, tetherPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else if(tetherPrice > 1.15 && moneroPrice < 230.0 && litePrice > 130.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "terra", "Sell", 15.0, litePrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else if(tetherPrice >= 1.00 && moneroPrice > 190.0 && litePrice < 190.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "terra", "Buy", 77.0, litePrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else {
				System.out.println("Trade complete. Did not buy or sell.");
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Null", null, price, date); 
				return false; 
			}
		}
		//if only tether and litecoin condition
		if(hasTether && haslite) {
			if(tetherPrice < 0.95  && litePrice >130) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "tether", "Buy", 500.0, tetherPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else if(tetherPrice > 1.18 && litePrice < 105) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "bnb", "Buy", 150.0, moneroPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
		}
			else if(tetherPrice > 1.05 & litePrice >117) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "tether", "Sell", 75.0, tetherPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else {
				System.out.println("Trade complete. Did not buy or sell.");
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Null", null, price, date); 
				return false; 
			}
		}
		//only monero conditon
		if(hasmon) {
			if(moneroPrice < 400) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), "bnb", "Buy", 7000.0, moneroPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
				}
			else {
				System.out.println("Trade complete. Did not buy or sell.");
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, price, date); 
				return false; 
				}
		}
		else {
			System.out.println("Failed trade.");
			generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, price, date); 
			return false; 
		 }
		
	}
}
	
