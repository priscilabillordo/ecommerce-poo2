package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class EcommerceData {

    private List<NotaDeCredito> notasDeCredito;

    public EcommerceData(){
        this.notasDeCredito = new ArrayList<>();
    }

    public void agregarNota(NotaDeCredito notaDeCredito){
        this.notasDeCredito.add(notaDeCredito);
    }
}
