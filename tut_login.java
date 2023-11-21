
// start display login ui

ATMgui.get().setEnterListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae){
        boolean userAuthenticated = bankDatabase.authenticateUser( accountNumber, pin );
      
      // check whether authentication succeeded
      if ( userAuthenticated )
      {
         currentAccountNumber = accountNumber; // save user's account #

         // proceeed to main menu
      } 
      else{
        login_ui.getContentPane().add(new JLabel("Wrong PIN"));
        ATMgui.get().addMainPanel(login_ui)
      }
    }
})
