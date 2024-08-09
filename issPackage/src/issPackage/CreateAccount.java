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
import javax.swing.JTextField;

	public class CreateAccount extends JFrame {

	JFrame createAcctGUI = new JFrame("ISS Docking Simulation Program");
	JPanel createAcctPanel = new JPanel(new GridBagLayout());
	JLabel passwordInfoLabel = new JLabel("Requires at least - One uppercase and lowercase letter, One digit...: ");
	JLabel userNameLabel = new JLabel("Enter a username (At least 5 characters): ");
	JLabel passWordLabel1 = new JLabel("Create a password (At least 9 characters; No whitespace)");
	JLabel passWordLabelConfirm = new JLabel("Confirm your password: ");
	JTextField textFieldUserName = new JTextField(10);
	JTextField textFieldPassword = new JTextField(10);
	JTextField textFieldConfirmPassword = new JTextField(10);
	private JButton createAcctButton = new JButton("Create Account");
	
	
	JButton createCancelButton = new JButton("Cancel");

		public CreateAccount(){
			
			createAcctGUI.setSize(600,300);
			GridBagConstraints con = new GridBagConstraints();
			con.insets = new Insets(1,1,10,1);
			
			// Label for Username
			con.gridx = 0;
			con.gridy = 0;
			con.anchor = GridBagConstraints.WEST;
			createAcctPanel.add(userNameLabel, con);
			
			// Text field for Username
			con.gridx = 1;
			con.anchor = GridBagConstraints.EAST;
			createAcctPanel.add(textFieldUserName, con);
			textFieldUserName.setHorizontalAlignment(JTextField.RIGHT);
			
			// Label for Password
			con.gridx = 0;
			con.gridy = 1;
			con.anchor = GridBagConstraints.WEST;
			createAcctPanel.add(passWordLabel1, con);
			
			// Password Requirements Label
			con.gridx = 0;
			con.gridy = 2;
			createAcctPanel.add(passwordInfoLabel, con);
			
			// Text field for password
			con.gridx = 1;
			con.anchor = GridBagConstraints.EAST;
			createAcctPanel.add(textFieldPassword, con);
			textFieldPassword.setHorizontalAlignment(JTextField.RIGHT);
			
			// Label for Confirm Password
			con.gridx = 0;
			con.gridy = 3;
			con.anchor = GridBagConstraints.WEST;
			createAcctPanel.add(passWordLabelConfirm, con);
			
			// Text field for Confirm Password
			con.gridx = 1;
			con.anchor = GridBagConstraints.EAST;
			createAcctPanel.add(textFieldConfirmPassword, con);
			textFieldConfirmPassword.setHorizontalAlignment(JTextField.RIGHT);
			
			// Create Account Button
			con.gridx = 1;
			con.gridy = 4;
			con.anchor = GridBagConstraints.EAST;
			createAcctPanel.add(createAcctButton, con);
			
			// Titled Border
			createAcctPanel.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), "ISS Docking Simulation - Create Account"));
			
			// JPanel Settings
			createAcctGUI.add(createAcctPanel);
			createAcctGUI.setResizable(false);
			createAcctGUI.setLocationRelativeTo(null);
			createAcctGUI.setVisible(true);
			createAcctGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			createAcctButton.addActionListener(new CreateAcctButtonListener()); 
		}
		
		private class CreateAcctButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				// Declare user inputted strings
				String username = textFieldUserName.getText();
				String password = textFieldPassword.getText();
				String confirmPassword = textFieldConfirmPassword.getText();
				
				System.out.println(password);
				
				// Checks if username is valid
				boolean validUsername = BoxFunctions.usernameValid(username);
				
				// If the username is 5 characters and has no whitespace, program proceeds
				if(validUsername == true) {
				
					// Checks if Confirm Password matches with Password.
					if(confirmPassword.equals(password)) {
					
						// Call boolean variables and functions to check if password is valid.
						boolean validPassword = BoxFunctions.passwordValid(password);
						boolean duplicatePassword = FileReading.validateAndWriteToPassword(password);
		
						// If password is valid and is not a duplicate, program proceeds
						if (validPassword == true && duplicatePassword == false) {
							System.out.println("The password is valid");
							FileReading.writeToUsername(username);
							JOptionPane.showMessageDialog(null, "Username and password are valid, proceeding to program...", "Account Creation Successful!", JOptionPane.PLAIN_MESSAGE);
							
							createAcctGUI.dispose();
							
							new SimulationBox();
							new AlignmentWindow();
							new GraphicsWindow();
						}
				
						// 4 Else statements to find what error the password has and display error message accordingly
						else if (validPassword == false) {
							System.out.println("The password is invalid.");
							JOptionPane.showMessageDialog(null, "Password is invalid. Try again.", "Invalid Password", JOptionPane.WARNING_MESSAGE);
						}
				
						else if (duplicatePassword == true) {
							System.out.println("The password is a duplicate.");
							JOptionPane.showMessageDialog(null, "Password is already in use, enter a new password", "Duplicate Password", JOptionPane.WARNING_MESSAGE);
						}
					
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Password does not match with confirm password, try again", "Passwords don't match", JOptionPane.WARNING_MESSAGE);
					}
				
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Username contains white space or is not a least 5 characters", "Invalid Username", JOptionPane.WARNING_MESSAGE);
				}
				
			}

		}
	}

