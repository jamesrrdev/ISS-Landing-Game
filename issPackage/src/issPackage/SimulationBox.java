package issPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

	public class SimulationBox extends JFrame {

		// Create Frame and Panel
		JFrame SimulationBox = new JFrame("ISS Docking Simulation Program");
		JPanel SimulationPanel = new JPanel(new GridBagLayout());

		// Program Buttons
		JButton RunProgramButton = new JButton("RUN PROGRAM");
		static JButton UpButton = new JButton("UP");
		static JButton LeftButton = new JButton("LEFT");
		static JButton DownButton = new JButton("DOWN");
		static JButton RightButton = new JButton("RIGHT");
		JButton BlankButton = new JButton("*");
		
		JMenuBar mBar = new JMenuBar();
		JMenu runMenu = new JMenu("Run");
		JMenu helpMenu = new JMenu("Help");
				
		static JMenuItem runItem = new JMenuItem("Run Program");
		static JMenuItem stopItem = new JMenuItem("Stop Program");
		
		JMenuItem programHelpItem = new JMenuItem("Program Help");
		
		
		// Adding the Image
		File issPicture = new File("ISSMain.jpg");
		BufferedImage issPic; {

			try {
				issPic = ImageIO.read(issPicture);
			}

			catch (IOException ex) {
				System.out.println("File cannot be found");
				ex.printStackTrace();
			}
		}

		// Label for Picture
		JLabel issPicLabel = new JLabel(new ImageIcon(issPic));


		// Public Class
		public SimulationBox() {
		
			SimulationBox.setSize(770,700);
			
			// Simulation Panel initial settings
			SimulationBox.add(SimulationPanel);
			SimulationPanel.setPreferredSize(new Dimension(900,700));
			SimulationPanel.setBackground(Color.black);
			
			// Add JMenus
			SimulationBox.setJMenuBar(mBar);
			
			runMenu.add(runItem);
			runMenu.add(stopItem);
			
			helpMenu.add(programHelpItem);
			
			mBar.add(runMenu);
			mBar.add(helpMenu);
			
			runItem.addActionListener(new ProgramRun());
			stopItem.addActionListener(new UserProgramStop());
			programHelpItem.addActionListener(new ProgramHelp());
			
			// Initial GridBagLayout Setup
			SimulationBox.setLayout(new GridBagLayout());
			GridBagConstraints con = new GridBagConstraints();
			con.insets = new Insets(5,5,20,5);
			con.ipadx = 100;
			con.ipady = 15;
			con.gridwidth = 3;
		
			// ISS Picture
			con.gridx = 0;
			con.gridy = 0;
			//con.fill = GridBagConstraints.BOTH;
			//con.anchor = GridBagConstraints.CENTER;
			SimulationPanel.add(issPicLabel, con);
			
			// Up Button
			con.gridx = 0;
			con.gridy = 1;
			con.anchor = GridBagConstraints.NORTH;
			SimulationPanel.add(UpButton, con);
			UpButton.setBackground(Color.black);
			UpButton.setForeground(Color.white);
			
			// Left Button
			con.gridx = 0;
			con.gridy = 2;
			con.anchor = GridBagConstraints.WEST;
			SimulationPanel.add(LeftButton, con);
			LeftButton.setBackground(Color.black);
			LeftButton.setForeground(Color.white);
			
			// Blank Button in Middle
			con.gridx = 1;
			con.gridy = 2;
			con.anchor = GridBagConstraints.CENTER;
			SimulationPanel.add(BlankButton, con);
			BlankButton.setBackground(Color.black);
			BlankButton.setForeground(Color.white);
			
			// Right Button
			con.gridx = 2;
			con.gridy = 2;
			con.anchor = GridBagConstraints.EAST;
			SimulationPanel.add(RightButton, con);
			RightButton.setBackground(Color.black);
			RightButton.setForeground(Color.white);
			
			// Down Button
			con.gridx = 0;
			con.gridy = 3;
			con.anchor = GridBagConstraints.SOUTH;
			SimulationPanel.add(DownButton, con);
			DownButton.setBackground(Color.black);
			DownButton.setForeground(Color.white);
			
			// Run Program Button (Most likely unneeded, JMenu used instead)
			/*con.weighty = 50;
			con.gridx = 0;
			con.gridy = 4;
			con.ipadx = 5;
			con.ipady = 5;
			con.insets = new Insets(5,5,50,5);
			con.anchor = GridBagConstraints.SOUTH;
			SimulationPanel.add(RunProgramButton, con); */
		
			// JPanel Settings
			SimulationBox.setResizable(false);
			SimulationBox.setLocationRelativeTo(null);
			SimulationBox.setVisible(true);
			SimulationBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			
			// Add action listeners to buttons
			UpButton.addActionListener(new UpButtonListener());
			DownButton.addActionListener(new DownButtonListener());
			LeftButton.addActionListener(new LeftButtonListener());
			RightButton.addActionListener(new RightButtonListener());
			
			// Input buttons start disabled
			UpButton.setEnabled(false);
			DownButton.setEnabled(false);
			LeftButton.setEnabled(false);
			RightButton.setEnabled(false);
			
			// Stop Program Button starts disabled
			stopItem.setEnabled(false);
		}
		
		class ProgramRun implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {

				AlignmentWindow.randomNum(); // Generates random values.
				
				// Call reset alignment label method
				AlignmentWindow.resetAlignmentLabel();
				
				JOptionPane.showMessageDialog(null, "Program running... Please refer to Alignment Window", "Successful Run", JOptionPane.INFORMATION_MESSAGE);
				
				runItem.setEnabled(false); // Disables Run button
				stopItem.setEnabled(true); // Enables Stop Button
				
				// Enable input buttons
				UpButton.setEnabled(true);
				DownButton.setEnabled(true);
				LeftButton.setEnabled(true);
				RightButton.setEnabled(true);
				
			}
			
		}
		
		class UserProgramStop implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {

				programStop(); // Call Program Stop Method
				
				// Inform user that the program stopped
				JOptionPane.showMessageDialog(null, "Program stopping...", "Stop Program", JOptionPane.WARNING_MESSAGE);
				
			}
			
		}
		
		class ProgramHelp implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog (
				null, 
				"Hello, welcome to the ISS Docking Simulation Program."
				+ "\n\n-To run the program, hover over 'Run' on the top left of the window and click 'Run Program'"
				+ "\n\n-When run, the program will generate random values for Approach Distance, X Offset, and Y Offset on the Alignment window"
				+ "\n\n-Refer to the buttons on the main program to adjust the X Offset and Y Offset, both values should be 0 before the Approach Distance is 0."
				+ "\n\n-Also refer to the 'ISS Docking View' window to see where your alignment currently is."
				+ "\n\n-For every button input, the Approach Distance will go down by 10.", 
				"Program Help",
				JOptionPane.PLAIN_MESSAGE
				);
				
			}
			
		}	
		
		private class UpButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				int upButton = 1;
				
				GraphicsWindow.updateGraphics(upButton); // Call method to update Graphic Window
				AlignmentWindow.upButtonPress(); // Call method to update Alignment Window
				
			}
		}
		
		private class DownButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				int downButton = 2;
				
				GraphicsWindow.updateGraphics(downButton); // Call method to update Graphic Window
				AlignmentWindow.downButtonPress(); // Call method to update Alignment Window
				
			}
		}
		
		private class LeftButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				int leftButton = 3;
				
				GraphicsWindow.updateGraphics(leftButton); // Call method to update Graphic Window
				AlignmentWindow.leftButtonPress(); // Call method to update Alignment Window

			}
		}
		
		private class RightButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				int rightButton = 4;
				
				GraphicsWindow.updateGraphics(rightButton); // Call method to update Graphic Window
				AlignmentWindow.rightButtonPress(); // Call method to update Alignment Window
				
			}
		}
		
		public static void programStop() {
			
			runItem.setEnabled(true); // Enables Run button
			stopItem.setEnabled(false); // Disables Stop Button
			
			// Enable input buttons
			UpButton.setEnabled(false);
			DownButton.setEnabled(false);
			LeftButton.setEnabled(false);
			RightButton.setEnabled(false);
			
		}
	}

