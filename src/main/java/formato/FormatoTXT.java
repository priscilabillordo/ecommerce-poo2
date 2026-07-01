package formato;

import item.Item;
import lombok.Getter;
import reporte.Estadistica;
import reporte.Reporte;
import reporte.ReporteProductosMasVendidos;

import java.util.Map;

@Getter
public class FormatoTXT implements Formato {
    private String estadoDeNegocio;

    @Override
    public void visitar(ReporteProductosMasVendidos reporte) {
        StringBuilder sb = new StringBuilder();
        sb.append("Item | Cantidad | Precio promedio\n");

        reporte.getEstadisticas().forEach((clave, valor) -> {
            sb.append(clave)
                    .append(" | ")
                    .append(valor.getCantidadTotal())
                    .append(" | ")
                    .append(valor.getMontoTotal())
                    .append("\n");
        });

        this.estadoDeNegocio = sb.toString();
    }

    @Override
    public String exportar(Reporte reporte) {
        reporte.accept(this);
        return estadoDeNegocio;
    }

}
