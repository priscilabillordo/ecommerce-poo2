package criterioDeBusqueda.criterioSimple;

import item.Item;
import lombok.Getter;
import java.util.function.Predicate;

@Getter
public class CriterioPorCategoria extends CriterioSimple {

    private String categoria;

    public CriterioPorCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public Predicate<Item> condicionDeFiltro() {
        return item -> item.getCategoria().equals(this.categoria);
    }

}
