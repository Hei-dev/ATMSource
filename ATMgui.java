

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
        System.out.println("ATMgui constructor");
    }
    
    
    /**
     * Creates a new GUI based on the `GUIType`
     * @param t GUIType the GUIType of which to be called
     */
    public void display(GUIType t) {
        switch(t){
            case Greeting:
                System.out.println("ATMgui display greeting");
                addMainPanel(greeting.getPanel());
                setKeypadAvailability(true, false);
                System.out.println("ATMgui call listener greeting");
                greeting.setallSelectionListener();
                break;
                
            case MainMenu:
                System.out.println("ATMgui display mainmenu");
                // disable keypad input
                setKeypadAvailability(true, true);
                addMainPanel(mainmenu.getPanel());
                System.out.println("ATMgui call listener mainmenu");
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
                System.out.println("ATMgui display balance");
                addMainPanel(balance.getPanel());
                setKeypadAvailability(true, false);
                System.out.println("ATMgui call listener balance");
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
