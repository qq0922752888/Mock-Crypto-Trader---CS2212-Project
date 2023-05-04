package cryptoTrader.views.loginView;

/**
 * Interface providing methods to be implemented by the LoginView class for
 * updating the user on login authentication procedures.   
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 *
 */
public interface UpdateLoginView {
	/**
	 * Displays a message dialog with the contents "Successful Login".
	 */
	public void succesfulLogin(); 
	
	/**
	 * Displays a message dialog with the contents "Unsuccessful Login".
	 */
	public void unsuccesfulLogin();
	/**
	 * Closes the current Login window.
	 */
	public void closeLoginView(); 
}
