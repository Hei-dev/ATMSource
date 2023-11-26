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
        // Acc number enter
        ATMgui.get().setEnterListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                setComponentText(login, Defaultgui.TITLE_LABEL, "Please Enter Your PIN", loginFont);
                // set masked input for password
                ATMgui.get().setKeypadConfiguration(true, false, true);
                String accountNumberinput = ATMgui.get().getInput();
                try {
                    accountNumber = Integer.parseInt(accountNumberinput);
                } catch (NumberFormatException nfe) {
                	System.out.println(nfe);
                }
                passwordCheck();
                if (getTextPaneText(login) != "")
                	setTextPaneText(login, "");
            }
        });
    }
    // Password check
    public void passwordCheck(){
        ATMgui.get().setEnterListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
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
                        		+ "<li>Please Enter Again</html>", loginFont);
                    }
                    if(inputWrongCount == 2){
                        setComponentText(login, "Title", 
                        		"<html>Account Number or PIN wrong, "
                        		+ "<li>You can try 1 more times, "
                        		+ "<li>Please Enter Again</html>", loginFont);
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
                            if(inputWrongCount%3 == 0 && inputWrongCount != 0 ){
                                setComponentText(login, "Title", 
                                		"Please Enter Your Account Number", loginFont);
                                ATMgui.get().display(GUIType.Greeting);
                            }
                            setComponentText(login, Defaultgui.TITLE_LABEL, 
                            		"Please Enter Your PIN", loginFont);
                            String accountNumberinput = ATMgui.get().getInput();
                            accountNumber = Integer.parseInt(accountNumberinput);
                            passwordCheck();
                            if (getTextPaneText(login) != "")
                            	setTextPaneText(login, "");
                            // set masked input for password
                        	ATMgui.get().setKeypadConfiguration(true, false, true);
                        }
                    });
                }
                // remove masked input for account input
            	ATMgui.get().setKeypadConfiguration(true, false, false);
                if (getTextPaneText(login) != "")
                	setTextPaneText(login, "");
            }
        });
    }
    
}
