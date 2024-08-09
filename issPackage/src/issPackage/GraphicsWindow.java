package issPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsWindow extends JFrame {
	
	JFrame GraphicsFrame = new JFrame("ISS Docking View");
	
	static int x = 178;
	static int y = 174;
	
	static JComponent component = new JComponent() {
		public void paintComponent(Graphics g) {
			
			// Draw X Y Lines
			g.setColor(Color.GREEN);
			g.drawLine(20, 180, 360, 180);
			g.drawLine(185, 20, 185, 340);
			
			// Draw proximity ovals
			g.drawOval(165, 160, 40, 40);
			g.drawOval(125, 120, 120, 120);
			g.drawOval(85, 80, 200, 200);
			
			// Draw ISS Indicator
			g.setColor(Color.RED);
			g.fillRect(x, y, 15, 15);
			
					
		}
	}; // end of JComponent
	
	public GraphicsWindow() {
		
		GraphicsFrame.setSize(400, 400);
		GraphicsFrame.getContentPane().setBackground(Color.black);
		
		
		GraphicsFrame.add(component);
		
		// JFame Settings
		GraphicsFrame.setResizable(false);
		GraphicsFrame.setLocationRelativeTo(null);
		GraphicsFrame.setVisible(true);
		GraphicsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void graphicsInitialize(int randX, int randY) {
		
		// Resets X and Y to default
		x = 178;
		y = 174;
		
		// Initializes the X and Y at a 5 pixel algorithm
		x = x + (randX * 5);
		y = y - (randY * 5);
		
		// Repaint the graphics
		component.repaint();
		
	}


	public static void updateGraphics(int button) {

		// Up Button
		if (button == 1) {
			y = y - 5;
		}
		
		// Down Button
		if (button == 2) {
			y = y + 5;
		}
		
		// Left Button
		if (button == 3) {
			x = x - 5;
		}
		
		// Right Button
		if (button == 4) {
			x = x + 5;
		}
		
		// Repaint the graphics
		component.repaint();
	}

}
