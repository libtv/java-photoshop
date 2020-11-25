package ui.abstract_Factory;

import ui.HanbatPhotoShop_SERVER;

public class Brush extends Displays {
	boolean brush;
	HanbatPhotoShop_SERVER server;

	Brush(boolean x, HanbatPhotoShop_SERVER server) {
		this.brush = x;
		this.server = server;
	}

	@Override
	public void print() {
		
		if(brush) {
			server.brush = false;
		} else { 
			server.line = false;
			server.brush = true;
			server.rect = false;
			server.expand = false;
			server.circle = false;
		}
	}

}
