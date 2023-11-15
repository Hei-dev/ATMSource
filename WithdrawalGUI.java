import javax.swing.JPanel;

public class WithdrawalGUI extends ATMgui{

    private JPanel mainPanel;

    protected WithdrawalGUI(){
        super("Withdrawal");
    }

    @Override
    public JPanel setInterface(){
        mainPanel = getdefaultGUI();

        setTitle("Please select the exact amount, or type the amount using the keypad.")

        setSelectionName(4, "$200");
		setSelectionName(5, "$500");
		setSelectionName(6, "$1000");
		setSelectionName(7, "Exit");

        return mainPanel;
    }
}
