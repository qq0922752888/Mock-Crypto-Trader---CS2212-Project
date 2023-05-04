package cryptoTrader.dataStorage.tradeReceiptDatabase;

import java.util.ArrayList;
import java.util.List;
/**
 * This class stores the trade receipts.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This method implements the UpdateTradeReceipts class as an interface.
 */
public class TradeReceiptsDatabase implements UpdateTradeReceipts{
	
	private static TradeReceiptsDatabase instance; 
	
	private List<TradeReceipt> tradeReceiptList; 
	/**
	 * If there is no instance created before, this method creates a new TradeReceiptsDatabase object, and returns it as instance.
	 * @return instance, the TradeReceiptsDatabase object.
	 */
	public static TradeReceiptsDatabase getInstance() {
		if (instance == null)
			instance = new TradeReceiptsDatabase(); 
		return instance; 
	}
	/**
	 * This constructor class creates an ArrayList to store the trade receipts.
	 */
	public TradeReceiptsDatabase() {
		tradeReceiptList = new ArrayList<TradeReceipt>(); 
	}/**
	 * This method adds a new trade receipts to trade receipt database.
	 * @param tradeReceipt, a new tradeReceipt upon performing a trade.
	 */
	@Override
	public void addTradeReceipt(TradeReceipt tradeReceipt) {
		tradeReceiptList.add(tradeReceipt); 
	}
	/**
	 * Getter method for trade receipt list
	 * @return tradeReceiptList
	 */
	@Override
	public List<TradeReceipt> getTradeReceiptList(){
		return this.tradeReceiptList; 
	}
	
}
