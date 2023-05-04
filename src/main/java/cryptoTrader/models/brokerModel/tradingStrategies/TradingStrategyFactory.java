package cryptoTrader.models.brokerModel.tradingStrategies;

import java.util.ArrayList;
import java.util.List;

// Uses Factory design pattern 
/**
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class works as a factory to represent the trading strategies.
 */
public class TradingStrategyFactory {
	
	private List<String> strategies;
	
	private static TradingStrategyFactory instance; 
	/**
	 * If a TradingStrategyFactory is not created as an instance, a new TradingStrategyFactory object is created and returned.
	 * @return returns a new TradingStrategyFactory object.
	 */
	public static TradingStrategyFactory getInstance() {
		if(instance == null)
			instance = new TradingStrategyFactory(); 
		
		return instance; 
	}
	/**
	 * If a TradingStrategyFactory is not created as an instance, a new TradingStrategyFactory object is created and returned.
	 * @return returns a new TradingStrategyFactory object.
	 */
	private TradingStrategyFactory()
	{
		strategies = new ArrayList<String>();
		strategies.add("None");
		strategies.add("Strategy-A");
		strategies.add("Strategy-B");
		strategies.add("Strategy-C");
		strategies.add("Strategy-D");
	}
	/**
	 * This method gets the tradingStrategy with the input strategyType.
	 * A unique trading strategy is being created(A,B,C or D) by using the parameter strategyType, and returned.
	 * @param strategyType, the strategy type a broker uses.
	 * @return the corresponding strategy.
	 */
	public TradingStrategy getTradingStrategy(String strategyType) {
		
		if(strategyType == null || !strategies.contains(strategyType)) {
			return null;
		}
		
		if(strategyType.equalsIgnoreCase("Strategy-A"))
			return new TradingStrategyA(); 
		
		else if(strategyType.equalsIgnoreCase("Strategy-B"))
			return new TradingStrategyB(); 
		
		else if(strategyType.equalsIgnoreCase("Strategy-C"))
			return new TradingStrategyC(); 
		
		else if(strategyType.equalsIgnoreCase("Strategy-D"))
			return new TradingStrategyD(); 
		
		return null; 
		
	}
	/**
	 * This method gets the available strategies.
	 * @return strategies, available strategies.
	 */
	public List<String> getStrategyList()
	{
		return strategies;
	}
	
}
