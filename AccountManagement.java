import java.util.ArrayList;

public class AccountManagement{
    private static ArrayList<Account> accounts;
    private static Keypad keypad;
    
    private static void addAccount(){
        int[] infos = new int[6];
        ArrayList<int> extraInfo = new ArrayList<>();
        screen.displayMessageLine("\nAdd Account: (Enter 0 at any input to cancel)");
        screen.displayMessageLin("Enter account number: ");
        infos[0] = keypad.input();
        if(infos[0]==0)
            return;
        screen.displayMessageLine("Enter account PIN: ");
        infos[1] = keypad.input();
        if(infos[1]==0)
            return;
        screen.displayMessageLine("Enter account available balance: ");
        infos[2] = keypad.input();
        if(infos[2]==0)
            return;
        screen.displayMessageLine("Enter account total balance: ");
        infos[3] = keypad.input();
        if(infos[3]==0)
            return;
        screen.displayMessageLine("Enter account type (1 = Saving, 2 = Current): ");
        while(infos[4]!=1 && infos[4]!=2){
            infos[4] = keypad.input();
            if(infos[4]!=1 && infos[4]!=2)
                screen.displayMessageLine("Invalid input, please enter again:")
        }
        screen.displayMessageLin("Enter account " + (infos[4]==) + ": ");
        infos[5] = keypad.input();
    }
    
    public static void main(String[] args){
        int input = -1;
        keypad = new Keypad();
        accounts = new ArrayList<>();
        screen.displayMessageLine("-Account Management System-");
        screen.displayMessageLine("0. Exit");
        screen.displayMessageLine("1. Load Data from files");
        screen.displayMessageLine("2. Save Data to files");
        screen.displayMessageLine("3. Add account");
        screen.displayMessageLine("4. Edit account");
        screen.displayMessageLine("5. Remove account");
        screen.displayMessageLine("Choose action: ");
        while(input!=0){
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