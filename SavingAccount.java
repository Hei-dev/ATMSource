public class SavingAccount extends Account{
    private double interestRate = 0.001;
    // The constructor, which also calls the parent constructor
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance , double interestRate){
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
        this.interestRate = interestRate;
    }
    // Setter for limitPerCheque
    public void setInterestRate(double amt){
        interestRate = amt;
    }
    
    // Getter for limitPerCheque
    public double getInterestRate(){
        return interestRate;
    }
    
    
    @Override
    public void saveAccount(double[] dummy){
        super.saveAccount(new double[]{(double) super.accountNumber, (double) super.pin, super.availableBalance, super.totalBalance, 2.0, interestRate});
    }
}