package knntfg;

import datos.Dataset;
import entrenamiento.Entrenamiento;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class KnnTfgTest {
    static class TestLogHandler extends Handler {
        private final StringBuilder mensajes = new StringBuilder();

        @Override
        public void publish(LogRecord record) {
            mensajes.append(record.getMessage());
        }

        @Override
        public void flush() {}

        @Override
        public void close() throws SecurityException {}

        public String getMensajes() {
            return mensajes.toString();
        }
    }

    @Test
    void testMain() throws IOException {
        String entradaSimulada = "5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        KnnTfg.main(null);
        System.setIn(System.in);
    }

    @Test
    void testOpcionExit() throws IOException {
        String entradaSimulada = "4\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        Boolean salida = KnnTfg.opciones(5);
        assertEquals(true, salida);
    }

    @Test
    void testModifyAdd() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "1\n5.1,3.5,1.4,0.2,Iris-setosa\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.modify(dataset);
        assertEquals(151, dataset.numeroCasos());
    }

    @Test
    void testModifyDelete() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "2\n5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.modify(dataset);
        assertEquals(149, dataset.numeroCasos());
    }

    @Test
    void testModifyModify() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "3\n5.1,3.5,1.4,0.2,Iris-setosa\n5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.modify(dataset);
        assertEquals(150, dataset.numeroCasos());
    }

    @Test
    void testPreporcesarCrudo() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "1\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        Dataset crudos = KnnTfg.preprocesar(dataset);
        assertEquals(dataset, crudos);
    }

    @Test
    void testPreprocesarNormalizado() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "2\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        Dataset normalizado = KnnTfg.preprocesar(dataset);
        assertEquals(dataset.numeroCasos(), normalizado.numeroCasos());
        assertEquals(dataset.numeroAtributos(), normalizado.numeroAtributos());
    }

    @Test
    void testPreprocesarEstandarizado() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "3\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        Dataset estandarizado = KnnTfg.preprocesar(dataset);
        assertEquals(dataset.numeroCasos(), estandarizado.numeroCasos());
        assertEquals(dataset.numeroAtributos(), estandarizado.numeroAtributos());
    }

    @Test
    void testPreprocesarDefecto() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "4\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        Dataset normalizadoEstandarizado = KnnTfg.preprocesar(dataset);
        assertEquals(dataset.numeroCasos(), normalizadoEstandarizado.numeroCasos());
        assertEquals(dataset.numeroAtributos(), normalizadoEstandarizado.numeroAtributos());
    }

    @Test
    void testCambiarPesoTodosIgual() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "1\n0.6,0.7,0.8,0.9,1.0\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.cambiarPesos(dataset);
        List<String> pesos = new ArrayList<>();
        pesos.add("sepal length: 0.6");
        pesos.add("sepal width: 0.7");
        pesos.add("petal length: 0.8");
        pesos.add("petal width: 0.9");
        pesos.add("iris: 1.0");
        assertEquals(pesos, dataset.getPesos());
    }

    @Test
    void testExperimentar() throws IOException {
        Dataset dataset = new Dataset("iris.csv");

        String entradaSimulada = "1\n60\n3\n5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        Logger logger = Logger.getLogger(Entrenamiento.class.getName());
        KnnTfgTest.TestLogHandler handler = new KnnTfgTest.TestLogHandler();
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);

        KnnTfg.experimentar(dataset);

        logger.removeHandler(handler);

        String logs = handler.getMensajes();
        assertTrue(logs.contains("La precisión predictiva: "));
    }

    @Test
    void testExperimentarAleatorioSeedDefecto() throws IOException {
        Dataset dataset = new Dataset("iris.csv");

        String entradaSimulada = "1\n60\n3\n5\n1\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        Logger logger = Logger.getLogger(Entrenamiento.class.getName());
        KnnTfgTest.TestLogHandler handler = new KnnTfgTest.TestLogHandler();
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);

        KnnTfg.experimentacionAleatoria(dataset);

        logger.removeHandler(handler);

        String logs = handler.getMensajes();
        assertTrue(logs.contains("La precisión predictiva: "));
    }

    @Test
    void testExperimentarAleatorioSeedManual() throws IOException {
        Dataset dataset = new Dataset("iris.csv");

        String entradaSimulada = "2\n60\n1732\n3\n5\n2\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        Logger logger = Logger.getLogger(Entrenamiento.class.getName());
        KnnTfgTest.TestLogHandler handler = new KnnTfgTest.TestLogHandler();
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);

        KnnTfg.experimentacionAleatoria(dataset);

        logger.removeHandler(handler);

        String logs = handler.getMensajes();
        assertTrue(logs.contains("La precisión predictiva: "));
    }

}
