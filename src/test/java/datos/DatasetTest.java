package datos;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class DatasetTest {
    @Test
     void testDatasetConstructorVacio() {
        Dataset dataset = new Dataset();
        assert(dataset.getAtributos().isEmpty());
    }

    @Test
     void testDatasetConstructorConLista() {
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(new Cuantitativo("Atributo1", 1.0));
        Dataset dataset = new Dataset(atributos);
        assertEquals(1, dataset.getAtributos().size());
        assertEquals("Atributo1", dataset.getAtributos().get(0).getNombre());
    }

    @Test
     void testDatasetConstructorConAtributos() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);
        assertEquals(2, dataset.getAtributos().size());
        assertEquals("Atributo1", dataset.getAtributos().get(0).getNombre());
        assertEquals("Atributo2", dataset.getAtributos().get(1).getNombre());
    }

    @Test
     void testDatasetConstructorArchivo() throws IOException {
        String ruta = "iris.csv";
        Dataset dataset = new Dataset(ruta);
        assertEquals(5, dataset.getAtributos().size());
        assertEquals(150, dataset.numeroCasos());
    }

    @Test
     void testDatasetCambiarPesoList() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);
        assertEquals(2, dataset.getAtributos().size());
        assertEquals(1.0, dataset.getAtributos().get(0).getPeso());
        assertEquals(1.0, dataset.getAtributos().get(1).getPeso());
        List<String> pesos = new ArrayList<>(List.of("0.5", "0.3"));
        dataset.cambiarPeso(pesos);
        assertEquals(0.5, dataset.getAtributos().get(0).getPeso());
        assertEquals(0.3, dataset.getAtributos().get(1).getPeso());
    }

    @Test
     void testDatasetCambiarPesoListException() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        Dataset dataset = new Dataset(atributos);
        assertEquals(dataset.getAtributos().size(), 1);
        assertEquals(dataset.getAtributos().get(0).getPeso(), 1.0);
        List<String> pesos = new ArrayList<>(List.of("0.5", "0.3"));
        assertThrows(IllegalArgumentException.class, () -> {
            dataset.cambiarPeso(pesos);
        });
    }

    @Test
     void testDatasetCambiarPesoIndividual() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);
        assertEquals(2, dataset.getAtributos().size());
        assertEquals(1.0, dataset.getAtributos().get(0).getPeso());
        assertEquals(1.0, dataset.getAtributos().get(1).getPeso());
        dataset.cambiarPeso(1, 0.5);
        assertEquals(1.0, dataset.getAtributos().get(0).getPeso());
        assertEquals(0.5, dataset.getAtributos().get(1).getPeso());
    }

    @Test
     void testDatasetCambiarPesoDouble() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);
        assertEquals(2, dataset.getAtributos().size());
        assertEquals(1.0, dataset.getAtributos().get(0).getPeso());
        assertEquals(1.0, dataset.getAtributos().get(1).getPeso());
        dataset.cambiarPeso(0.6);
        assertEquals(0.6, dataset.getAtributos().get(0).getPeso());
        assertEquals(0.6, dataset.getAtributos().get(1).getPeso());
    }

    @Test
     void testDatasetToString() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);
        String expected = "Atributo1,Atributo2\n1.0,Clase1\n";
        assertEquals(expected, dataset.toString());
    }

    @Test
     void testDatasetAddInstancia(){
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        Instancia instancia = new Instancia(List.of(2.0, "Clase2"));
        dataset.add(instancia);

        assertEquals(2, dataset.numeroCasos());
        assertEquals(2.0, dataset.getInstance(1).getValores().get(0));
        assertEquals("Clase2", dataset.getInstance(1).getValores().get(1));
    }

    @Test
     void testDatasetAddList(){
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<String> valores = new ArrayList<>();
        valores.add("2.0");
        valores.add("Clase2");
        dataset.add(valores);

        assertEquals(2, dataset.numeroCasos());
        assertEquals(2.0, dataset.getInstance(1).getValores().get(0));
        assertEquals("Clase2", dataset.getInstance(1).getValores().get(1));
    }

    @Test
     void testDatasetGetAtributosEmpty() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<Atributo> atributosEmpty = dataset.getAtributosEmpty();

        assertEquals(2, atributosEmpty.size());
        assertEquals("Atributo1", atributosEmpty.get(0).getNombre());
        assertEquals("Atributo2", atributosEmpty.get(1).getNombre());
    }

    @Test
     void testDatasetGetValores() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<String> valores = dataset.getValores();

        assertEquals(2, valores.size());
        assertEquals("1.0", valores.get(0));
        assertEquals("Clase1", valores.get(1));
    }

    @Test
     void testDatasetGetPesos() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<String> pesos = dataset.getPesos();

        assertEquals(2, pesos.size());
        assertEquals("Atributo1: 1.0", pesos.get(0));
        assertEquals("Atributo2: 1.0", pesos.get(1));
    }

    @Test
     void testDatasetGetClases() {
        Cualitativo atributo1 = new Cualitativo("Atributo1", "Clase1");
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<String> clases = dataset.getClases();

        assertEquals(1, clases.size());
        assertEquals("Clase2", clases.get(0));
    }

    @Test
     void testDatasetGetPreprocesado() {
        Dataset dataset = new Dataset();
        dataset.setPreprocesado(1);
        assertEquals(1, dataset.getPreprocesado());
    }

    @Test
     void testDatasetSetAtributos() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<Atributo> nuevosAtributos = new ArrayList<>();
        nuevosAtributos.add(new Cuantitativo("NuevoAtributo", 2.0));
        dataset.setAtributos(nuevosAtributos);

        assertEquals(1, dataset.getAtributos().size());
        assertEquals("NuevoAtributo", dataset.getAtributos().get(0).getNombre());
    }

    @Test
     void testDatasetClonar() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        Dataset clonado = dataset.clonar();

        assertEquals(2, clonado.getAtributos().size());
        assertEquals("Atributo1", clonado.getAtributos().get(0).getNombre());
        assertEquals("Atributo2", clonado.getAtributos().get(1).getNombre());
    }

    @Test
     void testDatasetGetInstance() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        Instancia instancia = dataset.getInstance(0);

        assertEquals(2,instancia.getValores().size());
        assertEquals(1.0, instancia.getValores().get(0));
        assertEquals("Clase1", instancia.getValores().get(1));
    }

    @Test
     void testDatasetGetAtributosEmptyWithCuantitativo() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        Dataset dataset = new Dataset(atributos);

        List<Atributo> atributosEmpty = dataset.getAtributosEmpty();

        assertEquals(1, atributosEmpty.size());
        assertEquals("Atributo1", atributosEmpty.get(0).getNombre());
    }

    @Test
     void testDatasetGetAtributosEmptyWithCualitativo() {
        Cualitativo atributo1 = new Cualitativo("Atributo1", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        Dataset dataset = new Dataset(atributos);

        List<Atributo> atributosEmpty = dataset.getAtributosEmpty();

        assertEquals(1, atributosEmpty.size());
        assertEquals( "Atributo1", atributosEmpty.get(0).getNombre());
    }

    @Test
     void testDatasetGetAtributosEmptyWithMixedTypes() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        List<Atributo> atributosEmpty = dataset.getAtributosEmpty();

        assertEquals(2, atributosEmpty.size());
        assertEquals("Atributo1", atributosEmpty.get(0).getNombre());
        assertEquals("Atributo2", atributosEmpty.get(1).getNombre());
    }

    @Test
     void testDatasetGetAtributosEmptyWithNoAttributes() {
        Dataset dataset = new Dataset();

        List<Atributo> atributosEmpty = dataset.getAtributosEmpty();

        assertEquals(0, atributosEmpty.size());
    }

    @Test
     void testDatasetDelete() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        dataset.delete(0);

        assertEquals(2, dataset.getAtributos().size());
        assertEquals("Atributo1", dataset.getAtributos().get(0).getNombre());
    }

    @Test
     void testDatasetDeleteException() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            dataset.delete(2); // Intentar eliminar un índice fuera de rango
        });
    }

    @Test
     void testDatasetRead() throws IOException {
        String ruta = "iris.csv";
        Dataset dataset = new Dataset(ruta);

        assertEquals(dataset.getAtributos().size(), 5);
        assertEquals(dataset.numeroCasos(), 150);

        // Verificar que los nombres de los atributos son correctos
        List<String> nombresAtributos = dataset.nombreAtributos();
        assertEquals("sepal length", nombresAtributos.get(0));
        assertEquals("sepal width", nombresAtributos.get(1));
        assertEquals("petal length", nombresAtributos.get(2));
        assertEquals("petal width", nombresAtributos.get(3));
        assertEquals("iris", nombresAtributos.get(4));
    }

    @Test
     void testDatasetReadException() {
        String ruta = "archivo_inexistente.csv";
        assertThrows(IOException.class, () -> {
            new Dataset(ruta); // Intentar leer un archivo que no existe
        });
    }

    @Test
     void testDatasetNumeroAtributos() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        assertEquals(2, dataset.numeroAtributos());
    }

    @Test
     void testDatasetNumeroCasos() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2", "Clase1");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        Instancia instancia1 = new Instancia(List.of(2.0, "Clase2"));
        Instancia instancia2 = new Instancia(List.of(3.0, "Clase3"));
        dataset.add(instancia1);
        dataset.add(instancia2);

        assertEquals(3, dataset.numeroCasos());
    }

    @Test
     void testDatasetGetAtributoByIndex() {
        Cuantitativo atributo1 = new Cuantitativo("Atributo1", 1.0);
        Cualitativo atributo2 = new Cualitativo("Atributo2");
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(atributo1);
        atributos.add(atributo2);
        Dataset dataset = new Dataset(atributos);

        Atributo atributoObtenido = dataset.get(0);

        assertEquals("Atributo1", atributoObtenido.getNombre());
    }


}
