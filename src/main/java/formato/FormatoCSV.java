package formato;

import lombok.Getter;
import reporte.Reporte;
import reporte.ReporteProductosMasVendidos;

@Getter
public class FormatoCSV implements Formato {
    private String estadoDeNegocio;

    @Override
    public void visitar(ReporteProductosMasVendidos reporte) {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto,Cantidad,PrecioPromedio\n");

        reporte.getEstadisticas().forEach((clave, valor) -> {
            sb.append(clave)
                    .append(",")
                    .append(valor.getCantidadTotal())
                    .append(",")
                    .append(valor.getMontoTotal())
                    .append("\n");
        });

        this.estadoDeNegocio = sb.toString();
    }

    @Override
    //desde afuera no me importa cuál es
    public String exportar(Reporte reporte) {
        reporte.accept(this);
        return this.estadoDeNegocio;
    }
}
