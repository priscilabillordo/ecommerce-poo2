package medioDePago.transferenciaBancaria;

import lombok.Getter;
import medioDePago.MedioDePago;

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
    public void validarDatos() {
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
    public void notificarResultado() {
        this.api.generarComprobante();
    }
}
