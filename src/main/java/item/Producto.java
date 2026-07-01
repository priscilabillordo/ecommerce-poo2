package item;

import exceptions.ItemException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Producto extends Item {
    private final String sku;
    private final String marca;
    private final String categoria;
    private final double precioBase;
    private final double peso;
    private final List<Atributo> atributos;
    private int stock;

    public Producto(String nombre, String descripcion, double descuento, String sku, String marca, String categoria, double precioBase, double peso, int stock){
        super(nombre, descripcion, descuento);
        this.sku        = sku;
        this.marca      = marca;
        this.categoria  = categoria;
        this.precioBase = precioBase;
        this.peso       = peso;
        this.stock      = stock;
        this.atributos  = new ArrayList<>();
        this.validarAtributosObligatorios();
    }

    public void validarAtributosObligatorios() {
        if (!this.tieneAtributosObligatoriosAsignados()) {
            throw new ItemException(
                    "El producto tiene algun atributo obligatorio sin asignar"
            );
        }
    }

    private boolean tieneAtributosObligatoriosAsignados() {
        return  this.textoValido(this.getNombre()) &&
                this.textoValido(this.getDescripcion()) &&
                this.textoValido(this.sku) &&
                this.textoValido(this.marca) &&
                this.textoValido(this.categoria) &&
                this.precioBase > 0 &&
                this.getDescuento() >= 0 &&
                this.peso > 0 &&
                this.stock >= 0;
    }

    private boolean textoValido(String texto) {
        return texto != null && !texto.isBlank();
    }

    public void agregarAtributoDinamico(Atributo atributo) {
        this.validarAtributoDinamico(atributo);
        this.atributos.add(atributo);
    }

    public void validarAtributoDinamico(Atributo atributo) {
        if (!atributo.esValido()) {
            throw new ItemException(
                    "El atributo dinamico que se quiere agregar no es valido"
            );
        }
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
        this.validarSiHayStock();
        this.stock -= 1;
    }

    @Override
    public void aumentarStock() {
        this.stock += 1;

    }
}
