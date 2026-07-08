package metodoDeEnvio;

import pedido.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RetiroEnSucursalTest {

    private RetiroEnSucursal retiroEnSucursal;
    private Pedido pedido;
    private Sucursal unaSucursal;

    @BeforeEach
    void setUp(){

        pedido = mock(Pedido.class);
        unaSucursal = mock(Sucursal.class);

        retiroEnSucursal = new RetiroEnSucursal(unaSucursal);
    }

    @Test
    void seInicializaUnRetiroEnSucursalCorrectamente(){
        assertThat(retiroEnSucursal.getSucursal()).isEqualTo(unaSucursal);
    }

    @Test
    void elCostoDeEnvioDeUnPedidoQueSeRetiraEnSucursalEsCero(){
        assertThat(retiroEnSucursal.costoDeEnvio(pedido)).isEqualTo(0);
    }

    @Test
    void laEstimacionDeDiasDeUnPedidoConStockEnSucursalEsCero(){
        when(unaSucursal.hayStock(pedido)).thenReturn(true);

        assertThat(retiroEnSucursal.estimacionDeDias(pedido)).isEqualTo(0);
    }

    @Test
    void laEstimacionDeDiasDeUnPedidoSinStockEnSucursalEsTres(){
        when(unaSucursal.hayStock(pedido)).thenReturn(false);

        assertThat(retiroEnSucursal.estimacionDeDias(pedido)).isEqualTo(3);
    }

}
