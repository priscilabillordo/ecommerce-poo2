package criterioDeBusqueda;

import item.Item;

import java.util.List;

public class CriterioPorDisponibilidad implements CriterioDeBusqueda {

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        return items.stream()
                .filter(Item::hayStock)
                .toList();
    }
}
