package issPackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

	public class Login extends JFrame {

	JFrame LoginGUI = new JFrame("ISS Docking Simulation Program");
	JPanel LoginPanel = new JPanel(new GridBagLayout());
	JLabel userNameLabel = new JLabel("Enter your username...: ");
	JLabel passWordLabel1 = new JLabel("Enter your password...: ");
	JTextField textFieldUserName = new JTextField(10);
	JPasswordField textFieldPassword = new JPasswordField(10);
	JButton LoginButton = new JButton("Login");
	//JButton createCancelButton = new JButton("Cancel");
	int passwordAttempts = 0;

		public Login() {
			
			LoginGUI.setSize(400,300);
			GridBagConstraints con = new GridBagConstraints();
			con.insets = new Insets(1,1,10,1);
			
			// Label for Username
			con.gridx = 0;
			con.gridy = 0;
			con.anchor = GridBagConstraints.WEST;
			LoginPanel.add(userNameLabel, con);
			
			// Text field for Username
			con.gridx = 1;
			con.anchor = GridBagConstraints.EAST;
			LoginPanel.add(textFieldUserName, con);
			textFieldUserName.setHorizontalAlignment(JTextField.RIGHT);
			
			// Label for Password
			con.gridx = 0;
			con.gridy = 1;
			con.anchor = GridBagConstraints.WEST;
			LoginPanel.add(passWordLabel1, con);
			
			// Text field for password
			con.gridx = 1;
			con.anchor = GridBagConstraints.EAST;
			LoginPanel.add(textFieldPassword, con);
			textFieldPassword.setHorizontalAlignment(JTextField.RIGHT);
			textFieldPassword.setEchoChar('*');
			
			// Cancel Button
			//con.gridx = 0;
			//con.gridy = 2;
			//con.anchor = GridBagConstraints.WEST;
			//LoginPanel.add(createCancelButton, con);
			
			// Login Button
			con.gridx = 1;
			con.gridy = 2;
			con.anchor = GridBagConstraints.EAST;
			LoginPanel.add(LoginButton, con);
			
			// Titled Border
			LoginPanel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), "ISS Docking Simulation - Login"));
			
			// JPanel Settings
			LoginGUI.add(LoginPanel);
			LoginGUI.setResizable(false);
			LoginGUI.setLocationRelativeTo(null);
			LoginGUI.setVisible(true);
			LoginGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Declare Login Button Listener
			LoginButton.addActionListener(new LoginButtonListener());
		}
		
		private class LoginButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				// User is not timed out.
				if (passwordAttempts < 5) {
					
					// Variable for printing remaining attempts.
					int remainingAttempts = 4 - passwordAttempts;
					
					// Declare Username and Password Variables
					String username = textFieldUserName.getText();
					char[] password = textFieldPassword.getPassword();
				
					// Assigns the line number of the password in the file.
					int lineNum = FileReading.checkLoginPassword(password);
				
					// Checks Username at assigned line number to see if it matches.
					boolean validLogin = FileReading.checkLoginUsername(lineNum, username);
				
					// Valid Login
					if (validLogin == true) {
						JOptionPane.showMessageDialog(null, "Successful login, proceeding to program...", "Login Successful!", JOptionPane.INFORMATION_MESSAGE);
												
						LoginGUI.dispose();
						
						new SimulationBox();
						new AlignmentWindow();
						new GraphicsWindow();
					}
				
					// Invalid Login
					else {
						
						if (passwordAttempts == 4) {
							JOptionPane.showMessageDialog(null, "Too many invalid login attempts. Please try again later.", "Time out", JOptionPane.ERROR_MESSAGE);
						}
								
						else {
							JOptionPane.showMessageDialog(null, "Invalid username or password. " + remainingAttempts + " login attempt(s) remaining.", "Invalid login", JOptionPane.WARNING_MESSAGE);
						}
					}
					
					passwordAttempts++;
				}
				
				// User is timed out.
				else {
					JOptionPane.showMessageDialog(null, "Too many invalid login attempts. Please try again later.", "Time out", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
