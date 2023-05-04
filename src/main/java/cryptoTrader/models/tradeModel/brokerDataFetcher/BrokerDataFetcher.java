package cryptoTrader.models.tradeModel.brokerDataFetcher;

import java.util.List;

import cryptoTrader.dataStorage.brokerDatabase.BrokerData;
import cryptoTrader.models.brokerModel.Broker;
/**
 * This class helps the broker to fetch the data.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 */
public class BrokerDataFetcher {
	/**
	 * This method gets the broker list.
	 * @return returns the broker list
	 */
	public List<Broker> getBrokers(){
		return BrokerData.getInstance().getBrokerList();
	}
	
}
