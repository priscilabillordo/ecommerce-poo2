package ecommerce;

import lombok.Getter;
import venta.Venta;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EcommerceData {
    private final List<Venta> ventas;
    private final List<NotaDeCredito> notasDeCredito;

    public EcommerceData(){
        this.notasDeCredito = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void agregarNota(NotaDeCredito notaDeCredito){
        this.notasDeCredito.add(notaDeCredito);
    }

    public void agregarVenta(Venta venta) { this.ventas.add(venta);}
}

