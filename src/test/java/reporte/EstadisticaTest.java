package reporte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstadisticaTest {
    private Estadistica estadistica;

    @BeforeEach
    void setUp() {
        estadistica = new Estadistica();
    }

    @Test
    void verificarEstadoInicialDeEstadistica() {
        assertEquals(0, estadistica.getCantidadTotal());
        assertEquals(0.0, estadistica.getMontoTotal());
    }

    @Test
    void verificarQueAcumularActualizaCantidadYMonto() {
        estadistica.acumular(1000);
        estadistica.acumular(2000);

        assertEquals(2, estadistica.getCantidadTotal());
        assertEquals(3000.0, estadistica.getMontoTotal());
    }

    @Test
    void verificarQueCalcularPrecioPromedioDevuelveElPromedioCorrecto() {
        estadistica.acumular(1000);
        estadistica.acumular(2000);
        estadistica.acumular(3000);

        assertEquals(2000.0, estadistica.calcularPrecioPromedio());
    }
}
