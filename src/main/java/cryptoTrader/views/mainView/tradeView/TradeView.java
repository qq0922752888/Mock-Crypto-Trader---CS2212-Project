package cryptoTrader.views.mainView.tradeView;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cryptoTrader.controllers.tradeController.TradeController;
import cryptoTrader.views.mainView.brokerTableView.BrokerTableView;

/**
 * Singleton class representing the main trade view extending JPanel and implementing the UpdateTradeView
 * and ActionListener interfaces. Contains the perform trade button to interact with the 
 * TradeController to facilitate trading logic. Notifies the TradeGraphView and the TradeTableView to begin 
 * creating visual statistic representations of the systems data.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public class TradeView extends JPanel implements UpdateTradeView, ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	// Singleton instance variable
	private static TradeView instance;
	
	// Member variables 
	private JPanel stats; 
	private BrokerTableView brokerTable; 
	private JFrame errorWindow; 
	private ScrollPane scrollPane; 
	
	/**
	 * If there is no instance, create a new TradeView and set it to instance.
	 * @return Instance variable of TradeView
	 */
	public static TradeView getInstance() {
		if (instance == null)
			instance = new TradeView();

		return instance;
	}
	
	/**
	 * Constructor for TradeView creates the main "Trade" button and adds it
	 * to the JPanel.
	 */
	public TradeView() {
		
		brokerTable = BrokerTableView.getInstance(); 
		
		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("trade");
		trade.addActionListener(this);
		add(trade); 
		stats = TradeTableView.getInstance();
	}
	
	/**
	 * Returns the stats JPanel associated to this class.
	 * @return
	 */
	public JPanel getStatsPanel() {
		return this.stats; 
	}
	
	/**
	 * Updates the stats JPanel with the given component
	 * @param component
	 */
	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	/**
	 * Overrides from ActionListener. When the "Trade" event occurs this method
	 * checks to ensure that all table information is filled out. If it is then it initiates the
	 * trading through the TradeController and then creates tabular and graphical visual representation requests.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// Checks for "trade" command
		if ("trade".equals(command)) {
			for (int count = 0; count < brokerTable.dtm.getRowCount(); count++){
					Object traderObject = brokerTable.dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					Object coinObject = brokerTable.dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().split(",");
					Object strategyObject = brokerTable.dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					System.out.println(traderName + " " + Arrays.toString(coinNames) + " " + strategyName);
	        }
			// Initiates trading
			TradeController.getInstance().initiateTrade(); 
			// Resets and sends requests to create table and create bar graph.
		}
	}
	
	@Override
	public void displayTradeView() {
		stats.removeAll();
		TradeTableView.getInstance().createTable(); 
		TradeGraphView.getInstance().barGraph();
	}
	
	@Override
	public void notifyFailTrade(String brokerName) {
		JOptionPane.showMessageDialog(null, "Trade Failed. Broker Name: " + brokerName);
	}
}
