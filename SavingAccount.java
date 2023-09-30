public class SavingAccount extends Account{
    private double interestRate = "0.1";
    // The constructor, which also calls the parent constructor
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance ){
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }
    // Getter for interestRate
    public double getInterestRate(){
        return interestRate;
    }
    
    // Setter for interestRate
    public void setInterestRate(double amt){
        interestRate = amt;
    }
}