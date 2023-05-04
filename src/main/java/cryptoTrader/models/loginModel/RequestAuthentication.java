package cryptoTrader.models.loginModel;

/**
 * Interface for requesting user authentication from the controller. 
 * Provides the authenticateDictionary method. 
 * @author wesley
 *
 */
public interface RequestAuthentication {
	/**
	 * This method checks the entered username and password.
	 * This is done by comparing the entered data with the data in authentication dictionary.
	 * Following the login info check, login view is updated.
	 * @param username, the username of the broker.
	 * @param password, the password of the broker.
	 */
	public void authenticateDictionary(String username, String password); 
}
