package medioDePago.transferenciaBancaria;

import exceptions.MedioDePagoException;

public interface APITransferencia {

    void validarCuenta() throws MedioDePagoException;
    void transferir();
}
