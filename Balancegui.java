import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Balancegui implements Defaultgui{
    private JPanel balance;

    private Font balancefont;

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
           
    public JPanel getPanel() {
        return balance;
    }
    
    public void setallListener() {

        // set action listener for Available Balance        
        ActionListener availableBalance = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String AvailableBalance = Double.toString(BalanceInquiry.getavailablebalance());
                setComponentText(balance, "Title", "Available Balance: " + AvailableBalance, balancefont);
                //execute_available();
                //+ AvailableBalance
            }
        };
        ATMgui.get().setSelectionListener(2, availableBalance);
        
        // set action listener for Total Balance
        ActionListener totalBalance = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String TotalBalance = Double.toString(BalanceInquiry.gettotalbalance());
                setComponentText(balance, "Title", "Total Balance: " + TotalBalance, balancefont);
                //execute_total();
                //+ TotalBalance
            }
        };
        ATMgui.get().setSelectionListener(6, totalBalance);
        
        // set action listener for transfer fund
        ActionListener mainMenu = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setComponentText(balance, Defaultgui.TITLE_LABEL, "View my balance", balancefont);
                 ATMgui.get().display(GUIType.MainMenu);
            }
            
        };
        ATMgui.get().setSelectionListener(3, mainMenu);
        
        // set action listener for exit
        ActionListener exit = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setComponentText(balance, Defaultgui.TITLE_LABEL, "View my balance", balancefont);
                ATMgui.get().display(GUIType.Exit);
            }
            
        };
        ATMgui.get().setSelectionListener(7, exit);
    }
}
