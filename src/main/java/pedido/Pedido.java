package pedido;

import ecommerce.EcommerceData;
import ecommerce.NotaDeCredito;
import exceptions.PedidoException;
import item.Item;
import lombok.Getter;
import lombok.Setter;
import medioDePago.MedioDePago;
import metodoDeEnvio.MetodoDeEnvio;
import pedido.estadoPedido.Borrador;
import pedido.estadoPedido.EstadoPedido;
import subsistema.Subsistema;
import venta.Venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Pedido {

     final List<Subsistema> subsistemas;
     final List<Item> items;
     final EcommerceData data;
     final String direccionEntrega;
     private EstadoPedido estado;
     private MetodoDeEnvio metodoDeEnvio;
     private MedioDePago medioDePago;
     private String codigoTransaccion;
     private LocalDate fecha;

    public Pedido(String direccionEntrega, MedioDePago medioDePago, MetodoDeEnvio metodoDeEnvio, EcommerceData data){
        this.subsistemas = new ArrayList<>();
        this.items = new ArrayList<>();
        this.direccionEntrega = direccionEntrega;
        this.medioDePago = medioDePago;
        this.metodoDeEnvio = metodoDeEnvio;
        this.data = data;
        this.estado = new Borrador();
        this.fecha = null;
    }

    public double peso(){
        return this.items.stream().mapToDouble(i -> i.getPeso()).sum();
    }

    public double costoTotal()   { return this.costoDeItems() + this.costoDeEnvio(); }
    public double costoDeItems() { return this.items.stream().mapToDouble(Item::getPrecioFinal).sum(); }
    public double costoDeEnvio() { return this.metodoDeEnvio.costoDeEnvio(this); }

    public void agregarItem(Item item) {
        if (!item.hayStock()) {
            throw new PedidoException("No se puede agregar el item (sin stock)");
        }
        this.estado.cargarItem(item, this);
    }

    public void sacarItem(Item item) {
        if (!this.items.contains(item)){
            throw new PedidoException("El item no está en el pedido");
        }
        this.estado.quitarItem(item, this);
    }

    public void confirmar() {
        this.medioDePago.procesarPago(this);
        this.estado.confirmarPedido(this);
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        notificar();
    }

    public void notificar() {
        this.subsistemas.stream().forEach(s -> this.estado.notificar(this, s));
    }

    public void preparar(){
        this.estado.prepararPedido(this);
    }

    public void enviar(){
        this.estado.enviarPedido(this);
    }

    public void entregar(){
        this.estado.entregarPedido(this);
    }

    public void cancelar(){
        this.estado.cancelarPedido(this);
    }

    public void registrarTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;

    }


    // Metodos que son llamados por los estados, el cliente no accede a estos
    public void addItem(Item item) {
        this.items.add(item);
        item.decrementarStock();
    }

    public void deleteItem(Item item){
        this.items.remove(item);
        item.aumentarStock();
    }

    public void decrementarStock(){
        this.items.forEach(Item::decrementarStock);
    }
    public void reponerStock(){
        this.items.forEach(Item::aumentarStock);
    }

    public void reembolsar(NotaDeCredito notaDeCredito){
        this.data.agregarNota(notaDeCredito);
    }

    public void addSubsistema(Subsistema subsistema) {
        this.subsistemas.add(subsistema);
    }
    public void deleteubsistema(Subsistema subsistema) {
        this.subsistemas.remove(subsistema);
    }

    public void registrarVenta(Venta venta) {
        data.agregarVenta(venta);
    }

    // Constructor solo válido para testear, ya que un estado no es inyectado
    Pedido(String direccionEntrega, MedioDePago medioDePago, MetodoDeEnvio metodoDeEnvio, EcommerceData data, EstadoPedido estado){
        this.subsistemas = new ArrayList<>();
        this.items = new ArrayList<>();
        this.direccionEntrega = direccionEntrega;
        this.medioDePago = medioDePago;
        this.metodoDeEnvio = metodoDeEnvio;
        this.data = data;
        this.estado = estado;
    }

}
