package criterioDeBusqueda;

import criterioDeBusqueda.criterioSimple.CriterioPorCategoria;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CriterioPorCategoriaTest {

    private CriterioPorCategoria criterioPorCategoria;
    private Item item1, item2, item3;

    @BeforeEach
    void setUp(){

        // Mock de Item
        item1 = mock(Item.class);
        when(item1.getCategoria()).thenReturn("Zapatillas");
        item2 = mock(Item.class);
        when(item2.getCategoria()).thenReturn("Kawaii");
        item3 = mock(Item.class);
        when(item3.getCategoria()).thenReturn("Kawaii");

        // SUT CriterioPorCategoria
        criterioPorCategoria = new CriterioPorCategoria("Kawaii");
    }

    @Test
    void seInicializaUnCriterioPorCategoriaCorrectamente(){
        assertThat(criterioPorCategoria.getCategoria()).isEqualTo("Kawaii");
    }

    @Test
    void seFiltranItemsPorCategoriaCorrectamente(){
        assertThat(criterioPorCategoria.filtrarItems(List.of(item1,item2,item3))).contains(item2,item3);
    }

    @Test
    void noSeFiltranItemsPorCategoria(){
        assertThat(criterioPorCategoria.filtrarItems(List.of(item1))).isEmpty();
    }
}
