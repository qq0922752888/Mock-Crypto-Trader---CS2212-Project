package cryptoTrader.controllers.tradeController;

/**
 * Interface for requesting a new trade.
 * Provides the initiateTrade method. 
 * @author wesley
 *
 */
public interface RequestTrade {
	
	/**
	 * This method initiates the trade by calling startTrading method.
	 */
	public void initiateTrade(); 
}
