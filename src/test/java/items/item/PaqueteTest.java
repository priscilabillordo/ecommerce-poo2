package items.item;

import items.item.Paquete;
import items.item.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaqueteTest {
    Paquete p;
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
                "Frescos",
                1999.03,
                1000,
                2
        );

        p3 = new Producto("Huevo 6u",
                "Huevo Blanco Grande 6u",
                0,
                "7798064180136",
                "-",
                "Frescos",
                1489,
                876,
                10
        );

        p = new Paquete("Bizcochuelo de chocolate",
                "Paquete con productos para preparar bizcochuelo de chocolate",
                0.1
        );

        p.agregarItem(p1);
        p.agregarItem(p2);
        p.agregarItem(p3);
    }

    @Test
    void verificarNombreDelPaquete() {
        assertEquals("Bizcochuelo de chocolate",
                p.getNombre());
    }

    @Test
    void verificarDescripcionDelPaquete() {
        assertEquals("Paquete con productos para preparar bizcochuelo de chocolate",
                p.getDescripcion());
    }

    @Test
    void verificarElPrecioBaseDelPaquete() {
        assertEquals(7084.03, p.getPrecioBase());
    }

    @Test
    void verificarElPrecioFinalDelPaquete() {
        assertEquals(6375.6269999999995, p.getPrecioFinal());
    }

    @Test
    void verificarElPesoDelPaquete() {
        assertEquals(2416, p.getPeso());
    }
}
