package medioDePago.billeteraVirtual;

import exceptions.MedioDePagoException;

public interface APIBilletera {

    void validarSaldo() throws MedioDePagoException;
    void bloquearSaldo();
    void acreditar();
    void notificar();
}
