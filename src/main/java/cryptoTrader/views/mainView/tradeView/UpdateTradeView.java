package cryptoTrader.views.mainView.tradeView;
/**
 * Interface for interacting with the TradeView provides
 * the displayTradeView and notifyFailTrade methods.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface UpdateTradeView {
	/**
	 * Displays the trade view which includes
	 * a table of trade history and a bar graph 
	 * of trader strategy distribution.
	 */
	public void displayTradeView(); 
	
	/**
	 * Displays an error message for a failed trade with the given brokerName.
	 * @param brokerName The broker name for the failed trade.
	 */
	public void notifyFailTrade(String brokerName);
}
