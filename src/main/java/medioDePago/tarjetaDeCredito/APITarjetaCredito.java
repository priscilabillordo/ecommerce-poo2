package medioDePago.tarjetaDeCredito;

public interface APITarjetaCredito {

    void validarTarjeta();
    void preautorizar();
    void transferir();
    void generarCupon();
}
