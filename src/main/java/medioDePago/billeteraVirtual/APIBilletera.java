package medioDePago.billeteraVirtual;

public interface APIBilletera {

    void validarSaldo();
    void bloquearSaldo();
    void acreditar();
    void notificar();
}
