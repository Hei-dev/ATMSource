

//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

    Greetinggui greeting = new Greetinggui();
    Exitgui exit = new Exitgui();
    MainMenugui mainmenu = new MainMenugui();
    Balancegui balance = new Balancegui();
    LoginGUI login = new LoginGUI();

    // Constructors

    protected ATMgui(){
        this("ATM");
        
    }

	private ATMgui(String title){
		super(title);
	}
    
    
    /**
     * Creates a new GUI based on the `GUIType`
     * @param t GUIType the GUIType of which to be called
     */
    public void display(GUIType t) {
        switch(t){
            case Greeting:
                setMainPanel(greeting.getPanel());
                setKeypadConfiguration(true, false, false);;
                greeting.setallSelectionListener();
                break;
                
            case MainMenu:
                // disable keypad input
            	setKeypadConfiguration(true, false, false);
                setMainPanel(mainmenu.getPanel());
                mainmenu.setallListener();
                break;
                
            case Withdrawal:
                setMainPanel(WithdrawalGUI.getMainPanel());
                break;
                
            case Exit:
            	setKeypadConfiguration(false, false, false);
                setMainPanel(exit.getPanel());
                exit.setallListener();
                break;
                
            case Balance:
                setMainPanel(balance.getPanel());
                setKeypadConfiguration(true, false, false);
                balance.setallListener();
                break;
                
            case Transfer:
            	TransferGUI transfer = new TransferGUI();
        		setKeypadConfiguration(true, true, false);
                setMainPanel(transfer.getPanel());
                transfer.setallListener();
                break;
            case Login:
                setKeypadConfiguration(true, false, false);
                setMainPanel(login.getPanel());
                login.setAllListener();
                break;
            default:
                break;
        }
        
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
