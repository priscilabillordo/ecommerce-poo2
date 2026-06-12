package items.item;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Item {
    private List<Item> items;

    public Paquete(String nombre, String descripcion, double descuento) {
        super(nombre, descripcion, descuento);
        this.items = new ArrayList<>();
    }

    @Override
    public void agregarItem(Item i){
        this.items.add(i);
    }

    @Override
    public void eliminarItem(Item i){
        this.items.remove(i);
    }

    @Override
    public double getDescuento() { return this.descuento; }

    @Override
    public double getPrecioBase() { return this.items.stream().mapToDouble(Item::getPrecioBase).sum(); }

    @Override
    public double getPrecioFinal() { return this.getPrecioBase() * (1 - this.descuento); }

    @Override
    public double getPeso() {
        return this.items.stream().mapToDouble(Item::getPeso).sum();
    }
}
