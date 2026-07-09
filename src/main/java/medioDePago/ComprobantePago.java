package medioDePago;

import lombok.Getter;

@Getter
public abstract class ComprobantePago {
    private String codigo;

    public ComprobantePago(String codigo) {
        this.codigo = codigo;
    }
}
