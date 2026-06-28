package criterioDeBusqueda;
import item.Item;
import java.util.List;

public class OperadorNOT implements CriterioDeBusqueda {

    private CriterioDeBusqueda criterio;

    public OperadorNOT(CriterioDeBusqueda criterio) {
        this.criterio = criterio;
    }

    @Override
    public List<Item> filtrarItems(List<Item> items) {
        List<Item> itemsQueCumplen = this.criterio.filtrarItems(items);
        return items.stream()
                .filter(item -> !itemsQueCumplen.contains(item))
                .toList();
    }
}
