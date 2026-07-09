package medioDePago.transferenciaBancaria;

import exceptions.MedioDePagoException;
import lombok.Getter;
import medioDePago.ComprobantePago;
import medioDePago.MedioDePago;
import pedido.Pedido;

@Getter
public class TransferenciaBancaria extends MedioDePago {

    private String cbu, alias;
    private APITransferencia api;

    public TransferenciaBancaria(String cbu, String alias, APITransferencia api){
        this.cbu = cbu;
        this.alias = alias;
        this.api = api;
    }

    @Override
    public void validarDatos() throws MedioDePagoException {
        this.api.validarCuenta();
    }

    @Override
    public void reservarFondos() {
    }

    @Override
    public void ejecutarTransaccion() {
        this.api.transferir();
    }

    @Override
    public void notificarResultado(Pedido pedido) {
        super.notificarResultado(pedido);
        ComprobantePago comprobanteCBU = new ComprobanteCBU(this.cbu, this.getCodigoTransaccion());

        pedido.registrarTransaccion(comprobanteCBU); //se lo doy al pedido para que él se encargue de guardarlo
    }
}
