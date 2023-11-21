import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferGUI implements Defaultgui 
{
    private JPanel TransferGUI;
    private Transfer transfer;
    
    private String target_account;
    private String amount;
    
    protected TransferGUI()
    {
        TransferGUI = getdefaultGUI();
        
        target_account = "-1";
        amount = "-1";
                    
        // change title
        setComponentText
        (TransferGUI, "Title", "Please enter transfer amount (0 to cancel)", 1, 20);
            
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
    
    public double execute_amount()
    {
        String input = getTextPaneText( TransferGUI );
        amount = input;
        
        setComponentText
        (TransferGUI, "Title", "Please enter target account (0 to cancel)", 1, 20);
        
        return Double.parseDouble( amount );
    }
    
    public void execute_targetaccount()
    {
        String input = getTextPaneText (TransferGUI );
        target_account = input;
        
        promt_confirm();
    }
    
    public void promt_confirm()
    {   
        setComponentText
        (TransferGUI, "Title", "Please check all inputs are correct", 1, 20);
        for (int i = 2; i<= 7; i++)
        {
            setSelectionDisplay(TransferGUI, i, true);
        }
        ATMgui.get().setComponentText(TransferGUI, "selection2", "transfer amount:",1, 12);
        ATMgui.get().setComponentText(TransferGUI, "selection3", amount);
        ATMgui.get().setComponentText(TransferGUI, "selection4", "target account:",1 ,12);
        ATMgui.get().setComponentText(TransferGUI, "selection5", target_account);
        ATMgui.get().setComponentText(TransferGUI, "selection6", "Confirm", 1, 12);
        ATMgui.get().setComponentText(TransferGUI, "selection7", "Cancel", 1, 12);
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
                 }
            };
        
        ATMgui.get().setEnterListener( buttonListener );
        
            ATMgui.get().setSelectionListener(7, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                ATMgui.get().display(GUIType.MainMenu);
            }
        });
    }
}
    