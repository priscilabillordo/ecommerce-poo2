package reporte;

import item.Item;
import venta.Venta;

import java.util.List;
import java.util.Map;

public interface Reporte {
    void visitar(Venta venta);
    Map<String, Estadistica> generarReporte(List<Venta> ventas);
}
