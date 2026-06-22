package criterioDeBusqueda.operadorBinario;

import criterioDeBusqueda.CriterioDeBusqueda;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperadorORTest {

    private OperadorOR operadorOR;
    private Item item1, item2, item3, item4;
    private CriterioDeBusqueda unCriterio, otroCriterio;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        // when(item1.getNombre()).thenReturn("Peluche de Hachiware"); -- no hace falta definir esto LOL
        // when(item1.getPrecioBase()).thenReturn(20000.0);
        item2 = mock(Item.class);
        // when(item2.getNombre()).thenReturn("Llavero de Chiikawa");
        // when(item2.getPrecioBase()).thenReturn(15000.0);
        item3 = mock(Item.class);
        // when(item3.getNombre()).thenReturn("Peluche de Usagi");
        // when(item3.getPrecioBase()).thenReturn(10000.0);
        item4 = mock(Item.class);
        // when(item4.getNombre()).thenReturn("TV Samsung");
        // when(item4.getPrecioBase()).thenReturn(500000.0);
        // item4 supera el precio maximo y no cumple con el nombre requerido para unCriterio y otroCriterio


        // Mock de CriterioDeBusqueda
        unCriterio = mock(CriterioDeBusqueda.class); // Filtra por nombre
        when(unCriterio.filtrarItems(anyList())).thenReturn(List.of(item1,item2));
        otroCriterio = mock(CriterioDeBusqueda.class); // Filtra por precio maximo
        when(otroCriterio.filtrarItems(anyList())).thenReturn(List.of(item2,item3));

        // SUT OperadorOR
        operadorOR = new OperadorOR(unCriterio, otroCriterio);
    }

    @Test
    void seInicializaUnOperadorORCorrectamente(){
        assertThat(operadorOR.getPrimerCriterio()).isEqualTo(unCriterio);
        assertThat(operadorOR.getSegundoCriterio()).isEqualTo(otroCriterio);
    }

    @Test
    void seFiltranItemsPorAlMenosUnCriterioCombinado(){
        assertThat(operadorOR.filtrarItems(List.of(item1,item2,item3))).contains(item1,item2,item3);
    }

    @Test
    void noSeFiltranItemsPorCriteriosCombinados(){
        assertThat(operadorOR.filtrarItems(List.of(item4))).doesNotContain(item4);
    }


}
