package cryptoTrader.models.brokerModel.tradingStrategies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceipt;
import cryptoTrader.models.brokerModel.Broker;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
import cryptoTrader.models.tradeModel.coinFetcher.DataFetcher;
/**
 * This class represents Trading Strategy A that we will use to perform a trade.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class extends TradingStrategy class.
 */
public class TradingStrategyA extends TradingStrategy{

	private String action = "Buy"; 
	private String coinUsed = "bitcoin"; 
	private Double quantity = 500.0; 
	private Double price = null;  
	private String date = ""; 
	private List<Coin> coinList; 
	private Double bitcoinPrice, ethereumPrice; 
	/**
	 * This method creates a coin list that will be used in Trading Strategy A.
	 * Trading Strategy A will perform a trade with bitcoin and ethereum.
	 */
	public TradingStrategyA() {
		coinList = new ArrayList<Coin>(); 
		coinList.add(new Coin("bitcoin")); 
		coinList.add(new Coin("ethereum")); 
	}
	
	/**
	 * This method returns the name of the strategy.
	 */
	@Override
	public String toString() 
	{
		return "Strategy-A";
	}
    /**
     * This method performs a trade.
     * First, the list of entered coins are checked to proceed to start the trade.
     * If bitcoin  and ethereum prices are as in the Strategy A, then the trade is performed successfully.
     * When a trade is not performed, "fail" message is shown in trading actions' table.
     * @return boolean, returns true if a trade is performed successfully, false otherwise.
     */
	public boolean performTrade(Broker broker) {
		
		boolean hasBitcoin = false;
		boolean hasEthereum = false; 
		
		date = getCurrentDate(); 
		 
		List<Coin> brokerCoinList = broker.getCoinList(); 

		
		for(Coin coin : brokerCoinList) {
			if(coin.equals(coinList.get(0))){
				hasBitcoin = true; 
				bitcoinPrice = coin.getPrice(); 
			}
			if(coin.equals(coinList.get(1))) {
				hasEthereum = true; 
				ethereumPrice = coin.getPrice(); 
			}
		}
		
		if(hasBitcoin && hasEthereum)
		{
			if(bitcoinPrice > 45000.0 && ethereumPrice > 3000.0) 
			{
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, action, quantity, bitcoinPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else
			{
				System.out.println("Trade complete. Did not buy or sell.");
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, price, date); 
				return false; 
			}
		}
		else
		{
			System.out.println("Failed trade.");
			generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, price, date); 
			return false; 
		}
	}	
}