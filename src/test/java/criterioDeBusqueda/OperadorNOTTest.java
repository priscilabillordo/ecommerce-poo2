package criterioDeBusqueda;

import items.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperadorNOTTest {

    private OperadorNOT operadorNOT;
    private CriterioDeBusqueda unCriterio;

    private Item item1, item2;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        when(item1.getNombre()).thenReturn("Peluche de Hachiware");
        item2 = mock(Item.class);
        when(item2.getNombre()).thenReturn("Peluche de Chiikawa");

        // Mock de CriterioDeBusqueda
        unCriterio = mock(CriterioDeBusqueda.class);
        when(unCriterio.filtrarItems(anyList())).thenReturn(List.of(item1));

        // SUT OperadorNOT
        operadorNOT = new OperadorNOT(unCriterio);
    }

    @Test
    void seFiltranItemsQueNoSatisfacenElCriterio(){
        assertThat(operadorNOT.filtrarItems(List.of(item1,item2))).contains(item2);
    }

    @Test
    void noSeFiltranItemsQueNoSatisfacenElCriterio(){
        assertThat(operadorNOT.filtrarItems(List.of(item1,item2))).doesNotContain(item1);
    }

}
