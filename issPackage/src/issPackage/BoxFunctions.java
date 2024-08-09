package issPackage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BoxFunctions {
	
	public static void guiAccess(){
		
		System.out.println("GUI.java successfully accessed.");
		
	}
	
	public static void guiLoginFrame() {
		
		JFrame loginFrame = new JFrame();
		loginFrame.setTitle("ISS Docking Simulation Login");
		loginFrame.setSize(600,500);
		loginFrame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);
		
	}
	
	public static int guiInitialDialogBox() {
		
		Object[] options = {"Cancel", "Create an account", "Login"};
		
		int selection = JOptionPane.showOptionDialog(null, 
		"ISS Docking Simulation\nPlease select an option to continue.", 
		"ISS Docking Simulation Program", 
		JOptionPane.YES_NO_CANCEL_OPTION, 
		JOptionPane.QUESTION_MESSAGE, 
		new ImageIcon("E:\\Workshop\\ISS Docking Simulation\\iss-icon.png"), 
		options, 
		options[2]);
		
		// System.out.println(selection);
		
		return selection;
	}
	
	public static boolean usernameValid(String username) {
		
		int index = 0, whiteSpace = 0;
		
		while (index < username.length()) {

			char ch = username.charAt(index);
		
			// Checks if user inputted whitespace
			if (Character.isWhitespace(ch)) {
				whiteSpace++;
			}
			
			index++;
		}

		if (whiteSpace == 0 && username.length() >= 5) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public static boolean passwordValid(String password) {
		
		int index = 0, digit = 0, upper = 0, lower = 0, whiteSpace = 0;
		
		while (index < password.length()) {

			char ch = password.charAt(index);
		
			// If statements to see the number of Digits and Uppercase/Lowercase letters
			if (Character.isDigit(ch)) {
				digit++;
			}
			
			if (Character.isUpperCase(ch)) {
				upper++;
			}
		
			if (Character.isLowerCase(ch)) {
				lower++;
			}
			
			if (Character.isWhitespace(ch)) {
				whiteSpace++;
			}
			
			index++;
		}
		
		// Test functions to see if password is being properly read
		//System.out.println("Digits " + digit); // displays Digits 3
		//System.out.println("Uppercase " + upper); // displays Uppercase 3
		//System.out.println("Lowercase " + lower); // displays Lowercase 3
	
		if (whiteSpace > 0) {
			return false;
		}
		
		else if (password.length() >= 9 && digit >= 1 && upper >= 1 && lower >= 1) {
			return true;
		}
		
		else {
			return false;
		}
	}

}

