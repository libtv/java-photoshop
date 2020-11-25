package ui.abstract_Factory;

import java.util.ArrayList;

import ui.HanbatPhotoShop_SERVER;

public class Circle_Factory implements AbstractFactory {
	boolean x;
	HanbatPhotoShop_SERVER server;
	
	public Circle_Factory(boolean x, HanbatPhotoShop_SERVER server) {
		this.x = x;
		this.server = server;
	}

	@Override
	public Displays createMethod() {
		// TODO Auto-generated method stub
		return new Circle(x, server);
	}

}
