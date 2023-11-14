import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui extends ATMgui{
	private JPanel MainMenu;
	
	protected MainMenugui() {
		super("MainMenu");
	}

	public JPanel setInterface() {
		MainMenu = getdefaultGUI();
		
		// change title to "Main Menu"
		setTitle("Main Menu", 1, 20);
		// disable keypad
		setKeypadAvailability(false);
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
		
		setSelectionButtonListener(7, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				ATMgui.get(GUIType.Exit).run();

				javax.swing.JOptionPane.showMessageDialog(null, "Btn clicked");
			}
		});

		return MainMenu;
	}
	
	@Override
	public void setActionListener() {
		
		super.setActionListener();
	}
}
