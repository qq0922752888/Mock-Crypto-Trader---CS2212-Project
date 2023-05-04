package cryptoTrader.controllers.loginController;

import java.io.IOException;

import cryptoTrader.models.loginModel.LoginModel;
/**
 * This class is coded to link LoginModel and LoginView classes in Controller system.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 * This class implements RequestUserLogin class as an interface.
 */
public class LoginController implements RequestUserLogin {
	
	private static LoginController instance; 
	/**
	 * If there is no instance created before, this method creates a new LoginController object, and returns it as instance.
	 * @return instance, the LoginController object.
	 */
	public static LoginController getInstance() {
		if (instance == null)
			instance = new LoginController();

		return instance;
	}
	/**
	 * This is a constructor method.
	 * This method generates a dictionary using the PasswordAuthenticationDatabase.
	 * Therefore, this dictionary will store the valid usernames and passwords.
	 * @throws IOException, if the file provided for the PasswordAuthenticationDatabase cannot be opened.
	 */
	public LoginController() {
		try {
			LoginModel.getInstance().generateDictionary();
		} catch (IOException e) {
			System.out.println("Failed to generate dictionary.");
		}
	}
	/**
	 * This method compares the entered username and password with the login information in the dictionary. 
	 * (the dictionary that stores the valid login information)
	 * @param username, the username that is entered in the Login window.
	 * @param password, the password that is entered in the Login window.
	 */
	@Override
	public void authenticateUser(String username, String password) {
		
		LoginModel.getInstance().authenticateDictionary(username, password);
	}
}
