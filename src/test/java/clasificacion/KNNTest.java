package clasificacion;

import datos.*;
import org.junit.jupiter.api.Test;
import vectores.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @brief Clase de prueba para el clasificador KNN.
 */
 class KNNTest {
    /**
     * @brief Prueba del constructor de KNN.
     */
    @Test
    void testKNNConstructor() {
        KNN knn = new KNN(3);
        assertEquals(3, knn.vecinos);
    }

   /**
    * @brief Prueba de la función getDistanciaEuclidea.
    */
   @Test
     void testGetDistanciaEuclidea() {
        KNN knn = new KNN(3);
        Vector vieja = new Vector();
        vieja.add(1.0);
        vieja.add(2.0);
        Vector nueva = new Vector();
        nueva.add(4.0);
        nueva.add(6.0);
        double peso1 = 1.0;
        double peso2 = 1.0;

        double distancia = knn.getDistanciaEuclidea(vieja, nueva, List.of(peso1, peso2));
        assertEquals(5.0, distancia, 0.001);
    }

   /**
    * @brief Prueba de la función getDistanciaEuclidea con un caso incorrecto.
    */
   @Test
     void testGetDistanciaEuclideaWrong() {
        KNN knn = new KNN(3);
        Vector vieja = new Vector();
        vieja.add(1.0);
        vieja.add(2.0);
        Vector nueva = new Vector();
        nueva.add(4.0);
        double peso1 = 1.0;
        double peso2 = 1.0;

        double distancia = knn.getDistanciaEuclidea(vieja, nueva, List.of(peso1, peso2));
        assertEquals(1.7976931348623157E308, distancia, 0.001);
    }

   /**
    * @brief Prueba de la función getClase.
    */
   @Test
     void testGetClase() {
        KNN knn = new KNN(3);
        List<Object> lista1 = new ArrayList<>();
        lista1.add(1.0);
        lista1.add(2.0);
        lista1.add("A");
        List<Object> lista2 = new ArrayList<>();
        lista2.add(2.0);
        lista2.add(3.0);
        lista2.add("B");
        List<Object> lista3 = new ArrayList<>();
        lista3.add(1.5);
        lista3.add(2.5);
        lista3.add("A");
        List<Instancia> candidatos = List.of(
            new Instancia(lista1),
            new Instancia(lista2),
            new Instancia(lista3)
        );

        String clase = knn.getClase(candidatos);
        assertEquals("A", clase);
    }

   /**
    * @brief Prueba de la función getVecino.
    * @throws IOException
    */
    @Test
     void testGetVecino() throws IOException {
        KNN knn = new KNN(2);
        List<Object> lista1 = new ArrayList<>();
        lista1.add(1.0);
        lista1.add(2.0);
        lista1.add("A");
        List<Object> lista2 = new ArrayList<>();
        lista2.add(2.0);
        lista2.add(3.0);
        lista2.add("B");
        List<Double> lista3 = new ArrayList<>();
        lista3.add(1.5);
        lista3.add(2.5);
        Vector vector1 = new Vector(lista3);
        List<Instancia> candidatos = List.of(
                new Instancia(lista1),
                new Instancia(lista2)
        );

        String clase = knn.getVecino(candidatos, vector1);
        assertEquals("A", clase);
    }

   /**
    * @brief Prueba de la función clasificar.
    */
   @Test
     void testClasificar() {
        KNN knn = new KNN(2);
        List<Double> lista1 = new ArrayList<>();
        lista1.add(1.0);
        lista1.add(2.0);
        Vector vector1 = new Vector(lista1);
        List<Atributo> listaAtributos = new ArrayList<>();
        Cuantitativo atributo1 = new Cuantitativo("A", vector1);
        List<Double> lista2 = new ArrayList<>();
        lista2.add(2.0);
        lista2.add(200.0);
        Vector vector2 = new Vector(lista2);
        Cuantitativo atributo2 = new Cuantitativo("B", vector2);
        listaAtributos.add(atributo1);
        listaAtributos.add(atributo2);
        List<String> clases = new ArrayList<>();
        clases.add("A");
        clases.add("B");
        Cualitativo atributo3 = new Cualitativo("Clase", clases);
        listaAtributos.add(atributo3);
        Dataset dataset = new Dataset(listaAtributos);
        List<Object> valores1 = new ArrayList<>();
        valores1.add(1.0);
        valores1.add(2.0);
        valores1.add("clase");
        Instancia instancia1 = new Instancia(valores1);

        String clase = knn.clasificar(dataset, instancia1);
        assertEquals("A", clase);
    }
}
