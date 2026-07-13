package medioDePago.comprobanteDePago;

public class ComprobanteCBU implements ComprobanteDePago {

    private String nroOperacion;
    private String cbu;

    public ComprobanteCBU(String nroOperacion, String cbu){
        this.nroOperacion = nroOperacion;
        this.cbu = cbu;
    }

    @Override
    public String imprimir() {
        return """
        ======================================
            COMPROBANTE DE TRANSFERENCIA
        ======================================
        CBU Origen   : %s
        Numero de Operacion: %s
        Estado: ACREDITADA
        ======================================
        """.formatted(cbu, nroOperacion);
    }
}
