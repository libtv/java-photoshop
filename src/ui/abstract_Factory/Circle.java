package ui.abstract_Factory;

import ui.HanbatPhotoShop_SERVER;

public class Circle extends Displays {
	boolean rect;
	HanbatPhotoShop_SERVER server;

	Circle(boolean x, HanbatPhotoShop_SERVER server) {
		this.rect = x;
		this.server = server;
	}

	@Override
	public void print() {
		
		if(rect) {
			server.circle = false;
		} else { 
			server.line = false;
			server.brush = false;
			server.rect = false;
			server.circle = true;
			server.expand = false;
		}
	}

}
