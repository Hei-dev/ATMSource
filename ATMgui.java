

//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

	
	Exitgui exit = new Exitgui();
	MainMenugui mainmenu = new MainMenugui();

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
			case MainMenu:
				addMainPanel(mainmenu.getPanel());
				// disable keypad input
				setKeypadAvailability(false);
				mainmenu.setallSelectionListener();
				break;
			case Withdrawal:
				addMainPanel(WithdrawalGUI.getMainPanel());
				break;
			case Exit:
				addMainPanel(exit.getPanel());
				exit.setallSelectionListener();
				break;
			case Balance:
		
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
