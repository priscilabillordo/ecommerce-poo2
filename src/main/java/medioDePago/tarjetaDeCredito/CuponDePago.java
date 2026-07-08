package medioDePago.tarjetaDeCredito;

public class CuponDePago {

    private String ultimosNumerosTarjeta;
    private String codigoTransaccion;

    public CuponDePago(String ultimosNumerosTarjeta, String codigoTransaccion){
        this.ultimosNumerosTarjeta = ultimosNumerosTarjeta;
        this.codigoTransaccion = codigoTransaccion;
    }
}
