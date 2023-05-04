package cryptoTrader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * This class stores available crpytocoin list.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 */
public class AvailableCryptoList {
	private static AvailableCryptoList instance = null;
	
	private Map<String, String> availableCryptosMap = new HashMap<>();
	private List<String> availableCryptosList = new ArrayList<>();
	/**
	 * If there is no instance created before, this method creates a new AvailableCryptoList object, and returns it as instance.
	 * @return instance, the AvailableCryptoList object.
	 */
	public static AvailableCryptoList getInstance() {
		if (instance == null)
			instance = new AvailableCryptoList();
		
		return instance;
	}
	/**
	 * This method calls findAvailableCrpytos method.
	 */
	private AvailableCryptoList() {
		findAvailableCryptos();
	}
	/**
	 * This method uses an API to access the available crypto data.
	 * Catches IOExeption if API does not work.
	 */
	public void call() {
		String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=VNEY4VV2AWF1EB51";
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
				System.out.println(inline);
//				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
//				int size = jsonArray.size();
//				
//				String name, id;
//				for (int i = 0; i < size; i++) {
//					JsonObject object = jsonArray.get(i).getAsJsonObject();
//					name = object.get("name").getAsString();
//					id = object.get("id").getAsString();
//					
//					availableCryptosMap.put(name, id);
//					availableCryptosList.add(name);
//				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	/**
	 * Finds available cryto list by using coingecko API.
	 * Available crpytos will be added to dictionary with their name and id.
	 * Catches IOException if API does not work.
	 */
	private void findAvailableCryptos() {

		String urlString = 
				"https://api.coingecko.com/api/v3/coins/markets" + 
						"?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
//		ALPHAVANTAGE API KEY = VNEY4VV2AWF1EB51
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
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				
				String name, id;
				for (int i = 0; i < size; i++) {
					JsonObject object = jsonArray.get(i).getAsJsonObject();
					name = object.get("name").getAsString();
					id = object.get("id").getAsString();
					
					availableCryptosMap.put(name, id);
					availableCryptosList.add(name);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
	}
	/**
	 * Getter method for he crypto list.
	 * @return the available crypto list.
	 */
	public String[] getAvailableCryptos() {
		return availableCryptosList.toArray(new String[availableCryptosList.size()]);
	}
	/**
	 * Getter method for crypto id.
	 * @param cryptoName, name of the crpyto coin.
	 * @return returns the id of the crypto coin.
	 */
	public String getCryptoID(String cryptoName) {
		return availableCryptosMap.get(cryptoName);
	}

}
