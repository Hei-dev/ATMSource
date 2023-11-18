import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui implements Defaultgui{
	private JPanel mainMenu;
	
	protected MainMenugui() {

		mainMenu = getdefaultGUI();
		
		// change title to "Main Menu"
		setComponentText(mainMenu, "Title", "Main Menu", 1, 20);
		
		setComponentText(mainMenu, "selection4", "View my balance");

		// change selection names
		setComponentText(mainMenu, "selection4", "View my balance");
		setComponentText(mainMenu, "selection5", "Withdraw cash");
		setComponentText(mainMenu, "selection6", "Transfer funds");
		setComponentText(mainMenu, "selection7", "Exit");
		// Set the rest of the selection with no text
		for (int i = 0; i < 4; i++) {
			setSelectionDisplay(mainMenu, i, false);
		}
	}
	
	public JPanel getPanel() {
		return mainMenu;
	}
	
	public void setallSelectionListener() {
		// set action listener for view balance
		ActionListener viewBalance = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		};
		ATMgui.get().setSelectionListener(2, viewBalance);
		
		// set action listener for withdraw
		ActionListener withdraw = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ATMgui.get().display(GUIType.Withdrawal);
			}
			
		};
		ATMgui.get().setSelectionListener(6, withdraw);
		
		// set action listener for transfer fund
		ActionListener transferFund = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		ATMgui.get().setSelectionListener(3, transferFund);
		
		// set action listener for exit
		ActionListener exit = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ATMgui.get().display(GUIType.Exit);
			}
			
		};
		ATMgui.get().setSelectionListener(7, exit);
	}
}
