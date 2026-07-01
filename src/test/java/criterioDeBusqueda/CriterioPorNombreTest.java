package criterioDeBusqueda;

import criterioDeBusqueda.criterioSimple.CriterioPorNombre;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriterioPorNombreTest {

    private CriterioPorNombre criterioPorNombre;
    private Item item1, item2, item3;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        when(item1.getNombre()).thenReturn("Peluche de Hachiware");
        item2 = mock(Item.class);
        when(item2.getNombre()).thenReturn("Peluche de Chiikawa");
        item3 = mock(Item.class);
        when(item3.getNombre()).thenReturn("Peluche de Usagi");

        // SUT CriterioPorNombre
        criterioPorNombre = new CriterioPorNombre("Peluche de Usagi");
    }

    @Test
    void seInicializaUnCriterioPorNombreCorrectamente(){
        assertThat(criterioPorNombre.getNombre()).isEqualTo("Peluche de Usagi");
    }

    @Test
    void seFiltranItemsPorNombreCorrectamente(){
        List<Item> itemsAFiltrar = List.of(item1,item2,item3);

        assertThat(criterioPorNombre.filtrarItems(itemsAFiltrar))
                .contains(item3);
    }

    @Test
    void noSeEncuentranItemsPorNombre(){
        List<Item> itemsAFiltrar = List.of(item1,item2);

        assertThat(criterioPorNombre.filtrarItems(itemsAFiltrar))
                .isEmpty();

    }

}
