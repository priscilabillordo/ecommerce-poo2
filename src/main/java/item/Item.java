package item;

import lombok.Getter;
@Getter
public abstract class Item {
    private final String nombre;
    private final String descripcion;
    private final double descuento;

    protected Item(String nombre, String descripcion, double descuento) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.descuento   = descuento;
    }

    public abstract double  getPrecioBase();
    public abstract double  getPrecioFinal();
    public abstract double  getPeso();
    public abstract String  getCategoria();
    public abstract int     getStock();
    public abstract boolean hayStock();
    public abstract void    decrementarStock();
    public abstract void    aumentarStock();

    public void incluir(Item i)  { }
}
