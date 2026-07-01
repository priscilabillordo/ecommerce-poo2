package criterioDeBusqueda.criterioSimple;

import item.Item;
import lombok.Getter;

import java.util.function.Predicate;

@Getter
public class CriterioPorPrecioMax extends CriterioSimple {

    private double precioMaximo;

    public CriterioPorPrecioMax(double precioMaximo){
        this.precioMaximo = precioMaximo;
    }

    @Override
    public Predicate<Item> condicionDeFiltro() {
        return item -> item.getPrecioBase() <= this.precioMaximo;
    }
}
