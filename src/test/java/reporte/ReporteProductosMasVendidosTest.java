package reporte;

import formato.Formato;
import item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import venta.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteProductosMasVendidosTest {
    private ReporteProductosMasVendidos reporte;
    private Venta venta1;
    private Venta venta2;
    private Venta venta3;
    private Pedido pedido1;
    private Pedido pedido2;
    private Pedido pedido3;

    private Item arroz;
    private Item leche;

    private Formato unFormato;

    @BeforeEach
    void setUp() {
        reporte = new ReporteProductosMasVendidos(
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2026, 6, 30));

        arroz = mock(Item.class);
        when(arroz.getNombre()).thenReturn("arroz");
        when(arroz.getPrecioFinal()).thenReturn(1000.0);

        leche = mock(Item.class);
        when(leche.getNombre()).thenReturn("leche");
        when(leche.getPrecioFinal()).thenReturn(2000.0);

        pedido1 = mock(Pedido.class);
        when(pedido1.getFecha()).thenReturn(LocalDate.of(2026, 6, 5));
        when(pedido1.getItems()).thenReturn(List.of(arroz));

        pedido2 = mock(Pedido.class);
        when(pedido2.getFecha()).thenReturn(LocalDate.of(2026, 6, 20));
        when(pedido2.getItems()).thenReturn(List.of(leche));

        pedido3 = mock(Pedido.class);
        when(pedido3.getFecha()).thenReturn(LocalDate.of(2026, 7, 5));
        when(pedido3.getItems()).thenReturn(List.of(arroz));

        venta1 = new Venta(pedido1);
        venta2 = new Venta(pedido2);
        venta3 = new Venta(pedido3);

        unFormato = mock(Formato.class);
    }

    @Test
    void seVerificaQueUnReporteFueInicializadoCorrectamente(){
        assertThat(reporte.getEstadisticas()).isEmpty();
        assertThat(reporte.getFechaInicio()).isEqualTo(LocalDate.of(2026, 6, 1));
        assertThat(reporte.getFechaFin()).isEqualTo(LocalDate.of(2026, 6, 30));
    }

    @Test
    void seVerificaQueUnReporteAceptaLaVisitaDeUnFormato(){
        reporte.accept(unFormato);
        verify(unFormato).visitar(reporte);
    }
    @Test
    void verificarQueGenerarReporteIncluyeSoloVentasDentroDelRango() {
        reporte.generarReporte(List.of(venta1, venta2, venta3));

        assertTrue(reporte.getEstadisticas().containsKey("arroz"));
        assertTrue(reporte.getEstadisticas().containsKey("leche"));
    }
/*
    @Test
    void verificarQueGenerarReporteNoIncluyeVentasFueraDelRango() {
        reporte.generarReporte(List.of(venta3));

        assertFalse(reporte.getEstadisticas().containsKey("arroz"));
    }*/
}