package issPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {
	
	final static String USERNAME_FILE_PATH = "UsernameInput.txt";
	final static String PASSWORD_FILE_PATH = "PasswordInput.txt";
	
	// Checks if Password is valid. If so, it will write the password to the file.
	public static boolean validateAndWriteToPassword(String password) {
		
	
		File inputFile = new File(PASSWORD_FILE_PATH);
		
		boolean duplicate = false;
		ArrayList<String> passwordList = new ArrayList<String>();
		
		
		try { // Try block
			Scanner in = new Scanner(inputFile);
			//PrintWriter out = new PrintWriter("E:\\Workshop\\ISS Docking Simulation\\PasswordInput.txt");
			
			// While statement to read each line of the file
			while (in.hasNextLine()) {
				String line = in.nextLine();
				//System.out.println(line);
				passwordList.add(line);
				
				// Finds if the line is the same as the password
				if (line.equals(password)) {
					duplicate = true;
				}
			}
			
			// Adds password to Array if it is not a duplicate
			if (duplicate == false) {
				passwordList.add(password);
			}
			
			// Declare printwriter
			PrintWriter out = new PrintWriter(PASSWORD_FILE_PATH);
			
			// Reads each line of the array and writes to the file
			for (String name : passwordList) {
				//System.out.println(name);
				out.println(name);
			}
				
			// Close Scanner and Printwriter
			in.close();
			out.close();
			
			
		}
		
		catch (FileNotFoundException e) { // catch block
			System.out.println("File cannot be found");
			e.printStackTrace();
		}
		
		//System.out.println(duplicate);
		
		if (duplicate == false) {
			return false;
		}
		
		else {
			return true;
		}
		
		
	}
	
	// Writes username to file.
	public static void writeToUsername(String username) {
		
		
		File inputFile = new File(USERNAME_FILE_PATH);
		
		ArrayList<String> usernameList = new ArrayList<String>();
		
		
		try { // Try block
			Scanner in = new Scanner(inputFile);
			
			// While statement to read each line of the file
			while (in.hasNextLine()) {
				String line = in.nextLine();
				usernameList.add(line);
			}
			
			// Adds username to Array
			usernameList.add(username);
			
			// Declare printwriter
			PrintWriter out = new PrintWriter(USERNAME_FILE_PATH);
			
			// Reads each line of the array and writes to the file
			for (String name : usernameList) {
				//System.out.println(name);
				out.println(name);
			}
				
			// Close Scanner and Printwriter
			in.close();
			out.close();
			
			
		}
		
		catch (FileNotFoundException e) { // catch block
			System.out.println("File cannot be found");
			e.printStackTrace();
		}
	}
	
	// Checks if password is valid
	public static int checkLoginPassword(char[] password) {
		
		File inputFile = new File(PASSWORD_FILE_PATH);
		
		// Convert password char array to string.
		String passwordString = new String(password);
		
		int index = 0;
		
		try {
			
			Scanner in = new Scanner(inputFile);
			
			// Read file.
			while (in.hasNextLine()) {
				String line = in.nextLine();
				
				if (line.equals(passwordString)) { // If matching, returns the line number of the password in the file
					return index;
				}
				
				index++;
				
			}
			
			in.close();
		} 

		catch (FileNotFoundException e) { // catch block
			System.out.println("File cannot be found");
			e.printStackTrace();
		}
		

		// Return an impossible line value if no matching password is found.
		return -1;
	}
	
	// Checks if Username matches with the Password's line number
	public static boolean checkLoginUsername(int lineNum, String username) {
		
		File inputFile = new File(USERNAME_FILE_PATH);
		
		int index = 0;
		
		try {
			Scanner in = new Scanner(inputFile);
			
			// Reads file and finds if username is matching with the login information at the correct Line Number.
			while (in.hasNextLine()) {
				String line = in.nextLine();
				
				if (index == lineNum) {
					
					if (line.equals(username)) {
						return true;
					}
				}
				
				index++;
			}
			
			in.close();
		} 

		catch (FileNotFoundException e) { // catch block
			System.out.println("File cannot be found");
			e.printStackTrace();
		}
		

		
		return false;
	}

}
