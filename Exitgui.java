import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exitgui extends BaseATMgui{
	
	private JPanel ExitGUI;
	
	private Exitgui() {
		super("Exit");
		
		ExitGUI = new JPanel();
		ExitGUI.add(new JLabel("Please take your card"));
		
		
		setInterface(ExitGUI);
	}
	
}
