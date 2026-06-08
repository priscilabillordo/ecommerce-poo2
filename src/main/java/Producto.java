import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {

// Prueba Producto
    private String nombre;

    public Producto(String nombre){
        this.nombre = nombre;
    }
}
