package cryptoTrader.controllers.startProgramController;

import cryptoTrader.views.loginView.LoginView;

/**
 * Class to start the program. Contain the main method which
 * initializes the login window.
 * @author wesley
 */
public class StartProgram {
	
	/**
	 * Main method for program
	 * @param args Program arguements (not required)
	 */
	public static void main(String[] args) {
		// Calls the initializeLoginWindow from the LoginView instance.
		LoginView.getInstance().initializeLoginWindow();
	}
}
