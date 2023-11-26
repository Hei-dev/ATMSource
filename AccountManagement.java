import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountManagement{
    private static ArrayList<Account> accounts;
    private static Keypad keypad;
    private static Screen screen;
    private static java.util.Scanner sc;
    
	/**
	 * Generate a random string consist of random number and characters
	 *
	 * @returns a randomly-generated string
	 */
    public static String genRandomString(){

       String saveFileName = "";

		// Initalise the random object
       java.util.Random rand = new java.util.Random();

       // Name the save file
       while(saveFileName=="" || Files.exists(Paths.get("./Database/" + saveFileName)) || saveFileName.length() < 20){
           // Gets what type of char to be added
            int type = rand.nextInt(2);
            switch (type){
                case 0: // Numbers
                   saveFileName += (char)(rand.nextInt(8) + 48); // Adds the respective ASCII value
                   break;
                case 1: // Lowercase letter
                   saveFileName += (char)(rand.nextInt(25) + 65);// Adds the respective ASCII value
                   break;
                case 2: // Uppercase Letter
                   saveFileName += (char)(rand.nextInt(25) + 97);// Adds the respective ASCII value
                   break;
           }
       }
       
       return saveFileName; // Returns the result
    }

    private static int findAccountByNumber(int num){
        if(accounts.size()==0){
            screen.displayMessageLine("No accounts found.");
            return -1;
        }
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountNumber()==num)
                return i;
        }
        return -1;
    }
    
    /**
     * Adds an account from user inputs
     */
    private static void addAccount(){
        double[] infos = new double[6]; // Initalise dummy variable for storing inputs
        // Prompt the user for input
        screen.displayMessageLine("\nAdd Account: (Enter 0 at any input to cancel)");
        boolean inputPass; // Flag to check if the input would pass
        do{
            screen.displayMessageLine("Enter account number: ");
            infos[0] = keypad.getInput();
            inputPass = (findAccountByNumber((int)infos[0])!=-1); // Check if account number exist
            if(infos[0]==0){ // 0 = Cancel, same below
                sc.close();
                return;
            }
            else if(inputPass)
                screen.displayMessageLine("Account already exist, please re-enter.");
        }while(inputPass);
        
        screen.displayMessageLine("Enter account PIN: ");
        infos[1] = keypad.getInput();
        if(infos[1]==0){sc.close(); return;}
        screen.displayMessageLine("Enter account available balance: ");
        infos[2] = sc.nextDouble();
        if(infos[2]==0){sc.close(); return;}
        screen.displayMessageLine("Enter account total balance: ");
        infos[3] = sc.nextDouble();
        if(infos[3]==0){sc.close(); return;}
        screen.displayMessageLine("Enter account type (1 = Saving, 2 = Current): ");
        while(infos[4]!=1 && infos[4]!=2){ // Keep entering until a account type is actually seleted
            infos[4] = keypad.getInput();
            if(infos[4]!=1 && infos[4]!=2)
                screen.displayMessageLine("Invalid input, please enter again:");
        }
		// Ternary operator for different account type displays different string
        screen.displayMessageLine("Enter account " 
		+ (infos[4]==1 ? "interest rate" : "limit per cheque") + ": ");
        infos[5] = sc.nextDouble();
        
		// Adds the acount to the stored accounts
        accounts.add(newAccount((int) infos[0],(int) infos[1]
        		,infos[2],infos[3],(int) infos[4],infos[5]));
        
        screen.displayMessageLine("Account successfully added");
        
        
    }

    /**
     * Edit an account's information from user input
     */
    private static void editAccount(){
        // Prompt the user for input
        screen.displayMessageLine("\nEdit Account: (Enter 0 at any input to cancel)");
        boolean inputPass; // Flag to check if the input would pass
        double inputVal = 0; // Dummy variable to store the user input
        int accPos;
        do{
            screen.displayMessageLine("Enter account number: ");
            inputVal = keypad.getInput();
            accPos = findAccountByNumber((int)inputVal); // Record the account position in the array list
            inputPass = accPos!=-1; // Check if account number exist
            if(inputVal==0){ // 0 = Cancel, same below
                return;
            }
            else if(!inputPass)
                screen.displayMessageLine("Account does not exist, please re-enter.");
        }while(!inputPass);
        
        screen.displayMessageLine("Enter the value to edit: ");
        screen.displayMessageLine("0. Cancel");
        screen.displayMessageLine("1. Account Number");
        screen.displayMessageLine("2. Account PIN");
        screen.displayMessageLine("3. Available Balance");
        screen.displayMessageLine("4. Total Balance");
        screen.displayMessageLine("5." + ((accounts.get(accPos) instanceof SavingAccount) 
        		? " Interest Rate" : " Limit Per Cheque")); // Ternary operator to show the correct
        int editVal = -1;
        while(!(editVal>-1 && editVal<6)){ // Checks if the input is in range
            editVal = sc.nextInt();
            if(!(editVal>-1 && editVal<6)){
                screen.displayMessageLine("Input invalid, please input again.");
            }
        }
        
        if(editVal==0) //Cancel
            return;


		// Ternary operator for different account type displays different string
        screen.displayMessageLine("Enter value: ");
        inputVal = sc.nextDouble();
        
		// Adds the acount to the stored accounts
        switch (editVal){
            case 1:
                accounts.get(accPos).accountNumber = (int)inputVal;
                break;
            case 2:
                accounts.get(accPos).pin = (int)inputVal;
                break;
            case 3:
                accounts.get(accPos).availableBalance = inputVal;
                break;
            case 4:
                accounts.get(accPos).totalBalance = inputVal;
                break;
            case 5:
                if(accounts.get(accPos) instanceof SavingAccount)
                    ((SavingAccount)accounts.get(accPos)).setInterestRate(inputVal);
                else
                    ((CurrentAccount)accounts.get(accPos)).setLimitPerCheque(inputVal);
                break;
        }
    }
    
    /**
     * Creates an account based on the given input
     * @param theAccountNumber the account Number
     * @param thePIN the account PIN
     * @param theAvailableBalance the available Balance of the account
     * @param theTotalBalance the total balance of the Account
     * @param theAccountType the account type. 1 for Saving Account, 2 for Current account.
     * @param extra1 the extra argument in the respective account type: 
     * 		  Interest Rate for Saving Account, Limit per cheque for Current Account
     * @returns the Account created based on the input
     */
    public static Account newAccount(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance, int theAccountType, double extra1){
          if(theAccountType==1){ // Saving Account
              return new SavingAccount( theAccountNumber,thePIN, theAvailableBalance, 
            		  theTotalBalance, extra1);
          }
          else if(theAccountType==2){ // Current Account
              return new CurrentAccount( theAccountNumber,thePIN, theAvailableBalance, 
            		  theTotalBalance, extra1);
          }
          else{ // Unknown account - return null
              return null;
          }
    }
	
	// Overload function for accepting double array input
   /**
     * Creates an account based on the given input
     * @param dArr the account information, stored in a double array, 
     * 		  with the order based on the order of the orginal funcion 
     * 		  (theAccountNumber, thePIN, theAvailableBalance, theTotalBalance, theAccountType, extra1)
     * @returns the Account created based on the input
     */
	public static Account newAccount(double[] dArr){
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
    public static void saveAccounts(){
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

    /**
     * Loads the accounts from the Database
     */
    public static void loadAccounts(){
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
				// initalise a byte array to store the double value in bytes from the file
				byte[] doubleByteVal = new byte[doubleLength]; 
				// Slice the double in bytes from the file bytes
				//               OrgArr  OrgPos    DestArr   DestPos       Len
				System.arraycopy(dbByte,readPos+1,doubleByteVal,0,doubleLength); 
				// Converts to double and store it in the temp. variable
				doubleVals[i] = java.nio.ByteBuffer.wrap(doubleByteVal).getDouble(); 
				readPos += doubleLength+1; // Skips the corresponding pos
				
			}
			
			// Check for account type
			accounts.add(newAccount(doubleVals)); // Add accounts to the current system
        }
    }

    /**
     * Delete an account based on user input
     */
    public static void delAccount(){
        // Prompt the user for input
        screen.displayMessageLine("\nDelete Account: (Enter 0 at any input to cancel)");
        boolean inputPass; // Flag to check if the input would pass
        int inputVal = 0; // Dummy variable to store the user input
        int accPos;
        do{
            screen.displayMessageLine("Enter account number: ");
            inputVal = keypad.getInput();
            accPos = findAccountByNumber((int)inputVal); // Record the account position in the array list
            inputPass = accPos!=-1; // Check if account number exist
            if(inputVal==0){ // 0 = Cancel, same below
                return;
            }
            else if(!inputPass) // Invalid Account
                screen.displayMessageLine("Account does not exist, please re-enter.");
        }while(!inputPass);

        screen.displayMessageLine("Are you sure? Enter the account PIN to delete: ");
        inputVal = keypad.getInput(); // Gets PIN
        if(accounts.get(accPos).pin==inputVal){
            // PIN Correct. delete account
            screen.displayMessageLine("Account deleted.");
            accounts.remove(accPos);
        }
        else{
            screen.displayMessageLine("PIN incorrect. Returning to menu.");
        }
    }
    
    public static void main(String[] args){
        int input = -1;
        screen = new Screen();
        keypad = new Keypad();
        accounts = new ArrayList<>();
        sc = new java.util.Scanner(System.in);
        
        while(input!=0){
            screen.displayMessageLine("-Account Management System-");
            screen.displayMessageLine("0. Exit");
            screen.displayMessageLine("1. Load Data from files");
            screen.displayMessageLine("2. Override and Save Data to files");
            screen.displayMessageLine("3. Add account");
            screen.displayMessageLine("4. Edit account");
            screen.displayMessageLine("5. Remove account");
            screen.displayMessageLine("Choose action: ");
            switch(input = keypad.getInput()){
                case 1:
                    loadAccounts();
                    break;
                case 2:
                    saveAccounts();
                    break;
                case 3:
                    addAccount();
                    break;
				case 4:
					editAccount();
					break;
				case 5:
					delAccount();
					break;
            }
        }
        
        sc.close();
    }
}