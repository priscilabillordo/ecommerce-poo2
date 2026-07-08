package reporte;

import formato.Formato;
import item.Item;
import lombok.Getter;
import venta.Venta;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ReporteProductosMasVendidos implements Reporte {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Map<String, Estadistica> estadisticas;

    public ReporteProductosMasVendidos(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio  = fechaInicio;
        this.fechaFin     = fechaFin;
        this.estadisticas = new HashMap<>();
    }


    @Override
    public Reporte generarReporte(List<Venta> ventas) {
        ventas.stream()
                .filter(v -> !v.fueEntre(this.fechaInicio, this.fechaFin))
                .forEach(v -> this.crearEstadisticas(v));
        return this;
    }

    private void crearEstadisticas(Venta venta) {
        for (Item item : venta.getItems()) {
            //calcula e inserta un valor para una clave específica
            Estadistica estadistica = estadisticas.computeIfAbsent(item.getNombre(), n -> new Estadistica());
            estadistica.acumular(item.getPrecioFinal());
        }
    }

    @Override
    public void accept(Formato formato) {
        formato.visitar(this);
    }
}