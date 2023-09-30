public class CurrentAccount extends Account{
    private double limitPerCheque = 10000;
    // The constructor, which also calls the parent constructor
    public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance ){
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }
    // Setter for limitPerCheque
    public void setLimitPerCheque(double amt){
        limitPerCheque = amt;
    }
    // Getter for limitPerCheque
    public double getLimitPerCheque(){
        return limitPerCheque;
    }
}