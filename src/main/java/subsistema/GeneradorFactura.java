package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.EstadoPedido;

import java.time.LocalDate;

public class GeneradorFactura implements Subsistema {
    @Override
    public void actualizar(Pedido pedido, EstadoPedido estadoAnterior, EstadoPedido estadoNuevo) {
        if (estadoNuevo.generaComprobante()) {
            this.generarComprobante("cliente@mail.com", pedido.getFecha() , pedido.costoTotal());
        }
    }

    private void generarComprobante(String emailCliente, LocalDate fecha, double total) {
        System.out.printf(
                "Cliente: %s%nFecha: %s%nTotal: %.2f%n",
                emailCliente, fecha, total
        );
    }
}
