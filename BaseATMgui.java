//BaseATMgui.java
//The base gui outline of ATM

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BaseATMgui extends JFrame {
	
	private JButton keys[], selection[], choice[];
	private JPanel
		guiLayout ,
		keyPadPanel,
		leftSelectionPanel,
		rightSelectionPanel,
		centerPanel,
		centreGridPanel,
		textPanel;
	private JTextPane textPane;
	private JLabel centreOneLinePanel;
	private String line = "";
	
	protected BaseATMgui() {
		this("ATM");
	}
	
	protected BaseATMgui(String title) {
		super(title);
		
		// set upper half gui panel
		guiLayout = new JPanel();
		guiLayout.setLayout( new BorderLayout() );

		// set centre screen to allocate different types of screen
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, (int) BoxLayout.PAGE_AXIS));
		
		// set center screen to Grid Layout
		centreGridPanel = new JPanel();
		centreGridPanel.setLayout( new GridLayout( 4 , 2,5,5) );
		centreGridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centreGridPanel.setVisible(true);

		centreGridPanel.add(new JLabel("TEst"));

		// set center screen with only 1 line be label
		centreOneLinePanel = new JLabel();
		centreOneLinePanel.setText("title");
		centreOneLinePanel.setVisible(false);

		centerPanel.add(centreGridPanel);
		//centerPanel.add(centreOneLinePanel);
		
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
	    
	    // create handler for buttons
	    ButtonHandler handler = new ButtonHandler();
	    // register event handler 
	    // 0 - 9 , CANCEL , CLEAR , ENTER , 00
	    for (int i = 0; i<=13; i++)
	    	 keys[i].addActionListener(handler);
	}
	
	//inner class for button 
	public class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
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
				// add numbers to text Pane
				line = line.concat(event.getActionCommand());
				textPane.setText(line);
				// System.out.printf("Text:%s%nLength:%s%nText in Integer:%d%n%n", line, line.length(), Long.parseLong(line));
			}
		}
	}
	
	// access the screen
	public void accessScreen() {
		
	}
	
	// default as getting number from text pane
	// can be Override for other functionality (e.g. confirmation) if needed
	public String enter() {	
		
		return getText();
	}
	
	// return number from text pane in String
	public String getText() {
		return textPane.getText();
	}
	
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);	//set frame size
		setVisible(true);	//display frame
	}
}

