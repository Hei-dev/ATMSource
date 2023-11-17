import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui extends ATMgui{
	private JPanel mainMenu;
	
	protected MainMenugui() {
		super("MainMenu");	
		
		// set action listener for view balance
		ActionListener viewBalance = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// atm.setMainPanel(ViewBalanceGUI);
			}
		};
		setSelectionListener(2, viewBalance);
		
		// set action listener for withdraw
		ActionListener withdraw = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WithdrawalGUI withdrawalgui = new WithdrawalGUI();
				setMainPanel(withdrawalgui.setInterface());
			}
			
		};
		setSelectionListener(6, withdraw);
		
		// set action listener for transfer fund
		ActionListener transferFund = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		setSelectionListener(3, transferFund);
		
		// set action listener for exit
		ActionListener exit = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MainMenugui Before calling exitgui");
				//Exitgui exitgui = new Exitgui();
				
				//exitgui.removeCurrentPanel();
				//setMainPanel(exitgui.setInterface());		naur
				ATMgui.get(GUIType.Exit);
				System.out.println("MainMenugui After calling exitgui");
			}
			
		};
		setSelectionListener(7, exit);
	}
	
	@Override
	public JPanel setInterface() {

		mainMenu = new JPanel();
		mainMenu = getdefaultGUI();
		
		// change title to "Main Menu"
		setTitle("Main Menu", 1, 20);
		// temporary enable keypad to allow side button input
		setKeypadAvailability(false);
		// change selection names
		setSelectionName(4, "View my balance");
		setSelectionName(5, "Withdraw cash");
		setSelectionName(6, "Transfer funds");
		setSelectionName(7, "Exit");
		// Set the rest of the selection with no text
		for (int i = 0; i < 4; i++) {
			setSelectionDisplay(i, false);
		}
		System.out.println("MainMenu setInterface() out");
		return mainMenu;
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
