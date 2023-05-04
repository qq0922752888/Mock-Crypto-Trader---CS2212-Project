package cryptoTrader.views.mainView.brokerTableView;

import java.util.Vector;

/**
 * Interface for interacting with the BrokerTableView. 
 * Provides the updateStrategyList, removeBroker and addBroker methods. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface UpdateBrokerTableView
{
	/**
	 * This method provides a way for the BrokerModel to send the
	 * list of available trading strategies to the BrokerTableView.
	 * @param strategies The list of strategies that are available.
	 */
	public void updateStrategyList(Vector<String> strategies);
	
	/**
	 * This method provides a way for the BrokerModel to update the
	 * BrokerTableView once a broker has been removed.
	 * @param index The table index of the broker to be removed.
	 */
	public void removeBroker(int index);
	
	/**
	 * This method provides a way for the BrokerModel to update the
	 * BrokerTableView once a broker has been added.
	 */
	public void addbroker();
}
