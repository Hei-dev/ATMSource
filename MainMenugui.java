import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenugui extends ATMgui{
	private JPanel MainMenu;
	
	protected MainMenugui() {
		super("MainMenu");
	}

	public JPanel setInterface() {
		MainMenu = getdefaultGUI();
		
		// change title to "Main Menu"
		setTitle("Main Menu", 1, 20);
		// temporary enable keypad to allow side button input
		setKeypadAvailability(true);
		// change selection names
		setSelectionName(4, "View my balance");
		setSelectionName(5, "Withdraw cash");
		setSelectionName(6, "Transfer funds");
		setSelectionName(7, "Exit");
		// Set the rest of the selection with no text
		for (int i = 0; i < 4; i++) {
			setSelectionName(i, "");
			setSelectionDisplay(i, false);
		}
		
		return MainMenu;
	}

	/*
	@Override
	public void setSelection7Listener() {
		JPanel ExitGUI = new JPanel();
		ExitGUI.add(new JLabel("Please take your card"));
		setMainPanel(ExitGUI);
		
		JOptionPane.showMessageDialog(null, "Click");
	} */

}
