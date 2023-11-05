//ATMgui.java
//Represents the GUI version of ATM

public class ATMgui extends BaseATMgui{

    private ATMgui(){
        super();
    }
	
	// Singleton stuff
	private static ATMgui Gui = null;
	public static synchronized ATMgui get(){
		if(Gui==null){Gui = new ATMgui();}
		return Gui;
	}


}
