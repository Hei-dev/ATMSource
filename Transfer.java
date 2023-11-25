// Transfer.java
// Represents a Transfer ATM transaction

public class Transfer extends Transaction
{
   private static double amount; // amount to transfer
   private static int target_account; // target account # to transfer
   private static int current_account;
   private static double available_balance;   
   private static int account_checker; // special checker for detect same account
   
   private final static int CANCELED = 0; // constant for cancel option
   private final static int Decimal_value = -2; // constant for handle non-integer
   private final static int Insufficient_cash = -3; // constant for not enough money
   private final static int same_account = -4; // constant for same account number
   private final static int Invaild_account = -5; // constant for unexist account number
   private final static int Invaild_value = -404; // constant for handle invaild value
   

   // Transfer constructor
   public Transfer( int userAccountNumber, ATMgui atmScreen, 
      BankDatabase atmBankDatabase)
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
   } // end Transfer constructor
   
   public static void transfer_execute()
   {   
       BankDatabase bankDatabase = new BankDatabase();

       bankDatabase.credit(target_account, amount);
       bankDatabase.debit(current_account, amount);
       
       bankDatabase.saveAccounts();
   }
   
   // perform transaction
   public void execute()
   {
      BankDatabase bankDatabase = getBankDatabase(); // get reference
      ATMgui screen = getScreen(); // get reference
      
      current_account = getAccountNumber();
      
      available_balance = bankDatabase.getAvailableBalance( current_account );
   } // end method promptForTargetAccount
   
   public static int check_amount( String input )
   {    
        try 
        {
            amount = Double.parseDouble( input );
        } catch (IllegalArgumentException e) {
            return Invaild_value;
        }
        if ( amount == CANCELED)
        {
            return CANCELED;
        }
        else if ( amount > available_balance )
        {
            return Insufficient_cash;
        }
        else 
        {
            return 1;
        }
   }
   
   public static int check_account( String input )
   {    
        double target_account_temp;
        BankDatabase bankDatabase = new BankDatabase();
        try 
        {
            target_account_temp = Double.parseDouble( input );
            account_checker = Integer.parseInt( input );
        } catch (IllegalArgumentException e) {
            return Invaild_value;
        }
        if ( target_account_temp % 1 != 0) 
        {   
            return Decimal_value;
        }
        else if ( target_account_temp == CANCELED)
        {
            return CANCELED;
        }
        else if ( account_checker == current_account)
        {
            return same_account;
        }
        else if ( !bankDatabase.authenticateUser( account_checker ) )
        {
            return Invaild_account;
        }
        else 
        {
            target_account = Integer.parseInt( input );
            return 1;
        }
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
