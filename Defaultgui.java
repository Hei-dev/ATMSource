//Defaultgui.java
//interface for implementing default gui and its functions

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public interface Defaultgui {
	
	final String 
		TITLE_LABEL = "Title",
		INPUT_AREA_PANEL = "Input Area",
		SELECTION0_LABEL = "selection0",
		SELECTION1_LABEL = "selection1",
		SELECTION2_LABEL = "selection2",
		SELECTION3_LABEL = "selection3",
		SELECTION4_LABEL = "selection4",
		SELECTION5_LABEL = "selection5",
		SELECTION6_LABEL = "selection6",
		SELECTION7_LABEL = "selection7",
		DEFAULT_PANEL = "DefaultPanel";
	
	default JPanel getdefaultGUI() {
		
		JLabel defaultTitle = new JLabel();
	    JLabel defaultSelection[] = new JLabel[8];
		JPanel defaultPanel = new JPanel();
		JTextPane defaultTextPane = new JTextPane();
		GridBagConstraints c_interface;
		// create default center panel representing screen
		// create default screen component
	    // create screen Title
		defaultTitle.setHorizontalAlignment(JLabel.CENTER);
		defaultTitle.setVerticalAlignment(JLabel.CENTER);
		defaultTitle.setText("Sample Title");
		defaultTitle.setName(TITLE_LABEL);		// create component title name
		//screenTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		
	    

		// create selection box
	    for (int i = 0; i < 8; i++) {
	    	defaultSelection[i] = new JLabel( String.valueOf(i));
	    	defaultSelection[i].setHorizontalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setVerticalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    	defaultSelection[i].setText(String.valueOf(i));
	    	defaultSelection[i].setName(getSelectionName(i));		// create components selection names
	    }
	    
	    // create text panel for reading input
 		defaultTextPane.setEditable(false);    // set textArea not editable
 	    //defaultTextPane.setText(line);  // display line1 in textArea 
 	    StyledDocument style = defaultTextPane.getStyledDocument();
 	    SimpleAttributeSet align= new SimpleAttributeSet();
 	    StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);	//set right alignment
 	    style.setParagraphAttributes(0, style.getLength(), align, false);
 	    defaultTextPane.setName(INPUT_AREA_PANEL);		// create component textpanel name
	    
	

		// create default screen
	    defaultPanel.setLayout(new GridBagLayout());
	    
	    c_interface = new GridBagConstraints();	  	    
	    
	    // add title label
	    c_interface.gridx = 0;
	    c_interface.gridy = 0;
	    c_interface.gridwidth = 8;
	    c_interface.gridheight = 4;
	    c_interface.weightx = 1;
	    c_interface.weighty = 0.4;
	    c_interface.fill = GridBagConstraints.BOTH;
	    defaultPanel.add(defaultTitle, c_interface);
	    
	    // add selections
		c_interface.gridwidth = 4;
		c_interface.gridheight = 2;
		c_interface.weightx = 0.5;
		c_interface.weighty = 0.125;
		c_interface.fill = GridBagConstraints.BOTH;
	    int selections = 0;
	    for (int y = 4; y < 12; y += 2) {
	    	for (int x = 0; x < 8; x += 4) {
	    		c_interface.gridx = x;
	    		c_interface.gridy = y;
	    		defaultPanel.add(defaultSelection[selections], c_interface);
	    		selections++;
	    	}
	    }
 	    
 	    c_interface.gridx = 0;
	    c_interface.gridy = 13;
	    c_interface.gridwidth = 8;
	    c_interface.gridheight = 1;
	    c_interface.weightx = 1;
	    c_interface.weighty = 0.001;
	    defaultPanel.add(defaultTextPane, c_interface);
	    
	    defaultPanel.setName(DEFAULT_PANEL);
	    
	    return defaultPanel;
	}
	
	/**
	 * get input from textpane
	 * @param panel of current panel
	 * @return text of input text from keyboard
	 */
	default String getTextPaneText(JPanel panel) {
		String text = null;
		JTextPane temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JTextPane) && (c.getName()==INPUT_AREA_PANEL)) {
				temp = (JTextPane)c;
				//System.out.println("text: " + temp.getText());
				text = temp.getText();
			}
		} 
		return text;
	}
	
	/**
	 * change text of textpane
	 * @param panel
	 * @param text
	 */	
	default void setTextPaneText(JPanel panel,String text) {
		JTextPane temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JTextPane) && (c.getName()==INPUT_AREA_PANEL)) {
				temp = (JTextPane)c;
				temp.setText(text);
				c = temp;
			}
		} 
	}
	
	/**
	 * set component text inside copied panel
	 * @param JPanel panel of your panel
	 * @param String componentName of the component you want to change
	 * @param String text of the component
	 */
	default void setComponentText(JPanel panel, String componentName, String text) {
		JLabel temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JLabel) && (c.getName()==componentName)) {
				temp = (JLabel)c;
				temp.setText(text);
				c = temp;
			}
		}
	}
	
	/**
	 * set component text inside copied panel, including the font and size
	 * @param JPanel panel of your panel
	 * @param String componentName of the component you want to change
	 * @param String text of the component
	 * @param int style of the font
	 * @param int size of the font
	 */
	
	default void setComponentText(JPanel panel, String componentName, String text, Font font) {
		JLabel temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JLabel) && (c.getName()==componentName)) {
				temp = (JLabel)c;
				temp.setFont(font);
				temp.setText(text);
				c = temp;
			}
		}
	}
	
	/**
	 * toggle the display of selection in the screen
	 * @param int of selection box number
	 * @param boolean display true/false
	 */
	default void setSelectionDisplay(JPanel panel, int selection, boolean display) {
		JLabel temp;
		String componentName = getSelectionName(selection);
		
		if (display == false) {
			for(Component c : panel.getComponents()) {
				if ((c instanceof JLabel) && (c.getName()==componentName)) {
					temp = (JLabel)c;
					temp.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
					temp.setText("");
					c = temp;
				}
			}
		} else if (display == true) {
			for(Component c : panel.getComponents()) {
				if ((c instanceof JLabel) && (c.getName()==componentName)) {
					temp = (JLabel)c;
					temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
					c = temp;
				}
			}
		}
	}
	
	/**
	 *  set the name of selections
	 * @param selection
	 * @return selection name
	 */
	private String getSelectionName(int selection) {
		switch(selection) {
		case 0:
			return SELECTION0_LABEL;
		case 1:
			return SELECTION1_LABEL;
		case 2:
			return SELECTION2_LABEL;
		case 3:
			return SELECTION3_LABEL;
		case 4:
			return SELECTION4_LABEL;
		case 5:
			return SELECTION5_LABEL;
		case 6:
			return SELECTION6_LABEL;
		case 7:
			return SELECTION7_LABEL;
		default:
			return null;
		}
	}
}
