package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaqueteTest {
    Paquete  p;
    Producto p1;
    Producto p2;
    Producto p3;
    List<Item> produtos;


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

        p = new Paquete("Bizcochuelo de chocolate",
                "Paquete con productos para preparar bizcochuelo de chocolate",
                0.1
        );

        p.incluir(p1);
        p.incluir(p2);
        p.incluir(p3);
    }

    @Test
    void verificarAtributosDelPaquete() {
        assertEquals("Bizcochuelo de chocolate",
                p.getNombre());

        assertEquals("Paquete con productos para preparar bizcochuelo de chocolate",
                p.getDescripcion());

        assertEquals("Almacen", p.getCategoria());
        assertEquals(2416, p.getPeso());
        assertEquals(0.1,  p.getDescuento());

        assertEquals(7084.03, p.getPrecioBase());
        assertEquals(6375.6269999999995, p.getPrecioFinal());

        assertEquals(2, p.getStock());
        assertTrue(p.hayStock());
    }

    @Test
    void verificarElDecrementoDelStockDePaquete() {
        p.decrementarStock();
        assertEquals(1, p.getStock());
    }

    @Test
    void verificarElAumentoDelStockDePaquete() {
        p.aumentarStock();
        assertEquals(3, p.getStock());
    }
}
