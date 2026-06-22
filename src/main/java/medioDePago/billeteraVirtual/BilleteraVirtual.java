package medioDePago.billeteraVirtual;

import lombok.Getter;
import medioDePago.MedioDePago;

@Getter
public class BilleteraVirtual extends MedioDePago {
    private double saldo;
    private APIBilletera api;

    public BilleteraVirtual(double saldo, APIBilletera api){
        this.saldo = saldo;
        this.api = api;
    }

    @Override
    public void validarDatos() {
        this.api.validarSaldo(); // check si tira error
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
    public void notificarResultado() {
        this.api.notificar();
    }
}
