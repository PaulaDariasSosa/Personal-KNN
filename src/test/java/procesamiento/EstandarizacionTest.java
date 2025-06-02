package procesamiento;

import datos.*;
import org.junit.jupiter.api.Test;
import vectores.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EstandarizacionTest {
    @Test
     void testEstandarizacionProcesar() {
        Vector valores = new Vector();
        valores.add(1.75);
        valores.add(1.80);
        valores.add(1.65);
        Cuantitativo cuantitativo = new Cuantitativo("altura", valores);
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(cuantitativo);
        Dataset dataset = new Dataset(atributos);
        new Estandarizacion().procesar(dataset);
        Vector valoresEstandarizados = cuantitativo.getValores();
        assertEquals(valoresEstandarizados.size(), 3);
        assertEquals(valoresEstandarizados.get(0), 0.2672612419124268, 0.01);
        assertEquals(valoresEstandarizados.get(1), 1.0690449676497, 0.01);
        assertEquals(valoresEstandarizados.get(2), -1.3363062095621197, 0.01);
    }
}
