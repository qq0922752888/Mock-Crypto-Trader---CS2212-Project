package cryptoTrader.dataStorage.brokerDatabase;

import java.util.ArrayList;
import java.util.List;

import cryptoTrader.models.brokerModel.Broker;
import cryptoTrader.models.brokerModel.BrokerModel;
/**
 * This class represents a Broker.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements UpdataBrokerData as an interface.
 * List data structure is used to store the data about the brokers.
 */
public class BrokerData implements UpdateBrokerData
{
	private List<Broker> brokerList; 
	
	private static BrokerData instance; 
	/**
	 * If there is no instance created before, this method creates a new BrokerData object, and returns it as instance.
	 * @return instance, the BrokerData object.
	 */
	public static BrokerData getInstance() {
		if (instance == null)
			instance = new BrokerData();

		return instance;
	}
	/**
	 * This constructor class creates an ArrayList to store the data about brokers.
	 */
	private BrokerData()
	{
		brokerList = new ArrayList<Broker>(); 
	}
	/**
	 * Getter method for broker list.
	 * @return brokerList, returns the brokers.
	 */
	public List<Broker> getBrokerList()
	{
		return this.brokerList; 	
	}
	/**
	 * @param index, the index in the broker list.
	 * @return returns the broker in the index.
	 */
	public Broker getBrokerAtIndex(int index) {
		return this.brokerList.get(index); 
	}
	/**
	 * This method adds a new broker to the database.
	 * @param broker, broker object that holds the data about a unique broker.
	 * This method overrides the addBroker method in UpdateBrokerData class.
	 */
	@Override
	public void addBroker(Broker broker)
	{
		brokerList.add(broker);
		
		System.out.println("Broker added.");
	}
	/**
     * This method removes a broker from the brokerList.
     * @param index, the index of the broker to remove from the broker list.
     * This method overrides the removeBroker method in UpdateBrokerData class.
     */
	@Override
	public void removeBroker(int index)
	{
		brokerList.remove(index);
		
		System.out.println("Broker removed at row " + index);
	}
}
