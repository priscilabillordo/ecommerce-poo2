package criterioDeBusqueda;

import item.Item;
import lombok.Getter;

import java.util.List;

@Getter
public class CriterioPorCategoria implements CriterioDeBusqueda {

    private String categoria;

    public CriterioPorCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        return items.stream()
                    .filter(item -> item.getCategoria().equals(this.categoria))
                    .toList();
    }

}
