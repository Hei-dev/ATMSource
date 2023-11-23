import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Exitgui implements Defaultgui {
	
	private JPanel 
		exitGUI,
		cardOnlyGUI,
		cardAndReciptGUI;
	private Font exitfont;
	private boolean card, recipt;
	
	protected Exitgui() {
		// create font for title
		exitfont = new Font("exitfont", 1, 20);
		// copy default gui
		exitGUI = getdefaultGUI();
		
		// set title
		setComponentText(exitGUI, Defaultgui.TITLE_LABEL, "Choose your exit method", exitfont);
		// setup selection
		setComponentText(exitGUI, Defaultgui.SELECTION6_LABEL, "Take card and recipt");
		setComponentText(exitGUI, Defaultgui.SELECTION7_LABEL, "Take card only");
		for (int i = 0; i < 6; i++) 
			setSelectionDisplay(exitGUI, i, false);
		
		// create card only gui
		cardOnlyGUI = getdefaultGUI();
		// set title
		setComponentText(cardOnlyGUI, Defaultgui.TITLE_LABEL, "Please take your card", exitfont);
		// disable all but 1 selection
		for (int i = 0; i < 7; i++)
			setSelectionDisplay(cardOnlyGUI, i, false);
		// setup selection
		setComponentText(cardOnlyGUI, Defaultgui.SELECTION7_LABEL, "Take card");
		
		// create card and recipt gui
		cardAndReciptGUI = getdefaultGUI();
		card = false;
		recipt = false;
		// set title
		setComponentText(cardAndReciptGUI, Defaultgui.TITLE_LABEL, "Please take your card and recipt", exitfont);
		// disable all but 2 selection
		for (int i = 0; i < 6; i++) 
			setSelectionDisplay(cardAndReciptGUI, i, false);
		// setup selections
		setComponentText(cardAndReciptGUI, Defaultgui.SELECTION6_LABEL, "Take recipt");
		setComponentText(cardAndReciptGUI, Defaultgui.SELECTION7_LABEL, "Take card");
	}
	
	public void setallListener() {
		
		ATMgui.get().setSelectionListener(3, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// set take card and recipt GUI
				ATMgui.get().setMainPanel(cardAndReciptGUI);
				ATMgui.get().setSelectionListener(3, new ActionListener() {
					// take recipt
					@Override
					public void actionPerformed(ActionEvent e) {
						recipt = true;
						if ((recipt == true) && (card == true)) {
							card = false;
							recipt = false;
							ATMgui.get().display(GUIType.Login);
						}
					}
					
				});
				ATMgui.get().setSelectionListener(7, new ActionListener() {
					// take card
					@Override
					public void actionPerformed(ActionEvent e) {
						card = true;
						if ((recipt == true) && (card == true)) {
							card = false;
							recipt = false;
							ATMgui.get().display(GUIType.Login);
						}
					}
					
				});
			}
			
		});
		
		ATMgui.get().setSelectionListener(7, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// set take card only GUI
				ATMgui.get().setMainPanel(cardOnlyGUI);
				ATMgui.get().setSelectionListener(7, new ActionListener() {
					// take recipt
					@Override
					public void actionPerformed(ActionEvent e) {
						ATMgui.get().display(GUIType.Login);
					}
					
				});
			}
			
		});
	}
	
	public JPanel getPanel() {
		return exitGUI;
	}
}
