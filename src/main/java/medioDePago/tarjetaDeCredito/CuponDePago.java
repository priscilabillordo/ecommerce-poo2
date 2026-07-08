package medioDePago.tarjetaDeCredito;

public class CuponDePago {
    private String ultimosNumerosTarjeta;
    private String codigo;

    public CuponDePago(String ultimosNumerosTarjeta, String codigo){
        this.ultimosNumerosTarjeta = ultimosNumerosTarjeta;
        this.codigo = codigo;
    }
}
