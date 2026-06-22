package item;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Producto extends Item {
    private final String sku;
    private final String marca;
    private final String categoria;
    private final double precioBase;
    private final double peso;
    private final List<Atributo> atributos;
    private int stock;
    //private double precioFinal; no es un estado independiente, es un cálculo derivado

    public Producto(String nombre, String descripcion, double descuento, String sku, String marca, String categoria, double precioBase, double peso, int stock){
        super(nombre, descripcion, descuento);
        this.sku        = sku;
        this.marca      = marca;
        this.categoria  = categoria;
        this.precioBase = precioBase;
        this.peso       = peso;
        this.stock      = stock;
        this.atributos  = new ArrayList<>();
    }

    public void agregarAtributoDinamico(Atributo atributo) {
        this.atributos.add(atributo);
    }

    @Override
    public double getPeso() { return this.peso; }

    @Override
    public double getPrecioBase() { return this.precioBase; }

    @Override
    public double getPrecioFinal() { return this.precioBase - (this.precioBase * this.getDescuento()); }

    @Override
    public boolean hayStock() {
        return this.stock > 0;
    }

    @Override
    public void decrementarStock() {
        this.stock -= 1;
    }

    @Override
    public void aumentarStock() {
        this.stock +=1;
    }

}
