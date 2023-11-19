import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Greetinggui implements Defaultgui{
    private JPanel 
                greeting,
                nextStep;
    private JLabel 
                greetingMessage,
                nextStepMessage;
    private final static String newline = "\n";
    
    //private ATMgui SCREEN;
    
    protected Greetinggui() {
        System.out.println("Greetinggui constructor");
        greeting = getdefaultGUI();
        
        greetingMessage = new JLabel();
        greetingMessage.setFont(new Font("exit", 1, 20));
        greetingMessage.setHorizontalAlignment(SwingConstants.CENTER);
        greetingMessage.setText("Welcome to Bank of KWOK Tsz Chun");
        
        // create next step panel
        nextStep = new JPanel(new BorderLayout());
        nextStepMessage = new JLabel("Please press any button to continue");
        nextStepMessage.setFont(new Font("nextstep", 1 , 15));
        nextStep.add(nextStepMessage, BorderLayout.EAST);
        
        // create greeting gui
        greeting = new JPanel(new BorderLayout());
        greeting.add(greetingMessage, BorderLayout.CENTER);
        greeting.add(nextStep, BorderLayout.SOUTH);
        
        // change title to "Greeting"
        //setComponentText(greeting, "Title", "Welcome ", 1, 20);
        //to Bank of KWOK Tsz Chun (Hong Kong) Limited (Please press any button to continue)

        // change selection names
        /*setComponentText(greeting, "selection4", "-");
        setComponentText(greeting, "selection5", "-");
        setComponentText(greeting, "selection6", "-");
        setComponentText(greeting, "selection7", "-");
        */
        // Set the rest of the selection with no text
        for (int i = 0; i < 8; i++) {
            setSelectionDisplay(greeting, i, false);
        }
        
        
    }

    
    public JPanel getPanel() {
        System.out.println("Greeting getPanel()");
        return greeting;
    }
    
    public void setallSelectionListener() {
        // set action listener for view balance
        /* */
 
        
        // set action listener for mainMenu
        for(int i = 0; i < 8 ; i++){
        ATMgui.get().setSelectionListener(i, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ATMgui.get().display(GUIType.MainMenu);
            }
            
        });
        System.out.println("Greetinggui mainMenu listener");
    }
    }
}
