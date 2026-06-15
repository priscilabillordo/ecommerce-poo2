package criterioDeBusqueda;

import items.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriterioPorPrecioMaxTest {

    private CriterioPorPrecioMax criterioPorPrecioMax;
    private Item item1,item2,item3;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        when(item1.getPrecioBase()).thenReturn(10000.0);
        item2 = mock(Item.class);
        when(item2.getPrecioBase()).thenReturn(20000.0);
        item3 = mock(Item.class);
        when(item3.getPrecioBase()).thenReturn(14999.9);

        // SUT CriterioPorPrecioMax
        criterioPorPrecioMax = new CriterioPorPrecioMax(15000.0);
    }

    @Test
    void seInicializaUnCriterioPorPrecioMaximoCorrectamente(){
        assertThat(criterioPorPrecioMax.getPrecioMaximo()).isEqualTo(15000.0);
    }

    @Test
    void seFiltranItemsPorPrecioMaximoCorrectamente(){
        assertThat(criterioPorPrecioMax.filtrarItems(List.of(item1,item2,item3))).contains(item1,item3);
    }

    @Test
    void noSeFiltranItemsPorPrecioMaximo(){
        assertThat(criterioPorPrecioMax.filtrarItems(List.of(item2))).isEmpty();
    }
}
