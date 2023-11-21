
// start display login ui

// Acc number enter
ATMgui.get().setEnterListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae){
        //accountNumber = textfield getvalue
        passwordCheck();
    }
});

// Password check
private void passwordCheck(){
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
                JLabel error_message = new JLabel("");
                error_message.setName("err_msg");
                login_ui.add(error_message);

                error_message = (JLabel)ATMgui.get().findComponentByName("err_msg");

                login_ui.remove(error_message);

                error_message.setText("Invalid");

                login_ui.add(error_message);
                ATMgui.get().addMainPanel(login_ui);


                ATMgui.get().revalidate();
                ATMgui.get().repaint();

                ATMgui.get().setEnterListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        passwordCheck();
                    }
                });

            }
        }
    }
}

