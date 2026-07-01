package item;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Item {
    private final List<Item> items;

    public Paquete(String nombre, String descripcion, double descuento) {
        super(nombre, descripcion, descuento);
        this.items = new ArrayList<>();
    }

    @Override
    public void incluir(Item i){
        this.items.add(i);
    }

    @Override
    public double getPrecioBase() { return this.items.stream().mapToDouble(Item::getPrecioBase).sum(); }

    @Override
    public double getPrecioFinal() { return this.getPrecioBase() * (1 - this.getDescuento()); }

    @Override
    public double getPeso() {
        return this.items.stream().mapToDouble(Item::getPeso).sum();
    }

    @Override
    public String getCategoria() {
        return this.items.getFirst().getCategoria();
    }

    @Override
    public int getStock() {
        return (!this.items.isEmpty()) ?
                this.items.stream().mapToInt(Item::getStock).min().getAsInt()
                : 0;
        // la operación min devuelve OptionalInt que es una especie de objeto contenedor
        // Si el OptionalInt tiene un valor lo devuelve, en caso contrario solo devuelve 0
    }

    @Override
    public boolean hayStock() {
        return this.getStock() > 0;
    }

    @Override
    public void decrementarStock() {
        this.validarSiHayStock();
        this.items.forEach(Item::decrementarStock);
    }

    @Override
    public void aumentarStock() {
        this.items.forEach(Item::aumentarStock);
    }
}