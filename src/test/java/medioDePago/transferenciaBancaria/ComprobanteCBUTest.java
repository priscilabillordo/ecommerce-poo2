package medioDePago.transferenciaBancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComprobanteCBUTest {
    private ComprobanteCBU comprobanteCBU;

    @BeforeEach
    void setUp() {
        comprobanteCBU = new ComprobanteCBU("1234567891011121314151", "8972341895");
    }

    @Test
    void seInicializaUnComprobanteCBUCorrectamente(){
        assertEquals("8972341895", comprobanteCBU.getCodigo());

    }
}
