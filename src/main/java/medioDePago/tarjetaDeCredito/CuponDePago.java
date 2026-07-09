package medioDePago.tarjetaDeCredito;

import medioDePago.ComprobantePago;

public class CuponDePago extends ComprobantePago {
    private String ultimosNumerosTarjeta;

    public CuponDePago(String ultimosNumerosTarjeta, String codigo){
        super(codigo);
        this.ultimosNumerosTarjeta = ultimosNumerosTarjeta;
    }
}
