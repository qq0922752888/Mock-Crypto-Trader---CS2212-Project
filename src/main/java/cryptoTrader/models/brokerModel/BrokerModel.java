package cryptoTrader.models.brokerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cryptoTrader.dataStorage.brokerDatabase.BrokerData;
import cryptoTrader.dataStorage.brokerDatabase.UpdateBrokerData;
import cryptoTrader.models.brokerModel.tradingStrategies.TradingStrategyFactory;
import cryptoTrader.models.tradeModel.TradeModel;
import cryptoTrader.models.tradeModel.coinFetcher.Coin;
import cryptoTrader.views.mainView.brokerTableView.BrokerTableView;
import cryptoTrader.views.mainView.brokerTableView.UpdateBrokerTableView;
/**
 * This class represents the BrokerModel in the Model system.
 * This BrokerModel adds a new broker, removes an existing broker, and edits the data about an existing broker.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements UpdateBrokerModel as an interface.
 */
public class BrokerModel implements UpdateBrokerModel {
	
	private UpdateBrokerData brokerDatabase;
	private UpdateBrokerTableView brokerTable;
	
	private static BrokerModel instance; 
	/**
	 * If a BrokerModel is not created as an instance, a new BrokerModel object is created and returned.
	 * @return instance, returns a BrokerModel object.
	 */
	public static BrokerModel getInstance() {
		if (instance == null)
			instance = new BrokerModel();

		return instance;
	}
	/**
	 * This constructor method assigns brokerDatabase with brokerModel object.
	 * This is done by calling getInstance() method.
	 */
	private BrokerModel()
	{
		brokerDatabase = BrokerData.getInstance();
	}
	/**
     * This method adds a new broker to brokerDatabase.
     * This method overrides the addBroker method in UpdateBrokerModel interface class.
     */
	@Override
	public void addBroker()
	{
		if (brokerTable == null) 
			brokerTable = BrokerTableView.getInstance();
		
		brokerDatabase.addBroker(new Broker("", null, null));
		brokerTable.addbroker();
	}
	/**
     * This method removes an existing broker at the corresponding index.
     * @param index, the index of an existing broker to remove from the list.
     * This method overrides the removeBroker method in UpdateBrokerModel interface class.
     */
	@Override
	public void removeBroker(int index)
	{
		if (brokerTable == null) 
			brokerTable = BrokerTableView.getInstance();
		brokerDatabase.removeBroker(index);
		brokerTable.removeBroker(index);
	}
	/**
     * This method edits a data about an existing broker.
     * @param index, the index of the broker.
     * @param name, the name of the broker.
     * @param coins, the coins that broker has to perform a trade.
     * @param strategy, the trading strategy a broker uses.
     * This method overrides the editBroker method in UpdateBrokerModel interface class.
     */
	public void editBroker(int index, String name, String coins, String strategy)
	{
		List<Coin> coinList = new ArrayList<Coin>(); 
		
		if (coins != null) 
		{
			String[] coinArray = coins.split(",");
			
			for(String coin : coinArray) {
				coin.strip();
			}
			
			// Create coin objects from each String coin
			
			for(String coinName : coinArray) {
				coinList.add(new Coin(coinName)); 
			}
		}
		Broker brokerToUpdate = brokerDatabase.getBrokerAtIndex(index);
		
		brokerToUpdate.setTradingStrategy(strategy);
		brokerToUpdate.setBrokerName(name); 
		brokerToUpdate.setCoinList(coinList);
		
		System.out.println("Broker updated:\n" + brokerToUpdate);
	}
	/**
     * This method gets the strategy list from brokerModel.
     * @return list, returns the list of strategies.
     * This method overrides the getStrategyList method in UpdateBrokerModel interface class.
     */
	@Override
	public void getStrategyList()
	{
		Vector<String> list = new Vector<String>();
		list.addAll(TradingStrategyFactory.getInstance().getStrategyList());
		
		if (brokerTable == null) 
			brokerTable = BrokerTableView.getInstance();
		
		brokerTable.updateStrategyList(list);
	}
}
