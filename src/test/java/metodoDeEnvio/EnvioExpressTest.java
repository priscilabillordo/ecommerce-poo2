package metodoDeEnvio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pedido.Pedido;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class EnvioExpressTest {
    private EnvioExpress envioExpress;
    private EnvioExpressI envioExpressI;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        envioExpressI = mock(EnvioExpressI.class);
        when(envioExpressI.calcularCosto(500F)).thenReturn(800F);

        pedido = mock(pedido.Pedido.class);
        when(pedido.costoDeItems()).thenReturn(500.0);

        envioExpress = new EnvioExpress(envioExpressI);
    }

    @Test
    void verificarElCostoDeEnvioExpress() {
        assertEquals(800, envioExpress.costoDeEnvio(pedido));
    }

    @Test
    void laEstimacionDeDiasDeEntregaDeUnPedidoEsUnDia(){
        assertThat(envioExpress.estimacionDeDias(pedido)).isEqualTo(1);
    }

}