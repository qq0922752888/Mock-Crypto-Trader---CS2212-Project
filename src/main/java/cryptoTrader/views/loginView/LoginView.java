package cryptoTrader.views.loginView;

import javax.swing.*;
import cryptoTrader.controllers.loginController.LoginController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

/**
 * Singleton class representing the LoginView and extending from JFrame. Implements the ActionListener
 * and UpdateLoginView interfaces. Creates a login window requiring user name and password inputs to be
 * authenticated by the LoginController upon pressing "Login" button.
 * @author Yakup Tezcan, Wesley James Fortin, Reginald Pierre Harder, TSUNG-YING TSAI
 */
public class LoginView extends JFrame implements ActionListener, UpdateLoginView {
	
	private static final long serialVersionUID = 1L;
	
	// Singleton instance variable
	private static LoginView instance;  
	
	// Member variables 
	private static JLabel passwordLabel;
	private static JLabel label; 
	private static JTextField username; 
	private static JButton button; 
	private static JPasswordField passwordField;
	private static JFrame frame;
	
	/**
	 * If there is no instance, creates a instance of LoginView
	 * @return	The singleton instance of a LoginView
	 */
	public static LoginView getInstance() {
		if (instance == null)
			instance = new LoginView();

		return instance;
	}
		
	/**
	 * Creates the login window with a user name and password field.
	 * Login button authenticates with the database and either sends the user
	 * to the main UI or displays an unsuccessful login.
	 */
	public void initializeLoginWindow() {
		
		// Set up JFrame and panel
		frame = new JFrame(); 
		frame.setTitle("Login Window");
		frame.setSize(new Dimension(400, 200));
		frame.setPreferredSize(new Dimension(400, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.add(panel); 
		
		// Set up user name label
		label = new JLabel("Username"); 
		label.setBounds(100, 10, 70, 20);
		panel.add(label); 
		
		// Set up user name text field 
		username = new JTextField(); 
		username.setBounds(100, 30, 190, 30);
		panel.add(username); 
		
		// Set up password Label
		passwordLabel = new JLabel("Password"); 
		passwordLabel.setBounds(100, 55, 70, 20);
		panel.add(passwordLabel); 
		
		// Set up password text field
		passwordField = new JPasswordField(); 
		passwordField.setBounds(100, 75, 190, 30);
		panel.add(passwordField); 
		
		// Set up Login button
		button = new JButton("Login"); 
		button.setBounds(100, 110, 90, 25);
		button.addActionListener((ActionListener) new LoginView());
		panel.add(button); 
		
		frame.pack(); 
		frame.setVisible(true);
	}
	
	
	/**
	 * Overrides from ActionListener interface. When a user presses the Login 
	 * button this method interacts with the LoginController for authentication.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Login")) {
			String username = this.username.getText(); 
			String password = this.passwordField.getText(); 
			
			LoginController.getInstance().authenticateUser(username, password);
		}
		else {
			System.out.println("Error no such event.");
		}
	}
	
	@Override
	public void succesfulLogin() {
		JOptionPane.showMessageDialog(null, "Login Successful.");
	}
	
	@Override
	public void unsuccesfulLogin() {
		JOptionPane.showMessageDialog(null, "Username or Password incorrect.");
		System.exit(0); //Safe exit upon error input
		
	}
	
	@Override
	public void closeLoginView() {
		frame.dispose(); 
	} 
}
