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

/**
 * @brief Clase de prueba para KnnTfg.
 */
 class KnnTfgTest {
    /**
     * @brief Clase interna para manejar los logs durante las pruebas.
     */
    static class TestLogHandler extends Handler {
        /**
         * @brief StringBuilder para almacenar los mensajes de log.
         */
        private final StringBuilder mensajes = new StringBuilder();

        /**
         * @brief Constructor de TestLogHandler.
         */
        @Override
        public void publish(LogRecord record) {
            mensajes.append(record.getMessage());
        }

        /**
         * @brief Método para flush.
         */
        @Override
        public void flush() {}

        /**
         * @brief Método para cerrar el handler.
         * @throws SecurityException si ocurre un error de seguridad.
         */
        @Override
        public void close() throws SecurityException {}

        /**
         * @brief Método para obtener los mensajes de log.
         * @return String con los mensajes de log.
         */
        public String getMensajes() {
            return mensajes.toString();
        }
    }

    /**
     * @brief Prueba unitaria para el método main de KnnTfg.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testMain() throws IOException {
        String entradaSimulada = "5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        KnnTfg.main(null);
        System.setIn(System.in);
    }

    /**
     * @brief Prueba unitaria para la opción de salir del menú.
     * @throws IOException
     */
    @Test
    void testOpcionExit() throws IOException {
        String entradaSimulada = "4\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);

        Boolean salida = KnnTfg.opciones(5);
        assertEquals(true, salida);
    }

    /**
     * @brief Prueba unitaria para la opción de modificar el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testModifyAdd() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "1\n5.1,3.5,1.4,0.2,Iris-setosa\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.modify(dataset);
        assertEquals(151, dataset.numeroCasos());
    }

    /**
     * @brief Prueba unitaria para la opción de modificar el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testModifyDelete() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "2\n5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.modify(dataset);
        assertEquals(149, dataset.numeroCasos());
    }

    /**
     * @brief Prueba unitaria para la opción de modificar el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testModifyModify() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "3\n5.1,3.5,1.4,0.2,Iris-setosa\n5\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        KnnTfg.modify(dataset);
        assertEquals(150, dataset.numeroCasos());
    }

    /**
     * @brief Prueba unitaria para la opción de preprocesar el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Test
    void testPreporcesarCrudo() throws IOException {
        Dataset dataset = new Dataset("iris.csv");
        String entradaSimulada = "1\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(entrada);
        Dataset crudos = KnnTfg.preprocesar(dataset);
        assertEquals(dataset, crudos);
    }

    /**
     * @brief Prueba unitaria para la opción de normalizar el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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

    /**
     * @brief Prueba unitaria para la opción de estandarizar el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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

    /**
     * @brief Prueba unitaria para la opción de preprocesar el dataset con valores por defecto.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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

    /**
     * @brief Prueba unitaria para la opción de cambiar los pesos de los atributos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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

    /**
     * @brief Prueba unitaria para la opción de experimentar con el dataset.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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

    /**
     * @brief Prueba unitaria para la opción de experimentar con el dataset con seed definida.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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

    /**
     * @brief Prueba unitaria para la opción de experimentar con el dataset con seed manual.
     * @throws IOException si ocurre un error de entrada/salida.
     */
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
