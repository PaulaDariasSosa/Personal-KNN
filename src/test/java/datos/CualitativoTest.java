package datos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class CualitativoTest {
    @Test
     void testCualitativo() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals(3, atributo.size());
        assertEquals("Color: 1.0", atributo.get());
    }

    @Test
     void testCualitativoGetPeso() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals(1.0, atributo.getPeso(), 0.001);
    }

    @Test
     void testCualitativoGet() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        assertEquals("Color: 1.0", atributo.get());
    }

    @Test
     void testCualitativoSetNombre() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.setNombre("NuevoColor");
        assertEquals("NuevoColor", atributo.getNombre());
    }

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

    @Test
     void testCualitativoNClases() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");
        atributo.add("Rojo");

        assertEquals(3, atributo.nClases());
    }

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

    @Test
     void testCualitativoToString() {
        Cualitativo atributo = new Cualitativo("Color");
        atributo.add("Rojo");
        atributo.add("Verde");
        atributo.add("Azul");

        String expectedString = "[Rojo, Verde, Azul]";
        assertEquals(expectedString, atributo.toString());
    }

    @Test
     void testCualitativoEmptyConstructor() {
        Cualitativo atributo = new Cualitativo();
        assertEquals(0, atributo.size());
        assertEquals(": 1.0", atributo.get());
    }

    @Test
     void testCualitativoValorConstructor() {
        Cualitativo atributo = new Cualitativo("Color", "Rojo");
        assertEquals(1, atributo.size());
        assertEquals("Color: 1.0", atributo.get());
    }

    @Test
     void testCualitativoValorListConstructor() {
        Cualitativo atributo = new Cualitativo("Color", List.of("Rojo", "Verde", "Azul"));
        assertEquals(3, atributo.size());
        assertEquals("Color: 1.0", atributo.get());
    }

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
