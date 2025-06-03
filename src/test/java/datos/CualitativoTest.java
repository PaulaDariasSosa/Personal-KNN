package datos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @brief Clase de prueba para la clase Cualitativo.
 */
 class CualitativoTest {
    /**
     * @brief Test para verificar el constructor por defecto de Cualitativo.
     */
    @Test
     void testCualitativo() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals(3, atributo.size());
        assertEquals("Color: 1.0", atributo.get());
    }

    /**
     * @brief Test para verificar el constructor con un valor inicial.
     */
    @Test
     void testCualitativoGetPeso() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals(1.0, atributo.getPeso(), 0.001);
    }

    /**
     * @brief Test para verificar el método get() de Cualitativo.
     */
    @Test
     void testCualitativoGet() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals("Color: 1.0", atributo.get());
    }

    /**
     * @brief Test para verificar el método setNombre() de Cualitativo.
     */
    @Test
     void testCualitativoSetNombre() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.setNombre("NuevoColor");
        assertEquals("NuevoColor", atributo.getNombre());
    }

    /**
     * @brief Test para verificar el método add() de Cualitativo.
     */
    @Test
     void testCualitativoAdd() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals(3, atributo.size());
        assertEquals("Rojo", atributo.getValor(0));
        assertEquals("Verde", atributo.getValor(1));
        assertEquals("Azul", atributo.getValor(2));
    }

    /**
     * @brief Test para verificar el método delete() de Cualitativo.
     */
    @Test
     void testCualitativoDelete() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        atributo.delete(1);
        assertEquals(2, atributo.size());
        assertEquals("Rojo", atributo.getValor(0));
        assertEquals("Azul", atributo.getValor(1));
    }

    /**
     * @brief Test para verificar el método clonar() de Cualitativo.
     */
    @Test
     void testCualitativoClonar() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        Cualitativo clon = atributo.clonar();
        assertEquals(atributo.size(), clon.size());
        assertEquals(atributo.getValor(0), clon.getValor(0));
        assertEquals(atributo.getValor(1), clon.getValor(1));
        assertEquals(atributo.getValor(2), clon.getValor(2));
    }

    /**
     * @brief Test para verificar el método frecuencia() de Cualitativo.
     */
    @Test
     void testCualitativoFrecuencia() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Rojo");

        assertEquals(2, atributo.clases().size());
        assertEquals(0.6666666666666666, atributo.frecuencia().get(0), 0.001); // Rojo
        assertEquals(0.3333333333333333, atributo.frecuencia().get(1), 0.001); // Verde
    }

    /**
     * @brief Test para verificar el método nClases() de Cualitativo.
     */
    @Test
     void testCualitativoNClases() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");
        atributo.add("Rojo");

        assertEquals(3, atributo.nClases());
    }

    /**
     * @brief Test para verificar el método clases() de Cualitativo.
     */
    @Test
     void testCualitativoClases() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");
        atributo.add("Rojo");

        assertEquals(3, atributo.clases().size());
        assertEquals("Rojo", atributo.clases().get(0));
        assertEquals("Verde", atributo.clases().get(1));
        assertEquals("Azul", atributo.clases().get(2));
    }

    /**
     * @brief Test para verificar el método getValor() de Cualitativo
     */
    @Test
     void testCualitativoGetValor() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals("Rojo", atributo.getValor(0));
        assertEquals("Verde", atributo.getValor(1));
        assertEquals("Azul", atributo.getValor(2));
    }

    /**
     * @brief Test para verificar el método toString() de Cualitativo.
     */
    @Test
     void testCualitativoToString() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        String expectedString = "[Rojo, Verde, Azul]";
        assertEquals(expectedString, atributo.toString());
    }

   /**
    * @brief Test para verificar el constructor vacío de Cualitativo.
    */
   @Test
     void testCualitativoEmptyConstructor() {
        Cualitativo atributo = new Cualitativo();
        assertEquals(0, atributo.size());
        assertEquals(": 1.0", atributo.get());
    }

   /**
    * @brief Test para verificar el constructor con un valor inicial de Cualitativo.
    */
   @Test
     void testCualitativoValorConstructor() {
        Cualitativo atributo = new Cualitativo("Color", "Rojo");
        assertEquals(1, atributo.size());
        assertEquals("Color: 1.0", atributo.get());
    }

    /**
     * @brief Test para verificar el constructor con una lista de valores de Cualitativo.
     */
    @Test
     void testCualitativoValorListConstructor() {
        Cualitativo atributo = new Cualitativo("Color", List.of("Rojo", "Verde", "Azul"));
        assertEquals(3, atributo.size());
        assertEquals("Color: 1.0", atributo.get());
    }

    /**
     * @brief Test para verificar el método getValores() de Cualitativo.
     */
    @Test
     void testCualitativoGetValores(){
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        List<String> valores = atributo.getValores();
        assertEquals(3, valores.size());
        assertEquals("Rojo", valores.get(0));
        assertEquals("Verde", valores.get(1));
        assertEquals("Azul", valores.get(2));
    }



}
