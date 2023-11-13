import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui extends BaseATMgui{
	private JPanel MainMenu;
	
	protected MainMenugui() {
		super("MainMenu");
	}
	
	public JPanel setInterface() {
		MainMenu = getdefaultGUI();
		return MainMenu;
	}
	
	public class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
