package ui.abstract_Factory;

import ui.HanbatPhotoShop_SERVER;

public class Line extends Displays {
	boolean line;
	HanbatPhotoShop_SERVER server;

	Line(boolean x, HanbatPhotoShop_SERVER server) {
		this.line = x;
		this.server = server;
	}

	@Override
	public void print() {
		
		if(line) {
			server.line = false;
		} else { 
			server.line = true;
			server.brush = false;
			server.rect = false;
			server.expand = false;
			server.circle = false;
		}
	}

}
