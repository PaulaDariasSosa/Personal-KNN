package vectores;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @brief Clase de prueba para la clase Matriz.
 */
 class MatrizTest {
    /**
     * @brief Prueba unitaria para verificar la creación y el acceso a una matriz.
     */
    @Test
     void testMatriz() {
        Matriz m = new Matriz(3, 3);
        m.set(0, 0, 1.0);
        m.set(1, 1, 2.0);
        m.set(2, 2, 3.0);

        // Imprimir la matriz para verificar los valores
        m.print();

        // Verificar los valores específicos
        assertEquals(1.0, m.get(0, 0));
        assertEquals(2.0, m.get(1, 1));
        assertEquals(3.0, m.get(2, 2));
    }

    /**
     * @brief Prueba unitaria para verificar el manejo de excepciones al acceder a índices fuera de rango.
     */
    @Test
     void testMatrizConExcepciones() {
        Matriz m = new Matriz(3, 3);

        // Verificar que se lanza una excepción al acceder a índices fuera de rango
        try {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                m.get(3, 3);
            });
        } catch (IndexOutOfBoundsException e) {
            // Excepción esperada
        }
    }

    /**
     * @brief Prueba unitaria para verificar el acceso y la modificación de valores en una matriz.
     */
    @Test
     void testMatrizGet() {
        Matriz m = new Matriz(2, 2);
        m.set(0, 0, 5.0);
        m.set(0, 1, 10.0);
        m.set(1, 0, 15.0);
        m.set(1, 1, 20.0);

        // Verificar los valores obtenidos
        assertEquals(5.0, m.get(0, 0));
        assertEquals(10.0, m.get(0, 1));
        assertEquals(15.0, m.get(1, 0));
        assertEquals(20.0, m.get(1, 1));
    }

    /**
     * @brief Prueba unitaria para verificar la modificación de valores en una matriz.
     */
    @Test
     void testMatrizSet() {
        Matriz m = new Matriz(2, 2);
        m.set(0, 0, 1.0);
        m.set(0, 1, 2.0);
        m.set(1, 0, 3.0);
        m.set(1, 1, 4.0);

        // Verificar que los valores se establecieron correctamente
        assertEquals(1.0, m.get(0, 0));
        assertEquals(2.0, m.get(0, 1));
        assertEquals(3.0, m.get(1, 0));
        assertEquals(4.0, m.get(1, 1));
    }
}
