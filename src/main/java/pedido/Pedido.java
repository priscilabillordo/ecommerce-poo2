package pedido;

import item.Item;
import lombok.Getter;
import lombok.Setter;
import medioDePago.MedioDePago;
import metodoDeEnvio.MetodoDeEnvio;
import pedido.estadoPedido.EstadoPedido;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Pedido {

    private EstadoPedido estado;
    private List<Item> items;
    private String direccionEntrega;
<<<<<<< HEAD
    // * Comento porque todavia no está definida la clase MetodoDeEnvio
    // private MetodoDeEnvio metodoDeEnvio;
    private MedioDePago medioDePago;
    private EcommerceData data;

    public Pedido(String direccionEntrega, MedioDePago medioDePago, MetodoDeEnvio metodoDeEnvio){
        this.items = new ArrayList<>();
        this.direccionEntrega = direccionEntrega;
        this.medioDePago = medioDePago;
        this.metodoDeEnvio = metodoDeEnvio;
    }

    public void agregarItem(Item item){
        this.estado.cargarItem(item, this);
    }

    public void sacarItem(Item item){
        this.estado.quitarItem(item, this);
    }

    public void confirmar(){
        this.estado.confirmarPedido(this);
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

    public double peso(){
        return this.items.stream().mapToDouble(i -> i.getPeso()).sum();
    }

    public double costoDeItems(){
        return this.items.stream().mapToDouble(Item::getPrecioFinal).sum();
    }

    public double costo(){
        return 0d;
        // this.metodoDeEnvio.costoDeEnvio(this);
    }


    public void addItem(Item item){
        this.items.add(item);
    }

    public void deleteItem(Item item){
        this.items.remove(item);
    }

    public void decrementarStock(){
        // todo: c/item tiene que decrementar su stock
        // this.items.forEach(i -> i.decrementarStock())
    }
    public void reponerStock(){
        // todo
        // Confirmado --> Cancelado
        //   - repone stock de las unidades
    }

    public void reembolsar(NotaDeCredito notaDeCredito){
        this.data.agregarNota(notaDeCredito);
    }
    public double costoDeItems() {
        return 0;
    }

}
