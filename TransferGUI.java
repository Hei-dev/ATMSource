import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransferGUI implements Defaultgui 
{
    private static JPanel TransferGUI; // declare unique panel
      
    private static Font transferfont; // declare local text font for title
    private static Font numberfont; // declare local text font for number
    
    private final static int CANCELED = 0; // constant for cancel option
    private final static int Decimal_value = -2; // constant for handle non-integer
    private final static int Insufficient_cash = -3; // constant for not enough money
    private final static int Same_account = -4; // constant for same account number
    private final static int Invalid_account = -5; // constant for unexist account number
    private final static int Invalid_value = -404; // constant for handle invaild value
    
    protected TransferGUI()
    {
         transferfont = new Font("Transfont", 1, 20); //concrete text font of title
         numberfont = new Font("numberfont", 1, 16); //concrete text font of value
    }
    
    public JPanel getMainPanel() 
    {
        
        TransferGUI = ATMgui.get().getdefaultGUI();
        
        ATMgui.get().setComponentText
        (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)</html>", transferfont);

         //disable all selection message
        for (int i = 0; i < 8; i++)
        {
            ATMgui.get().setSelectionDisplay(TransferGUI, i, false);
        }

        return TransferGUI;
    }
    
    public static void execute_amount( double status)
    {        
        //disable all selection message
        for (int i = 0; i < 8; i++)
        {
            ATMgui.get().setSelectionDisplay(TransferGUI, i, false);
        }
        
        if (status == CANCELED)
        {
            Return_MainMenu();
        }
        else if (status == Invalid_value)
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>Only support numbers! Please Input again</html>"
            , transferfont);
        }
        else if (status == Insufficient_cash)
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" + 
            "<br/><br/><br/><br/>Insufficient cash available<br/>Please select smaller amount</html>"
            , transferfont);
        }
        else
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter target account number<br/>(0 to cancel)</html>", transferfont);
        }
        
        if (ATMgui.get().getTextPaneText(TransferGUI) != "")
        ATMgui.get().setTextPaneText(TransferGUI, "");
    }
    
    public static void execute_account( double input)
    {        
        //disable all selection message
        for (int i = 0; i < 8; i++)
        {
            ATMgui.get().setSelectionDisplay(TransferGUI, i, false);
        }
        
        int status = (int) input ;
        
        if (status == CANCELED)
        {
            Return_MainMenu();
        }
        else if (status == Decimal_value)
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer account number!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>Only support integer value! Please Input again</html>"
            , transferfont);
        }
        else if (status == Invalid_value)
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer account number!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>Only support numbers! Please Input again</html>"
            , transferfont);
        }
        else if (status == Same_account)
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer account number!<br/>(0 to cancel)" + 
            "<br/><br/><br/><br/>Transfer to your own account<br/>is unavailable!" + 
            "<br/>Please input again</html>"
            , transferfont);
        }
        else if (status == Invalid_account)
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer account number!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>The account does not exist! Please Input again</html>"
            , transferfont);
        }
        else
        {
            ATMgui.get().setComponentText
            (TransferGUI, "Title", "<html>Please confirm the information correct!<br/>(0 to cancel)</html>", transferfont);
        }
        
        if (ATMgui.get().getTextPaneText(TransferGUI) != "")
        ATMgui.get().setTextPaneText(TransferGUI, "");
    }
    
    public static void execute_confirm( double input1, int input2)
    {           
        String amount = Double.toString( input1 );
        String target_account = Integer.toString( input2 );
        
        ATMgui.get().setComponentText
        (TransferGUI, "Title", "Please check all the inputs are correct", transferfont);
        for (int i = 0; i<= 1; i++)
        {
            ATMgui.get().setSelectionDisplay(TransferGUI, i, false);
        }
        for (int i = 2; i<= 7; i++)
        {
            ATMgui.get().setSelectionDisplay(TransferGUI, i, true);
        }
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION2_LABEL, "transfer amount:", transferfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION3_LABEL, "HK$" + amount, numberfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION4_LABEL, "target account:", transferfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION5_LABEL, target_account, numberfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION6_LABEL, "Confirm", transferfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION7_LABEL, "Cancel", transferfont);
        
        ATMgui.get().setSelectionListener(7, new ActionListener()
        {   

                @Override
                public void actionPerformed(ActionEvent e) {
                Return_MainMenu();
                }
            
            });
    }
    
    public static void execute_exit()
    {   
        //disable all selection message
        for (int i = 0; i < 8; i++)
        {
            ATMgui.get().setSelectionDisplay(TransferGUI, i, false);
        }
        
        for(int i = 0; i < 8 ; i++){
            ATMgui.get().setSelectionListener(i, new ActionListener() 
            {   

                @Override
                public void actionPerformed(ActionEvent e) {
                Return_MainMenu();
                }
            
            });
        }
        
        ATMgui.get().setComponentText
        (TransferGUI, "Title", "<html>Transfer success<br/> " +
        "Press any key back to main menu</html>", transferfont);
    }
    
    public static void Return_MainMenu()
    {   
        if (ATMgui.get().getTextPaneText(TransferGUI) != "")
        ATMgui.get().setTextPaneText(TransferGUI, "");
        
        ATMgui.get().display(GUIType.MainMenu);
    }
}
    
