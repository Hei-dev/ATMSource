//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

	// Constructors

    protected ATMgui(){
        super();
    }

	protected ATMgui(String title){
		super(title);
	}

	/**
	 * Creates a new GUI based on the `GUIType`
	 * @param t GUIType the GUIType of which to be constructed
	 * @return ATMgui the GUI of that type.
	 */
	private static ATMgui newGui(GUIType t){
		switch(t){
			case MainMenu:
				return new MainMenugui(); 
			case Withdrawal:
				return new WithdrawalGUI();
			case Exit:
				return new Exitgui();
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
	
	// Singleton stuff
	private static ATMgui Gui = null;
	private static GUIType currentGUIType = GUIType.Default;
	/**
	 * Gets the current ATM GUI
	 * @param t the GUIType to be gotten
	 * @return the concerned GUI Type
	 */
	public static synchronized ATMgui get(GUIType t){
		if(Gui == null){
			//Gui = new ATMgui();
			Gui = newGui(t);
		}
		
		return Gui;
	}
}
