package cryptoTrader.models.brokerModel.tradingStrategies;

import java.util.ArrayList;
import java.util.List;

import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceipt;
import cryptoTrader.models.brokerModel.Broker;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
import cryptoTrader.models.tradeModel.coinFetcher.DataFetcher;
/**
 * This class represents Trading Strategy B that we will use to perform a trade.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class extends TradingStrategy class.
 */
public class TradingStrategyB extends TradingStrategy {

	private String action = "Buy"; 
	private String coinUsed = "binancecoin"; 
	private Double quantity = 1000.0; 
	private Double price = null; 
	private String date = ""; 
	private List<Coin> coinList; 
	private Double binancecoinPrice, cardanoPrice; 
	/**
	 * This method creates a coin list that will be used in Trading Strategy B.
	 * Trading Strategy B will perform a trade with binance.coin and cardano.
	 */
	public TradingStrategyB() {
		coinList = new ArrayList<Coin>(); 
		coinList.add(new Coin("binancecoin")); 
		coinList.add(new Coin("cardano")); 
	}
	/**
     * This method performs a trade.
     * First, the list of entered coins are checked to proceed to start the trade.
     * If binance.coin  and cardano prices are as in the Strategy B, then the trade is performed successfully.
     * When a trade is not performed, "fail" message is shown in trading actions' table.
     * @return boolean, returns true if a trade is performed successfully, false otherwise.
     */
	@Override
	public boolean performTrade(Broker broker) 
	{
		
		boolean hasBinancecoin = false;
		boolean hasCardano = false; 
		
		date = getCurrentDate();
		 
		List<Coin> brokerCoinList = broker.getCoinList(); 

		
		for(Coin coin : brokerCoinList) {
			if(coin.equals(coinList.get(0))){
				hasBinancecoin = true; 
				binancecoinPrice = coin.getPrice(); 
			}
			if(coin.equals(coinList.get(1))) {
				hasCardano = true; 
				cardanoPrice = coin.getPrice(); 
			}
		}
		
		if(hasBinancecoin && hasCardano) {
			  
			if(binancecoinPrice > 400.00 && cardanoPrice > 0.8) {
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, action, quantity, binancecoinPrice, date); 
				broker.incrementStrategyDictionary();
				return true; 
			}
			else
			{
				System.out.println("Trade complete. Did not buy or sell.");
				generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Null", null, price, date); 
				return false; 
			}
		}
		else
		{
			System.out.println("Failed Trade");
			generateTradeReceipt(broker.getBrokerName(), broker.getTradingStrategy().toString(), coinUsed, "Fail", null, price, date); 
			return false;
		}
	}
	/**
	 * This method returns the name of the strategy.
	 */
	@Override
	public String toString() 
	{
		return "Strategy-B";
			
	}
}
