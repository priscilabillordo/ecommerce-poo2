package criterioDeBusqueda;

import items.item.Item;

import java.util.List;

public class CriterioPorCategoria implements CriterioDeBusqueda {

    private String categoria;

    public CriterioPorCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        return items.stream()
                    .filter(item -> item.getDescripcion().equals(this.categoria))
                    .toList();
        // TODO: falta la categoria
    }

}
