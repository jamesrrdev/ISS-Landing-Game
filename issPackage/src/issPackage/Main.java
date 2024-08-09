// James Robbie ISS Docking Simulation Main
package issPackage;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int selection = 0;
		
		selection = BoxFunctions.guiInitialDialogBox();
		
		if (selection == 1)	{
			new CreateAccount();
		}
		
		else if (selection == 2) {
			new Login(); 
			//new SimulationBox(); // FOR TESTING THE SIMULATION AND ALIGNMENT BOXES
			//new AlignmentWindow();
		}
		
		else {
			System.out.println("Cancel.");
		}
		
		// System.out.println(selection);
		
	}

}