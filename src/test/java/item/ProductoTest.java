package item;

import exceptions.ItemException;
import exceptions.PedidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {
    private Producto producto;
    private Producto productoIvalido;
    private Atributo atributo;
    private Atributo atributoInvalido;

    @BeforeEach
    public void setUp() {
        producto = new Producto("Bizcochuelo en caja",
                "Bizcochuelo sabor chocolate Morixe 540 Grm",
                0.25,
                "7790199604198",
                "Morixe",
                "Almacen",
                3596,
                540,
                2
        );

        atributo = new Atributo("fechaDeVencimiento", LocalDate.of(2026, 6, 11));
        atributoInvalido = new Atributo(null, null);
    }

    @Test
    void verificarAtributosObligatoriosDelProducto() {
        assertEquals("Bizcochuelo en caja", producto.getNombre());
        assertEquals("7790199604198",       producto.getSku());

        assertEquals("Bizcochuelo sabor chocolate Morixe 540 Grm", producto.getDescripcion());

        assertEquals("Morixe",  producto.getMarca());
        assertEquals("Almacen", producto.getCategoria());

        assertEquals(2,    producto.getStock());
        assertEquals(540,  producto.getPeso());
        assertEquals(3596, producto.getPrecioBase());
        assertEquals(2697, producto.getPrecioFinal());
        assertEquals(0.25, producto.getDescuento());

        assertTrue(producto.hayStock());
    }

    @Test
    void verificarAtributosDinamicosDelProducto() {
        producto.agregarAtributoDinamico(atributo);
        assertTrue(producto.getAtributos().contains(atributo));
    }

    @Test
    void verificarElAumentoDeStockDelProducto() {
        producto.aumentarStock();

        assertEquals(3, producto.getStock());
    }

    @Test
    void verificarDecrementoDeStockDelProducto() {
        producto.decrementarStock();

        assertEquals(1, producto.getStock());
    }

    @Test
    void verificarNoHayStockDelProducto() {
        producto.decrementarStock();
        producto.decrementarStock();

        assertFalse(producto.hayStock());
    }

    @Test
    void verificarNombreVacioInvalidoDelProducto() {
        assertThatThrownBy(() -> productoIvalido = new Producto("",
                "Leche parcialmente descremada liviana 1% La Serenisima 1L",
                0.25,
                "7790742363107",
                "La Serenisima",
                "Almacen",
                1999.03,
                1000,
                2))
                .isInstanceOf(ItemException.class)
                .hasMessage("El producto tiene algun atributo obligatorio sin asignar");
    }

    @Test
    void verificarNombreInvalidoDelProducto() {
        assertThatThrownBy(() -> productoIvalido = new Producto(null,
                "Leche parcialmente descremada liviana 1% La Serenisima 1L",
                0.25,
                "7790742363107",
                "La Serenisima",
                "Almacen",
                1999.03,
                1000,
                2))
                .isInstanceOf(ItemException.class)
                .hasMessage("El producto tiene algun atributo obligatorio sin asignar");
    }


    @Test
    void verificarDescuentoNegativoInvalidoDelProducto() {
        assertThatThrownBy(() -> productoIvalido = new Producto("Leche",
                "Leche parcialmente descremada liviana 1% La Serenisima 1L",
                -1,
                "7790742363107",
                "La Serenisima",
                "Almacen",
                1999.03,
                1000,
                2))
                .isInstanceOf(ItemException.class)
                .hasMessage("El producto tiene algun atributo obligatorio sin asignar");
    }

    @Test
    void verificarSkuVacioInvalidoDelProducto() {
        assertThatThrownBy(() -> new Producto(
                "Leche",
                "Leche parcialmente descremada liviana 1% La Serenisima 1L",
                0.25,
                "",
                "La Serenisima",
                "Almacen",
                100,
                100,
                1))
                .isInstanceOf(ItemException.class)
                .hasMessage("El producto tiene algun atributo obligatorio sin asignar");
    }

    @Test
    void verificarPrecioBaseInvalidoDelProducto() {
        assertThatThrownBy(() -> new Producto(
                "Leche",
                "Leche parcialmente descremada liviana 1% La Serenisima 1L",
                0.25,
                "7790742363107",
                "La Serenisima",
                "Almacen",
                0,
                100,
                1
                ))
                .isInstanceOf(ItemException.class)
                .hasMessage("El producto tiene algun atributo obligatorio sin asignar");
    }

    @Test
    void verificarAtributoDinamicoInvalidoDelProducto() {
        assertThatThrownBy(() -> producto.agregarAtributoDinamico(atributoInvalido))
                .isInstanceOf(ItemException.class)
                .hasMessage("El atributo dinamico que se quiere agregar no es valido");
    }

    @Test
    void verificarIncluirDeItem() {
        producto.incluir(null);
    }
}
