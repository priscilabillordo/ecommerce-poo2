package formato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reporte.Estadistica;
import reporte.ReporteProductosMasVendidos;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FormatoHTMLTest {
    private FormatoHTML formato;
    private ReporteProductosMasVendidos reporte;

    @BeforeEach
    void setUp() {
        formato = new FormatoHTML();

        reporte = mock(ReporteProductosMasVendidos.class);

        Map<String, Estadistica> estadisticas = new HashMap<>();

        Estadistica arroz = new Estadistica();
        arroz.acumular(1000);

        Estadistica leche = new Estadistica();
        leche.acumular(2000);

        estadisticas.put("arroz", arroz); //inserto clave y valor
        estadisticas.put("leche", leche); //inserto clave y valor

        when(reporte.getEstadisticas()).thenReturn(estadisticas);
    }

    @Test
    void verificarQueExportarGeneraElFormatoHTML() {
        formato.exportar(reporte);

        verify(reporte).accept(formato);
    }

    @Test
    void verificarQueElFormatoHTMLVisitaElReporte() {
        formato.visitar(reporte);

        String html = formato.getEstadoDeNegocio();

        assertTrue(html.contains("<table>"));
        assertTrue(html.contains("<th>Producto</th>"));
        assertTrue(html.contains("<th>Cantidad</th>"));
        assertTrue(html.contains("<th>Precio promedio</th>"));

        assertTrue(html.contains("<td>arroz</td>"));
        assertTrue(html.contains("<td>leche</td>"));

        assertTrue(html.contains("</table>"));
    }
}
