package cryptoTrader.views.mainView.tradeView;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceipt;
import cryptoTrader.dataStorage.tradeReceiptDatabase.TradeReceiptsDatabase;

/**
 * Singleton class representing the TradeTable view extending JPanel. When a trade is performed 
 * a table containing all trade history information is generated and sent to the
 * TradeTableView for visual representation.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI 
 *
 */
public class TradeTableView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Singleton instance variable
	private static TradeTableView instance; 
	
	/**
	 * If there is no instance create a new TradeTableView and assign it
	 * @return Instance of TradeTableView
	 */
	public static TradeTableView getInstance() {
		if(instance == null)
			instance = new TradeTableView();
		
		return instance; 
	}
	
	/**
	 * Constructor for TradeTableView. Sets the layout and size of JPanel.
	 */
	public TradeTableView() {
		setLayout(new GridLayout(2, 2));
		setPreferredSize(new Dimension(1250, 650));
	}
	
	/**
	 * Public method to create the TradeTable showing a list of all trade actions.
	 */
	public void createTable() {
		createTableOutput(); 
	}
	
	/**
	 * Creates the table output from the trade receipt database.
	 * Creates a row for each trade with the following data.
	 * <Trader, Strategy, Cryptocoin, Action, Quantity, Price, Date>. 
	 */
	private void createTableOutput() {
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		TradeReceiptsDatabase tradeData = TradeReceiptsDatabase.getInstance();
		List<TradeReceipt> updateTable = tradeData.getTradeReceiptList();
		Object[][] data = new Object[updateTable.size() + 1][8];
		
		// Takes the data out of the TradeReceipt list and into an Object[][]
		for(int i = 0; i <updateTable.size();i++) {
				data[i][0] = updateTable.get(i).getBroker() ;
				data[i][1] = updateTable.get(i).getTradingStrategy() ;
				data[i][2] = updateTable.get(i).getCoin();
				data[i][3] = updateTable.get(i).getAction();
				if(updateTable.get(i).getQuantity() == null)
					data[i][4] = "null"; 
				else
					data[i][4] = updateTable.get(i).getQuantity();
				
				if(updateTable.get(i).getPrice() == null)
					data[i][5] = "null"; 
				else
					data[i][5] = updateTable.get(i).getPrice();
				
				data[i][6] = updateTable.get(i).getDate() ;
		}
		
		// Generates a table with the trade data 
		JTable table = new JTable(data, columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		// Updates the stats component on the TradeView to display the table.
		TradeView.getInstance().updateStats(scrollPane);
	}
}
