package cryptoTrader.models.brokerModel.tradingStrategies;

import cryptoTrader.models.brokerModel.Broker;

public interface Strategy {
	public boolean performTrade(Broker broker);
}
