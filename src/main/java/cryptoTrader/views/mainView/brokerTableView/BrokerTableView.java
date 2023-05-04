package cryptoTrader.views.mainView.brokerTableView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.controllers.brokerController.BrokerController;
import cryptoTrader.controllers.brokerController.RequestBrokerUpdate;

/**
 * Singleton class representing the Broker Table view in the main UI.
 * Allows users to visually add and remove rows from the table and allows
 * users to visually edit broker information and send it to the BrokerController.
 * Extends JPanel and implements the UpdateBrokerTableView and ActionListener interfaces.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 */
public class BrokerTableView extends JPanel implements UpdateBrokerTableView, ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	// Member Variables
	public DefaultTableModel dtm;
	private JTable table;
	private JComboBox<String> strategyOptions;
	private RequestBrokerUpdate controller;
	
	// Singleton Instance variable
	private static BrokerTableView instance;
	
	/**
	 * If there is no instance instantiated then create a new one
	 * @return	Instance variable of BrokerTableView
	 */
	public static BrokerTableView getInstance() {
		if (instance == null)
			instance = new BrokerTableView();

		return instance;
	}
	
	/**
	 * Constructor for the BrokerTableView creates a 
	 * tabular representation for storing broker information.
	 */
	public BrokerTableView()
	{
		controller = BrokerController.getInstance();
		
		// Create the table in a scrollpane
		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 0);
		table = new JTable(dtm);
		dtm.addTableModelListener(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		
		//Make the third row a drop-down menu for strategies
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		strategyOptions = new JComboBox<String>();
		strategyColumn.setCellEditor(new DefaultCellEditor(strategyOptions));
		
		// Create the buttons for adding and removing brokers
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		// Organize and add all View elements (scrollpane with table, buttons)
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		add(buttons);
		
		// Creates the actionPerformed for the TableCellListener
		Action action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        TableCellListener tcl = (TableCellListener)e.getSource();		        
		        int row = tcl.getRow();
		        // Requests an edit in the broker object
		        controller.requestBrokerEdit(row, (String)table.getValueAt(row, 0), (String)table.getValueAt(row, 1), (String)table.getValueAt(row, 2));
		    }
		};
		// Creates the table cell listener for detecting changes to the table for dynamic broker updating.
		TableCellListener tcl = new TableCellListener(table, action);
	}
	
	/**
	 * actionPerformed override for button events. Allows for the
	 * functionality of the "Add Row" button and "Remove row" button while 
	 * notifying the BrokerController. 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		// Checks that the list of strategies is available to the user.
		if (strategyOptions.getItemCount() == 0)
			controller.requestStrategyList();
		
		// Checks which command has been issued
		if ("addTableRow".equals(command))
		{
			// requests that a broker be added/created
			controller.requestAddBroker();
		}
		else if ("remTableRow".equals(command))
		{
			// Requests that a broker be removed
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
			{
				controller.requestRemoveBroker(selectedRow);
			}
		}
		
	}

	@Override
	public void updateStrategyList(Vector<String> strategies)
	{
		for(String str : strategies)
				strategyOptions.addItem(str);
	}

	@Override
	public void removeBroker(int index) {
		dtm.removeRow(index);
		
	}

	@Override
	public void addbroker() {
		dtm.addRow(new String[3]);
		
	}
}
