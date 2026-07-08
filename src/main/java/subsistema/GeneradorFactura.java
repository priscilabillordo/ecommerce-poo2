package subsistema;

import pedido.Pedido;
import pedido.estadoPedido.Entregado;
import pedido.estadoPedido.EstadoPedido;

import java.time.LocalDate;

public class GeneradorFactura implements Subsistema {
    private ComprobanteFiscal comprobanteFiscal;

    public GeneradorFactura(ComprobanteFiscal comprobanteFiscal) {
        this.comprobanteFiscal = comprobanteFiscal;
    }

    @Override
    public void cambioAEntregado(Pedido pedido) {
        this.comprobanteFiscal.generarComprobante(pedido);
    }

    @Override
    public void cambioAEnviado(Pedido pedido) { }

    @Override
    public void cambioACancelado(Pedido pedido) { }

    @Override
    public void cambioAConfirmado(Pedido pedido) { }
}
