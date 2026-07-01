package criterioDeBusqueda.criterioSimple;

import item.Item;
import lombok.Getter;
import java.util.function.Predicate;

@Getter
public class CriterioPorNombre extends CriterioSimple {

    private String nombre;

    public CriterioPorNombre(String nombre){
        this.nombre = nombre;
    }

    @Override
    public Predicate<Item> condicionDeFiltro() {
        return item -> item.getNombre().contains(this.nombre);
    }
}
