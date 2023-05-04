package cryptoTrader.dataStorage.brokerDatabase;

import cryptoTrader.models.brokerModel.Broker;

/**
 * Interface provided for updating the broker database.
 * Provides the addBroker, removeBroker, and getBrokerAtIndex methods. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface UpdateBrokerData
{
	/**
	 * This method adds a new broker to the database.
	 * @param broker, broker object that holds the data about a unique broker.
	 * This method overrides the addBroker method in UpdateBrokerData class.
	 */
	public void addBroker(Broker broker);
	
	/**
     * This method removes a broker from the brokerList.
     * @param index, the index of the broker to remove from the broker list.
     * This method overrides the removeBroker method in UpdateBrokerData class.
     */
	public void removeBroker(int index);
	
	/**
	 * @param index, the index in the broker list.
	 * @return returns the broker in the index.
	 */
	public Broker getBrokerAtIndex(int index); 
}
