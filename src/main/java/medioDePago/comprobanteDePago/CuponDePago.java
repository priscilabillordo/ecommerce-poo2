package medioDePago.comprobanteDePago;

public class CuponDePago implements ComprobanteDePago {
    private String ultimosNumerosTarjeta;
    private String codigo;

    public CuponDePago(String ultimosNumerosTarjeta, String codigo){
        this.ultimosNumerosTarjeta = ultimosNumerosTarjeta;
        this.codigo = codigo;
    }

    @Override
    public String imprimir() {
        return """
        ======================================
                    CUPON DE PAGO
        ======================================
        Comercio: UNQ SHOP
        Tarjeta: **** **** ****%s
        Código Transacción: %s
        Estado: APROBADA
        ======================================
        """.formatted(ultimosNumerosTarjeta, codigo);
    }
}
