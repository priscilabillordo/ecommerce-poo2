package medioDePago.tarjetaDeCredito;

import exceptions.MedioDePagoException;
import lombok.Getter;
import medioDePago.ComprobantePago;
import medioDePago.MedioDePago;
import pedido.Pedido;

import java.time.LocalDate;

@Getter
public class TarjetaDeCredito extends MedioDePago {

    private String numeroDeTarjeta, cvv;
    private LocalDate vencimiento;
    private APITarjetaCredito api;

    public TarjetaDeCredito(String numeroDeTarjeta, String cvv, LocalDate vencimiento, APITarjetaCredito api){
        this.numeroDeTarjeta = numeroDeTarjeta;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
        this.api = api;
    }

    @Override
    public void validarDatos() throws MedioDePagoException {
        this.api.validarTarjeta();
    }

    @Override
    public void reservarFondos() {
        this.api.preautorizar();
    }

    @Override
    public void ejecutarTransaccion() {
        this.api.transferir();
    }

    @Override
    public void notificarResultado(Pedido pedido) {
        super.notificarResultado(pedido);
        String ultimosDigitosTarjeta = this.numeroDeTarjeta.substring(this.numeroDeTarjeta.length() - 4);
        CuponDePago comprobante = new CuponDePago(ultimosDigitosTarjeta, this.getCodigoTransaccion());

        pedido.registrarTransaccion(comprobante); //le doy al pedido el comprobante creado, para que él se encargue de guardarlo
    }
}
