package item;

public abstract class Item {
    private   String nombre;
    private   String descripcion;
    protected double descuento;

    public Item(String nombre, String descripcion, double descuento) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.descuento   = descuento;
    }

    public String getNombre()      { return this.nombre; }
    public String getDescripcion() { return this.descripcion; }

    public  abstract double getDescuento();
    public  abstract double getPrecioBase();
    public  abstract double getPrecioFinal();
    public  abstract double getPeso();

    public void agregarItem(Item i) { }
    public void eliminarItem(Item i) { }
}
