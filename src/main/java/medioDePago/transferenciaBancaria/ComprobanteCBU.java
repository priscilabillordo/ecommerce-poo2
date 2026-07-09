package medioDePago.transferenciaBancaria;

import medioDePago.ComprobantePago;

public class ComprobanteCBU extends ComprobantePago {
    private String cbu;

    public ComprobanteCBU(String cbu, String codigo) {
        super(codigo);
        this.cbu = cbu;
    }
}
