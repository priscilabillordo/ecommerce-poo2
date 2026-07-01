package criterioDeBusqueda.criterioSimple;

import item.Item;
import java.util.function.Predicate;

public class CriterioPorDisponibilidad extends CriterioSimple {

    @Override
    public Predicate<Item> condicionDeFiltro() {
        return Item::hayStock;
    }
}
