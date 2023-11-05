//BaseATMgui.java
//The base gui outline of ATM

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BaseATMgui extends JFrame {
	
	private JButton keys[], selection[], choice[];
	private JPanel guiLayout , keyPadPanel, leftSelectionPanel, rightSelectionPanel, centerPanel, textPanel;
	private JTextPane textPane;
	private String line = "";
	
	public BaseATMgui() {
		this("ATM");
	}
	
	public BaseATMgui(String title) {
		super(title);
		
		// set upper half gui panel
		guiLayout = new JPanel();
		guiLayout.setLayout( new BorderLayout() );
		
		// set center screen to Grid Layout
		centerPanel = new JPanel();
		centerPanel.setLayout( new GridLayout( 4 , 2 , 10 , 10) );
		
		// set left selection panel to Grid Layout
		leftSelectionPanel = new JPanel();
		leftSelectionPanel.setLayout( new GridLayout( 6 , 1 , 0 , 50) );
		
		// set right selection panel to Grid Layout
		rightSelectionPanel = new JPanel();
		rightSelectionPanel.setLayout( new GridLayout( 6 , 1 , 0 , 50) );				
		
		selection = new JButton[8];
		// initialize all selection button, TEMPORARY STRING VALUE FOR RECOGNITION
		for ( int i = 0; i <= 7; i++ )
		selection[i] = new JButton( String.valueOf( i ) );
		
		// skip a grid in leftSelectionPanel
		leftSelectionPanel.add(new JLabel());
		// add 4 buttons to left selection panel
		for ( int i = 0; i <= 3; i++)
			leftSelectionPanel.add(selection[i]);
		
		// skip a grid in rightSelectionPanel
		rightSelectionPanel.add(new JLabel());
		// add 4 buttons to left selection panel
		for ( int i = 4; i <= 7; i++)
			rightSelectionPanel.add(selection[i]);
		
		// set textArea
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
		//create space for east and west
		textPanel.add(new JLabel("			"), BorderLayout.EAST);
		textPanel.add(new JLabel("			"), BorderLayout.WEST);
		
	    keys = new JButton[14];
	    // initialize all digit key buttons
	    for ( int i = 0; i <= 9; i++ )
	    	keys[ i ] = new JButton( String.valueOf( i ) );

	    // initialize all function key buttons
	    keys[ 10 ] = new JButton( "CANCEL" );
	    keys[ 11 ] = new JButton( "CLEAR" );
	    keys[ 12 ] = new JButton( "ENTER" );
	    keys[ 13 ] = new JButton( "00" );

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
	    keyPadPanel.add(new JLabel());
	    
	    // 0, 00
	    keyPadPanel.add( keys[ 0 ] );
	    keyPadPanel.add( keys[ 13 ] );
	    
	    // add screen
	    guiLayout.add(centerPanel , BorderLayout.CENTER);
	    // add selection areas
	    guiLayout.add(leftSelectionPanel , BorderLayout.WEST);
	    guiLayout.add(rightSelectionPanel , BorderLayout.EAST);
	    // add text area
	    guiLayout.add( textPanel , BorderLayout.SOUTH );
	    // add guiLayout
	    add( guiLayout , BorderLayout.CENTER );
	    // add keypad area
	    add( keyPadPanel , BorderLayout.SOUTH );
	    
	    //create handler for buttons
	    ButtonHandler handler = new ButtonHandler();
	    //register event handler
	    for (int i = 0; i<=13;i++)
	    	 keys[i].addActionListener(handler);
	}
	
	//inner class for button 
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			line = line.concat(event.getActionCommand());
			textPane.setText(line);
		}
	}
	
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);	//set frame size
		setVisible(true);	//display frame
	}
}

