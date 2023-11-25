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
    
    LoginGUI login = new LoginGUI();
    
    protected MainMenugui() {
        mainMenu = getdefaultGUI();
        mainMenuFont = new Font("mainMenuFont", 1 ,20);
        
        // change title to "Main Menu"
        setComponentText(mainMenu, Defaultgui.TITLE_LABEL, "Main Menu", mainMenuFont);
        // change selection names
        setComponentText(mainMenu, Defaultgui.SELECTION4_LABEL, "View my balance");
        setComponentText(mainMenu, Defaultgui.SELECTION5_LABEL, "Withdraw cash");
        setComponentText(mainMenu, Defaultgui.SELECTION6_LABEL, "Transfer funds");
        setComponentText(mainMenu, Defaultgui.SELECTION7_LABEL, "Exit");
        // Set the rest of the selection with no text
        for (int i = 0; i < 4; i++) {
            setSelectionDisplay(mainMenu, i, false);
        }
        bankDB = new BankDatabase();
    }

    public JPanel getPanel() {
        return mainMenu;
    }
    
    public void setallListener() {
        // set action listener for view balance
        ATMgui.get().setSelectionListener(2, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction temp = new BalanceInquiry( ATMgui.get().getAccountNumber(), ATMgui.get(),bankDB );
                temp.execute();
                ATMgui.get().display(GUIType.Balance);
            }
        });
        
        // set action listener for withdraw
        ATMgui.get().setSelectionListener(6, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction temp = new Withdrawal( ATMgui.get().getAccountNumber(), SCREEN, bankDB, new Keypad(), new CashDispenser() );
                temp.execute();
                ATMgui.get().display(GUIType.Withdrawal);
            }
            
        });
        
        // set action listener for transfer fund
        ATMgui.get().setSelectionListener(3, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction temp = new Transfer( ATMgui.get().getAccountNumber(), ATMgui.get(),bankDB );
                temp.execute();
                ATMgui.get().display(GUIType.Transfer);
            }
            
        });
        
        // set action listener for exit
        ATMgui.get().setSelectionListener(7, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ATMgui.get().display(GUIType.Exit);
            }
            
        });
    }
}
