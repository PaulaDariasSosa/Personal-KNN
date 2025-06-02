package datos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class AtributoTest {
    @Test
     void testAtributo() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        assertEquals("nombre", atributo.getNombre());
        assertEquals("[valor]", atributo.getValores().toString());
    }

    @Test
     void testAtributoGetPeso() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        assertEquals(1.0, atributo.getPeso(), 0.001);
    }

    @Test
     void testAtributoGet() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        assertEquals("nombre: 1.0", atributo.get());
    }

    @Test
     void testAtributoSetNombre() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        atributo.setNombre("nuevoNombre");
        assertEquals("nuevoNombre", atributo.getNombre());
    }
}
