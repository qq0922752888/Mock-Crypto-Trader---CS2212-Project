package cryptoTrader.controllers.brokerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import cryptoTrader.models.brokerModel.BrokerModel;
import cryptoTrader.models.brokerModel.UpdateBrokerModel;
/**
 * This class is coded to link BrokerModel and BrokerTableView classes in Controller system.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements RequestBrokerUpdate class as an interface. 
 */
public class BrokerController implements RequestBrokerUpdate {
	
	private UpdateBrokerModel brokerModel;
	
	private static BrokerController instance; 
	/**
	 * If there is no instance created before, this method creates a new BrokerController object, and returns it as instance.
	 * @return instance, the BrokerController object.
	 */
	public static BrokerController getInstance() {
		if (instance == null)
			instance = new BrokerController();

		return instance;
	}
	/**
	 * This method assigns brokerModel.
	 */
	private BrokerController()
	{
		brokerModel = BrokerModel.getInstance();
	}
	/**
	 * This method takes the request of adding a new broker.
	 * This method overrides the requestAddBroker method in RequestBrokerUpdate interface.
	 */
	@Override
	public void requestAddBroker()
	{
		brokerModel.addBroker();
	}
	/**
	 * This method takes the request of removing a new broker.
	 * @param index, the index of the broker to remove from the table.
	 * This method overrides the requestRemoveBroker method in RequestBrokerUpdate interface.
	 */
	@Override
	public void requestRemoveBroker(int index)
	{
		brokerModel.removeBroker(index);
	}
	/**
	 * This method takes the request of editing an information about an existing broker.
	 * @param index, the index of the broker to edit in the table.
	 * @param name, the name of the broker to edit in the table.
	 * @param coins, the name of the coins to edit in the table.
	 * @param strategy, the name of the trading strategy to edit in the table.
	 * This method overrides the requestBrokerEdit method in RequestBrokerUpdate interface.
	 */
	@Override
	public void requestBrokerEdit(int index, String name, String coins, String strategy)
	{
		brokerModel.editBroker(index, name, coins, strategy);
	}
	/**
	 * This method takes the request of returning a trading strategy.
	 * @return returns the trading strategy for the corresponding broker.
	 * This method overrides the requestStrategyList method in RequestBrokerUpdate interface.
	 */
	@Override
	public void requestStrategyList()
	{
		brokerModel.getStrategyList();
	}

	
}
