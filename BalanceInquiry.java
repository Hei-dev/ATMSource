// BalanceInquiry.java
// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction
{
    private static double available_balance;   
    private static double total_balance;
    private static int current_account;
   // BalanceInquiry constructor
   public BalanceInquiry( int userAccountNumber, ATMgui atmScreen, 
      BankDatabase atmBankDatabase )
   {
      super( userAccountNumber, atmScreen, atmBankDatabase );
   } // end BalanceInquiry constructor

   // performs the transaction
   public void execute()
   {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();

      current_account = getAccountNumber();
      
      // get the available balance for the account involved
            available_balance = bankDatabase.getAvailableBalance( current_account );

      // get the total balance for the account involved
            total_balance = bankDatabase.getTotalBalance( current_account );
         
   } // end method execute
   
   public static double getavailablebalance(){
       BankDatabase bankDatabase = new BankDatabase();
       return available_balance;
   }
   
    public static double gettotalbalance(){
        BankDatabase bankDatabase = new BankDatabase();
       return total_balance;
   }
   
} // end class BalanceInquiry



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
