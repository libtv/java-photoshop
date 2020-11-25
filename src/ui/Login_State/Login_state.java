package ui.Login_State;

import ui.Home;

public class Login_state {
	private LoginState loginState;
	Home home;
	
	public Login_state(Home home) {
		this.loginState = new Off();
		this.home = home;
	}
	
    public void setLoginState(LoginState loginState){
        this.loginState = loginState;
    }
    
    public LoginState getLoginState(){
        return loginState;
    }
    
    public void Login(){
    	loginState.Action(home);
    }
    
}
