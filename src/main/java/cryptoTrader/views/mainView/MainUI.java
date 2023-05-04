package cryptoTrader.views.mainView;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import cryptoTrader.views.mainView.brokerTableView.BrokerTableView;
import cryptoTrader.views.mainView.tradeView.TradeTableView;
import cryptoTrader.views.mainView.tradeView.TradeView;

/**
 * Singleton class for the MainUI extends JFrame and implements UpdateMainView interface.
 * Organizes the different views on one JFrame and ensures they are viewed properly by the user.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI 
 *
 */
public class MainUI extends JFrame implements UpdateMainView {

	private static final long serialVersionUID = 1L;
	// Singleton instance variable
	private static MainUI instance;
	
	// Member variables 
	private JPanel tradeTableView;
	private BrokerTableView brokerTable;
	private TradeView tradeView; 

	/**
	 * If there is no instance, create a new MainUI and set it.
	 * @return The instance variable of MainUI
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	/**
	 * Constructor for the MainUI. Sets up the JPanel components for
	 * the frame and determines the location of seperate views inside it.
	 */
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");


		JPanel north = new JPanel();
		
		brokerTable = BrokerTableView.getInstance();
		tradeView = TradeView.getInstance();
		tradeTableView = TradeTableView.getInstance(); 
		
		// Adds the different views to the frame.
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(brokerTable, BorderLayout.EAST);
		getContentPane().add(tradeTableView, BorderLayout.CENTER);
		getContentPane().add(tradeView, BorderLayout.SOUTH);
	}
	
	public void startMainUI() {
		JFrame frame = MainUI.getInstance(); 
		frame.setSize(900, 600);
		frame.pack(); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
}
