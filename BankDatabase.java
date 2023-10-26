// BankDatabase.java
// Represents the bank account information database 

public class BankDatabase
{
   private Account accounts[]; // array of Accounts
   
   // no-argument BankDatabase constructor initializes accounts
   public BankDatabase()
   {
       java.util.ArrayList<Account> tempAccountList = new java.util.ArrayList<>(); // Initalise a dummy list to store all the accounts
       File[] dbList = new File("./Database").listFiles(); // Loads a list of files in the database
        byte[] dbByte;
        for(File db : dbList){
            try {
                dbByte = Files.readAllBytes(db.toPath()); // read the file
            } catch (IOException e) { // catch IOException
                e.printStackTrace();
                return;
            }
			int readPos = 0; // The position to read the byte from
			double[] doubleVals = new double[6]; // Temp variable to store the double values from the file
            for(int i=0; i<6; i++){ // Total 6 parameters
				int doubleLength = dbByte[readPos]; // reads the length of the double
				byte[] doubleByteVal = new byte[doubleLength]; // initalise a byte array to store the double value in bytes from the file
				//               OrgArr  OrgPos    DestArr   DestPos       Len
				System.arraycopy(dbByte,readPos+1,doubleByteVal,0,doubleLength); // Slice the double in bytes from the file bytes
				doubleVals[i] = java.nio.ByteBuffer.wrap(doubleByteVal).getDouble(); // Converts to double and store it in the temp. variable
				readPos += doubleLength+1; // Skips the corresponding pos
			}
			
			// Check for account type
			tempAccountList.add(newAccount(doubleVals)); // Add accounts to the current system
        }
      accounts = tempAccountList.toArray(new Account[tempAccountList.size()]);
      
      //accounts[0].saveAccount(null);
   } // end no-argument BankDatabase constructor
   
   /**
     * Creates an account based on the given input
     * @param dArr the account information, stored in a double array, with the order based on the order of the orginal funcion (theAccountNumber, thePIN, theAvailableBalance, theTotalBalance, theAccountType, extra1)
     * @returns the Account created based on the input
     */
	private Account newAccount(double[] dArr){
          if(dArr[4]==1){ // Saving Account
              return new SavingAccount((int)dArr[0],(int)dArr[1], dArr[2], dArr[3], dArr[5]);
          }
          else if(dArr[4]==2){ // Current Account
              return new CurrentAccount((int)dArr[0],(int)dArr[1], dArr[2], dArr[3], dArr[5]);
          }
          else{
              return null;
          }
    }

    /**
     * Saves the stored account to the Database
     */
    public void saveAccounts(){
        File dbPath = new File("./Database");
        String[] dbFiles = dbPath.list();
        for(String s: dbFiles){ // Delete orginal files
            File currentFile = new File(dbPath,s);
            currentFile.delete();
        }
        for(Account acc : accounts){
            acc.saveAccount(null);  // Invoke overrided function in the respectve classes
        }
    }
   
   // retrieve Account object containing specified account number
   private Account getAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( Account currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for

      return null; // if no matching account was found, return null
   } // end method getAccount

   // determine whether user-specified account number and PIN match
   // those of an account in the database
   public boolean authenticateUser( int userAccountNumber, int userPIN )
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );

      // if account exists, return result of Account method validatePIN
      if ( userAccount != null )
         return userAccount.validatePIN( userPIN );
      else
         return false; // account number not found, so return false
   } // end method authenticateUser
   
   // determine whether valid account number for transfer function
   public boolean authenticateUser( int userAccountNumber ) 
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );

      // if account exists, return result of valid #
      if ( userAccount != null )
         return true;
      else
         return false; // account number not found, so return false
   } // end method authenticateUser
   
   // return available balance of Account with specified account number
   public double getAvailableBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getTotalBalance();
   } // end method getTotalBalance

   // credit an amount to Account with specified account number
   public void credit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).credit( amount );
   } // end method credit

   // debit an amount from of Account with specified account number
   public void debit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).debit( amount );
   } // end method debit
} // end class BankDatabase



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
