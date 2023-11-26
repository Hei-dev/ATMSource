import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Greetinggui implements Defaultgui{
    private JPanel 
                greeting,
                nextStep;
    private JLabel 
                greetingMessage,
                nextStepMessage;
    
    protected Greetinggui() {
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

        // change selection names
        // Set the rest of the selection with no text
        for (int i = 0; i < 8; i++) {
            setSelectionDisplay(greeting, i, false);
        }
        
        
    }

    
    public JPanel getPanel() {
        return greeting;
    }
    
    public void setallSelectionListener() {
        // set action listener for mainMenu
        for(int i = 0; i < 8 ; i++){
        ATMgui.get().setSelectionListener(i, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ATMgui.get().display(GUIType.Login);
            }
            
        });
    }
    }
}
