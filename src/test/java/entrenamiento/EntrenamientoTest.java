package entrenamiento;

import datos.Dataset;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Clase de prueba para la clase Entrenamiento.
 */
 class EntrenamientoTest {

    /**
     * @brief Clase de prueba para capturar los mensajes de log.
     */
    static class TestLogHandler extends Handler {
        /**
         * @brief Mensajes capturados por el handler.
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
         * @brief Método que se invoca para limpiar el buffer de mensajes.
         */
        @Override
        public void flush() {}

        /**
         * @brief Método que se invoca para cerrar el handler.
         * @throws SecurityException si ocurre un error de seguridad.
         */
        @Override
        public void close() throws SecurityException {}

        /**
         * @brief Método para obtener los mensajes capturados.
         * @return los mensajes capturados como una cadena.
         */
        public String getMensajes() {
            return mensajes.toString();
        }
    }

    /**
     * @brief Prueba del constructor vacío de la clase Entrenamiento.
     */
    @Test
     void testEntrenamientoConstructorVacio() {
        Entrenamiento entrenamiento = new Entrenamiento();
        assertNull(entrenamiento.clases);
        assertNull(entrenamiento.train);
        assertNull(entrenamiento.test);
    }

    /**
     * @brief Prueba del constructor de la clase Entrenamiento con un Dataset.
     * @throws IOException
     */
    @Test
     void testEntrenamientoConstructorConDatosYPorcentaje() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5);

        assertEquals(75, entrenamiento.train.numeroCasos());
        assertEquals(75, entrenamiento.test.numeroCasos());
    }

    /**
     * @brief Prueba del constructor de la clase Entrenamiento con un Dataset, porcentaje y semilla.
     * @throws IOException
     */
    @Test
     void testEntrenamientoConstructorConDatosPorcentajeYSemilla() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5, 12345);

        assertEquals(75, entrenamiento.train.numeroCasos());
        assertEquals(75, entrenamiento.test.numeroCasos());
    }

    /**
     * @brief Prueba del constructor de la clase Entrenamiento con un Dataset y porcentaje cero.
     * @throws IOException
     */
    @Test
     void testEntrenamientoConstructorConDatosYPorcentajeCero() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.0);

        assertEquals(0, entrenamiento.train.numeroCasos());
        assertEquals(150, entrenamiento.test.numeroCasos());
    }

    /**
     * @brief Prueba del constructor de la clase Entrenamiento con un Dataset y porcentaje uno(100%).
     * @throws IOException
     */
    @Test
     void testEntrenamientoConstructorConDatosYPorcentajeUno() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 1.0);

        assertEquals(150, entrenamiento.train.numeroCasos());
        assertEquals(0, entrenamiento.test.numeroCasos());
    }

    /**
     * @brief Prueba del metodo generarPrediccion de la clase Entrenamiento.
     * @throws IOException
     */
    @Test
     void testEntrenamientoGenerarPrediccion() throws IOException {
        Logger logger = Logger.getLogger(Entrenamiento.class.getName());
        TestLogHandler handler = new TestLogHandler();

        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
        Dataset datos = new Dataset("iris.csv");
        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5);
        entrenamiento.generarPrediccion(3);

        logger.removeHandler(handler);

        String logs = handler.getMensajes();
        assertTrue(logs.contains("La precisión predictiva: "));
        assertTrue(logs.contains("0.0"));
        assertTrue(logs.contains(" / "));
        assertTrue(logs.contains("75"));
        assertTrue(logs.contains("0.0"));
    }

    /**
     * @brief Prueba del metodo generarMatriz de la clase Entrenamiento.
     * @throws IOException
     */
    @Test
     void testEntrenamientoGenerarMatriz() throws IOException {
        Logger logger = Logger.getLogger(Entrenamiento.class.getName());
        TestLogHandler handler = new TestLogHandler();

        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
        Dataset datos = new Dataset("iris.csv");
        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5);
        entrenamiento.generarMatriz(3);

        logger.removeHandler(handler);

        String logs = handler.getMensajes();
        assertTrue(logs.contains("[Iris-setosa, Iris-versicolor, Iris-virginica]"));
    }

    /**
     * @brief Prueba del método write de la clase Entrenamiento.
     */
    @Test
     void testEntrenamientoWrite() throws IOException {
        Dataset datos = new Dataset("iris.csv");
        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5);

        entrenamiento.write("train.csv", "test.csv");

        assertTrue(new java.io.File("train.csv").exists());
        assertTrue(new java.io.File("test.csv").exists());

        new java.io.File("train.csv").delete();
        new java.io.File("test.csv").delete();
    }

    /**
     * @brief Prueba del método read de la clase Entrenamiento.
     * @throws IOException
     */
    @Test
     void testEntrenamientoRead() throws IOException {
        Dataset datos = new Dataset("iris.csv");
        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5);

        entrenamiento.write("train.csv", "test.csv");
        Entrenamiento nuevoEntrenamiento = new Entrenamiento();
        nuevoEntrenamiento.read("train.csv", "test.csv");

        assertEquals(entrenamiento.train.numeroCasos(), nuevoEntrenamiento.train.numeroCasos());
        assertEquals(entrenamiento.test.numeroCasos(), nuevoEntrenamiento.test.numeroCasos());

        new java.io.File("train.csv").delete();
        new java.io.File("test.csv").delete();
    }
}
