package ui.Login_State;

import ui.Home;

public class Off implements LoginState {

	@Override
	public void Action(Home home) {
		home.logout_openTableAction();
	}

}
