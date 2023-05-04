package cryptoTrader.models.loginModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import cryptoTrader.views.loginView.LoginView;
import cryptoTrader.views.loginView.UpdateLoginView;
import cryptoTrader.views.mainView.MainUI;
import cryptoTrader.views.mainView.UpdateMainView;
/**
 * This class is used for the authentication of the users.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements RequestAuthentication class as an interface.
 */
// Responsible for generating a dictionary<username, password> on runtime. Validating for each login action request. 
public class LoginModel implements RequestAuthentication {
	
	private Hashtable<String, String> userAuthenticationDictionary; 
	private UpdateLoginView loginView;
	private UpdateMainView mainView;
	
	private static LoginModel instance; 
	/**
	 * If a LoginModel is not created as an instance, a new LoginModel object is created and returned.
	 * @return returns a new LoginModel object.
	 */
	public static LoginModel getInstance() {
		if (instance == null)
			instance = new LoginModel();

		return instance;
	}
	/**
	 * This method reads the database file, and generates a dictionary with usernames and passwords.
	 * @throws IOException, if the Database file cannot be read.
	 */
	public void generateDictionary() throws IOException {
		
		userAuthenticationDictionary = new Hashtable<String, String>(); 
		
		File userFile = new File("src//main//resources//PasswordAuthenticationDatabase.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
			String usernamePair; 
			while((usernamePair = br.readLine()) != null) { 
				String[] usernamePairSplit = usernamePair.split(","); 
				userAuthenticationDictionary.put(usernamePairSplit[0].trim(), usernamePairSplit[1].trim()); 
			}
		}
	}
	/**
	 * This method checks the entered username and password.
	 * This is done by comparing the entered data with the data in authentication dictionary.
	 * Following the login info check, login view is updated.
	 * @param username, the username of the broker.
	 * @param password, the password of the broker.
	 */
	@Override
	public void authenticateDictionary(String username, String password) {
		if((userAuthenticationDictionary.get(username) != null) && ((userAuthenticationDictionary.get(username)).equals(password))){
			loginView = LoginView.getInstance();
			loginView.succesfulLogin();
			loginView.closeLoginView();
			startMainUI(); 
		}
		else {
			LoginView.getInstance().unsuccesfulLogin();
		}
	}
	/**
	 * This method starts the main UI.
	 */
	private void startMainUI() {
		mainView = MainUI.getInstance();
		mainView.startMainUI();
	}
}
