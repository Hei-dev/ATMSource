//BaseATMgui.java
//The base gui outline of ATM

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;




public class BaseATMgui extends JFrame implements Defaultgui{
	
	private static JButton[] keys, selection;
	private static GridBagLayout hardwareLayout;
	private static JPanel
		keyPadPanel,
		leftSelectionPanel,
		rightSelectionPanel,
		centreBasePanel;
	private JTextPane textPane;
	private String line = "";
	private boolean activeFloatingPointButton;
	private GridBagConstraints 
		c_hardware,
		c_interface = new GridBagConstraints();
	private JPanel 
		defaultPanel,
		currentPanel;	
	private JLabel 
		screenTitle,
		screenSelection[];
	
	private boolean enableKeypad = true;
	
	protected static final int ATM_WIDTH = 538;
	protected static final int ATM_HEIGHT = 650;
	protected static final int DEFAULT_BORDER_WIDTH = 2;
	
	//private static final JLabel defaultTitle = new JLabel();
	private static final JTextPane defaultTextPane = new JTextPane();
	//private static final JLabel defaultSelection[] = new JLabel[8];
	//private static final JPanel defaultPanel = new JPanel();
	
	private Component findComponentByName(String name){
		return findComponentByName(name, getContentPane());
	}

	// Gets the components inside the main frame.
	private Component findComponentByName(String name, Container mContainer){
		Component returnCom = null;
		for(Component c : mContainer.getComponents()){
			if(c.getName()==name)
				return c;
			if(c instanceof Container)
				if((returnCom = findComponentByName(name,(Container)c))!=null)
					return returnCom;
		}
		return null;
	}
	
	protected BaseATMgui() {
		this("ATM");
	}

	protected BaseATMgui(String title) {
		super(title);
		
		/*
		// enable floating point button as default
		activeFloatingPointButton = true;
		
		// create default center panel representing screen
		// create default screen component
	    // create screen Title
		defaultTitle.setHorizontalAlignment(JLabel.CENTER);
		defaultTitle.setVerticalAlignment(JLabel.CENTER);
		defaultTitle.setText("Sample Title");
		defaultTitle.setName("Title");		// create component title name
		screenTitle = defaultTitle;
		//screenTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		
		
		
		

	    
	    // create selection box
	    for (int i = 0; i < 8; i++) {
	    	defaultSelection[i] = new JLabel( String.valueOf(i));
	    	defaultSelection[i].setHorizontalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setVerticalAlignment(JLabel.CENTER);
	    	defaultSelection[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	    	defaultSelection[i].setText(String.valueOf(i));
	    	defaultSelection[i].setName("selection"+ Integer.toString(i));		// create components selection names
	    }
	    screenSelection = defaultSelection;
	    

		// create default screen
	    defaultPanel.setLayout(new GridBagLayout());
	    clonePanel = defaultPanel;
	    
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
	    defaultPanel.add(screenTitle, c_interface);
	    
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
	    		clonePanel.add(screenSelection[selections], c_interface);
	    		selections++;
	    	}
	    }
	    */
	    
		defaultPanel = getdefaultGUI();
		
		// create text panel for reading input
		defaultTextPane.setEditable(false);    // set textArea not editable
	    defaultTextPane.setText(line);  // display line1 in textArea 
	    StyledDocument style = defaultTextPane.getStyledDocument();
	    SimpleAttributeSet align= new SimpleAttributeSet();
	    StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);	//set right alignment
	    style.setParagraphAttributes(0, style.getLength(), align, false);
	    defaultTextPane.setName("Input Area");		// create component textpanel name
		textPane = defaultTextPane;
		
	    // add textPane for showing input
		setTextPanel(defaultPanel);
	    //clonePanel.add(textPane, c_interface);
		
		// create left and right selection button panel
		// set left selection panel to Grid Layout
		leftSelectionPanel = new JPanel();
		leftSelectionPanel.setLayout( new GridLayout( 4 , 1 , 0 , 7) );
		leftSelectionPanel.setVisible(true);
		
		// set right selection panel to Grid Layout
		rightSelectionPanel = new JPanel();
		rightSelectionPanel.setLayout( new GridLayout( 4 , 1 , 0 , 7) );	
		rightSelectionPanel.setVisible(true);
		
		selection = new JButton[8];
		// initialize all selection button, TEMPORARY STRING VALUE FOR RECOGNITION
		// set the ActionCommand for button handler when temporary string value is deleted
		for ( int i = 0; i <= 7; i++ ) {
			selection[i] = new JButton( String.valueOf( i ) );
			selection[i].setActionCommand("selection" + String.valueOf(i));
		}

		// add 4 buttons to left selection panel
		for ( int i = 0; i <= 3; i++)
			leftSelectionPanel.add(selection[i]);
		
		// add 4 buttons to left selection panel
		for ( int i = 4; i <= 7; i++)
			rightSelectionPanel.add(selection[i]);
		
		
		
		// set keyPadPanel layout to grid layout
	    keyPadPanel = new JPanel();
	    keyPadPanel.setLayout( new GridLayout( 4 , 4 , 1, 1) );
	    keyPadPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		keyPadPanel.setVisible(true);
	    
		// add keypad panel
		keys = new JButton[15];
	    // initialize all digit key buttons
	    for ( int i = 0; i <= 9; i++ )
	    	keys[ i ] = new JButton( String.valueOf( i ) );
		
	    // initialize all function key buttons
	    keys[ 10 ] = new JButton( "CANCEL" );
	    keys[ 11 ] = new JButton( "CLEAR" );
	    keys[ 12 ] = new JButton( "ENTER" );
	    keys[ 13 ] = new JButton( "00" );
	    keys[ 14 ] = new JButton( "." );
	    
	    // add buttons to keyPadPanel panel
	    // 7, 8, 9, CANCEL
	    for ( int i = 7; i <= 10; i++ )
	    	keyPadPanel.add( keys[ i ] );
	    // 4, 5, 6
	    for ( int i = 4; i <= 6; i++ )
	    	keyPadPanel.add( keys[ i ] );
	    // CLEAR
	    keyPadPanel.add( keys[ 11 ] );	    
	    // 1, 2, 3
	    for ( int i = 1; i <= 3; i++ )
	    	keyPadPanel.add( keys[ i ] );
	    // ENTER
	    keyPadPanel.add( keys[ 12 ] );    
	    // 0, 00
	    keyPadPanel.add( keys[ 0 ] );
	    keyPadPanel.add( keys[ 14 ]);
	    keyPadPanel.add( keys[ 13 ] );

	    
	    
	    // set the hardware environment of the ATM 
	    // screen, select button, keypad
	    hardwareLayout = new GridBagLayout();
	    setLayout(hardwareLayout);

		c_hardware = new GridBagConstraints();
	    c_hardware.anchor = GridBagConstraints.CENTER;
	    
	    // override the default panel if setInterface() is Overrided
		//try{
		//	remove(findComponentByName("MainPanel"));
		//}
		//catch(NullPointerException npe){
			//System.err.println(npe);
		//}
	    //System.out.println("BaseATMgui Before currentPanel = setInterface();");
	    
	    //currentPanel = setInterface();
	    
	    //System.out.println("BaseATMgui After currentPanel = setInterface();" );
	    currentPanel = new JPanel();
		currentPanel.setName("MainPanel");
	    // put panel onto screen
	    c_hardware.weightx = 0.7;
	    c_hardware.weighty = 0.7;
	    c_hardware.anchor = GridBagConstraints.CENTER;
	    
	    c_hardware.gridy = 0;
	    c_hardware.gridx = 2;
	    c_hardware.gridwidth = 8;
	    c_hardware.gridheight = 13;
	    c_hardware.fill = GridBagConstraints.BOTH;
	    c_hardware.ipady = 200;
	    c_hardware.ipadx = 50;
	    c_hardware.insets = new Insets(5, 5, 5, 5);
		//currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		//currentPanel.setVisible(true);

		centreBasePanel = new JPanel(new BorderLayout());
		centreBasePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		centreBasePanel.add(currentPanel);

		add(centreBasePanel, c_hardware);

		
	    
	 	// add top and dummy component for left and right selection Panel
	 	c_hardware.gridy = 0;
	 	c_hardware.gridx = 0;
	 	c_hardware.gridwidth = 2;
	 	c_hardware.gridheight = 2;
	 	c_hardware.weightx = 0.3;
	 	c_hardware.weighty = 0.5;
	    c_hardware.ipady = 0;
	    c_hardware.ipadx = 0;
	    c_hardware.fill = GridBagConstraints.BOTH;
	    c_hardware.insets = new Insets(35,0,50,0);
	 	add(new JLabel(), c_hardware);	// top right dummy
	 	c_hardware.gridx = 10;
	 	add(new JLabel(), c_hardware);	// top left dummy
	 	c_hardware.gridy = 11;
	 	c_hardware.gridheight = 1;
	 	c_hardware.weighty = 0.3;
	 	c_hardware.insets = new Insets(0,0,0,0);
	 	add(new JLabel(), c_hardware);	// bottom right dummy
	 	c_hardware.gridx = 0;
	 	add(new JLabel(), c_hardware);	// bottom left dummy
	 	
	    // add left selection Panel
	    c_hardware.gridy = 2;
	    c_hardware.gridx = 0;
	    c_hardware.gridwidth = 2;
	    c_hardware.gridheight = 8;
	    c_hardware.weightx = 0.3;
	    c_hardware.weighty = 0.7;
	    c_hardware.ipady = 0;
	    c_hardware.ipadx = 0;
	    c_hardware.fill = GridBagConstraints.BOTH;
	    add(leftSelectionPanel, c_hardware);
	    
	    //add right selection Panel;
	    c_hardware.gridx = 10;
	    add(rightSelectionPanel, c_hardware);
	    
	    //add keyPad
	    c_hardware.gridy = 13;
	    c_hardware.gridx = 4;
	    c_hardware.gridwidth = 4;
	    c_hardware.gridheight = 4;
	    c_hardware.weightx = 0.5;
	    c_hardware.weighty = 0.3;
	    c_hardware.fill = GridBagConstraints.NONE;
	    c_hardware.ipady = 100;
	    c_hardware.ipadx = 0;
	    c_hardware.insets = new Insets(5, 25, 5, 25);
	    add(keyPadPanel, c_hardware);   
	    	    
	    
	    
	    // create handler for keypad buttons
  		ActionListener keyPadListener = new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent event) {
  			
 				// enter numbers to text pane, divide buttons ENTER, CANCEL, CLEAR functionality
 				if (((event.getActionCommand() == "CANCEL") || (event.getActionCommand() == "ENTER") || (event.getActionCommand() == "CLEAR")) && (enableKeypad == true)) {
 					switch (event.getActionCommand()) {
 					// Pressing CANCEL, same as backspace
 					case "CANCEL":
 						if (line != "") {
 							line = line.substring(0, line.length() - 1);
 							textPane.setText(line);
 						}
 						// System.out.printf("Text:%s%nLength:%s%nText in Integer:%d%n%n", line, line.length(), Long.parseLong(line));
 						break;
 					// Pressing CLEAR, clear the text from textPane
 					case "CLEAR":
 						line = "";
 						textPane.setText(line);
 						// System.out.printf("Text:%s%nLength:%s%nText in Integer:%d%n%n", line, line.length(), Long.parseLong(line));
 						break;
 					// Pressing ENTER
 					case "ENTER":
 						enter();
 						// System.out.printf("Before:%nText:%s%nLength:%s%nText in Integer:%id%n%n", line, line.length(), Long.parseLong(line));
 						line = "";
 						// System.out.printf("After:%nText:%s%nLength:%s%nText in Integer:%d%n%n", line, line.length(),Long.parseLong(line));
 						textPane.setText(line);
 						break;
 					}
 				} else if (enableKeypad == true) {
 					/**
 					 * add numbers to text Pane
 					 * check if 
 					 * 1. "." exist in line
 					 */
 					if (event.getActionCommand() != ".") {
 						line = line.concat(event.getActionCommand());
 						textPane.setText(line);
 					}
 					// check if "." exist in line
 					else if ((event.getActionCommand() == ".") && (!line.contains(".")) && (activeFloatingPointButton == true)) {
 						// setting of Floating Point numbers, only 1 "." can exist once
 						// remember to set back to true after finishing the whole input if set to false
 						
 						// check if length of string > 0 and if floating point is enabled
 						if (line.length() > 0) {
 								line = line.concat(".");
 								textPane.setText(line);
 						}
 						//check if the first input is "."
 						else {
 							line = "0.";
 							textPane.setText(line);	
 						}
 					}
 					//System.out.printf("Text:%s%nLength:%s%nText in Integer:%f%n%n", line, line.length(), Double.parseDouble(line));
 				}
  			}
  		};
  		
  		/**
  		
  		*/
  		
  		// register event handler 
  	    // 0 - 9 , CANCEL , CLEAR , ENTER , 00
  		for (int i = 0; i<=14; i++)
  			keys[i].addActionListener(keyPadListener);
  		
  		
  		// Side buttons
	    //for (int i = 0; i<=7; i++) {
	    //	selection[i].addActionListener(selectionListener); 
  		
	}
	

	
	//	GUI RELATED	#############################################
	/*
	public void removeCurrentPanel() {
		centreBasePanel.remove(currentPanel);
	}
	*/
	
	public void addMainPanel(JPanel panel){
		//System.out.println("BaseATMgui setMainPanel in");
		
		currentPanel = (JPanel)findComponentByName("MainPanel");
		try	{
			centreBasePanel.remove(currentPanel);
		} catch (NullPointerException npt) {
			System.out.println(npt);
		}
		
		currentPanel = panel;
		currentPanel.setName("MainPanel");
		centreBasePanel.add(currentPanel);
		
		
		//centreBasePanel.add(panel);
		
		revalidate();
		repaint();
		//System.out.println("BaseATMgui setMainPanel out");
	}
	
	/**
	 * allow the user to get the default interface
	 * @return JPanel of the default interface
	 */
	/**
	public JPanel getdefaultGUI() {
		//System.out.println("BaseATMgui getdefaultGUI");
		clonePanel = defaultPanel;
		return clonePanel;
	}
	*/
	/**
	 * add text panel to your screen component
	 * @param Jpanel of your panel 
	 */
	public void setTextPanel(JPanel panel) {
	    c_interface.gridx = 0;
	    c_interface.gridy = 13;
	    c_interface.gridwidth = 8;
	    c_interface.gridheight = 1;
	    c_interface.weightx = 1;
	    c_interface.weighty = 0.001;
	    panel.add(textPane, c_interface);
	}
	
	/**
	 * set availability of keypad (default true)
	 * @param boolean enable true/false
	 */
	public void setKeypadAvailability(boolean enable) {
		enableKeypad = enable;
	}
	
	/**
	 * set Name of selection in defaultPanel
	 * @param int of selection box number
	 * @param String name of selection
	 */
	/*
	public void setSelectionName(int selection, String name) {
		screenSelection[selection].setText(name);
	}
	*/
	/**
	 * toggle the display of selection in the screen
	 * @param int of selection box number
	 * @param boolean display true/false
	 */
	/**
	public void setSelectionDisplay(JPanel panel, int selection, boolean display) {
		JLabel temp;
		String componentName = "selection" + Integer.toString(selection);
		
		switch(String.valueOf(display)) {
		case "true":
			for(Component c : panel.getComponents()) {
				if ((c instanceof JLabel) && (c.getName()==componentName)) {
					temp = (JLabel)c;
					temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
					temp.setText("");
					c = temp;
				}
			}
		break;
		case "false":
			for(Component c : panel.getComponents()) {
				if ((c instanceof JLabel) && (c.getName()==componentName)) {
					temp = (JLabel)c;
					temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
					c = temp;
				}
			}
			break;
		}
		
	}
	*/
 
	
	/**
	 * replace "Sample Title" to custom title
	 * @param String of custom title
	 */
	/**
	public void setTitle(String title) {
		screenTitle.setText(title);
	}
	*/
	/**
	 * replace "Sample Title" to custom title, with custom Font style
	 * @param String of custom title
	 * @param int of font style
	 * @param int of font size
	 */
	/*
	public void setTitle(String title, int style, int size) {
		Font font = new Font(title, style, size);
		screenTitle.setFont(font);
		screenTitle.setText(title);
	}
	*/
	
	/**
	 * set component text inside copied panel
	 * @param JPanel panel of your panel
	 * @param String componentName of the component you want to change
	 * @param String text of the component
	 */
	/*
	public void setComponentText(JPanel panel, String componentName, String text) {
		JLabel temp;
		for(Component c : panel.getComponents()) {
			if ((c instanceof JLabel) && (c.getName()==componentName)) {
				temp = (JLabel)c;
				temp.setText(text);
				c = temp;
			}
		}
	}
	*/
	/**
	 * set component text inside copied panel, including the font and size
	 * @param JPanel panel of your panel
	 * @param String componentName of the component you want to change
	 * @param String text of the component
	 * @param int style of the font
	 * @param int size of the font
	 */
	/*
	public void setComponentText(JPanel panel, String componentName, String text, int style, int size) {
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
	*/
	/**
	 * @Override this function to edit the GUI Screen
	 * access the screen
	 * @return defaultPanel
	 */
	/**
	public JPanel setInterface() {
		//setMainPanel(getdefaultGUI());
		//System.out.println("BastATMgui setInterface() out");
	    return getdefaultGUI();
	}
	*/
	
	
	//	FUNCTIONALITY RELATED	####################################################
	
	/**
	 * @Override to change functionality of enter button
	 * functionality of enter button
	 * default as getting number from text pane
	 * @return String of inputed numbers
	 */
	public String enter() {	
		return getText();
	}
	
	// method of 8 selection buttons
	/**
	 * change the functionality of different buttons
	 * default as none
	 * @param int i of btnNo of specific button in selection panel
	 * @param ActionListener al of the specific button event
	 */	
	public void setSelectionListener(int i, ActionListener al) {
		selection[i].addActionListener(al);
	}
	
	/**
	 * @Override this method by putting all setSelectionListener in this method
	 * update all selection event to class specific event
	 */
	public void setallSelectionListener() {
		for (int i = 1; i < 8; i++)
			selection[i].addActionListener(null);
	}	
	
	/**
	 * return number from text pane in String
	 * @return String of inputed numbers
	 */
	public String getText() {
		return textPane.getText();
	}	
	
	/**
	 * To enable/disable "." Button
	 * @param boolean enable true/false
	 */
	public void setFloatingPointButtonStatus(boolean enable) {
		activeFloatingPointButton = enable;
	}	
	
	public void run() {
		ATMgui.get().display(GUIType.MainMenu);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ATM_WIDTH, ATM_HEIGHT);	//set frame size
		setVisible(true);	//display frame
		setResizable(false);	//disable resizing window
	}
}

