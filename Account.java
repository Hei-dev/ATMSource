// Account.java
// Represents a bank account
import java.nio.file.Files;
import java.util.Random;

public class Account 
{
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available + pending deposits

   // Account constructor initializes attributes
   public Account( int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance )
   {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
   } // end Account constructor

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN( int userPIN )
   {
      if ( userPIN == pin )
         return true;
      else
         return false;
   } // end method validatePIN
   
   // returns available balance
   public double getAvailableBalance()
   {
      return availableBalance;
   } // end getAvailableBalance

   // returns the total balance
   public double getTotalBalance()
   {
      return totalBalance;
   } // end method getTotalBalance

   // credits an amount to the account
   public void credit( double amount )
   {
      totalBalance += amount; // add to total balance
   } // end method credit

   // debits an amount from the account
   public void debit( double amount )
   {
      availableBalance -= amount; // subtract from available balance
      totalBalance -= amount; // subtract from total balance
   } // end method debit

   // returns account number
   public int getAccountNumber()
   {
      return accountNumber;  
   } // end method getAccountNumber
   
   /**
    * Saves account info into a file
    * @param additionalVars
    */
   public void saveAccount(String[] additionalVars){
       String saveFileName = "";
       Random rand = new Random();
       // Name the save file
       while(saveFileName!="" && !Files.exists(java.nio.file.Paths.get(saveFileName)) && saveFileName.length() >= 10){
           // Gets what type of char to be added
            int type = rand.nextInt(2);
            switch (type){
                case 0:
                   saveFileName += (char)(rand.nextInt(8) + 48);
                   break;
                case 1:
                   saveFileName += (char)(rand.nextInt(25) + 65);
                   break;
                case 2:
                   saveFileName += (char)(rand.nextInt(25) + 97);
                   break;
           }
       }
       // Create byte array based on number of variables needed to store
       byte[] ba = new byte[4+1+additionalVars.length];
       System.out.println((byte)accountNumber);
   }
} // end class Account


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