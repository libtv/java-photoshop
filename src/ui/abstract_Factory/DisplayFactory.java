package ui.abstract_Factory;

public class DisplayFactory {
    public static Displays getProduct(AbstractFactory display) {
        return display.createMethod();
    }
}
