package criterioDeBusqueda.operadorBinario;

import criterioDeBusqueda.CriterioDeBusqueda;
import item.Item;

import java.util.List;
import java.util.stream.Stream;

public class OperadorOR extends OperadorBinario {


    public OperadorOR(CriterioDeBusqueda primerCriterio, CriterioDeBusqueda segundoCriterio) {
        super(primerCriterio, segundoCriterio);
    }

    @Override
    public List<Item> filtrarItems(List<Item> items) {

        List<Item> primerFiltro = this.getPrimerCriterio().filtrarItems(items);
        List<Item> segundoFiltro = this.getSegundoCriterio().filtrarItems(items);
        return Stream.concat(primerFiltro.stream(),segundoFiltro.stream()).distinct().toList();
    }
}
