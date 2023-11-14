import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exitgui extends ATMgui{
	
	private JPanel ExitGUI;
	
	protected Exitgui() {
		super("Exit");
	}
	
	public JPanel setInterface() {
		ExitGUI = new JPanel();
		ExitGUI.add(new JLabel("Please take your card"));
		return ExitGUI;
	}
}
