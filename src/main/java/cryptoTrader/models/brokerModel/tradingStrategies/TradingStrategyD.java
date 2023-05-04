package cryptoTrader.models.brokerModel.tradingStrategies;

import java.util.ArrayList;
import java.util.List;

import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceipt;
import cryptoTrader.models.brokerModel.Broker;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
import cryptoTrader.models.tradeModel.coinFetcher.DataFetcher;
/**
 * This class represents Trading Strategy D that we will use to perform a trade.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class extends TradingStrategy class.
 */
public class TradingStrategyD extends TradingStrategy {
	
	private String action = "Buy"; 
	private String coinUsed = "ripple"; 
	private Double quantity = 850.0;  
	private String date = ""; 
	private List<Coin> coinList; 
	private Double ripplePrice, moneroPrice; 
	/**
	 * This method creates a coin list that will be used in Trading Strategy D.
	 * Trading Strategy D will perform a trade with ripple and monero.
	 */
	public TradingStrategyD()
	{
		coinList = new ArrayList<Coin>(); 
		coinList.add(new Coin("ripple")); 
		coinList.add(new Coin("monero")); 
	}
	/**
	 * This method returns the name of the strategy.
	 */
	@Override
	public String toString() 
	{
		return "Strategy-D";
	}
	/**
     * This method performs a trade.
     * First, the list of entered coins are checked to proceed to start the trade.
     * If tether and monero prices are as in the Strategy D, then the trade is performed successfully.
     * When a trade is not performed, "fail" message is shown in trading actions' table.
     * @return boolean, returns true if a trade is performed successfully, false otherwise.
     */
	@Override
	public boolean performTrade(Broker broker) {
		boolean hasRipple = false;
		boolean hasMonero = false; 
		
		date = getCurrentDate();
		 
		List<Coin> brokerCoinList = broker.getCoinList(); 

		// Fetches the coin prices from the web and checks that  the correct coins were specified by the broker's list.
		for(Coin coin : brokerCoinList) {
			if(coin.equals(coinList.get(0))){
				hasRipple = true; 
				ripplePrice = coin.getPrice(); 
			}
			if(coin.equals(coinList.get(1))) {
				hasMonero = true; 
				moneroPrice = coin.getPrice(); 
			}
		}
		
		// Only runs if the correct coins were specified and coin data was fetched successfully
		if (hasRipple && hasMonero)
		{
			if(ripplePrice > 0.5 && moneroPrice > 250.0) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, action, quantity, ripplePrice, date); 
				return true; 
			}
			else
			{
				System.out.println("Trade complete. Did not buy or sell.");
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, ripplePrice, date); 
				return false; 
			}
		}
		else
		{
			System.out.println("Failed trade.");
			generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, ripplePrice, date); 
			return false; 
		}
	}
}
