package formato;

import reporte.Estadistica;
import reporte.Reporte;
import reporte.ReporteProductosMasVendidos;

import java.util.Map;

public class FormatoHTML implements Formato {
    private String estadoDeNegocio;

    @Override
    public void visitar(ReporteProductosMasVendidos reporte) {
        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<th>Producto</th>");
        sb.append("<th>Cantidad</th>");
        sb.append("<th>Precio promedio</th>");
        sb.append("</tr>");

        reporte.getEstadisticas().forEach((clave, valor) -> {
            sb.append("<tr>");
            sb.append("<td>")
                    .append(clave)
                    .append("</td>");
            sb.append("<td>")
                    .append(valor.getMontoTotal())
                    .append("</td>");
            sb.append("<td>")
                    .append(valor.getCantidadTotal())
                    .append("</td>");
            sb.append("</tr>");
        });
        sb.append("</table>");

        this.estadoDeNegocio = sb.toString();
    }

    @Override
    public String exportar(Reporte reporte) {
        reporte.accept(this);
        return this.estadoDeNegocio;
    }
}
