package cryptoTrader.models.tradeModel;
/**
 * Interface for updating the TradeModel. 
 * Provides the startTrading method. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface UpdateTradeModel {
	/**
	 * This method starts the trade for each broker in currentBrokerList.
	 * After a trade is performed, it is displayed in the table and graph.
	 */
	public void startTrading(); 
}
