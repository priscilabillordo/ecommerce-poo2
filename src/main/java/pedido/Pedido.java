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

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Pedido {

    private EstadoPedido estado;
    private List<Item> items;
    private String direccionEntrega;
    private MetodoDeEnvio metodoDeEnvio;
    private MedioDePago medioDePago;
    private EcommerceData data;

    public Pedido(String direccionEntrega, MedioDePago medioDePago, MetodoDeEnvio metodoDeEnvio, EcommerceData data){
        this.items = new ArrayList<>();
        this.direccionEntrega = direccionEntrega;
        this.medioDePago = medioDePago;
        this.metodoDeEnvio = metodoDeEnvio;
        this.data = data;
        this.estado = new Borrador();
    }

    public double peso(){
        return this.items.stream().mapToDouble(i -> i.getPeso()).sum();
    }

    public double costoDeItems(){
        return this.items.stream().mapToDouble(Item::getPrecioFinal).sum();
    }

    public double costo(){
        return this.metodoDeEnvio.costoDeEnvio(this);
    }

    public void agregarItem(Item item){
        /*
        * Agrega un item al pedido dependiendo el estado.
        * Solo funciona si su estado es Borrador, sino lanza una excepción.
        * */
        this.estado.cargarItem(item, this);
    }

    public void sacarItem(Item item){
        /*
         * Saca un item del pedido dependiendo el estado.
         * Solo funciona si su estado es Borrador, sino lanza una excepción.
         * */
        if (!this.items.contains(item)){
            throw new PedidoException("El item no está en el pedido");
        }
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

    /*
    * Metodos que son llamados por los estados, el cliente no accede a estos
    * */

    public void addItem(Item item){
        this.items.add(item);
    }

    public void deleteItem(Item item){
        this.items.remove(item);
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

}
