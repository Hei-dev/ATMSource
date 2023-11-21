import java.awt.*;         // Using AWT's containers and components
import java.awt.event.*;   // Using AWT's event classes and listener interfaces
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
public class SwingTemp extends JFrame{
    private JLabel la_c;
    private JLabel la_f;
    private JTextField tb_c;
    private JTextField tb_f;

    private double c1;
    private double f;

    public SwingTemp() {

        

        Container c = getContentPane();
        c.setLayout(new GridLayout(2,2));

        la_c = new JLabel("Celsius");
        c.add(la_c);

        tb_c = new JTextField("",15);
        tb_c.getDocument().putProperty("name","c");
        c.add(tb_c);
        
        la_f = new JLabel("Fahrenheit");
        c.add(la_f);

        tb_f = new JTextField("",15);
        tb_f.getDocument().putProperty("name","f");
        c.add(tb_f);

        setSize(200,300);
        setTitle("Swing TempConvertor");
        setVisible(true);
        
        tb_c.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if(check(tb_c.getText())){
                    c1 = Double.valueOf(tb_c.getText()) * 1.8 +32;
                    tb_f.setText(c1 + "");
                }

            }



        });

        tb_f.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if(check(tb_f.getText())){
                    f = (Double.valueOf("0" + tb_f.getText()) - 32) + (5/9);
                    tb_c.setText(f + "");
                }
            }
        });
        /*tb_c.getDocument().addDocumentListener(new DocumentListener(){
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }
        
            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                updateFieldState();
                
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                updateFieldState();
                
            }

            protected void updateFieldState(){
                if(check(tb_c.getText())){
                    c1 = Double.valueOf(tb_c.getText()) * 1.8 +32;
                    tb_f.setText(c1 + "");
                }
            }
        });

        tb_f.getDocument().addDocumentListener(new DocumentListener(){
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }
        
            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                updateFieldState();
                
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                updateFieldState();
                
            }

            protected void updateFieldState(){
                if(check(tb_f.getText())){
                    f = (Double.valueOf("0" + tb_f.getText()) - 32) + (5/9);
                    tb_c.setText(f + "");
                }

                
            }
        });*/

        /*DocumentListener dl = new DocumentListener(){
        
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }
        
            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                updateFieldState();
                
            }
        
            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                updateFieldState();
                
            }

            protected void updateFieldState(){
                c1 = Double.valueOf(tb_c.toString()) * 1.8 +32;
                tb_f.setText(c1 + "");
            }
        };*/

       
    }

    public static boolean check(String inp){
        double d;
        if (inp == "") {return false;}
        else{
            try{
                d = Double.valueOf(inp);
            }
            catch (NumberFormatException nfe){
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        new SwingTemp();
     }

}