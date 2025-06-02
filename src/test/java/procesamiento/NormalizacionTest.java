package procesamiento;

import datos.Atributo;
import datos.Cuantitativo;
import datos.Dataset;
import org.junit.jupiter.api.Test;
import vectores.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalizacionTest {
    @Test
     void testNormalizacionProcesar() {
        Vector valores = new Vector();
        valores.add(1.75);
        valores.add(1.80);
        valores.add(1.65);
        Cuantitativo cuantitativo = new Cuantitativo("altura", valores);
        List<Atributo> atributos = new ArrayList<>();
        atributos.add(cuantitativo);
        Dataset dataset = new Dataset(atributos);
        new Normalizacion().procesar(dataset);
        Vector valoresEstandarizados = cuantitativo.getValores();
        assertEquals(valoresEstandarizados.size(), 3);
        assertEquals(valoresEstandarizados.get(0), 0.6666666666666666, 0.01);
        assertEquals(valoresEstandarizados.get(1), 1.0, 0.01);
        assertEquals(valoresEstandarizados.get(2), 0.0, 0.01);
    }
}
