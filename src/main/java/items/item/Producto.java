package items.item;

import items.Atributo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Producto extends Item {
    private String sku;
    private String marca;
    private String categoria;
    private double precioBase;
    private double peso;
    private int    stock;
    private List<Atributo> atributos;
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
    public double getDescuento() { return this.descuento; }

    @Override
    public double getPeso() { return this.peso; }

    @Override
    public double getPrecioBase() { return this.precioBase; }

    @Override
    public double getPrecioFinal() { return this.precioBase - (this.precioBase * this.descuento); }

    public void decrementarStock() {
        this.stock -= 1;
    }
}
