public class CurrentAccount extends Account{
    private double limitPerCheque = 10000;
    // The constructor, which also calls the parent constructor
    public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, 
    		double theTotalBalance , double limitPerCheque){
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
        this.limitPerCheque = limitPerCheque;
    }
    // Setter for limitPerCheque
    public void setLimitPerCheque(double amt){
        limitPerCheque = amt;
    }
    
    // Getter for limitPerCheque
    public double getLimitPerCheque(){
        return limitPerCheque;
    }

    @Override
    public void saveAccount(double[] dummy){
        super.saveAccount(new double[]{(double) super.accountNumber, (double) super.pin, super.availableBalance, 
        		super.totalBalance, 2.0, limitPerCheque});
    }
}