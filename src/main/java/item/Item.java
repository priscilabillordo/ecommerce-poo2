package item;

import exceptions.ItemException;
import exceptions.PedidoException;
import lombok.Getter;
import lombok.Setter;

@Setter
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
    public abstract void    aumentarStock();
    public abstract void    decrementarStock();

    protected void validarSiHayStock() {
        if (!this.hayStock()) {
            throw new ItemException("No hay stock suficiente.");
        }
    }

    public void incluir(Item i)  { }
}
