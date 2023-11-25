import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoginGUI implements Defaultgui{

    private JPanel login;
    private BankDatabase loginBankDB;

    private int currentAccountNumber;

    private Font loginFont;

    private int accountNumber;
    private int PIN;

    private int inputWrongCount = 0;

    protected LoginGUI(){
        System.out.println("Login");
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
                ATMgui.get().setKeypadConfiguration(true, false, true);
                String accountNumberinput = getTextPaneText (login);
                try {
                    accountNumber = Integer.parseInt(accountNumberinput);
                } catch (NumberFormatException nfe) {
                	System.out.println(nfe);
                }
                System.out.println(accountNumber);
                passwordCheck();
                System.out.println("enter acc number");
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

                String PINinput = getTextPaneText (login);
                try {
                	PIN = Integer.parseInt(ATMgui.get().getMaskedInput());
                } catch (NumberFormatException nfe) {
                	System.out.println(nfe);
                }
                System.out.println(PIN);
                boolean userAuthenticated = loginBankDB.authenticateUser( accountNumber, PIN );
                System.out.println("password enter");
                if (getTextPaneText(login) != "")
                	setTextPaneText(login, "");
                // check whether authentication succeeded
                if ( userAuthenticated )
                {
                    currentAccountNumber = accountNumber; // save user's account #
                    ATMgui.get().display(GUIType.MainMenu);
                    // proceeed to main menu
                }

                else{
                    inputWrongCount++;
                    if(inputWrongCount == 1){
                        setComponentText(login, "Title", "<html>Account Number or PIN wrong, <li>You can try 2 more times, <li>Please Enter Again</html>", loginFont);
                    }
                    if(inputWrongCount == 2){
                        setComponentText(login, "Title", "<html>Account Number or PIN wrong, <li>You can try 1 more times, <li>Please Enter Again</html>", loginFont);
                    }
                    if(inputWrongCount%3 == 0 && inputWrongCount != 0){
                            setComponentText(login, "Title", "<html>Your card is freezed, <li>Please contact us, <li>Click Enter back to greeting page</html>", loginFont);
                    }
                    
                    ATMgui.get().setEnterListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent evt){
                            if(inputWrongCount%3 == 0 && inputWrongCount != 0 ){
                                setComponentText(login, "Title", "Please Enter Your Account Number", loginFont);
                                ATMgui.get().display(GUIType.Greeting);
                            }
                            setComponentText(login, Defaultgui.TITLE_LABEL, "Please Enter Your PIN", loginFont);
                            String accountNumberinput = getTextPaneText (login);
                            accountNumber = Integer.parseInt(accountNumberinput);
                            passwordCheck();
                            System.out.println("wrong acc or pin");
                            if (getTextPaneText(login) != "")
                            	setTextPaneText(login, "");
                        }
                    });
                }
            }
        });
    }
    
}
