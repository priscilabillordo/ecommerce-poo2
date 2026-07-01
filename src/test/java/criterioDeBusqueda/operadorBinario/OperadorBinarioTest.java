package criterioDeBusqueda.operadorBinario;

import criterioDeBusqueda.*;
import criterioDeBusqueda.criterioSimple.CriterioPorDisponibilidad;
import criterioDeBusqueda.criterioSimple.CriterioPorNombre;
import criterioDeBusqueda.criterioSimple.CriterioPorPrecioMax;
import item.Item;
import item.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OperadorBinarioTest {

    private Item pelucheChiikawaConStock, pelucheUsagiSinStock, pelucheTataSinStock, cartucheraPusheenConStock;
    private CriterioDeBusqueda unCriterio;
    private List<Item> items;

    @BeforeEach
    void setUp(){
        pelucheChiikawaConStock = new Producto("Peluche de Chiikawa", "Figura con forma de oso", 0, "10CHKW", "Miniso", "Chiikawa", 25000, 200, 1000);
        pelucheUsagiSinStock = new Producto("Peluche de Usagi", "Figura con forma de conejo", 0, "10USG", "Miniso", "Chiikawa", 25000, 200, 0);
        pelucheTataSinStock = new Producto("Peluche de Tata", "Figura con forma de corazon", 5, "20TBTS", "Miniso", "BTS", 36000, 200, 0);
        cartucheraPusheenConStock = new Producto("Cartuchera de Pusheen", "Cartuchera tierna", 25, "54PSHN", "Miniso", "Escolar", 20000, 50, 200);
        items = List.of(pelucheChiikawaConStock, pelucheUsagiSinStock, pelucheTataSinStock, cartucheraPusheenConStock);
        unCriterio = new OperadorOR(
                new OperadorAND(
                        new CriterioPorDisponibilidad(),
                        new CriterioPorNombre("Peluche")
                ),
                /*
                (Disponible AND Contiene "Peluche") = pelucheChiikawaConStock
                * */
                new OperadorNOT(
                        new CriterioPorPrecioMax(26000)
                ));
                /*
                NOT precio > 26000 = pelucheTataSinStock
                * */

                /*
                (Disponible AND Contiene "Peluche") OR (NOT precio > 26000) = pelucheChiikawaConStock, cartucheraPusheenConStock, pelucheTataSinStock
                * */
    }

    @Test
    void seFiltranItemsConUnCriterioCorrectamente(){
        assertThat(unCriterio.filtrarItems(items)).contains(pelucheChiikawaConStock, pelucheTataSinStock);
    }

    @Test
    void noSeFiltranItemsConUnCriterio(){
        assertThat(unCriterio.filtrarItems(items)).doesNotContain(cartucheraPusheenConStock);
    }



}
