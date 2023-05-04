package cryptoTrader.controllers.loginController;

/**
 * Interface provided for authenticating at login.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface RequestUserLogin {
	
	/**
	 * This method compares the entered username and password with the login information in the dictionary. 
	 * (the dictionary that stores the valid login information)
	 * @param username, the username that is entered in the Login window.
	 * @param password, the password that is entered in the Login window.
	 */
	public void authenticateUser(String username, String password); 
}
