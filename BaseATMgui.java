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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BaseATMgui extends JFrame implements Defaultgui{
	
	private static JButton[] keys, selection;
	private static GridBagLayout hardwareLayout;
	private static JPanel
		keyPadPanel,
		leftSelectionPanel,
		rightSelectionPanel,
		centreBasePanel;
	private String line;
	private GridBagConstraints c_hardware;
	private JPanel currentPanel;	
	
	private boolean 
		enableKeypad,
		enableFloatingPointButton,
		isPassword;
	
	private String input;
	private int wordLength;
	
	private Image 
		rightarrow,
		leftarrow;
	
	protected static final int ATM_WIDTH = 638;
	protected static final int ATM_HEIGHT = 750;
	protected static final int DEFAULT_BORDER_WIDTH = 2;

    
    protected BaseATMgui() {
        this("ATM");
    }

    protected BaseATMgui(String title) {
    	super(title);
        
        
        // enable floating point button as default
        enableFloatingPointButton = true;
        // enable Keypad as default
        enableKeypad = true;
        // disable encapsulating password
        isPassword = false;
        // set encrypted password to null
        input = "";
        // set default wordLength to 9
        wordLength = 9;
		
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
		//load in the button icon
		loadImage();
		
		// initialize all selection button and add to respective panel
		// set the ActionCommand for button handler when temporary string value is deleted
		for ( int i = 0; i <= 3; i++ ) {
			selection[i] = new JButton(getScaledIcon(leftarrow, 0.018));
			selection[i].setActionCommand("selection" + String.valueOf(i));
            leftSelectionPanel.add(selection[i]);
		}
		for ( int i = 4; i <= 7; i++) {
			selection[i] = new JButton(getScaledIcon(rightarrow, 0.018));
			selection[i].setActionCommand("selection" + String.valueOf(i));	
            rightSelectionPanel.add(selection[i]);
		}
		
        
        
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
        keyPadPanel.add( keys[ 14 ] );
        keyPadPanel.add( keys[ 13 ] );

        
        
        // set the hardware environment of the ATM 
        // screen, select button, keypad
        hardwareLayout = new GridBagLayout();
        setLayout(hardwareLayout);

        c_hardware = new GridBagConstraints();
        c_hardware.anchor = GridBagConstraints.CENTER;
        
        
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
		add(new JLabel(), c_hardware);    // top right dummy
		c_hardware.gridx = 10;
		add(new JLabel(), c_hardware);    // top left dummy
		c_hardware.gridy = 11;
		c_hardware.gridheight = 1;
		c_hardware.weighty = 0.3;
		c_hardware.insets = new Insets(0,0,0,0);
		add(new JLabel(), c_hardware);    // bottom right dummy
		c_hardware.gridx = 0;
		add(new JLabel(), c_hardware);    // bottom left dummy
        
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
                
                line = getTextPaneText(currentPanel);
                if (enableKeypad == true) {
                      switch (event.getActionCommand()) {
                      case "CANCEL":
                    	  try {
                    		  // prevent out of bound error
                    		  if (line.length() > 0) {
                    			  line = line.substring(0, line.length() - 1);
                    			  input = line;
                    			  setTextPaneText(currentPanel, line);
                    		  }
                    	  } catch (NullPointerException npe) {
                    		  System.out.println(npe);
                    	  }
                      	  break;
                      // Pressing CLEAR, clear the text from textPane
                      case "CLEAR":
                    	  line = "";
                    	  input = line;
                    	  setTextPaneText(currentPanel, line);
                    	  break;
                      // Pressing ENTER
                      case "ENTER":
                		  line = "";
                		  input = line;
                		  setTextPaneText(currentPanel, line);
                          break;
                      default:
                    	  if ((isPassword == false) && (line.length() < wordLength)) {
                    		  if (event.getActionCommand() != ".") {
                    			  // check if input is for password
                    			  line = line.concat(event.getActionCommand());
                    			  input = line;
                    			  setTextPaneText(currentPanel, line);
                             }
                             // check if "." exist in line and "." button is enabled
                             else if ((event.getActionCommand() == ".") && 
                            		 (!line.contains(".")) && 
                            		 (enableFloatingPointButton == true)) {
                                 // setting of Floating Point numbers, only 1 "." can exist once
                                 // remember to set back to true after finishing the whole input if set to false                        
                                 // check if length of string > 0 and if floating point is enabled
                                 if (line.length() > 0) {
                                     line = line.concat(".");
                                     input = line;
                                     setTextPaneText(currentPanel, line);
                                 }
                                 //check if the first input is "."
                                 else {
                                     line = "0.";
                                     input = line;
                                     setTextPaneText(currentPanel, line);
                                 }
                             }
                    	  } else if (isPassword == true) {
                    		  // mask all input
                    		  line = line.concat("*");
                    		  input = input.concat(event.getActionCommand());
                    		  setTextPaneText(currentPanel, line);
                    	  }
                      }
                  }
              }
          };
          
          
          // register event handler 
          // 0 - 9 , CANCEL , CLEAR , ENTER , 00
          for (int i = 0; i<=14; i++)
              keys[i].addActionListener(keyPadListener);
    }
    

    
    //    GUI RELATED    #############################################
    
    public void setMainPanel(JPanel panel){
        // find component "MainPanel"
        currentPanel = (JPanel)findComponentByName("MainPanel", getContentPane());
        // try to remove
        try    {
            centreBasePanel.remove(currentPanel);
        } catch (NullPointerException npt) {
            System.out.println(npt);
        }
        // update panel
        currentPanel = panel;
        // set name to "MainPanel"
        currentPanel.setName("MainPanel");
        // add to gui
        centreBasePanel.add(currentPanel);
        
        // reset selection ActionListener
        for( JButton currentButton: selection ) {
            for( ActionListener al : currentButton.getActionListeners() ) {
                currentButton.removeActionListener( al );
            }
        }
        // reset enter ActionListener
        for( ActionListener a : keys[12].getActionListeners() ) {
        	keys[12].removeActionListener(a);
            keys[12].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
          		  	line = "";
				}            	
            });
            break;
        }
        // reset word length
        wordLength = 9;
        revalidate();
        repaint();
    }    
    
    /**
     * get scaled size of image icon
     * @param image
     * @param scale of required size
     * @return Imageicon
     */
    private ImageIcon getScaledIcon(final Image image, final double scale)
    {
        ImageIcon scaledIcon = new ImageIcon(image)
        {
        	// change image width
            public int getIconWidth()
            {
                return (int)(image.getWidth(null) * scale);
            }
            // change image height
            public int getIconHeight()
            {
                return (int)(image.getHeight(null) * scale);
            }
            // update image
            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                g.drawImage(image, x, y, getIconWidth(), getIconHeight(), c);
            }
        };
        return scaledIcon;
    }
    
    //    FUNCTIONALITY RELATED    ####################################################
    
    public Component findMainComponentByName(String name){
        return findComponentByName(name, (Container)findComponentByName("MainPanel",getContentPane()));
    }
    
    /**
     * Gets the components inside the given panel
     * @param name
     * @param mContainer
     * @return component
     */
    public Component findComponentByName(String name, Container mContainer){
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
    
    /**
     * load imange from file
     */
    private void loadImage()
    {
        String righturl = "images/Right_Arrow.png";
        String lefturl = "images/Left_Arrow.png";
        try
        {
            URL urlright = getClass().getResource(righturl);
            URL urlleft = getClass().getResource(lefturl);
            rightarrow = ImageIO.read(urlright);
            leftarrow = ImageIO.read(urlleft);
        }
        catch(MalformedURLException mue)	
        {
            System.out.println("bad URL: " + mue.getMessage());
        }
        catch(IOException ioe)
        {
            System.out.println("io help: " + ioe.getMessage());
        }
    }
    
    /**
     * change functionality of enter button
     * @param al of preferred actionlistener for enter key
     */
    public void setEnterListener(ActionListener al) {    
        // reset enter button ActionListener
        for( ActionListener a : keys[12].getActionListeners() ) {
            keys[12].removeActionListener( a );
            break;
        }
        keys[12].addActionListener(al);
        input = "";
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
     * set availability of keypad and floating point and change display of input
     * @param enableNumber true/false
     * @param enableFloatingPoint true/false
     */
    public void setKeypadConfiguration(boolean enableNumber, boolean enableFloatingPoint, boolean input) {
        enableKeypad = enableNumber;
        enableFloatingPointButton = enableFloatingPoint;
        isPassword = input;
    }
    
    /**
     * set input value
     */
    public void setInput(String s) {
        input = s;
    }
    
    /**
     * get encapsulated Input
     * @return input encapsulated Input
     */
    public String getInput() {
        return input;
    }
    
    /**
     * set the word length inside text pane
     * @param wl of required word length
     */
    public void setWordLength(int wl) {
    	wordLength = wl;
    }
    
    public void run() {
        ATMgui.get().display(GUIType.Greeting);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ATM_WIDTH, ATM_HEIGHT);    //set frame size
        setVisible(true);    //display frame
        setResizable(false);    //disable resizing window
    }
}
 
