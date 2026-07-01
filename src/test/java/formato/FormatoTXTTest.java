package formato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reporte.Estadistica;
import reporte.ReporteProductosMasVendidos;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FormatoTXTTest {
    private FormatoTXT formato;
    private ReporteProductosMasVendidos reporte;

    @BeforeEach
    void setUp() {
        formato = new FormatoTXT();

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
    void verificarQueExportarGeneraElFormatoTXT() {
        formato.exportar(reporte);

        verify(reporte).accept(formato);
    }

    @Test
    void verificarQueElFormatoTXTVisitaElReporte() {
        formato.visitar(reporte);

        String txt = formato.getEstadoDeNegocio();

        assertTrue(txt.startsWith("Item | Cantidad | Precio promedio\n"));
        assertTrue(txt.contains("arroz | 1 | 1000.0"));
        assertTrue(txt.contains("leche | 1 | 2000.0"));
    }
}
