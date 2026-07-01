package reporte;

import lombok.Getter;

@Getter
public class Estadistica {
    private int    cantidadTotal;
    private double montoTotal;

    public void acumular(double precioCobrado){
        this.cantidadTotal++;
        this.montoTotal += precioCobrado;
    }

    public double calcularPrecioPromedio(){
        return montoTotal / cantidadTotal;
    }

}