package issPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GraphicsEnvironment;

	public class AlignmentWindow extends JFrame {

		// Create Frame and Panel
		JFrame AlignmentBox = new JFrame("ISS Docking Alignment");
		JPanel AlignmentPanel = new JPanel(new GridBagLayout());
		
		// JLabels
		JLabel ApproachDistanceLabel = new JLabel("Approach Distance");
		JLabel XOffsetLabel = new JLabel("X Offset");
		JLabel YOffsetLabel = new JLabel ("Y Offset");

		static JLabel ApproachDistanceValue = new JLabel("null");
		static JLabel XOffsetValue = new JLabel("null");
		static JLabel YOffsetValue = new JLabel ("null");
		static JLabel AlignmentIndicator = new JLabel ("<< ALIGNMENT NOT YET ACHIEVED >>");
		
		// Public Class
		public AlignmentWindow() {
		
			AlignmentBox.setSize(450,450);
			AlignmentBox.getContentPane().setBackground(Color.black);
			
			// Simulation Panel initial settings
			AlignmentBox.add(AlignmentPanel);
			AlignmentPanel.setPreferredSize(new Dimension(400,400));
			AlignmentPanel.setBackground(Color.black);
		
			// Initial GridBagLayout Setup
			AlignmentBox.setLayout(new GridBagLayout());
			GridBagConstraints con = new GridBagConstraints();
			con.weightx = 0.75;
			con.insets = new Insets(25, 25, 25, 25);
			//con.ipadx = 100;
			//con.ipady = 15;
			con.gridwidth = 3;
			
			// Approach Distance Label
			con.gridx = 0;
			con.gridy = 0;
			con.anchor = GridBagConstraints.WEST;
			AlignmentPanel.add(ApproachDistanceLabel, con);
			ApproachDistanceLabel.setForeground(Color.green);
			ApproachDistanceLabel.setFont(new Font("Courier", Font.BOLD, 17));
			
			// Approach Distance Value
			con.gridx = 1;
			con.gridy = 0;
			con.anchor = GridBagConstraints.EAST;
			AlignmentPanel.add(ApproachDistanceValue, con);
			ApproachDistanceValue.setForeground(Color.red);
			ApproachDistanceValue.setFont(new Font("Courier", Font.BOLD, 17));
			
			// XOffset Label
			con.gridx = 0;
			con.gridy = 1;
			con.anchor = GridBagConstraints.WEST;
			AlignmentPanel.add(XOffsetLabel, con);
			XOffsetLabel.setForeground(Color.green);
			XOffsetLabel.setFont(new Font("Courier", Font.BOLD, 17));
			
			// XOffset Value
			con.gridx = 1;
			con.gridy = 1;
			con.anchor = GridBagConstraints.EAST;
			AlignmentPanel.add(XOffsetValue, con);
			XOffsetValue.setForeground(Color.red);
			XOffsetValue.setFont(new Font("Courier", Font.BOLD, 17));
			
			// YOffset Label
			con.gridx = 0;
			con.gridy = 2;
			con.anchor = GridBagConstraints.WEST;
			AlignmentPanel.add(YOffsetLabel, con);
			YOffsetLabel.setForeground(Color.green);	
			YOffsetLabel.setFont(new Font("Courier", Font.BOLD, 17));
			
			// YOffset Value
			con.gridx = 1;
			con.gridy = 2;
			con.anchor = GridBagConstraints.EAST;
			AlignmentPanel.add(YOffsetValue, con);
			YOffsetValue.setForeground(Color.red);
			YOffsetValue.setFont(new Font("Courier", Font.BOLD, 17));
			
			// Alignment Achieved/NotAchieved Label
			con.gridx = 0;
			con.gridy = 3;
			con.anchor = GridBagConstraints.CENTER;
			AlignmentPanel.add(AlignmentIndicator, con);
			AlignmentIndicator.setForeground(Color.red);
			AlignmentIndicator.setFont(new Font("Courier", Font.BOLD, 17));
		
			// JPanel Settings
			AlignmentBox.setResizable(false);
			AlignmentBox.setLocationRelativeTo(null);
			AlignmentBox.setVisible(true);
			AlignmentBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
		public static void randomNum() {
			
			// Generate random approach value number
			int randomApproach = (int) (Math.random() * ((250 - 100) + 1) + 100);
			
			// Sets Approach Value to the converted string of Random Approach
			ApproachDistanceValue.setText(String.valueOf(randomApproach));
			
			// Generate random XOffset value
			int randomXOffset = (int) (-20 + (Math.random() * (41)));
			
			// Sets XOffsetValue to the converted string of Random X Offset
			XOffsetValue.setText(String.valueOf(randomXOffset));
			
			// Generate random YOffset Value
			int randomYOffset = (int) (-15 + (Math.random() * (31)));
			
			// Sets YValue to the converted string of Random Y Offset
			YOffsetValue.setText(String.valueOf(randomYOffset));
			
			// Initializes the Graphics Window
			GraphicsWindow.graphicsInitialize(randomXOffset, randomYOffset);
		}
		
		public static void upButtonPress() {
			
			// Create temporary ints to hold values.
			int tempXOffset = Integer.parseInt(XOffsetValue.getText());
			int tempYOffset = Integer.parseInt(YOffsetValue.getText());
			int tempApproach = Integer.parseInt(ApproachDistanceValue.getText());
			
			System.out.println(tempYOffset);
			
			// Add 1 to Offset Value and Subtract 10 from Approach Distance
			tempYOffset++;
			tempApproach = tempApproach - 10;
			
			// Assign these values back to JLabels
			YOffsetValue.setText(String.valueOf(tempYOffset));
			ApproachDistanceValue.setText(String.valueOf(tempApproach));
			
			// Runs a check to see if alignment has been reached
			alignmentCheck(tempApproach, tempXOffset, tempYOffset);
			
		}
		
		public static void downButtonPress() {
			
			// Create temporary ints to hold values.
			int tempXOffset = Integer.parseInt(XOffsetValue.getText());
			int tempYOffset = Integer.parseInt(YOffsetValue.getText());
			int tempApproach = Integer.parseInt(ApproachDistanceValue.getText());
			
			System.out.println(tempYOffset);
			
			// Subtract 1 from Offset Value and Subtract 10 from Approach Distance
			tempYOffset--;
			tempApproach = tempApproach - 10;
			
			// Assign these values back to JLabels
			YOffsetValue.setText(String.valueOf(tempYOffset));
			ApproachDistanceValue.setText(String.valueOf(tempApproach));
			
			// Runs a check to see if alignment has been reached
			alignmentCheck(tempApproach, tempXOffset, tempYOffset);
			
		}
		
		public static void leftButtonPress() {
			
			// Create temporary ints to hold values.
			int tempXOffset = Integer.parseInt(XOffsetValue.getText());
			int tempYOffset = Integer.parseInt(YOffsetValue.getText());
			int tempApproach = Integer.parseInt(ApproachDistanceValue.getText());
			
			System.out.println(tempXOffset);
			
			// Add 1 to Offset Value and Subtract 10 from Approach Distance
			tempXOffset--;
			tempApproach = tempApproach - 10;
			
			// Assign these values back to JLabels
			XOffsetValue.setText(String.valueOf(tempXOffset));
			ApproachDistanceValue.setText(String.valueOf(tempApproach));
			
			// Runs a check to see if alignment has been reached
			alignmentCheck(tempApproach, tempXOffset, tempYOffset);
			
		}
		
		public static void rightButtonPress() {
			
			// Create temporary ints to hold values.
			int tempXOffset = Integer.parseInt(XOffsetValue.getText());
			int tempYOffset = Integer.parseInt(YOffsetValue.getText());
			int tempApproach = Integer.parseInt(ApproachDistanceValue.getText());
			
			System.out.println(tempXOffset);
			
			// Add 1 to Offset Value and Subtract 10 from Approach Distance
			tempXOffset++;
			tempApproach = tempApproach - 10;
			
			// Assign these values back to JLabels
			XOffsetValue.setText(String.valueOf(tempXOffset));
			ApproachDistanceValue.setText(String.valueOf(tempApproach));
			
			// Runs a check to see if alignment has been reached
			alignmentCheck(tempApproach, tempXOffset, tempYOffset);
			
		}
		
		public static void resetAlignmentLabel() {
			
			// Resets label back to default
			AlignmentIndicator.setForeground(Color.red);
			AlignmentIndicator.setText("<< ALIGNMENT NOT YET ACHIEVED >>");
			
			
		}
		
		public static void alignmentCheck(int approach, int xOffset, int yOffset) {
			
			if (approach < 0) { // If Approach Distance is less than 0
				
				if (xOffset == 0 && yOffset == 0) { // If Alignment has been reached
					
					// Update alignment indicator
					AlignmentIndicator.setText("<< ALIGNMENT REACHED >>");
					AlignmentIndicator.setForeground(Color.green);
					
					// Display success message
					JOptionPane.showMessageDialog(null, "Alignment successfully reached at the final moment!", "Alignment Success!", JOptionPane.INFORMATION_MESSAGE);
					
					// Stop Program
					SimulationBox.programStop();
					
				}
				
				else { // If Alignment has not been reached
					
					// Display failure message and stop program
					JOptionPane.showMessageDialog(null, "Alignment has not been reached", "Alignment Failure", JOptionPane.ERROR_MESSAGE);
					SimulationBox.programStop();
					
				}
			}
			
			else if (xOffset == 0 && yOffset == 0) { // If Alignment has been reached
				
				// Update alignment indicator
				AlignmentIndicator.setText("<< ALIGNMENT REACHED >>");
				AlignmentIndicator.setForeground(Color.green);
				
				// Display success message
				JOptionPane.showMessageDialog(null, "Alignment successfully reached!", "Alignment Success!", JOptionPane.INFORMATION_MESSAGE);
			
				// Stop Program
				SimulationBox.programStop();
				
			}
		}
	}
