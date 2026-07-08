package venta;

import item.Item;
import item.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import reporte.Reporte;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VentaTest {
    private Venta   venta;
    private Pedido  pedido;
    private Item    item;
    private Reporte reporte;

    @BeforeEach
    void setUp() {
        pedido = mock(Pedido.class);
        item   = mock(Producto.class);
        when(pedido.getItems()).thenReturn(List.of(item));
        when(pedido.getFecha()).thenReturn(LocalDate.of(2026, 7, 1));

        venta = new Venta(pedido);

        reporte = mock(Reporte.class);
    }

    @Test
    void verificarQueSeCreaCorrectamenteUnaVenta() {
        assertEquals(LocalDate.of(2026, 7, 1), venta.getFecha());
        assertEquals(List.of(item), venta.getItems());
    }

}
