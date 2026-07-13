package medioDePago.billeteraVirtual;

import exceptions.MedioDePagoException;
import lombok.Getter;
import medioDePago.MedioDePago;
import pedido.Pedido;

@Getter
public class BilleteraVirtual extends MedioDePago {
    private double saldo;
    private APIBilletera api;

    public BilleteraVirtual(double saldo, APIBilletera api){
        this.saldo = saldo;
        this.api = api;
    }

    @Override
    public void validarDatos() throws MedioDePagoException {
        this.api.validarSaldo();
    }

    @Override
    public void reservarFondos() {
        this.api.bloquearSaldo();
    }

    @Override
    public void ejecutarTransaccion() {
        this.api.acreditar();
    }

    @Override
    public void notificarResultado(Pedido pedido) {
        super.notificarResultado(pedido);
        this.api.notificar(pedido);
    }
}
