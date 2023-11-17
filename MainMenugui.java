import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui{
	private static JPanel mainMenu;
	
	public static void init() {
		//super("MainMenu");	
		
		// set action listener for view balance
		ActionListener viewBalance = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// atm.setMainPanel(ViewBalanceGUI);
			}
		};
		ATMgui.get().setSelectionListener(2, viewBalance);
		
		// set action listener for withdraw
		ActionListener withdraw = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WithdrawalGUI withdrawalgui = new WithdrawalGUI();
				ATMgui.get().setMainPanel(withdrawalgui.setInterface());
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
				System.out.println("MainMenugui Before calling exitgui");
				//Exitgui exitgui = new Exitgui();
				
				//exitgui.removeCurrentPanel();
				//setMainPanel(exitgui.setInterface());		naur
				Exitgui.init();
				ATMgui.get().setMainPanel(Exitgui.setInterface());
				System.out.println("MainMenugui After calling exitgui");
			}
			
		};
		ATMgui.get().setSelectionListener(7, exit);
	}
	
	public static JPanel setInterface() {

		mainMenu = new JPanel();
		mainMenu = ATMgui.get().getdefaultGUI();
		
		// change title to "Main Menu"
		ATMgui.get().setTitle("Main Menu", 1, 20);
		// temporary enable keypad to allow side button input
		ATMgui.get().setKeypadAvailability(false);
		// change selection names
		ATMgui.get().setSelectionName(4, "View my balance");
		ATMgui.get().setSelectionName(5, "Withdraw cash");
		ATMgui.get().setSelectionName(6, "Transfer funds");
		ATMgui.get().setSelectionName(7, "Exit");
		// Set the rest of the selection with no text
		for (int i = 0; i < 4; i++) {
			ATMgui.get().setSelectionDisplay(i, false);
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
