// Account.java
// Represents a bank account
import java.nio.file.Files;
import java.nio.file.Paths;

public class Account 
{
   protected int accountNumber; // account number
   protected int pin; // PIN for authentication
   protected double availableBalance; // funds available for withdrawal
   protected double totalBalance; // funds available + pending deposits

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

   private static byte[] doubleToByte(double d){
      return java.nio.ByteBuffer.allocate(Double.BYTES).putDouble(d).array();
   }
   
   /**
    * Saves account info into a file
    * @param additionalVars
    */
   public void saveAccount(double[] vals){
      byte[] finalByteArray = new byte[0];

       for(double d : vals){ //Loops through every double value
            byte[] doubleByte = doubleToByte(d);
            finalByteArray = java.nio.ByteBuffer
                .allocate(finalByteArray.length + doubleByte.length + 1) // allocate spaces
                .put(finalByteArray) // append to the byte array
                .put((byte)doubleByte.length) // append the double length in bytes
                .put(doubleByte) // append the double
                .array(); // convert back to array
        }
        
        try{
            Files.createDirectories(Paths.get("./Database"));
            Files.write(Paths.get(("./Database/" + AccountManagement.genRandomString())), finalByteArray); // Write the data into the file
        }
        catch(java.io.IOException ioe){
            //new Screen().displayMessageLine("Error while writing to the Database");
            ioe.printStackTrace();
            return;
        }
        
        //screen.displayMessageLine("Successfully added account");
        
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