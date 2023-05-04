package cryptoTrader.models.brokerModel;

import java.util.HashMap;
import java.util.List;

import cryptoTrader.models.brokerModel.tradingStrategies.TradingStrategy;
import cryptoTrader.models.brokerModel.tradingStrategies.TradingStrategyFactory;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
/**
 * This class represents a Broker object.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * The Broker object has a name, a list of coin names, and a trading strategy.
 * The Broker object also has a dictionary to count the number of strategies a broker uses.
 */
public class Broker {
	private String name; 
	private List<Coin> coinList; 
	private TradingStrategy strategy; 
	private HashMap<String, Integer> strategyDictionary; 
	/**
	 * This constructor sets the attributes of a Broker objects.
	 * @param name, the name of the broker.
	 * @param coinList, the list of coins.
	 * @param strategy, the trading strategy a broker has to perform a trade.
	 * This method also stores a dictionary to count the number of trading strategies a broker used to perform a trade.
	 */
	public Broker(String name, List<Coin> coinList, String strategy) {
		this.name = name;
		this.coinList = coinList; 
		this.strategy = TradingStrategyFactory.getInstance().getTradingStrategy(strategy); 
		strategyDictionary = new HashMap<String, Integer>(); 
		strategyDictionary.put("Strategy-A", 0); 
		strategyDictionary.put("Strategy-B", 0); 
		strategyDictionary.put("Strategy-C", 0);
		strategyDictionary.put("Strategy-D", 0); 
	}
	/**
	 * Getter method for strategy dictionary
	 * @return returns the dictionary of trading strategies used by a broker.
	 */
	public HashMap<String, Integer> getStrategyDictionary(){
		return this.strategyDictionary; 
	}
	/**
	 * This method increments the number of a trading strategy used by a broker.
	 */
	public void incrementStrategyDictionary() {
		strategyDictionary.put(this.strategy.toString(), strategyDictionary.get(this.strategy.toString()) + 1); 
	}
	/**
	 * This method sets the trading a broker has to perform a trade.
	 * @param strategy, trading strategy a broker uses.
	 */
	public void setTradingStrategy(String strategy) {
		this.strategy = TradingStrategyFactory.getInstance().getTradingStrategy(strategy); 
	}
	/**
	 * Getter method for trading strategy
	 * @return trading strategy
	 */
	public TradingStrategy getTradingStrategy() {
		return this.strategy; 
	}
	/**
	 * Getter method for a coin list.
	 * @return the coin list a broker has.
	 */
	public List<Coin> getCoinList(){
		return this.coinList;
	}
	/**
	 * Getter method broker name
	 * @return name, the name of a broker.
	 */
	public String getBrokerName() {
		return name;
	}
	/**
	 * Setter method for broker name.
	 * @param brokerName, name of the broker.
	 */
	public void setBrokerName(String brokerName) {
		this.name = brokerName; 
	}
	/**
	 * Setter method for coin list.
	 * @param coinList, the coin list a broker has.
	 */
	public void setCoinList(List<Coin> coinList) {
		this.coinList = coinList; 
	}
	/**
	 * toString method that prints out the relative information about a broker.
	 * This information includes name, coin list, and strategy.
	 */
	// Need to change this because doing List<Coin> objects.
	public String toString()
	{
		String str = "\tName: ";
		str += name + "\n\tCoins: ";
		
		str += "[";
		if (coinList != null) {
			for(Coin coin : coinList){
				str += coin.getName();
			}
		}
		str += "]";
		str+= "\n\tStrategy: ";
		

		
		if (strategy != null) str += strategy.toString();
		
		return str;
	}
}
