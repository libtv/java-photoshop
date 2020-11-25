package ui.Login_State;

import ui.Home;

public class On implements LoginState {

	@Override
	public void Action(Home home) {
		home.login_openTableAction();
	}

}
