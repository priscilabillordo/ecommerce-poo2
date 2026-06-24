package item;

import lombok.Getter;

@Getter
public class Atributo {
    private String nombre;
    private Object dato;

    public Atributo(String nombre, Object dato) {
        this.nombre = nombre;
        this.dato   = dato;
    }

    public boolean esValido() {
        return nombre != null && !nombre.isBlank() && dato != null;
    }
}
