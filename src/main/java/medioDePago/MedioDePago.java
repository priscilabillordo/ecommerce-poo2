package medioDePago;

public abstract class MedioDePago {

    public final void procesarPago(){
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
    public abstract void notificarResultado();
}
