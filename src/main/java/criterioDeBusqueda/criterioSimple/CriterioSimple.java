package criterioDeBusqueda.criterioSimple;

import criterioDeBusqueda.CriterioDeBusqueda;
import item.Item;

import java.util.List;
import java.util.function.Predicate;

public abstract class CriterioSimple implements CriterioDeBusqueda {

    /*
    * Defino un template para filtrar los items, donde cada criterio define la condición de filtro
    * */

    public final List<Item> filtrarItems(List<Item> items){
        return items.stream()
                .filter(this.condicionDeFiltro())
                .toList();
    }

    public abstract Predicate<Item> condicionDeFiltro();
}
