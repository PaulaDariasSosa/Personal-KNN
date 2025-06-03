package datos;

import org.junit.jupiter.api.Test;
import vectores.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @brief Clase de prueba para la clase Instancia.
 */
 class InstanciaTest {
   /**
    * @brief Prueba del constructor de la clase Instancia con una cadena de texto.
    */
   @Test
     void testInstanciaConstrucotrString() {
        Instancia instancia = new Instancia("1.0,2.0,3.0,ClaseA");
        assertEquals(4, instancia.getValores().size());
        assertEquals("1.0", instancia.getValores().get(0));
        assertEquals("ClaseA", instancia.getClase());
    }

   /**
    * @brief Prueba del constructor de la clase Instancia con una lista de valores.
    */
   @Test
     void testInstanciaConstructorList() {
        Instancia instancia = new Instancia(List.of(1.0, 2.0, 3.0, "ClaseA"));
        assert instancia.getValores().size() == 4;
        assert instancia.getValores().get(0).equals(1.0);
    }

   /**
    * @brief Prueba del método toString de la clase Instancia.
    */
   @Test
     void testInstanciaToString() {
        Instancia instancia = new Instancia("1.0,2.0,3.0,ClaseA");
        assertEquals("[1.0, 2.0, 3.0, ClaseA]", instancia.toString());
    }

   /**
    * @brief Prueba del método getVector de la clase Instancia.
    */
   @Test
     void testInstanciaGetVector() {
        Instancia instancia = new Instancia("1.0,2.0,3.0");
        Vector vector = instancia.getVector();
        assertEquals(0, vector.size());
    }

   /**
    * @brief Prueba del método getClase de la clase Instancia.
    */
   @Test
     void testInstanciaGetClase() {
        Instancia instancia = new Instancia("1.0,2.0,3.0,ClaseA");
        assertEquals("ClaseA", instancia.getClase());
    }

   /**
    * @brief Prueba del método deleteClase de la clase Instancia.
    */
   @Test
     void testInstanciaDeleteClase() {
        Instancia instancia = new Instancia("1.0,2.0,3.0,ClaseA");
        instancia.deleteClase();
        assertEquals(3, instancia.getValores().size());
        assertEquals("1.0", instancia.getValores().get(0));
        assertEquals("2.0", instancia.getValores().get(1));
        assertEquals("3.0", instancia.getValores().get(2));
    }
}
