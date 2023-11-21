import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui implements Defaultgui{
    private JPanel mainMenu;
    private BankDatabase bankDB;
    private Font mainMenuFont;

	private int currentAccountNumber; // current user's account number
	
	private ATMgui SCREEN;
	
	protected MainMenugui() {
		mainMenu = getdefaultGUI();
		mainMenuFont = new Font("mainMenuFont", 1 ,20);
		
		// change title to "Main Menu"
		setComponentText(mainMenu, "Title", "Main Menu", mainMenuFont);
        // change selection names
        setComponentText(mainMenu, "selection4", "View my balance");
        setComponentText(mainMenu, "selection5", "Withdraw cash");
        setComponentText(mainMenu, "selection6", "Transfer funds");
        setComponentText(mainMenu, "selection7", "Exit");
        // Set the rest of the selection with no text
        for (int i = 0; i < 4; i++) {
            setSelectionDisplay(mainMenu, i, false);
        }
        bankDB = new BankDatabase();
    }

    public int getAccountNumber(){ return currentAccountNumber;}
	
	public JPanel getPanel() {
		return mainMenu;
	}
	
	public void setallListener() {
		// set action listener for view balance
		ATMgui.get().setSelectionListener(2, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Transaction temp = new BalanceInquiry( getAccountNumber(), ATMgui.get(),bankDB );
				temp.execute();
				ATMgui.get().display(GUIType.Balance);
			}
		});
		
		// set action listener for withdraw
		ActionListener withdraw = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Transaction temp = new Withdrawal( currentAccountNumber, SCREEN, 
               		bankDB, new Keypad(), new CashDispenser() );
				temp.execute();
				ATMgui.get().display(GUIType.Withdrawal);
			}
			
		};
		ATMgui.get().setSelectionListener(6, withdraw);
		
		// set action listener for transfer fund
		ActionListener transferFund = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ATMgui.get().display(GUIType.Transfer);
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
