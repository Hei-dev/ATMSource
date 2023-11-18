//Defaultgui.java
//interface for implementing default gui and its functions

/**
 * Default GUI Component name:
 * Title label: 
 * "Title"
 * 
 * Selection label:
 * "selection0"
 * "selection1"
 * "selection2"
 * "selection3"
 * "selection4"
 * "selection5"
 * "selection6"
 * "selection7"
 *  
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface Defaultgui {
 
	default JPanel getdefaultGUI() {
		JLabel defaultTitle = new JLabel();
	    JLabel defaultSelection[] = new JLabel[8];
		JPanel defaultPanel = new JPanel();
		GridBagConstraints c_interface;
		// create default center panel representing screen
		// create default screen component
	    // create screen Title
		defaultTitle.setHorizontalAlignment(JLabel.CENTER);
		defaultTitle.setVerticalAlignment(JLabel.CENTER);
		defaultTitle.setText("Sample Title");
		defaultTitle.setName("Title");		// create component title name
		//screenTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		
	    

		// create selection box
	    for (int i = 0; i < 8; i++) {
	    	defaultSelection[i] = new JLabel( String.valueOf(i));
	    	defaultSelection[i].setHorizontalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setVerticalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    	defaultSelection[i].setText(String.valueOf(i));
	    	defaultSelection[i].setName("selection"+ Integer.toString(i));		// create components selection names
	    }
	    
	

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
	    
	    return defaultPanel;
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
	
	default void setComponentText(JPanel panel, String componentName, String text, int style, int size) {
		JLabel temp;
		Font font = new Font(text, style, size);
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
		String componentName = "selection" + Integer.toString(selection);
		
		switch(String.valueOf(display)) {
		case "true":
			for(Component c : panel.getComponents()) {
				if ((c instanceof JLabel) && (c.getName()==componentName)) {
					temp = (JLabel)c;
					temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
					temp.setText("");
					c = temp;
				}
			}
		break;
		case "false":
			for(Component c : panel.getComponents()) {
				if ((c instanceof JLabel) && (c.getName()==componentName)) {
					temp = (JLabel)c;
					temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
					c = temp;
				}
			}
			break;
		}
		
	}
	
}
