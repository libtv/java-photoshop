package ui.abstract_Factory;
import ui.HanbatPhotoShop_SERVER;

public class Dog_Factory implements AbstractFactory {
	boolean x;
	HanbatPhotoShop_SERVER server;
	
	public Dog_Factory(boolean x, HanbatPhotoShop_SERVER server) {
		this.x = x;
		this.server = server;
	}

	@Override
	public Displays createMethod() {
		// TODO Auto-generated method stub
		return new Dog(x, server);
	}

}
