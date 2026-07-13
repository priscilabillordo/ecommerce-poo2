package medioDePago.tarjetaDeCredito;

import exceptions.MedioDePagoException;
import lombok.Getter;
import medioDePago.MedioDePago;
import pedido.Pedido;

import java.time.LocalDate;

@Getter
public class TarjetaDeCredito extends MedioDePago {

    private String numeroDeTarjeta, cvv;
    private LocalDate vencimiento;
    private APITarjetaCredito api;
    private CuponDePago cupon;

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
        String ultimosDigitosTarjeta = this.numeroDeTarjeta.substring(this.numeroDeTarjeta.length() - 4);
        super.notificarResultado(pedido); // Esto es necesario para que se genere el codigo de transaccion
        this.cupon = new CuponDePago(ultimosDigitosTarjeta, this.getCodigoTransaccion());
    }
}
