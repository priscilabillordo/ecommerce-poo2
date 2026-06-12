package envios.envio;

public interface MetodoDeEnvio {
    public double costoDeEnvio(double precio, double peso, String direccionEnvio);
}
