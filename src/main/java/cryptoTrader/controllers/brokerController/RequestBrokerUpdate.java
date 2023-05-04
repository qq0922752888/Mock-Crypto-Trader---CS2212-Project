package cryptoTrader.controllers.brokerController;

/**
 * Interface for requesting BrokerController tasks.
 * Provides the requestAddBroker, requestRemoveBroker, 
 * requestBrokerEdit and requestStrategyList methods. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface RequestBrokerUpdate
{
	/**
	 * This method takes the request of adding a new broker.
	 */
	public void requestAddBroker();
	
	/**
	 * This method takes the request of removing a new broker.
	 * @param index, the index of the broker to remove from the table.
	 */
	public void requestRemoveBroker(int index);
	
	/**
	 * This method takes the request of editing an information about an existing broker.
	 * @param index, the index of the broker to edit in the table.
	 * @param name, the name of the broker to edit in the table.
	 * @param coins, the name of the coins to edit in the table.
	 * @param strategy, the name of the trading strategy to edit in the table.
	 */
	public void requestBrokerEdit(int index, String name, String coins, String strategy);
	
	/**
	 * This method takes the request of returning a trading strategy.
	 * @return returns the trading strategy for the corresponding broker.
	 */
	public void requestStrategyList();
}
