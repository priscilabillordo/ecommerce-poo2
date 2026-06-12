package items;

import lombok.Getter;

@Getter
public class Atributo {
    private String nombre;
    private Object dato;

    public Atributo(String nombre, int dato) {
        this.nombre = nombre;
        this.dato   = dato;
    }
}
