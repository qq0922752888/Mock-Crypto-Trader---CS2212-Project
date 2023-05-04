package cryptoTrader.models.tradeModel.coinFetcher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * This class fetches data for coin prices by using a website called coingecko.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 */
public class DataFetcher {
    /**
     * This method goes to coingecko, and get the current price for the entered coin.
     * @param id, coin name.
     * @param date, current date.
     * @return data for the given coin.
     * Catches IOException if API does not work, and prints out a message.
     */
	private JsonObject getDataForCrypto(String id, String date) {

		String urlString = String.format(
				"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	/**
	 * This method gets the coin price for the entered coin by using the data that is accessed by above method.
	 * @param id, coin name.
	 * @param date, current date.
	 * @return price, price of the coin.
	 */
	public double getPriceForCoin(String id, String date) {
		double price = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			price = currentPrice.get("cad").getAsDouble();
		}
		
		return price;
	}
	/**
	 * This method gets the market cap for the entered coin at the current date.
	 * @param id, coin name.
	 * @param date, current date.
	 * @return marketcap of the coin.
	 */
	public double getMarketCapForCoin(String id, String date) {
		double marketCap = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
			marketCap = currentPrice.get("cad").getAsDouble();
		}
		
		return marketCap;
	}
	/**
	 * This method gets the volume for the entered coin at the current date.
	 * @param id, coin name.
	 * @param date, current date.
	 * @return market volume for the coin.
	 */
	public double getVolumeForCoin(String id, String date) {
		double volume = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
			volume = currentPrice.get("cad").getAsDouble();
		}
		
		return volume;
	}
	/**
	 * This main method prints out the price, marketcap, and volume for bitcoin.
	 * @param args arguments given.
	 */
	public static void main(String[] args) {
		DataFetcher fetcher = new DataFetcher();
		double price = fetcher.getPriceForCoin("bitcoin", "08-09-2021");
		double marketCap = fetcher.getMarketCapForCoin("bitcoin", "08-09-2021");
		double volume = fetcher.getVolumeForCoin("bitcoin", "08-09-2021");
		
		System.out.println("Bitcoin=>\tPrice: " + price + 
								"\n\t\tMarket Cap: " + marketCap + 
								"\n\t\tVolume: "+volume);
		
	}
}
