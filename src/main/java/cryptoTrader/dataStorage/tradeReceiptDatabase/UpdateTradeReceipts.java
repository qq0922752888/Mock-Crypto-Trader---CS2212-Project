package cryptoTrader.dataStorage.tradeReceiptDatabase;

import java.util.List;

/**
 * Interface for updating the trade receipt database.
 * Provides the addTradeReceipt and getTradeReceiptList methods. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface UpdateTradeReceipts{
	/**
	 * This method adds a new trade receipts to trade receipt database.
	 * @param tradeReceipt, a new tradeReceipt upon performing a trade.
	 */
	public void addTradeReceipt(TradeReceipt tradeReceipt); 
	
	/**
	 * Getter method for trade receipt list
	 * @return tradeReceiptList
	 */
	public List<TradeReceipt> getTradeReceiptList(); 

}
