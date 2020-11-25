package ui.abstract_Factory;

import ui.HanbatPhotoShop_SERVER;

public class Rect extends Displays {
	boolean rect;
	HanbatPhotoShop_SERVER server;

	Rect(boolean x, HanbatPhotoShop_SERVER server) {
		this.rect = x;
		this.server = server;
	}

	@Override
	public void print() {
		
		if(rect) {
			server.rect = false;
		} else { 
			server.line = false;
			server.brush = false;
			server.rect = true;
			server.expand = false;
			server.circle = false;
		}
	}

}
