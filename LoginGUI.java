import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class LoginGUI implements Defaultgui{

    private JPanel login;
    private BankDatabase loginBankDB;

    private int currentAccountNumber;

    private Font loginFont;

    private int accountNumber;
    private int PIN;

    private int inputWrongCount = 0;
    
    private String accountNumberInput;

    protected LoginGUI(){
        login = getdefaultGUI();
        loginFont = new Font("loginFont", 1 ,20);

        setComponentText(login, Defaultgui.TITLE_LABEL, "Please Enter Your Account Number", loginFont);


        for (int i = 0; i < 8; i++) {
            setSelectionDisplay(login, i, false);
        }

        loginBankDB = new BankDatabase();

    }

    public JPanel getPanel() {
        return login;
    }

    public int getcurrentAccountNumber() {
    	return currentAccountNumber;
    }

    // Acc number enter
    public void setAllListener() {
    	// clear text pane
        if (getTextPaneText(login) != "")
        	setTextPaneText(login, "");
        // set keypad for input
        ATMgui.get().setKeypadConfiguration(true, false, false);
        // Acc number enter
        ATMgui.get().setEnterListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
            	// clear text pane
            	if (getTextPaneText(login) != "")
                	setTextPaneText(login, "");
            	// get account input
                accountNumberInput = ATMgui.get().getInput();
                // set up password input
                setComponentText(login, Defaultgui.TITLE_LABEL, "Please Enter Your PIN", loginFont);
                // set masked input for password
                ATMgui.get().setKeypadConfiguration(true, false, true);
                try {
                    accountNumber = Integer.parseInt(accountNumberInput);
                } catch (NumberFormatException nfe) {
                	System.out.println(nfe);
                }
                passwordCheck();
            }
        });
    }
    
    // Password check
    public void passwordCheck(){
        ATMgui.get().setEnterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	// clear text pane
                if (getTextPaneText(login) != "")
                	setTextPaneText(login, "");
            	// get password input
                try {
                	PIN = Integer.parseInt(ATMgui.get().getInput());
                } catch (NumberFormatException nfe) {
                	System.out.println(nfe);
                }
                boolean userAuthenticated = loginBankDB.authenticateUser( accountNumber, PIN );
                // check whether authentication succeeded
                if ( userAuthenticated )
                {
                    currentAccountNumber = accountNumber; // save user's account #
                    inputWrongCount = 0;
                    ATMgui.get().display(GUIType.MainMenu);
                    // proceeed to main menu
                }

                else{
                    inputWrongCount++;
                    if(inputWrongCount == 1){
                        setComponentText(login, "Title", 
                        		"<html>Account Number or PIN wrong, "
                        		+ "<li>You can try 2 more times, "
                        		+ "<li>Click Enter to Retry</html>", loginFont);
                    }
                    if(inputWrongCount == 2){
                        setComponentText(login, "Title", 
                        		"<html>Account Number or PIN wrong, "
                        		+ "<li>You can try 1 more times, "
                        		+ "<li>Click Enter to Retry</html>", loginFont);
                    }
                    if(inputWrongCount%3 == 0 && inputWrongCount != 0){
                        setComponentText(login, "Title", 
                        		"<html>Your card is freezed, "
                        		+ "<li>Please contact us, "
                        		+ "<li>Click Enter back to greeting page</html>", loginFont);
                    }
                    
                    ATMgui.get().setEnterListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent evt){
                        	// clear text pane
                            if (getTextPaneText(login) != "")
                            	setTextPaneText(login, "");
                            if(inputWrongCount%3 == 0 && inputWrongCount != 0 ){
                            	inputWrongCount = 0;
                                ATMgui.get().display(GUIType.Greeting);
                            }
                            setComponentText(login, Defaultgui.TITLE_LABEL, 
                            		"Please Enter Your Account Number", loginFont);
                            setAllListener();
                        }
                    });
                }
            }
        });
    }
    
}
