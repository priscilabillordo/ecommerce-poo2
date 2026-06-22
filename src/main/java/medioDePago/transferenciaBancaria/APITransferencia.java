package medioDePago.transferenciaBancaria;

public interface APITransferencia {

    void validarCuenta();
    void transferir();
    void generarComprobante();
}
