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
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public interface Defaultgui {
 
	default JPanel getdefaultGUI() {
		System.out.println("Defaultgui create gui");
		
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
		defaultTitle.setName("Title");		// create component title name
		//screenTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		
	    

		// create selection box
	    for (int i = 0; i < 8; i++) {
	    	defaultSelection[i] = new JLabel( String.valueOf(i));
	    	defaultSelection[i].setHorizontalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setVerticalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    	defaultSelection[i].setText(String.valueOf(i));
	    	defaultSelection[i].setName(setSelectionName(i));		// create components selection names
	    	System.out.println(defaultSelection[i].getName());
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
	    
	    // create text panel for reading input
 		defaultTextPane.setEditable(false);    // set textArea not editable
 	    //defaultTextPane.setText(line);  // display line1 in textArea 
 	    StyledDocument style = defaultTextPane.getStyledDocument();
 	    SimpleAttributeSet align= new SimpleAttributeSet();
 	    StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);	//set right alignment
 	    style.setParagraphAttributes(0, style.getLength(), align, false);
 	    defaultTextPane.setName("Input Area");		// create component textpanel name
 	    
 	    c_interface.gridx = 0;
	    c_interface.gridy = 13;
	    c_interface.gridwidth = 8;
	    c_interface.gridheight = 1;
	    c_interface.weightx = 1;
	    c_interface.weighty = 0.001;
	    defaultPanel.add(defaultTextPane, c_interface);
	    System.out.println("Defaultgui output default gui");
	    

	    return defaultPanel;
	}
	/**
	 * 
	 * @param panel of current panel
	 * @return text of input text from keyboard
	 */
	default String getTextPaneText(JPanel panel) {
		String text = null;
		JTextPane temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JTextPane) && (c.getName()=="Input Area")) {
				System.out.println("has input area");
				temp = (JTextPane)c;
				text = temp.getText();
			}
		} 
		return text;
	}
	
	default void updateTextPane(JPanel panel,String text) {
		JTextPane temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JTextPane) && (c.getName()=="Input Area")) {
				//System.out.println("has input area");
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
		String componentName = setSelectionName(selection);
		
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
	
	private String setSelectionName(int selection) {
		String compName = new String();
		switch(selection) {
		case 0:
			compName = "selection0";
			break;
		case 1:
			compName = "selection1";
			break;
		case 2:
			compName = "selection2";
			break;
		case 3:
			compName = "selection3";
			break;
		case 4:
			compName = "selection4";
			break;
		case 5:
			compName = "selection5";
			break;
		case 6:
			compName = "selection6";
			break;
		case 7:
			compName = "selection7";
			break;
		default:
			compName = null;
			break;
		}
		
		return compName;
	}
}
