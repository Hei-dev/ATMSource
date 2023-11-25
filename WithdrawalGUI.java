import javax.swing.JPanel;

public class WithdrawalGUI implements Defaultgui{

    private static JPanel mainPanel;

    protected WithdrawalGUI(){
        //super("Withdrawal");
    }


    public static JPanel getMainPanel(boolean inputError) {
        
        mainPanel = ATMgui.get().getdefaultGUI();

        ATMgui.get().setComponentText(
            mainPanel,
            "Title",
            "Please select the exact amount,<br>or type the amount using the keypad." 
            + (inputError ?
                "<br><span style='color:red; text-weight:bold;'>Invalid input. Please try again.</span>"
                : "")
            );

        ATMgui.get().setComponentText(mainPanel, "selection4", "$200");
        ATMgui.get().setComponentText(mainPanel, "selection5", "$500");
        ATMgui.get().setComponentText(mainPanel, "selection6", "$1000");
        ATMgui.get().setComponentText(mainPanel, "selection7", "Exit");
        
        for (int i = 0; i < 4; i++) {
            ATMgui.get().setSelectionDisplay(mainPanel, i, false);
        }
        
        return mainPanel;
   }
   
   public static JPanel getDispensePanel(){
       return null;
   }
   
   public static JPanel getErrorScreen(String msg){
       mainPanel = ATMgui.get().getdefaultGUI();

        ATMgui.get().setComponentText(mainPanel, "Title", "Operation cancelled:<br>" + msg);

        //ATMgui.get().setSelectionDisplay(mainPanel, 4, false);
        ATMgui.get().setSelectionDisplay(mainPanel, 6, false);
        ATMgui.get().setComponentText(mainPanel, "selection5", "Return to Main Menu");
        ATMgui.get().setComponentText(mainPanel, "selection7", "Take card and Exit");
        
        for (int i = 0; i < 5; i++) {
            ATMgui.get().setSelectionDisplay(mainPanel, i, false);
        }
        
        return mainPanel;
   }
   
   public static JPanel getDispensedScreen(){
       mainPanel = ATMgui.get().getdefaultGUI();

        ATMgui.get().setComponentText(mainPanel, "Title", "Please take your cash");

        //ATMgui.get().setSelectionDisplay(mainPanel, 4, false);
        ATMgui.get().setSelectionDisplay(mainPanel, 6, false);
        ATMgui.get().setComponentText(mainPanel, "selection5", "Return to Main Menu");
        ATMgui.get().setComponentText(mainPanel, "selection7", "Take card and Exit");
        
        for (int i = 0; i < 5; i++) {
            ATMgui.get().setSelectionDisplay(mainPanel, i, false);
        }
        
        return mainPanel;
   }
}
