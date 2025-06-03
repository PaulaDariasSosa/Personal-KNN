package vectores;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @brief Clase de prueba para la clase Vector
 */
 class VectorTest {

    /**
     * @brief Prueba unitaria para el constructor vacío de Vector
     */
    @Test
     void testVectorConstructorVacio() {
        Vector v = new Vector();
        assertEquals(0, v.size());
    }

    /**
     * @brief Prueba unitaria para el constructor de Vector con una lista de coeficientes
     */
    @Test
     void testVectorConstructorConLista() {
        List<Double> lista = List.of(1.0, 2.0, 3.0);
        Vector v = new Vector(lista);
        assertEquals(3, v.size());
        assertEquals("[1.0, 2.0, 3.0]", v.toString());
    }

    /**
     * @brief Prueba unitaria para el constructor de Vector con un tamaño específico
     */
    @Test
     void testVectorConstructorConTamano() {
        Vector v = new Vector(5);
        assertEquals(5, v.size());
        assertEquals("[0.0, 0.0, 0.0, 0.0, 0.0]", v.toString());
    }

    /**
     * @brief Prueba unitaria para el método clonar de Vector
     */
    @Test
     void testVectorClonar() {
        List<Double> lista = List.of(1.0, 2.0, 3.0);
        Vector v = new Vector(lista);
        Vector vClon = v.clonar();
        assertEquals(v.size(), vClon.size());
        assertEquals(v.toString(), vClon.toString());
    }

    /**
     * @brief Prueba unitaria para el método toString de Vector
     */
    @Test
     void testVectorToString() {
        List<Double> lista = List.of(1.0, 2.0, 3.0);
        Vector v = new Vector(lista);
        assertEquals("[1.0, 2.0, 3.0]", v.toString());
    }

    /**
     * @brief Prueba unitaria para el método print de Vector
     */
    @Test
     void testVectorPrint() {
        Vector v = new Vector();
        v.print();
    }

    /**
     * @brief Prueba unitaria para el método size de Vector
     */
    @Test
     void testVectorSize() {
        Vector v = new Vector();
        assertEquals(0, v.size());

        List<Double> lista = List.of(1.0, 2.0, 3.0);
        v = new Vector(lista);
        assertEquals(3, v.size());

        v = new Vector(5);
        assertEquals(5, v.size());
    }

    /**
     * @brief Prueba unitaria para el método get y set de Vector
     */
    @Test
     void testVectorGetSet() {
        Vector v = new Vector(3);
        v.set(0, 1.0);
        v.set(1, 2.0);
        v.set(2, 3.0);

        assertEquals(1.0, v.get(0));
        assertEquals(2.0, v.get(1));
        assertEquals(3.0, v.get(2));
    }

    /**
     * @brief Prueba unitaria para el método add y remove de Vector
     */
    @Test
     void testVectorAddRemove() {
        Vector v = new Vector();
        v.add(1.0);
        v.add(2.0);
        v.add(3.0);

        assertEquals(3, v.size());
        assertEquals("[1.0, 2.0, 3.0]", v.toString());

        v.remove(1); // Remove the second element
        assertEquals(2, v.size());
        assertEquals("[1.0, 3.0]", v.toString());
    }

    /**
     * @brief Prueba unitaria para el método getMax de Vector
     */
    @Test
     void testVectorGetMax() {
        Vector v = new Vector(List.of(1.0, 2.0, 3.0));
        assertEquals(3.0, v.getMax());
    }

    /**
     * @brief Prueba unitaria para el método getMaxInt de Vector
     */
    @Test
     void testVectorGetMaxInt() {
        Vector v = new Vector(List.of(1.0, 2.0, 3.0));
        assertEquals(2, v.getMaxInt()); // Index of the maximum value (3.0)
    }

    /**
     * @brief Prueba unitaria para el método getMin de Vector
     */
    @Test
     void testVectorGetMin() {
        Vector v = new Vector(List.of(1.0, 2.0, 3.0));
        assertEquals(1.0, v.getMin());
    }

    /**
     * @brief Prueba unitaria para el método normalize de Vector
     */
    @Test
     void testVectorNormalize() {
        Vector v = new Vector(List.of(3.0, 4.0));
        v.normalize();
        assertEquals(0.0, v.get(0), 1e-6);
        assertEquals(1.0, v.get(1), 1e-6); // 4/3
    }


}
