import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountManagement{
    private static ArrayList<Account> accounts;
    private static Keypad keypad;
    private static Screen screen;
    
    private static byte[] doubleToByte(double d){
        return java.nio.ByteBuffer.allocate(Double.BYTES).putDouble(d).array();
    }
    
    public static String genRandomString(){

       String saveFileName = "";

       java.util.Random rand = new java.util.Random();

       // Name the save file
       while(saveFileName=="" || Files.exists(Paths.get("./Database/" + saveFileName)) || saveFileName.length() < 20){
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
       
       return saveFileName;
    }
    
    private static void addAccount(){
        java.util.Scanner sc = new java.util.Scanner(System.in);
        double[] infos = new double[6]; // Initalise dummy variable for storing inputs
        // Prompt the user for input
        screen.displayMessageLine("\nAdd Account: (Enter 0 at any input to cancel)");
        screen.displayMessageLine("Enter account number: ");
        infos[0] = keypad.getInput();
        if(infos[0]==0)
            return;
        screen.displayMessageLine("Enter account PIN: ");
        infos[1] = keypad.getInput();
        if(infos[1]==0)
            return;
        screen.displayMessageLine("Enter account available balance: ");
        infos[2] = sc.nextDouble();
        if(infos[2]==0)
            return;
        screen.displayMessageLine("Enter account total balance: ");
        infos[3] = sc.nextDouble();
        if(infos[3]==0)
            return;
        screen.displayMessageLine("Enter account type (1 = Saving, 2 = Current): ");
        while(infos[4]!=1 && infos[4]!=2){
            infos[4] = keypad.getInput();
            if(infos[4]!=1 && infos[4]!=2)
                screen.displayMessageLine("Invalid input, please enter again:");
        }
        screen.displayMessageLine("Enter account " + (infos[4]==1 ? "interest rate" : "limit per cheque") + ": ");
        infos[5] = sc.nextDouble();
        
        accounts.add(newAccount((int) infos[0],(int) infos[1],infos[2],infos[3],(int) infos[4],infos[5]));
    }
    
    public static Account newAccount(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance, int theAccountType, double extra1){
          if(theAccountType==1){
              return new SavingAccount( theAccountNumber,thePIN, theAvailableBalance, theTotalBalance, extra1);
          }
          else if(theAccountType==2){
              return new CurrentAccount( theAccountNumber,thePIN, theAvailableBalance, theTotalBalance, extra1);
          }
          else{
              return new Account( theAccountNumber,thePIN, theAvailableBalance, theTotalBalance);
          }
      }
    
    public void saveAccounts(){
    }
    
    public static void main(String[] args){
        int input = -1;
        screen = new Screen();
        keypad = new Keypad();
        accounts = new ArrayList<>();
        
        while(input!=0){
            screen.displayMessageLine("-Account Management System-");
            screen.displayMessageLine("0. Exit");
            screen.displayMessageLine("1. Load Data from files");
            screen.displayMessageLine("2. Save Data to files");
            screen.displayMessageLine("3. Add account");
            screen.displayMessageLine("4. Edit account");
            screen.displayMessageLine("5. Remove account");
            screen.displayMessageLine("Choose action: ");
            switch(input = keypad.getInput()){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    addAccount();
            }
        }
    }
}