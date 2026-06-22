package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductoTest {
    Producto p1;
    Atributo a;

    @BeforeEach
    public void setUp() {
        p1 = new Producto("Bizcochuelo en caja",
                "Bizcochuelo sabor chocolate Morixe 540 Grm",
                0.25,
                "7790199604198",
                "Morixe",
                "Almacen",
                3596,
                540,
                5
        );

        a = new Atributo("fechaDeVencimiento", 2026-06-11);
    }

    @Test
    void verificarAtributosObligatoriosDelProducto() {
        assertEquals("Bizcochuelo en caja", p1.getNombre());
        assertEquals("7790199604198",       p1.getSku());

        assertEquals("Bizcochuelo sabor chocolate Morixe 540 Grm", p1.getDescripcion());

        assertEquals("Morixe",  p1.getMarca());
        assertEquals("Almacen", p1.getCategoria());

        assertEquals(5,    p1.getStock());
        assertEquals(540,  p1.getPeso());
        assertEquals(3596, p1.getPrecioBase());
        assertEquals(2697, p1.getPrecioFinal());
        assertEquals(0.25, p1.getDescuento());

        assertTrue(p1.hayStock());
    }

    @Test
    void verificarAtributosDinamicosDelProducto() {
        p1.agregarAtributoDinamico(a);
        assertTrue(p1.getAtributos().contains(a));
    }

    @Test
    void verificarElAumentoDeStockDelProducto() {
        p1.aumentarStock();

        assertEquals(6, p1.getStock());
    }

    @Test
    void verificarElDecrementoDeStockDelProducto() {
        p1.decrementarStock();

        assertEquals(4, p1.getStock());
    }
}
