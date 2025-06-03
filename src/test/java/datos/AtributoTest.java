package datos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @brief Clase de prueba para la clase Atributo.
 */
 class AtributoTest {
    /**
     * @brief Prueba unitaria para verificar el comportamiento de la clase Atributo.
     */
    @Test
     void testAtributo() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        assertEquals("nombre", atributo.getNombre());
        assertEquals("[valor]", atributo.getValores().toString());
    }

    /**
     * @brief Prueba unitaria para verificar el metodo getPeso de la clase Atributo.
     */
    @Test
     void testAtributoGetPeso() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        assertEquals(1.0, atributo.getPeso(), 0.001);
    }

    /**
     * @brief Prueba unitaria para verificar el metodo get de la clase Atributo.
     */
    @Test
     void testAtributoGet() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        assertEquals("nombre: 1.0", atributo.get());
    }

    /**
     * @brief Prueba unitaria para verificar el metodo getNombre de la clase Atributo.
     */
    @Test
     void testAtributoSetNombre() {
        Atributo atributo = new Cualitativo("nombre", "valor");
        atributo.setNombre("nuevoNombre");
        assertEquals("nuevoNombre", atributo.getNombre());
    }
}
