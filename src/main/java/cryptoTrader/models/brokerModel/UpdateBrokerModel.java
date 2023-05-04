package cryptoTrader.models.brokerModel;

/**
 * Interface for updating the broker model.
 * Provides the addBroker, removeBroker, editBroker and
 * getStrategyList methods. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 */
public interface UpdateBrokerModel
{
	/**
	 * This method creates a new broker in BrokerData and adds a new row to the BrokerTableView.
	 */
	public void addBroker();
	
	/**
	 * This method removes the target broker from BrokerTableView and BrokerData.
	 * @param index The index of the target broker.
	 */
	public void removeBroker(int index);
	
	/**
	 * This method will update the target broker with the given information.
	 * @param index The index of the target broker.
	 * @param name The (new) name of the broker.
	 * @param coins The (new) coins of the broker.
	 * @param strategy The (new) strategy of the broker.
	 */
	public void editBroker(int index, String name, String coins, String strategy);
	
	/**
	 * This method will send the list of available strategies to the BrokerTableView.
	 */
	public void getStrategyList();
}
