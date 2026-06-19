package pedido;

import lombok.Getter;
import lombok.Setter;
import pedido.estadoPedido.EstadoPedido;

import item.Item;
import item.item.Item;
import metodoDeEnvio.MetodoDeEnvio;
import util.ArrayList;
import util.List;

@Setter
@Getter
public class Pedido {

    private EstadoPedido estado;
    private List<Item> items;
    private String direccionEntrega;
    private MetodoDeEnvio metodoDeEnvio;
    private MedioDePago medioDePago;
    // private double costo;

    public Pedido(String direccionEntrega, MedioDePago medioDePago, MetodoDeEnvio metodoDeEnvio){

        this.items = new ArrayList<Item>();
        this.direccionEntrega = direccionEntrega;
        this.medioDePago = medioDePago;
        this.metodoDeEnvio = metodoDeEnvio;
        // this.costo = 0;
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

    public void reembolsar(){
        // todo: todavia no se como lo hago a esto
        // EnPreparacion --> Cancelado
        //   - reembolsa tanto costo de prods y envio
        // Enviado --> Cancelado
        //   - reembolsa solo costo de prods
    }


}
