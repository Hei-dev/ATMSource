import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class TransferGUI implements Defaultgui 
{
    private JPanel TransferGUI;
    
    private String target_account;
    private String amount;
    private Font 
    	transferfont,
    	amountfont;
    
    protected TransferGUI()
    {
        TransferGUI = getdefaultGUI();
        transferfont = new Font("Transfont", 1, 20);
        amountfont = new Font("Amtfont", 1, 12);
        
        target_account = "-1";
        amount = "-1";
                    
        // change title
        setComponentText
        (TransferGUI, "Title", "Please enter transfer amount (0 to cancel)", transferfont);
            
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
        (TransferGUI, "Title", "Please enter target account (0 to cancel)", transferfont);
        
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
        (TransferGUI, "Title", "Please check all inputs are correct", transferfont);
        for (int i = 2; i<= 7; i++)
        {
            setSelectionDisplay(TransferGUI, i, true);
        }
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION2_LABEL, "transfer amount:", amountfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION3_LABEL, amount);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION4_LABEL, "target account:", amountfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION5_LABEL, target_account);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION6_LABEL, "Confirm", amountfont);
        ATMgui.get().setComponentText(TransferGUI, Defaultgui.SELECTION7_LABEL, "Cancel", amountfont);
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
    