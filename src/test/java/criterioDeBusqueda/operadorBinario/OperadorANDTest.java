package criterioDeBusqueda.operadorBinario;

import criterioDeBusqueda.CriterioDeBusqueda;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperadorANDTest {

    private OperadorAND operadorAND;
    private Item item1, item2, item3, item4;
    private CriterioDeBusqueda unCriterio, otroCriterio;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        item2 = mock(Item.class);
        item3 = mock(Item.class);
        item4 = mock(Item.class);
        List<Item> lista = List.of(item4);

        // Mock de CriterioDeBusqueda
        unCriterio = mock(CriterioDeBusqueda.class); // Filtra por nombre
        when(unCriterio.filtrarItems(anyList())).thenReturn(List.of(item1,item2));
        when(unCriterio.filtrarItems(lista)).thenReturn(List.of());
        otroCriterio = mock(CriterioDeBusqueda.class); // Filtra por precio maximo
        when(otroCriterio.filtrarItems(anyList())).thenReturn(List.of(item2,item3));
        when(unCriterio.filtrarItems(lista)).thenReturn(List.of());

        // SUT OperadorAND
        operadorAND = new OperadorAND(unCriterio, otroCriterio);
    }

    @Test
    void seInicializaUnOperadorANDCorrectamente(){
        assertThat(operadorAND.getPrimerCriterio()).isEqualTo(unCriterio);
        assertThat(operadorAND.getSegundoCriterio()).isEqualTo(otroCriterio);
    }

    @Test
    void seFiltranItemsQueSatisfacenAmbosCriterios(){
        assertThat(operadorAND.filtrarItems(List.of(item1,item2,item3))).contains(item2);
    }

    @Test
    void noSeFiltranItemsQueNoSatisfacenAmbosCriterios(){
        assertThat(operadorAND.filtrarItems(List.of(item4))).isEmpty();
    }
}
