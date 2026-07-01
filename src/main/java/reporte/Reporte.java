package reporte;

import formato.Formato;
import venta.Venta;

import java.util.List;

public interface Reporte {
    void visitar(Venta venta);

    Reporte generarReporte(List<Venta> ventas);

    void accept(Formato formato);
}
