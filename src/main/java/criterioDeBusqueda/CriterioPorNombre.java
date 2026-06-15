package criterioDeBusqueda;
import items.item.Item;
import lombok.Getter;

import java.util.List;

@Getter
public class CriterioPorNombre implements CriterioDeBusqueda {

    private String nombre;

    public CriterioPorNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        return items.stream().filter(item -> item.getNombre().contains(this.nombre)).toList();
        // En el ejemplo de uso el nombre no debe ser exacto: "Nombre contiene "pack" "
    }
}
