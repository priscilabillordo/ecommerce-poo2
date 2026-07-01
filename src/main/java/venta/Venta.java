package venta;

import item.Item;
import lombok.Getter;
import pedido.Pedido;
import reporte.Reporte;
import reporte.ReporteProductosMasVendidos;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Venta {
    private final LocalDate fecha;
    private List<Item> items;

    public Venta(Pedido pedido) {
        this.fecha = pedido.getFecha();
        this.items = pedido.getItems();
    }

    public void accept(Reporte reporte) {
        reporte.visitar(this);
    }
}
