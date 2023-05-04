package cryptoTrader.views.mainView.tradeView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import cryptoTrader.dataStorage.brokerDatabase.BrokerData;
import cryptoTrader.models.brokerModel.Broker;

/**
 * Singleton class representing the GraphView. Provides a visual
 * representation for the distribution of brokers and trading strategies 
 * in the program. 
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public class TradeGraphView {
	
	// Singleton instance variable
	private static TradeGraphView instance; 
	
	/**
	 * If there is currently no instance of TradeGraphView, 
	 * returns a new TradeGraphView and sets instance.  
	 * @return Instance of TradeGraphView
	 */
	public static TradeGraphView getInstance() {
		if(instance == null)
			instance = new TradeGraphView();
		return instance;
	}
	
	/**
	 * Creates a bar graph to visually represent distribution of brokers/trading strategies.
	 */
	public void barGraph() {
		createBar(); 
	}
	
	/**
	 * Generates a bar graph and updates the TradeView with the new Panel to display it to the user.
	 */
	private void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		// Get the brokerList
		List<Broker> brokerList = BrokerData.getInstance().getBrokerList(); 
		
		// Checks each broker in the database to determine frequency of strategies used.
		for (Broker broker : brokerList) {
			// Set the data set values for each (#times used, Broker name, Strategy Name).
			HashMap<String, Integer> strategyDictionary = broker.getStrategyDictionary(); 
			dataset.setValue(strategyDictionary.get("Strategy-A"), broker.getBrokerName(), "Strategy-A");
			dataset.setValue(strategyDictionary.get("Strategy-B"), broker.getBrokerName(), "Strategy-B");
			dataset.setValue(strategyDictionary.get("Strategy-C"), broker.getBrokerName(), "Strategy-C");
			dataset.setValue(strategyDictionary.get("Strategy-D"), broker.getBrokerName(), "Strategy-D");
		}
		
		// Generates the bar graph on a ChartPanel object.
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0));
		plot.setRangeAxis(rangeAxis);

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		// Updates the TradeView's stats component with the ChartPanel object.
		TradeView.getInstance().updateStats(chartPanel);
	}
}
