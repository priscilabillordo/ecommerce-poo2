package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtributoTest {
    private Atributo atributo;
    private Atributo atributoInvalido;

    @BeforeEach
    void setUp() {
        atributo = new Atributo("color", "Rojo");
        atributoInvalido = new Atributo(" ", " ");
    }

    @Test
    void verificarGetterDeAtributo() {
        assertEquals("color", atributo.getNombre());
        assertEquals("Rojo", atributo.getDato());
    }

    @Test
    void verificarSiEsValidoLaInformacionDelAtributo() {
        assertTrue(atributo.esValido());
    }

    @Test
    void verificarSiNoEsValidoLaInformacionDelAtributo() {
        assertFalse(atributoInvalido.esValido());
    }
}
