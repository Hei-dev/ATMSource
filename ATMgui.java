

//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

	
	Exitgui exit = new Exitgui();
	MainMenugui mainmenu = new MainMenugui();
	WithdrawalGUI withdrawal = new WithdrawalGUI();
	// Constructors

    private ATMgui(){
        this("ATM");
    }

	private ATMgui(String title){
		super(title);
		//System.out.println("ATMgui Constructor start");
		//System.out.println("ATMgui Constructor end");
	}
	

	/**
	 * Creates a new GUI based on the `GUIType`
	 * @param t GUIType the GUIType of which to be constructed
	 * @return ATMgui the GUI of that type.
	 */
	/**
	private static ATMgui newGui(GUIType t){
		switch(t){
			case MainMenu:
				//return new MainMenugui(); 
			case Withdrawal:
				//return new WithdrawalGUI();
			case Exit:
				//return new Exitgui();
			case Balance:
				;
			case Transfer:
				;
			case Login:
				;
			default:
				return new ATMgui();
		}
	}
	*/
	
	/**
	 * Creates a new GUI based on the `GUIType`
	 * @param t GUIType the GUIType of which to be called
	 */
	public void display(GUIType t) {
		switch(t){
			case MainMenu:
				System.out.println("Mainmenu");
				addMainPanel(mainmenu.getPanel());
				System.out.println("2");
				// disable keypad input
				setKeypadAvailability(false);
				System.out.println("3");
				mainmenu.setallSelectionListener();
				System.out.println("4");
				break;
			case Withdrawal:
		
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
		}
		return Gui;
	}
}
