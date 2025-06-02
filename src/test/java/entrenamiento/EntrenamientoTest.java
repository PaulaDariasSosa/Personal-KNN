package entrenamiento;

import datos.Dataset;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

 class EntrenamientoTest {

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
     void testEntrenamientoConstructorVacio() {
        Entrenamiento entrenamiento = new Entrenamiento();
        assertNull(entrenamiento.clases);
        assertNull(entrenamiento.train);
        assertNull(entrenamiento.test);
    }

    @Test
     void testEntrenamientoConstructorConDatosYPorcentaje() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5);

        assertEquals(75, entrenamiento.train.numeroCasos());
        assertEquals(75, entrenamiento.test.numeroCasos());
    }

    @Test
     void testEntrenamientoConstructorConDatosPorcentajeYSemilla() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.5, 12345);

        assertEquals(75, entrenamiento.train.numeroCasos());
        assertEquals(75, entrenamiento.test.numeroCasos());
    }

    @Test
     void testEntrenamientoConstructorConDatosYPorcentajeCero() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 0.0);

        assertEquals(0, entrenamiento.train.numeroCasos());
        assertEquals(150, entrenamiento.test.numeroCasos());
    }

    @Test
     void testEntrenamientoConstructorConDatosYPorcentajeUno() throws IOException {
        Dataset datos = new Dataset("iris.csv");

        Entrenamiento entrenamiento = new Entrenamiento(datos, 1.0);

        assertEquals(150, entrenamiento.train.numeroCasos());
        assertEquals(0, entrenamiento.test.numeroCasos());
    }

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
