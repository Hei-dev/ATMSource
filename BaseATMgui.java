//BaseATMgui.java
//The base gui outline of ATM

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BaseATMgui extends JFrame {
	
	private static JButton keys[], selection[];
	private static JPanel
		guiLayout,
		keyPadPanel,
		leftSelectionPanel,
		rightSelectionPanel,
		centerPanel;
	private JTextPane textPane;
	private String line = "";
	private boolean 
		activeFloatingPoint = true,
		activeFloatingPointButton = true;
	private GridBagConstraints c;
	private JPanel 
		defaultPanel,
		upperGUIPanel,
		lowerGUIPanel,
		textPanel;			
	
	protected static final int ATM_WIDTH = 540;
	protected static final int ATM_HEIGHT = 650;
	
	protected BaseATMgui() {
		this("ATM");
	}
	
	protected BaseATMgui(String title) {
		super(title);
		
		// set upper half gui panel
		guiLayout = new JPanel();
		guiLayout.setLayout( new BorderLayout() );

		
		
		// set up default GUI Panel
		defaultPanel = new JPanel();
		defaultPanel.setLayout(new GridBagLayout());
		//defaultPanel.setPreferredSize(new Dimension());
		defaultPanel.setVisible(true);
		
		c = new GridBagConstraints();

		// divide centreGridPanel to upper and lower panel
		
        defaultPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
		
        c.insets = new Insets(5, 5, 5, 5);
        
		// upper panel for showing title or description
		JButton upperGUIButton = new JButton();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		defaultPanel.add(upperGUIButton, c);
		
		// lower panel for showing choices
		lowerGUIPanel = new JPanel();
		c.gridx = 0;
		c.gridy = 8;
		lowerGUIPanel.add(new JButton("LOWER"));
		defaultPanel.add(lowerGUIPanel, c);
		
		
		
			
		
		// set center screen to allocate different types of screen
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		centerPanel.setVisible(true);
		setdefaultGUI();
		
		
		
		// set left selection panel to Grid Layout
		leftSelectionPanel = new JPanel();
		leftSelectionPanel.setLayout( new GridLayout( 8 , 1 , 0 , 30) );
		
		// set right selection panel to Grid Layout
		rightSelectionPanel = new JPanel();
		rightSelectionPanel.setLayout( new GridLayout( 8 , 1 , 0 , 30) );				
		
		selection = new JButton[8];
		// initialize all selection button, TEMPORARY STRING VALUE FOR RECOGNITION
		for ( int i = 0; i <= 7; i++ )
		selection[i] = new JButton( String.valueOf( i ) );
		
		// skip 3 grid in leftSelectionPanel
		for ( int i = 0; i < 3; i++)
		leftSelectionPanel.add(new JLabel());
		// add 4 buttons to left selection panel
		for ( int i = 0; i <= 3; i++)
			leftSelectionPanel.add(selection[i]);
		
		// skip 3 grid in rightSelectionPanel
		for ( int i = 0; i < 3; i++)
			rightSelectionPanel.add(new JLabel());
		// add 4 buttons to left selection panel
		for ( int i = 4; i <= 7; i++)
			rightSelectionPanel.add(selection[i]);
		
		
		
		//setTextPanel();
		textPane = new JTextPane();
		textPane.setEditable(false);    // set textArea not editable
	    textPane.setText(line);  // display line1 in textArea 
	    StyledDocument style = textPane.getStyledDocument();
	    SimpleAttributeSet align= new SimpleAttributeSet();
	    StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);	//set right alignment
	    style.setParagraphAttributes(0, style.getLength(), align, false);
	    
		// create panel for text box
		textPanel = new JPanel();
		textPanel.setLayout( new BorderLayout( 40 , 40 ) );
		//set text box to center
		textPanel.add(textPane, BorderLayout.CENTER);
		
		
		
		
		
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

	    // set keyPadPanel layout to grid layout
	    keyPadPanel = new JPanel();
	    keyPadPanel.setLayout( new GridLayout( 4 , 6 , 5 , 5) );
	    
	    // skip a grid
	    keyPadPanel.add(new JLabel());
	    
	    // add buttons to keyPadPanel panel
	    // 7, 8, 9, CANCEL
	    for ( int i = 7; i <= 10; i++ )
	    	keyPadPanel.add( keys[ i ] );
	    
	    // skip a grid
	    keyPadPanel.add(new JLabel());
	    keyPadPanel.add(new JLabel());

	    // 4, 5, 6
	    for ( int i = 4; i <= 6; i++ )
	    	keyPadPanel.add( keys[ i ] );

	    // CLEAR
	    keyPadPanel.add( keys[ 11 ] );
	    
	    // skip a grid
	    keyPadPanel.add(new JLabel());
	    keyPadPanel.add(new JLabel());
	    
	    // 1, 2, 3
	    for ( int i = 1; i <= 3; i++ )
	    	keyPadPanel.add( keys[ i ] );

	    // ENTER
	    keyPadPanel.add( keys[ 12 ] );
	    
	    // skip a grid
	    keyPadPanel.add(new JLabel());
	    keyPadPanel.add(new JLabel());
	    
	    // 0, 00
	    keyPadPanel.add( keys[ 0 ] );
	    keyPadPanel.add( keys[ 14 ]);
	    keyPadPanel.add( keys[ 13 ] );
	    
	    
	    
	    // add screen
	    guiLayout.add(centerPanel , BorderLayout.CENTER);
	    // add selection areas
	    guiLayout.add(leftSelectionPanel , BorderLayout.WEST);
	    guiLayout.add(rightSelectionPanel , BorderLayout.EAST);
	    
	    // add text area
	    // guiLayout.add( textPanel , BorderLayout.SOUTH );
	    
	    // add guiLayout
	    add( guiLayout , BorderLayout.CENTER );
	    // add keypad area
	    add( keyPadPanel , BorderLayout.SOUTH );
	    
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
			if ((event.getActionCommand() == "CANCEL") || (event.getActionCommand() == "ENTER") || (event.getActionCommand() == "CLEAR")) {
				switch (event.getActionCommand()) {
				// Pressing CANCEL, same as backspace
				case "CANCEL":
					line = line.substring(0, line.length() - 1);
					textPane.setText(line);
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
	
	
	
	public void setdefaultGUI() {			
		setGUIScreen(defaultPanel);
	}
	
	// return default gui
	public JPanel getdefaultGUI() {
		return defaultPanel;
	}
	
	// access the screen
	// use setGUIScreen([YourJPanel]); to edit the GUI Screen
	public void setGUIScreen(JPanel pane) {
		centerPanel.add(pane);
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
		setResizable(false);	//disable resizing window
	}
}

