package criterioDeBusqueda;

import item.Item;
import lombok.Getter;

import java.util.List;

@Getter
public class CriterioPorPrecioMax implements CriterioDeBusqueda {

    private double precioMaximo;

    public CriterioPorPrecioMax(double precioMaximo){
        this.precioMaximo = precioMaximo;
    }

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        return items.stream()
                .filter(item -> item.getPrecioBase() <= this.precioMaximo)
                .toList();
    }
}
