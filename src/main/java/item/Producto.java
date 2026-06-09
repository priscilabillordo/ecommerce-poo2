package item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto extends Item {
    private String sku;
    private String marca;
    private String categoria;
    private double precioBase;
    private double peso;
    //private double precioFinal;

    public Producto(String nombre, String descripcion, double descuento, String sku, String marca, String categoria, double precioBase){
        super(nombre, descripcion, descuento);
        this.sku        = sku;
        this.marca      = marca;
        this.categoria  = categoria;
        this.precioBase = precioBase;
    }

    @Override
    public double getDescuento() { return this.descuento; }

    @Override
    public double getPeso() { return this.peso; }

    @Override
    public double getPrecioBase() { return this.precioBase; }

    @Override
    public double getPrecioFinal() { return this.precioBase - (this.precioBase * this.descuento); }
}
