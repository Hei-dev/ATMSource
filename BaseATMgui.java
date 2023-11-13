//BaseATMgui.java
//The base gui outline of ATM

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BaseATMgui extends JFrame {
	
	private static JButton keys[], selection[];
	private static GridBagLayout hardwareLayout;
	private static JPanel
		keyPadPanel,
		leftSelectionPanel,
		rightSelectionPanel;
	private JTextPane textPane;
	private String line = "";
	private boolean 
		activeFloatingPoint = true,
		activeFloatingPointButton = true;
	private GridBagConstraints 
		c_hardware,
		c_interface;
	private JPanel 
		defaultPanel,
		currentPanel;	
	private JLabel 
		screenTitle,
		screenSelection[];
	
	protected static final int ATM_WIDTH = 540;
	protected static final int ATM_HEIGHT = 650;
	protected static final int DEFAULT_BORDER_WIDTH = 2;
	
	private boolean textPaneVisible = true;
	
	protected BaseATMgui() {
		this("ATM");
	}

	protected BaseATMgui(String title) {
		super(title);
		
		// create default center panel representing screen
		// create default screen component
	    // create screen Title
		screenTitle = new JLabel();
		screenTitle.setHorizontalAlignment(JLabel.CENTER);
		screenTitle.setVerticalAlignment(JLabel.CENTER);
		screenTitle.setText("Sample Title");
		//screenTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		
		
		
		// create text panel for reading input
		textPane = new JTextPane();
		textPane.setEditable(false);    // set textArea not editable
	    textPane.setText(line);  // display line1 in textArea 
	    StyledDocument style = textPane.getStyledDocument();
	    SimpleAttributeSet align= new SimpleAttributeSet();
	    StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);	//set right alignment
	    style.setParagraphAttributes(0, style.getLength(), align, false);
		// default not visible
	    textPane.setVisible(textPaneVisible);
		

	    
	    // create selection box
	    screenSelection = new JLabel[8];
	    for (int i = 0; i < 8; i++) {
	    	screenSelection[i] = new JLabel( String.valueOf(i));
	    	screenSelection[i].setHorizontalAlignment(JLabel.CENTER);
	    	screenSelection[i].setVerticalAlignment(JLabel.CENTER);
	    	screenSelection[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
	    }
	    

		// create default screen
	    defaultPanel = new JPanel();
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
	    		defaultPanel.add(screenSelection[selections], c_interface);
	    		selections++;
	    	}
	    }
	    
	    // add textPane for showing input
	    c_interface.gridx = 0;
	    c_interface.gridy = 13;
	    c_interface.gridwidth = 8;
	    c_interface.gridheight = 1;
	    c_interface.weightx = 1;
	    c_interface.weighty = 0.001;
	    defaultPanel.add(textPane, c_interface);
		
		// create left and right selection button panel
		// set left selection panel to Grid Layout
		leftSelectionPanel = new JPanel();
		leftSelectionPanel.setLayout( new GridLayout( 4 , 1 , 0 , 10) );
		//leftSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		leftSelectionPanel.setVisible(true);
		
		// set right selection panel to Grid Layout
		rightSelectionPanel = new JPanel();
		rightSelectionPanel.setLayout( new GridLayout( 4 , 1 , 0 , 10) );	
		//rightSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		rightSelectionPanel.setVisible(true);
		
		selection = new JButton[8];
		// initialize all selection button, TEMPORARY STRING VALUE FOR RECOGNITION
		for ( int i = 0; i <= 7; i++ )
		selection[i] = new JButton( String.valueOf( i ) );

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
	    
	    // check whether currentPanel is null, else set default interface
	    currentPanel = setInterface();
	    setPanel();
	    
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
	  
	    // create handler for buttons
	    ButtonHandler handler = new ButtonHandler();
	    // register event handler 
	    // 0 - 9 , CANCEL , CLEAR , ENTER , 00
	    for (int i = 0; i<=14; i++)
	    	keys[i].addActionListener(handler);
	    
	    for (int i = 0; i<=7; i++)
	    	selection[i].addActionListener(handler);
	    
	    setActionListener();
	}
	
	//inner class for button 
	public class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			// ActionListener of NumPad
			// enter numbers to text pane, divide buttons ENTER, CANCEL, CLEAR functionality
			if (((event.getActionCommand() == "CANCEL") || (event.getActionCommand() == "ENTER") || (event.getActionCommand() == "CLEAR")) && (textPaneVisible == true)) {
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
			} else {
				// check if string has "."
				activeFloatingPoint = !line.contains(".");
				// add numbers to text Pane
				// check if 
				// 1. "." exist in line
				// 2. "." button is active
				if ((event.getActionCommand() == ".") && (activeFloatingPoint == true) && (activeFloatingPointButton == true)) {
					setFloatingPointSetting();
				}
				if (event.getActionCommand() != ".") {
					line = line.concat(event.getActionCommand());
					textPane.setText(line);
				}
				//System.out.printf("Text:%s%nLength:%s%nText in Integer:%f%n%n", line, line.length(), Double.parseDouble(line));
			}
			
			// ActionListener of Selection Panel
			
			
		}
	}
	
	//	GUI RELATED	#############################################
	
	
	// return default interface
	public JPanel getdefaultGUI() {
		return defaultPanel;
	}

	// return text pane component and set visibility to true
	public JTextPane getTextPanel() {
		textPaneVisible = true;
		return textPane;
	}
	
	// set visibility of text pane
	public void setTextPanelVisibility(boolean visible) {
		textPaneVisible = visible;
	}
	
	// put panel onto screen
	public void setPanel() {
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
		currentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, DEFAULT_BORDER_WIDTH));
		currentPanel.setVisible(true);
	    add(currentPanel, c_hardware);
	}
	
	// set Name of selection in defaultPanel
	public void setSelectionName(int selection, String name) {
		screenSelection[selection].setText(name);
	}
	
	// replace "Sample Title" to custom title
	public void setTitle(String title) {
		screenTitle.setText(title);
	}
	
	// access the screen
	// Override this function to edit the GUI Screen
	public JPanel setInterface() {
	    return defaultPanel;
	}
	
	// Override to set actionlisteners
	public void setActionListener() {
		
	}
	
	//	FUNCTIONALITY RELATED	####################################################
	
	// default as getting number from text pane
	// can be Override for other functionality (e.g. confirmation) if needed
	public String enter() {	
		return getText();
	}
	
	// return number from text pane in String
	public String getText() {
		return textPane.getText();
	}
	
	// enabling / disabling "." button
	public void setFloatingPointButton_Status(boolean active) {
		activeFloatingPointButton = active;
	}
	
	// return state of "." button
	public boolean getActiveFloatingPointButton_Status() {
		return activeFloatingPointButton;
	}
	
	// setting of Floating Point numbers, only 1 "." can exist once
	// remember to set back to true after finishing the whole input if set to false
	private void setFloatingPointSetting() {
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
	
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ATM_WIDTH, ATM_HEIGHT);	//set frame size
		setVisible(true);	//display frame
		//setResizable(false);	//disable resizing window
	}
}

