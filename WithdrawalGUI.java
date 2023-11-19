import javax.swing.JPanel;

public class WithdrawalGUI implements Defaultgui{

    private static JPanel mainPanel;

    protected WithdrawalGUI(){
        //super("Withdrawal");
    }

    public static JPanel getMainPanel() {

        mainPanel = ATMgui.get().getdefaultGUI();

        ATMgui.get().setComponentText(mainPanel, "Title", "Please select the exact amount, or type the amount using the keypad.");

        ATMgui.get().setComponentText(mainPanel, "selection4", "$200");
        ATMgui.get().setComponentText(mainPanel, "selection5", "$500");
        ATMgui.get().setComponentText(mainPanel, "selection6", "$1000");
        ATMgui.get().setComponentText(mainPanel, "selection7", "Exit");

        return mainPanel;
        
   }
}
