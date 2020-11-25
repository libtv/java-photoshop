package ui.abstract_Factory;

import ui.HanbatPhotoShop_SERVER;

public class Dog extends Displays {
	boolean expand;
	HanbatPhotoShop_SERVER server;

	Dog(boolean x, HanbatPhotoShop_SERVER server) {
		this.expand = x;
		this.server = server;
	}

	@Override
	public void print() {
		
		if(expand) {
			server.expand = false;
		} else { 
			server.line = false;
			server.brush = false;
			server.rect = false;
			server.expand = true;
			server.circle = false;
		}
	}

}
