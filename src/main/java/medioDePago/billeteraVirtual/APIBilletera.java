package medioDePago.billeteraVirtual;

import exceptions.MedioDePagoException;
import pedido.Pedido;

public interface APIBilletera {

    void validarSaldo() throws MedioDePagoException;
    void bloquearSaldo();
    void acreditar();
    void notificar(Pedido pedido);
}
