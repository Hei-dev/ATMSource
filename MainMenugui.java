import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenugui extends BaseATMgui{
	private JPanel MainMenu = new JPanel();
	
	private MainMenugui() {
		super("MainMenu");
		
		setGUIScreen(MainMenu);
	}
	
	public class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
