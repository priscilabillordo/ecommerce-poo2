package formato;

import reporte.Reporte;
import reporte.ReporteProductosMasVendidos;

public interface Formato {
    String  exportar(Reporte reporte);

    void    visitar(ReporteProductosMasVendidos reporte);
}
