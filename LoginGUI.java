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

    public int accountNumber;
    public int PIN;

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

    // Acc number enter
    public void setAllListener() {
        // Acc number enter
        ATMgui.get().setEnterListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                setComponentText(login, Defaultgui.TITLE_LABEL, "Please Enter Your PIN", loginFont);
                String accountNumberinput = getTextPaneText (login);
                accountNumber = Integer.parseInt(accountNumberinput);
                //setComponentText(login, "Title", accountNumberinput, loginFont);
                //String input = getTextPaneText( TransferGUI );
                System.out.println(accountNumber);
                passwordCheck();
                System.out.println("enter acc number");
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
                PIN = Integer.parseInt(PINinput);
                System.out.println(PIN);
                boolean userAuthenticated = loginBankDB.authenticateUser( accountNumber, PIN );
                System.out.println("password enter");
                setTextPaneText(login, "");
                // check whether authentication succeeded
                if ( userAuthenticated )
                {
                    currentAccountNumber = accountNumber; // save user's account #
                    ATMgui.get().display(GUIType.MainMenu);
                    // proceeed to main menu
                }

                else{

                    setComponentText(login, "Title", "<html>Account Number or PIN wrong, <li>Please Enter Again</html>", loginFont);
                    
                    ATMgui.get().setEnterListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent evt){
                            passwordCheck();
                            System.out.println("wrong acc or pin");
                            setTextPaneText(login, "");
                        }
                    });
                }
            }
        });
    }
}
