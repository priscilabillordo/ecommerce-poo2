package criterioDeBusqueda;

import items.item.Item;

import java.util.List;

public class CriterioPorDisponibilidad implements CriterioDeBusqueda {

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        return List.of();
        // TODO: No puedo testear hasta ver el tema del stock
    }
}
