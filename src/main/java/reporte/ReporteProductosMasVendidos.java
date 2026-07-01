package reporte;

import item.Item;
import venta.Venta;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void visitar(Venta venta) {
        for (Item item : venta.getItems()) {
            //calcula e inserta un valor para una clave específica
            Estadistica estadistica = estadisticas.computeIfAbsent(item.getNombre(), n -> new Estadistica());
            estadistica.acumular(item.getPrecioFinal());
        }
    }

    @Override
    //TODO necesita ser más generico
    public Map<String, Estadistica>  generarReporte(List<Venta> ventas) {
        ventas.stream()
                .filter(v -> !v.getFecha().isBefore(this.fechaInicio))
                .filter(v -> !v.getFecha().isAfter(this.fechaFin))
                .forEach(v -> v.accept(this));

        return this.estadisticas;
    }
}
