import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Transfer.java
// Represents a Transfer ATM transaction
public class Transfer extends Transaction
{
   private double amount; // amount to transfer
   private int target_account; // target account # to transfer
   private int current_account; // current account # to transfer
   private double available_balance; // amount can be transfer
   private int account_checker; // special checker for detect same account
   
   boolean valid_amount; //determind correct amount input
   boolean valid_account; //determind correct target account input
   boolean finish_transfer; //determind finished transaction
   
   private final static int CANCELED = 0; // constant for cancel option
   private final static int Decimal_value = -2; // constant for handle non-integer
   private final static int Insufficient_cash = -3; // constant for not enough money
   private final static int Same_account = -4; // constant for same account number
   private final static int Invalid_account = -5; // constant for unexist account number
   private final static int Invalid_value = -404; // constant for handle invalid value
   
   private BankDatabase bankdatabase; // get reference from BankDatabase
   private Keypad keypad; // get reference from keypad
   // Transfer constructor
   public Transfer( int userAccountNumber, ATMgui atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad)
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      
      //initialize keypad
      keypad = atmKeypad;
   } // end Transfer constructor
   
   public void execute()
   {
      bankdatabase = getBankDatabase();  // get reference from BankDatabase
      amount = -1; // initialize amount
      target_account = -1; // initialize target account number
      
      valid_amount = false; // initialize amount checker
      valid_account = false; // initialize account checker
      finish_transfer = false; // initialize finished transaction checker
      
      current_account = getAccountNumber(); // access related using account number
      available_balance = bankdatabase.getAvailableBalance( current_account ); // access rerlated amount able to transfer
      
      //receive input of amount and target account number
      ATMgui.get().setEnterListener(new ActionListener() // add new actionlistener for detect enter pressed
      {
          @Override
          public void actionPerformed(ActionEvent ae){
              //receive input from the numberpad
              String temp = ( (javax.swing.JTextPane)ATMgui.get().findMainComponentByName(Defaultgui.INPUT_AREA_PANEL) ).getText();
              
              if ( !valid_amount )
              {
                  amount_execute( temp ); // check amount
              }
              else if ( !valid_account )
              {
                  account_execute( temp ); // check target account only if the amount is valid
              }
          }
      });
   }
   
   public void amount_execute( String input )
   {
       double status = check_amount( input );
       TransferGUI.execute_amount(status);
       if ( valid_amount )
       {
           //only save vaild amount, keep waitting the new vaild input by the enterlistener
           amount = status;
       }
   }
   
   public void account_execute( String input )
   {
       double status = check_account( input );
       TransferGUI.execute_account(status); // display typical GUI message
       if ( valid_account)
       {
           target_account = Integer.parseInt( input ); // save vaild target account number
           
           // move to confirm process since all invalid data input
                   
           ATMgui.get().setKeypadConfiguration(false, false, false); //disable number pad 
           TransferGUI.execute_confirm(amount, target_account); 
           setConfirmSelectionListener();
       }
   }
   
   public void transferfund_execute()
   {
       //only executed when select "confirm" with button # 3
       BankDatabase bankDatabase = getBankDatabase(); // get reference
       
       //handle transfer operation
       bankDatabase.debit( current_account, amount ); 
       bankDatabase.credit( target_account, amount );
       
       finish_transfer = true; 
       
       //guide user back to the main menu
       TransferGUI.execute_exit();
   }
   
   public double check_amount( String input )
   {
       try 
       {
            amount = Double.parseDouble( input );
       } catch (IllegalArgumentException e) {
            valid_amount = false;
            return Invalid_value;
       }
       if ( amount == CANCELED)
       {
            valid_amount = false;
            return CANCELED;
       }
       else if ( amount > available_balance )
       {
            valid_amount = false;
            return Insufficient_cash;
       }
       else 
       {
            valid_amount = true;
            return amount;
       }
   }
   
   public int check_account( String input )
   {    
        bankdatabase = getBankDatabase(); 
        double account_temp;
        
        try 
        {
            account_temp = Double.parseDouble( input );
            account_checker = Integer.parseInt( input );
        } catch (IllegalArgumentException e) {
            valid_account = false;
            return Invalid_value;
        }
        if ( account_temp % 1 != 0) 
        {   
            valid_account = false;
            return Decimal_value;
        }
        else if ( account_temp == CANCELED)
        {
            valid_account = false;
            return CANCELED;
        }
        else if ( account_checker == current_account)
        {
            valid_account = false;
            return same_account;
        }
        else if ( !bankdatabase.authenticateUser( account_checker ) )
        {
            valid_account = false;
            return Invalid_account;
        }
        else 
        {
            valid_account = true;
            return account_checker;
        }
   }
   
   //detect user clicked button during the confirmation
   private void setConfirmSelectionListener()
   {
       //operation of confirm
       ATMgui.get().setSelectionListener(3, new ActionListener() 
       {
       @Override
       public void actionPerformed(ActionEvent e) 
       {
           if ( !finish_transfer)
           {
              transferfund_execute();
           }
           
       }
        });
       //operation of cancel
       ATMgui.get().setSelectionListener(7, new ActionListener() 
       {
       @Override
       public void actionPerformed(ActionEvent e) 
       {
           TransferGUI.execute_exit();
       }
        });
        }
} // end class Transfer



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
