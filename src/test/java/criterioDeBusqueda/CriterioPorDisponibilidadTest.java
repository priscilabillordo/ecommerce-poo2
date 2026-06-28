package criterioDeBusqueda;

import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriterioPorDisponibilidadTest {

    private CriterioPorDisponibilidad criterioPorDisponibilidad;
    private Item item1, item2, item3;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        when(item1.hayStock()).thenReturn(true);
        item2 = mock(Item.class);
        when(item2.hayStock()).thenReturn(false);
        item3 = mock(Item.class);
        when(item3.hayStock()).thenReturn(true);

        // SUT CriterioPorDisponibilidad
        criterioPorDisponibilidad = new CriterioPorDisponibilidad();
    }

    @Test
    void seFiltranItemsPorDisponibilidadCorrectamente(){
        assertThat(criterioPorDisponibilidad.filtrarItems(List.of(item1,item2,item3))).contains(item1,item3);
    }

    @Test
    void noSeFiltranItemsPorDisponibilidad(){
        assertThat(criterioPorDisponibilidad.filtrarItems(List.of(item2))).isEmpty();
    }
}
