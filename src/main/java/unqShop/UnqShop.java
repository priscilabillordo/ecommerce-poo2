package unqShop;

import criterioDeBusqueda.CriterioDeBusqueda;
import ecommerce.EcommerceData;
import ecommerce.NotaDeCredito;
import formato.Formato;
import item.Item;
import lombok.Getter;
import pedido.Pedido;
import reporte.Reporte;
import venta.Venta;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UnqShop {

    private List<Pedido> pedidos;
    private List<Item> catalogo;
    private final EcommerceData data;

    public UnqShop(EcommerceData data){
        this.data = data;
        this.catalogo = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void registrarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    public List<Item> buscarItems(CriterioDeBusqueda criterio){
        return criterio.filtrarItems(this.catalogo);
    }

    public void agregarItem(Item item){
        this.catalogo.add(item);
    }

    public String generarReporte(Reporte reporte, Formato formato){
        reporte.generarReporte(this.ventas());
        return formato.exportar(reporte);
    }

    public List<Venta> ventas(){
        return this.data.getVentas();
    }

    public List<NotaDeCredito> notasDeCredito(){
        return this.data.getNotasDeCredito();
    }

}
