// Transfer.java
// Represents a Transfer ATM transaction

public class Transfer extends Transaction
{
   private double amount; // amount to transfer
   private int target_account; // target account # to transfer
   private int confirm; // user option 
   private Keypad keypad; // reference to keypad
   private final static int CANCELED = 0; // constant for cancel option

   // Transfer constructor
   public Transfer( int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad)
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );

      // initialize references to keypad
      keypad = atmKeypad;
   } // end Transfer constructor

   // perform transaction
   public void execute()
   {
      BankDatabase bankDatabase = getBankDatabase(); // get reference
      Screen screen = getScreen(); // get reference
      boolean Not_enough_balance = false;
      boolean Invalid_account = false;
      
      //process transfer amount
      do
      {
          amount = promptForTransferAmount(); // get transfer amount from user
          
          // check whether user entered canceled
          if ( amount == CANCELED) 
          {
              screen.displayMessageLine( "\nCanceling transaction..." );
          } // end if
          else if ( bankDatabase.getAvailableBalance( getAccountNumber() ) < amount )
          {
              screen.displayMessageLine( 
                    "\nInsufficient cash available in the ATM." +
                    "\n\nPlease choose a smaller amount." );
              Not_enough_balance = true;
          } // end if
          else
          {
              Not_enough_balance = false;
          }
      } while (Not_enough_balance && amount != CANCELED); //end do-while
      
      // process target account #
      do
      {
          target_account = promptForTargetAccount(); 
          
          // check whether user entered canceled
          if ( target_account == CANCELED)
          {
              screen.displayMessageLine( "\nCanceling transaction..." );
          } //end if
          else if ( !bankDatabase.authenticateUser( target_account ) )
          {
              screen.displayMessageLine( "\nInvalid account number." +
                    "\n\nPlease input again." );
              Invalid_account = true;
          } // end if
          else if ( target_account == getAccountNumber() )
          {
              screen.displayMessageLine( "\nTransfer to your own account " +
                    "is unavailable" + "\n\nPlease input again." );
              Invalid_account = true;
          } // end if
          else
          {
              Invalid_account = false;
          }
      } while (Invalid_account && target_account != CANCELED); // end do-while
     
      // confirm user transfer to the target account #
      do {      
          confirm = promptForConfirm();
          
          if ( confirm == CANCELED)
          {
              screen.displayMessageLine( "\nCanceling transaction..." );
          } //end if
          else if ( confirm == 1 )
          {
               // credit account to reflect the transfer
               bankDatabase.debit( getAccountNumber(), amount ); 
               bankDatabase.credit( target_account, amount );
               
               screen.displayMessageLine( "\nYour envelope has been " + 
                   "transfered.\n" );
          } //end if
          else
          {
               screen.displayMessageLine( "\nOnly 1 or 0 are accepted" + 
                   "\nPlease input again.\n" );
          }
      } while (confirm != 1 && confirm != CANCELED); //end do-while
   } // end method execute

   // prompt user to enter a tramsfer amount in cents 
   private double promptForTransferAmount()
   {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage( "\nPlease enter a transfer amount in " + 
         "CENTS (or 0 to cancel): " );
      int input = keypad.getInput(); // receive input of transfer amount
      
      // check whether the user canceled or entered a transfer amount
      if ( input == CANCELED ) 
      {
          return CANCELED;
      }
      else
      {
         return ( double ) input / 100; // return dollar amount 
      } // end else
   } // end method promptForTransferAmount
   
   // prompt user to enter a target account #
   private int promptForTargetAccount()
   {
       Screen screen = getScreen(); // get reference to screen
       BankDatabase bankDatabase = getBankDatabase(); // get reference to bank database
       
       // display the prompt
       screen.displayMessage( "\nPlease enter a target account number " +
           "(or 0 to cancel): ");
       int input = keypad.getInput(); // receive input of target account number
       
       // check whether the user canceled or entered bank account
       if ( input == CANCELED )
       {
           return CANCELED;
       } //end if
       else 
       {
           return input;
       }
   } // end method promptForTargetAccount
   
   // prompt user to enter a target account #
   private int promptForConfirm()
   {
       Screen screen = getScreen(); // get reference to screen
       BankDatabase bankDatabase = getBankDatabase(); // get reference to bank database
       
       // display the prompt
       screen.displayMessageLine( "\nAre you sure to transfer" );
       screen.displayDollarAmount( amount );
       screen.displayMessageLine( "." );
       screen.displayMessageLine( "to the following account?" );
       screen.displayAccountNumber( target_account );

       screen.displayMessageLine( "\n\nPlease Enter 1 to confirm" +
              " (or 0 to cancel): ");
       int input = keypad.getInput(); // receive input of user option
       
       // check whether the user canceled or entered bank account
       if ( input == CANCELED )
       {
           return CANCELED;
       } //end if
       else 
       {
           return input;
       }
   } // end method promptForTargetAccount
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
