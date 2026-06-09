package item;

import java.util.List;

public class Paquete extends Item {
    private List<Item> items;

    public Paquete(String nombre, String descripcion, double descuento, List<Item> items) {
        super(nombre, descripcion, descuento);
        this.items = items;
    }

    @Override
    public double getDescuento() {
        return 0;
    }

    @Override
    public double getPrecioBase() {
        return 0;
    }

    @Override
    public double getPrecioFinal() {
        return 0;
    }

    @Override
    public double getPeso() {
        return 0;
    }

}
