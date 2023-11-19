import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Exitgui{
	
	private JPanel 
		exitGUI,
		countdownGUI;
	private JLabel 
		exitMessage,
		countdownMessage;
	
	protected Exitgui() {
		
		// create exit message
		exitMessage = new JLabel();
		exitMessage.setFont(new Font("exit", 1, 20));
	    exitMessage.setHorizontalAlignment(SwingConstants.CENTER);
		exitMessage.setText("Please take your card");
		
		// create count down panel
		countdownGUI = new JPanel(new BorderLayout());
		//takeCard = new JButton("Take Card");
		countdownMessage = new JLabel("Take Card");
		countdownGUI.add(countdownMessage, BorderLayout.EAST);
		
		// create exit gui
		exitGUI = new JPanel(new BorderLayout());
		exitGUI.add(exitMessage, BorderLayout.CENTER);
		exitGUI.add(countdownGUI, BorderLayout.SOUTH);
		
	}
	
	public void setallSelectionListener() {
		ATMgui.get().setSelectionListener(7, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ATMgui.get().display(GUIType.Greeting);
			}
		});
	}
	
	public JPanel getPanel() {
		return exitGUI;
	}
}
