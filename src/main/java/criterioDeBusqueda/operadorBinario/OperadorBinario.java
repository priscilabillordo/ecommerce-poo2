package criterioDeBusqueda.operadorBinario;

import criterioDeBusqueda.CriterioDeBusqueda;
import lombok.Getter;

@Getter
public abstract class OperadorBinario implements CriterioDeBusqueda {

    private CriterioDeBusqueda primerCriterio, segundoCriterio;

    public OperadorBinario(CriterioDeBusqueda primerCriterio, CriterioDeBusqueda segundoCriterio){
        this.primerCriterio = primerCriterio;
        this.segundoCriterio = segundoCriterio;
    }

}
