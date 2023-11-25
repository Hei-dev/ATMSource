import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class TransferGUI implements Defaultgui 
{
    private JPanel TransferGUI;
    
    private String target_account;
    private String amount;
    private int current_account;
    private Font 
        transferfont,
        amountfont;
    
    private final static int CANCELED = 0; // constant for cancel option
    private final static int Decimal_value = -2; // constant for handle non-integer
    private final static int Insufficient_cash = -3; // constant for not enough money
    private final static int same_account = -4; // constant for not enough money
    private final static int Invaild_account = -5; // constant for unexist account number
    private final static int Invaild_value = -404; // constant for handle invaild value    
    
    protected TransferGUI()
    {
        TransferGUI = getdefaultGUI();
        transferfont = new Font("Transfont", 1, 20);
        amountfont = new Font("Amtfont", 1, 12);
        
        target_account = "-1";
        amount = "-1";
                    
        // change title
        setComponentText
        (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)</html>", transferfont);
            
        //disable all selection message
        for (int i = 0; i < 8; i++)
        {
                setSelectionDisplay(TransferGUI, i, false);
        }
    }
    
    public JPanel getPanel() 
    {
        return TransferGUI;
    }
    
    private void execute_amount()
    {
        String input = getTextPaneText( TransferGUI );
        int status = Transfer.check_amount ( input );
        if (status == CANCELED)
        {
            ATMgui.get().display(GUIType.MainMenu);
        }
        else if (status == Invaild_value)
        {
            setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>Only support numbers! Input again</html>"
            , transferfont);
        }
        else if (status == Insufficient_cash)
        {
            setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" + 
            "<br/><br/><br/><br/>Insufficient cash available<br/>Please select smaller amount</html>"
            , transferfont);
        }
        else
        {
            amount = input;
            setComponentText
            (TransferGUI, "Title", "<html>Please enter target account number<br/>(0 to cancel)</html>", transferfont);
        }
    }
    
    private void execute_targetaccount()
    {
        String input = getTextPaneText (TransferGUI );
        int status = Transfer.check_account ( input );
        if (status == CANCELED)
        {
            ATMgui.get().display(GUIType.MainMenu);
        }
        else if (status == Decimal_value)
        {
            setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>Only support integer value! Input again</html>"
            , transferfont);
        }
        else if (status == Invaild_value)
        {
            setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>Only support numbers! Input again</html>"
            , transferfont);
        }
        else if (status == same_account)
        {
            setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" + 
            "<br/><br/><br/><br/>Transfer to your own account<br/>is unavailable" + 
            "<br/>Please input again</html>"
            , transferfont);
        }
        else if (status == Invaild_account)
        {
            setComponentText
            (TransferGUI, "Title", "<html>Please enter transfer amount!<br/>(0 to cancel)" +
            "<br/><br/><br/><br/>The account does not exist! Input again</html>"
            , transferfont);
        }
        else
        {
            target_account = input;
            setComponentText
            (TransferGUI, "Title", "<html>Please enter target account number<br/>(0 to cancel)</html>", transferfont);
            promt_confirm();
        }
    }
    
    private void promt_confirm()
    {   
        setComponentText
        (TransferGUI, "Title", "Please check all the inputs are correct", transferfont);
        for (int i = 2; i<= 7; i++)
        {
            setSelectionDisplay(TransferGUI, i, true);
        }
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION2_LABEL, "HK$" + "transfer amount:", amountfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION3_LABEL, amount);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION4_LABEL, "target account:", amountfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION5_LABEL, target_account);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION6_LABEL, "Confirm", amountfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION7_LABEL, "Cancel", amountfont);
    }
    
    private void transfer_execute()
    {
        for (int i = 0; i<= 7; i++)
        {
            setSelectionDisplay(TransferGUI, i, false);
        }
        Transfer.transfer_execute();
        
        //message for returning mainmenu after finish transfer
        for(int i = 0; i < 8 ; i++){
        ATMgui.get().setSelectionListener(i, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                ATMgui.get().display(GUIType.MainMenu);
            }
        });
        }
        setComponentText
        (TransferGUI, "Title", "<html>Transfer successed<br/> " +
        "Press any key back to main menu</html>", transferfont);
    }
    
    public void setallListener()
    {
        ActionListener buttonListener = new ActionListener()
        
        {
                 @Override
                 public void actionPerformed(ActionEvent e)
                 {
                     if (amount == "-1") 
                     execute_amount();
                     else if (target_account == "-1")
                     execute_targetaccount();
                     
                     setTextPaneText(TransferGUI, "");
                 }
        };
        
        ATMgui.get().setEnterListener( buttonListener );
         
        ATMgui.get().setSelectionListener(7, new ActionListener() 
        {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    ATMgui.get().display(GUIType.MainMenu);
                }
        });
        ATMgui.get().setSelectionListener(3, new ActionListener() 
        {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if (amount != "-1" && target_account !="-1")
                    transfer_execute();
                }
        });
    }
}
    
