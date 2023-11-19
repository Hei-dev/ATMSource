

//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

    Greetinggui greeting = new Greetinggui();
    Exitgui exit = new Exitgui();
    MainMenugui mainmenu = new MainMenugui();
    Balancegui balance = new Balancegui();
    

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
                addMainPanel(greeting.getPanel());
                setKeypadAvailability(true, false);
                greeting.setallSelectionListener();
                break;
                
            case MainMenu:
                // disable keypad input
                setKeypadAvailability(true, true);
                addMainPanel(mainmenu.getPanel());
                mainmenu.setallSelectionListener();
                break;
                
            case Withdrawal:
                addMainPanel(WithdrawalGUI.getMainPanel());
                break;
                
            case Exit:
                setKeypadAvailability(false, false);
                addMainPanel(exit.getPanel());
                exit.setallSelectionListener();
                break;
                
            case Balance:
                addMainPanel(balance.getPanel());
                setKeypadAvailability(true, false);
                balance.setallSelectionListener();
                break;
                
            case Transfer:
        
                break;
            case Login:
                // TODO displays login screen
                display(GUIType.MainMenu);
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
