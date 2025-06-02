package datos;

import org.junit.jupiter.api.Test;
import vectores.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class CuantitativoTest {

    @Test
     void testCuantitativoConstructorSinParametros() {
        Cuantitativo cuantitativo = new Cuantitativo();
        assertEquals("", cuantitativo.getNombre());
        assertEquals(0, cuantitativo.getValores().size());
    }

    @Test
     void testCuantitativoConstructorConNombre() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        assertEquals("Test", cuantitativo.getNombre());
        assertEquals(0, cuantitativo.getValores().size());
    }

    @Test
     void testCuantitativoConstructorConNombreYValor() {
        Cuantitativo cuantitativo = new Cuantitativo("Test", 5.0);
        assertEquals("Test", cuantitativo.getNombre());
        assertEquals(1, cuantitativo.getValores().size());
        assertEquals(5.0, cuantitativo.getValores().get(0));
    }

    @Test
     void testCuantitativoConstructorConNombreYVector() {
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        Cuantitativo cuantitativo = new Cuantitativo("Test", valores);
        assertEquals("Test", cuantitativo.getNombre());
        assertEquals(2, cuantitativo.getValores().size());
        assertEquals(1.0, cuantitativo.getValores().get(0));
        assertEquals(2.0, cuantitativo.getValores().get(1));
    }

    @Test
     void testCuantitativoSetValores() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector nuevosValores = new Vector();
        nuevosValores.add(3.0);
        nuevosValores.add(4.0);
        cuantitativo.setValores(nuevosValores);

        assertEquals(nuevosValores, cuantitativo.getValores());

    }

    @Test
     void testCuantitativoMinimo() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(3.0);
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        assertEquals(1.0, cuantitativo.minimo());
    }

    @Test
     void testCuantitativoMaximo() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(3.0);
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        assertEquals(3.0, cuantitativo.maximo());
    }

    @Test
     void testCuantitativoMedia() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        valores.add(3.0);
        cuantitativo.setValores(valores);

        assertEquals(2.0, cuantitativo.media());
    }

    @Test
     void testCuantitativoDesviacion() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        valores.add(3.0);
        cuantitativo.setValores(valores);

        assertEquals(0.8165, cuantitativo.desviacion(), 0.0001);
    }

    @Test
     void testCuantitativoToString() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        String expected = "[1.0, 2.0]";
        assertEquals(expected, cuantitativo.toString());
    }

    @Test
     void testCuantitativoSize() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        assertEquals(2, cuantitativo.size());
    }

    @Test
     void testCuantitativoEstandatizacion(){
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        valores.add(3.0);
        cuantitativo.setValores(valores);

        cuantitativo.estandarizacion();

        assertEquals(3, cuantitativo.size());
        assertEquals(-1.2247, cuantitativo.getValores().get(0), 0.0001);
        assertEquals(0.0, cuantitativo.getValores().get(1), 0.0001);
        assertEquals(1.2247, cuantitativo.getValores().get(2), 0.0001);
    }

    @Test
     void testCuantitativoAdd() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        cuantitativo.add(5.0);

        assertEquals(1, cuantitativo.size());
        assertEquals(5.0, cuantitativo.getValores().get(0));
    }

    @Test
     void testCuantitativoGetValor() {
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        assertEquals(1.0, cuantitativo.getValor(0));
        assertEquals(2.0, cuantitativo.getValor(1));
    }

    @Test
     void testCuantitativoDelete(){
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        cuantitativo.delete(1);

        assertEquals(1, cuantitativo.size());
        assertEquals(1.0, cuantitativo.getValor(0));
    }

    @Test
     void testCuantitativoClonar(){
        Cuantitativo cuantitativo = new Cuantitativo("Test");
        Vector valores = new Vector();
        valores.add(1.0);
        valores.add(2.0);
        cuantitativo.setValores(valores);

        Cuantitativo clon = cuantitativo.clonar();

        assertEquals(cuantitativo.getNombre(), clon.getNombre());
        assertEquals(cuantitativo.getValores().size(), clon.getValores().size());
        assertEquals(cuantitativo.getValor(0), clon.getValor(0));
        assertEquals(cuantitativo.getValor(1), clon.getValor(1));
    }
}
