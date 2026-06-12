package medioDePago.tarjetaDeCredito;

import lombok.Getter;
import medioDePago.MedioDePago;

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
    public void validarDatos() {
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
    public void notificarResultado() {
        this.api.generarCupon();
    }
}
