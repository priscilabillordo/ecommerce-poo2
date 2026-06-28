package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaqueteTest {
    private Paquete paquete;
    private Paquete paqueteVacio;
    private Producto p1;
    private Producto p2;
    private Producto p3;
    private List<Item> produtos;


    @BeforeEach
    public void setUp() {
        p1 = new Producto("Bizcochuelo en caja",
                "Bizcochuelo sabor chocolate Morixe 540 Grm",
                0,
                "7790199604198",
                "Morixe",
                "Almacen",
                3596,
                540,
                5
        );

        p2 = new Producto("Leche",
                "Leche parcialmente descremada liviana 1% La Serenisima 1L",
                0.25,
                "7790742363107",
                "La Serenisima",
                "Almacen",
                1999.03,
                1000,
                2
        );

        p3 = new Producto("Huevo 6u",
                "Huevo Blanco Grande 6u",
                0,
                "7798064180136",
                "-",
                "Almacen",
                1489,
                876,
                10
        );

        paquete = new Paquete("Bizcochuelo de chocolate",
                "Paquete con productos para preparar bizcochuelo de chocolate",
                0.1
        );

        paquete.incluir(p1);
        paquete.incluir(p2);
        paquete.incluir(p3);

        paqueteVacio = new Paquete(
                "vacio",
                "sin productos",
                0
        );
    }

    @Test
    void verificarAtributosDelPaquete() {
        assertEquals("Bizcochuelo de chocolate",
                paquete.getNombre());

        assertEquals("Paquete con productos para preparar bizcochuelo de chocolate",
                paquete.getDescripcion());

        assertEquals("Almacen", paquete.getCategoria());
        assertEquals(2416, paquete.getPeso());
        assertEquals(0.1,  paquete.getDescuento());

        assertEquals(7084.03, paquete.getPrecioBase());
        assertEquals(6375.6269999999995, paquete.getPrecioFinal());

        assertEquals(2, paquete.getStock());
        assertTrue(paquete.hayStock());
    }


    @Test
    void verificarElDecrementoDelStockDePaquete() {
        paquete.decrementarStock();
        assertEquals(1, paquete.getStock());
    }

    @Test
    void paqueteSinItemsTieneStockCero() {
        assertEquals(0, paqueteVacio.getStock());
        assertFalse(paqueteVacio.hayStock());
    }

    @Test
    void verificarElAumentoDelStockDePaquete() {
        paquete.aumentarStock();
        assertEquals(3, paquete.getStock());
    }
}
