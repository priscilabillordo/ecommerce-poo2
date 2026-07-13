import ecommerce.EcommerceData;
import formato.Formato;
import formato.FormatoCSV;
import formato.FormatoHTML;
import formato.FormatoTXT;
import item.Item;
import item.Paquete;
import item.Producto;
import medioDePago.MedioDePago;
import medioDePago.billeteraVirtual.APIBilletera;
import medioDePago.billeteraVirtual.BilleteraVirtual;
import medioDePago.tarjetaDeCredito.APITarjetaCredito;
import medioDePago.tarjetaDeCredito.TarjetaDeCredito;
import medioDePago.transferenciaBancaria.APITransferencia;
import medioDePago.transferenciaBancaria.TransferenciaBancaria;
import metodoDeEnvio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import reporte.Reporte;
import reporte.ReporteProductosMasVendidos;
import unqShop.UnqShop;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class mainTest {
    private UnqShop unqShop;
    private EcommerceData ecommerceData;

    private Reporte reporteDeVendidos;
    private Formato formatotxt;
    private Formato formatocsv;
    private Formato formatohtml;

    private Pedido unPedidoEnviado;
    private Pedido otroPedidoEnviado;
    private Pedido pedidoCancelado;
    private Pedido pedidoEnProceso;

    private MedioDePago tarjetaCredito;
    private MedioDePago transferenciaBancaria;
    private MedioDePago billeteraVirtual;

    private MetodoDeEnvio retiroSucursal;
    private MetodoDeEnvio envioEstandar;
    private MetodoDeEnvio envioExpress;

    private Sucursal sucursalCentral;
    private EnvioExpressI   envioExpressI;
    private CorreoArgentino correoArgentino;
    private APITransferencia  apiTransferencia;
    private APIBilletera      apiBilletera;
    private APITarjetaCredito apiTarjetaCredito;

    private Item productoAuriculares;
    private Item productoTeclado;
    private Item productoLaptop;
    private Item productoCamara;
    private Item paqueteGamer;

    @BeforeEach
    void setUp() {
        productoAuriculares = new Producto(
                "Audicular Sony",
                "Audiculares con un diseño de lujo elaborado con materiales cuidadosamente seleccionados",
                0.30,
                "SKU001",
                "Sony",
                "Tecnologia",
                89999.99,
                320,
                50
        );

        productoTeclado = new Producto(
                "Teclado Mecánico Logitech",
                "Teclado mecánico con retroiluminación RGB, switches de alta precisión y diseño ergonómico",
                0.85,
                "SKU002",
                "Logitech",
                "Tecnologia",
                74999.99,
                850,
                35
        );
        productoLaptop  = new Producto(
                "Laptop Lenovo ThinkPad",
                "Notebook de alto rendimiento con procesador Intel Core i7, 16 GB de RAM y SSD de 512 GB",
                0,
                "SKU003",
                "Lenovo",
                "Tecnologia",
                1850000.00,
                1750,
                12
        );
        productoCamara  = new Producto(
                "Cámara Canon EOS R50",
                "Cámara mirrorless con sensor de alta resolución, enfoque automático rápido y grabación en 4K",
                0,
                "SKU004",
                "Canon",
                "Tecnologia",
                1250000.00,
                650,
                8
        );

        paqueteGamer = new Paquete(
                "Oferta de gamer",
                "Incluye un teclado y un auricular",
                0.25
        );
        paqueteGamer.incluir(productoTeclado);
        paqueteGamer.incluir(productoAuriculares);

        ecommerceData = new EcommerceData();
        unqShop = new UnqShop(ecommerceData);

        //SE AGREGAN LOS ITEMS AL UNQSHOP
        unqShop.agregarItem(productoAuriculares);
        unqShop.agregarItem(productoTeclado);
        unqShop.agregarItem(productoCamara);
        unqShop.agregarItem(productoLaptop);

        unqShop.agregarItem(paqueteGamer);

        //EXTRAS
        sucursalCentral = mock(Sucursal.class);
        when(sucursalCentral.hayStock(any())).thenReturn(true);
        correoArgentino = mock(CorreoArgentino.class);
        when(correoArgentino.estimarEnvio(anyFloat(), anyString())).thenReturn(2000f);
        envioExpressI   = mock(EnvioExpressI.class);
        when(envioExpressI.calcularCosto(anyFloat())).thenReturn(1000f);

        apiTarjetaCredito = mock(APITarjetaCredito.class);
        apiTransferencia  = mock(APITransferencia.class);
        apiBilletera = mock(APIBilletera.class);

        //TIPOS DE ENVIOS
        retiroSucursal  = new RetiroEnSucursal(sucursalCentral);
        envioEstandar   = new EnvioEstandar(correoArgentino);
        envioExpress    = new EnvioExpress(envioExpressI);

        //TIPO DE PAGO
        transferenciaBancaria = new TransferenciaBancaria("1234567891011121314151","priscilaCuenta", apiTransferencia);
        billeteraVirtual = new BilleteraVirtual(20000, apiBilletera);
        tarjetaCredito   = new TarjetaDeCredito("1234-5678-7654-3210", "100", LocalDate.of(2030,6,29), apiTarjetaCredito);

        //PEDIDOS; 2 PEDIDO ENVIADO, 1 PEDIDO CANCELADO, 1 PEDIDO EN PROCESO
        unPedidoEnviado   = new Pedido("Av. Siempre viva", transferenciaBancaria, retiroSucursal, ecommerceData);
        otroPedidoEnviado = new Pedido("Av. Nunca viva", billeteraVirtual, envioEstandar, ecommerceData);
        pedidoCancelado   = new Pedido("Roque Saenz Penia", tarjetaCredito, envioExpress, ecommerceData);
        pedidoEnProceso   = new Pedido("Roque Saenz Penia", transferenciaBancaria, retiroSucursal, ecommerceData);

        //AGREGO PEDIDOS A LA UNQSHOP
        unqShop.registrarPedido(unPedidoEnviado);
        unqShop.registrarPedido(otroPedidoEnviado);
        unqShop.registrarPedido(pedidoCancelado);
        unqShop.registrarPedido(pedidoEnProceso);

        // =======================================  FLUJO DE PEDIDOS  ===============================================

        // PEDIDO ENVIADO 1
        unPedidoEnviado.agregarItem(paqueteGamer);
        unPedidoEnviado.confirmar();
        unPedidoEnviado.preparar();
        unPedidoEnviado.enviar();
        unPedidoEnviado.entregar();

        // PEDIDO ENVIADO 2
        otroPedidoEnviado.agregarItem(paqueteGamer);
        otroPedidoEnviado.agregarItem(productoLaptop);
        otroPedidoEnviado.confirmar();
        otroPedidoEnviado.preparar();
        otroPedidoEnviado.enviar();
        otroPedidoEnviado.entregar();

        // PEDIDO CANCELADO CON REEMBOLSO
        pedidoCancelado.agregarItem(productoLaptop);
        pedidoCancelado.agregarItem(productoAuriculares);
        pedidoCancelado.confirmar();
        pedidoCancelado.preparar();
        pedidoCancelado.cancelar();


        // PEDIDO EN PROCESO
        pedidoEnProceso.agregarItem(paqueteGamer);
        pedidoEnProceso.sacarItem(paqueteGamer);
        pedidoEnProceso.agregarItem(productoAuriculares);

        //REPORTE
        reporteDeVendidos = new ReporteProductosMasVendidos(LocalDate.of(2026,7,13), LocalDate.now());

        //TIPOS DE FORMATO
        formatocsv  = new FormatoCSV();
        formatohtml = new FormatoHTML();
        formatotxt  = new FormatoTXT();
    }

    @Test
    void verificar() {
        System.out.println("Sistema de Gestion UNQ SHOP");
        System.out.println("Los reportes de las ventas son:");
        System.out.println(unqShop.generarReporte(reporteDeVendidos, formatocsv));
        System.out.println(unqShop.generarReporte(reporteDeVendidos, formatotxt));
        System.out.println("La cantidad de pedidos es " + unqShop.getPedidos().size());
        System.out.println("La cantidad de ventas es " + unqShop.ventas().size());
        System.out.println("La cantidad de notas de credito es " + unqShop.notasDeCredito().size());
    }

}
