// Withdrawal.java
// Represents a withdrawal ATM transaction

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdrawal extends Transaction
{
   private CashDispenser cashDispenser; // reference to cash dispenser
   
   private BankDatabase bankDatabase;

   // Withdrawal constructor
   public Withdrawal( int userAccountNumber, ATMgui atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser )
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      
      cashDispenser = atmCashDispenser;
   } // end Withdrawal constructor

   // perform transaction
   public void execute()
   {
      // get references to bank database and screen
      bankDatabase = getBankDatabase(); 
      ATMgui screen = getScreen();
      
      
      // set screen GUI
      //WithdrawalGUI.setMainPanel();
      
      // Add action listeners
      ATMgui.get().setEnterListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent ae){
              prepareDispenseCash(((javax.swing.JTextPane)ATMgui.get().findMainComponentByName(Defaultgui.INPUT_AREA_PANEL)).getText());
          }
      });
      
      
      ActionListener selectionListener = new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent ae){
                switch (ae.getActionCommand()){
                    case "selection2":
                        prepareDispenseCash("200");
                        break;
                    case "selection3":
                        prepareDispenseCash("500");
                        break;
                    case "selection6":
                        prepareDispenseCash("1000");
                        break;
              }
          }
      };
      ATMgui.get().setSelectionListener(2,selectionListener);
      ATMgui.get().setSelectionListener(3,selectionListener);
      ATMgui.get().setSelectionListener(6,selectionListener);
      ATMgui.get().setSelectionListener(7, new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent ae){
              returnToMenu();
          }
      });

   } // end method execute
   
   public void prepareDispenseCash(String amt){
       System.out.println(amt);
       int amount = Integer.parseInt(amt);
       
       // get available balance of account involved
            double availableBalance = 
               bankDatabase.getAvailableBalance( getAccountNumber() );
            // check whether the user has enough money in the account
            if(!isValidInput(amount)){
                displayErrorInputAgain();
            }
            else if ( amount <= availableBalance )
            {   
               // check whether the cash dispenser has enough money
               if ( cashDispenser.isSufficientCashAvailable( amount ) )
               {
                  cashDispenser.dispenseCash( amount ); // dispense cash
                  //cashDispensed = true; // cash was dispensed
                  
                  // update the account involved to reflect withdrawal
                  bankDatabase.debit( getAccountNumber(), (int)amount);
                  

                  // instruct user to take cash
                  displayDespencedCash();
               } // end if
               else{ // cash dispenser does not have enough cash
                    displayErrorDispense("The ATM does not have enough cash");
               }
            }
            else{
                displayErrorDispense("Account does not have suffivent cash");
            }
   }
   
   private void displayDespencedCash(){
       ATMgui.get().setMainPanel(WithdrawalGUI.getDispensedScreen());
       
       setFinishSelectionListener();
   }
   
   private void displayErrorDispense(String msg){
       ATMgui.get().setMainPanel(WithdrawalGUI.getErrorScreen(msg));
       
       setFinishSelectionListener();
   }
   
   private void displayErrorInputAgain(){
       ATMgui.get().setMainPanel(WithdrawalGUI.getMainPanel(true));
       execute();
   }
   
   private void setFinishSelectionListener(){
       ATMgui.get().setSelectionListener(3,new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               returnToMenu();
           }
       });
       ATMgui.get().setSelectionListener(7, new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               ATMgui.get().display(GUIType.Exit);
           }
       });
   }
   
   private void returnToMenu(){
       ATMgui.get().display(GUIType.MainMenu);
   }
   
   /**
    * Checks whether the inputted amount is valid to be withdrawn
    * @param amt the amount to withdrawn
    * @return boolean - is the amount valid to withdraw
    */
   private boolean isValidInput(double amt){
       return (amt%100==0 && amt>90);
   }

} // end class Withdrawal



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