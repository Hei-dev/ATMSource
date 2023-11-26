

//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

    // Constructors
    Greetinggui greeting = new Greetinggui();
    Exitgui exit = new Exitgui();
    MainMenugui mainmenu = new MainMenugui();
    Balancegui balance = new Balancegui();
    LoginGUI login = new LoginGUI();
    TransferGUI transfer = new TransferGUI();
    private BankDatabase bankDB;
    
    int currentaccountnumber;

    protected ATMgui(){
        this("ATM");
        bankDB = new BankDatabase();
    }

    private ATMgui(String title){
        super(title);
    }
    
    public int getAccountNumber()
    { 
        return currentaccountnumber;
    }
    
    /**
     * Creates a new GUI based on the `GUIType`
     * @param t GUIType the GUIType of which to be called
     */
    public void display(GUIType t) {
        switch(t){
            case Greeting:
                // disable keypad input
                setKeypadConfiguration(false, false, false);
                // set panel to greeting
                setMainPanel(greeting.getPanel());
                // update action listeners
                greeting.setallSelectionListener();
                break;
                
            case MainMenu:
            	// save current account number
                currentaccountnumber = login.getcurrentAccountNumber();
                // disable keypad input
                setKeypadConfiguration(false, false, false);
                // set panel to main menu
                setMainPanel(mainmenu.getPanel());
                // update action listeners
                mainmenu.setallListener();
                break;
                
            case Withdrawal:
            	// enable keypad input
            	setKeypadConfiguration(true, false, false);
            	// set panel to withdrawal panel
                setMainPanel(WithdrawalGUI.getMainPanel(false));
                Transaction withdraw = new Withdrawal( ATMgui.get().getAccountNumber(), 
                		this, bankDB, new Keypad(), new CashDispenser() );
                withdraw.execute();
                break;
                
            case Exit:
            	// disable keypad input
                setKeypadConfiguration(false, false, false);
                // set panel to exit panel
                setMainPanel(exit.getPanel());
                // update action listeners
                exit.setallListener();
                break;
                
            case Balance:
            	// set panel to view balance
                setMainPanel(balance.getPanel());
                Transaction bal = new BalanceInquiry( ATMgui.get().getAccountNumber(), 
                		ATMgui.get(),bankDB );
                bal.execute();
                // disable keypad input
                setKeypadConfiguration(false, false, false);
                balance.setallListener();
                break;
                
            case Transfer:
            	// enable keypad input with "." function
                setKeypadConfiguration(true, true, false);
                // set panel to transfer panel
                setMainPanel(transfer.getMainPanel());
                Transaction trans = new Transfer( ATMgui.get().getAccountNumber(), 
                		ATMgui.get(),bankDB , new Keypad());
                trans.execute();
                break;
            case Login:
            	// enable keypad input
                setKeypadConfiguration(true, false, false);
                // set panel to login panel
                setMainPanel(login.getPanel());
                // update action listeners
                login.setAllListener();
                break;
            default:
                break;
        }
        // update changes
        revalidate();
        repaint();
        }
    
    // Singleton stuff
    private static ATMgui Gui = null;
    //private static GUIType currentGUIType = GUIType.Default;
    /**
     * Gets the current ATM GUI
     * @param t the GUIType to be gotten
     * @return the concerned GUI Type
     */
    public static synchronized ATMgui get(){
        if(Gui == null){
            Gui = new ATMgui();
            //ATM innerATM = new ATM();
        }
        return Gui;
    }
}
