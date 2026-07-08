package medioDePago.tarjetaDeCredito;

import exceptions.MedioDePagoException;

public interface APITarjetaCredito {

    void validarTarjeta() throws MedioDePagoException;
    void preautorizar();
    void transferir();
}
