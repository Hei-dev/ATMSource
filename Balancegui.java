import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Balancegui implements Defaultgui{
    private JPanel balance;

    private Font balancefont;

    private int currentAccountNumber; // current user's account number
    
    private ATMgui SCREEN;
    //private static final ATMgui SCREEN = ATMgui.get();
    
    protected Balancegui() {
        balance = getdefaultGUI();
        balancefont = new Font("balfont", 1, 20);
        // change title to "balance"
        setComponentText(balance, Defaultgui.TITLE_LABEL, "View my balance", balancefont);

        // change selection names   
        setComponentText(balance, Defaultgui.SELECTION4_LABEL, "Available balance");
        setComponentText(balance, Defaultgui.SELECTION5_LABEL, "Total balance");
        setComponentText(balance, Defaultgui.SELECTION6_LABEL, "Main menu");
        setComponentText(balance, Defaultgui.SELECTION7_LABEL, "Exit");
        // Set the rest of the selection with no text
        for (int i = 0; i < 4; i++) {
            setSelectionDisplay(balance, i, false);
        }
        //setTextPanel(mainMenu);
    }
    
    private void execute_available(){
                String AvailableBalance = Double.toString(BalanceInquiry.getavailablebalance());
                setComponentText(balance, "Title", "Available Balance: " + AvailableBalance, balancefont);
            }
            
    private void execute_total(){
                String TotalBalance = Double.toString(BalanceInquiry.gettotalbalance());
                setComponentText(balance, "Title", "Total Balance: " + TotalBalance, balancefont);
            }
            
    public JPanel getPanel() {
        System.out.println("Balance getPanel()");
        return balance;
    }
    
    public void setallListener() {

        // set action listener for Available Balance        
        ActionListener availableBalance = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Transaction temp = new Transfer( currentAccountNumber, SCREEN, bankDB, new Keypad() );
                execute_available();
                //+ AvailableBalance
            }
        };
        ATMgui.get().setSelectionListener(2, availableBalance);
        System.out.println("Balancegui available balance listener");
        
        // set action listener for Total Balance
        ActionListener totalBalance = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Transaction temp = new Transfer( currentAccountNumber, SCREEN, bankDB, new Keypad() );
                execute_total();
                //+ TotalBalance
            }
        };
        ATMgui.get().setSelectionListener(6, totalBalance);
        System.out.println("Balancegui available balance listener");
        
        // set action listener for transfer fund
        ActionListener mainMenu = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 ATMgui.get().display(GUIType.MainMenu);
            }
            
        };
        ATMgui.get().setSelectionListener(3, mainMenu);
        System.out.println("Balancegui main menu listener");
        
        // set action listener for exit
        ActionListener exit = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ATMgui.get().display(GUIType.Exit);
            }
            
        };
        ATMgui.get().setSelectionListener(7, exit);
        System.out.println("Balancegui exit listener");
    }
}
