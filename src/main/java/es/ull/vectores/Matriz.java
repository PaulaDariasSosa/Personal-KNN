package vectores;

import java.util.ArrayList;
import java.util.List;

public class Matriz {
    private final List<Vector> matrix;
    private final int numRows;
    private final int numCols;

    // Constructor que crea una matriz de dimensión mxn con todos sus elementos a 0
    public Matriz(int m, int n) {
        this.numRows = m;
        this.numCols = n;
        matrix = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            matrix.add(i, (new Vector(n)));
        }
    }

    public void print() {
        for (int i = 0; i < numRows; i++) {
            matrix.get(i).print();
        }
    }
    
    public double get(int x, int y) {
        if (x < 0 || x >= numRows || y < 0 || y >= numCols) {
            throw new IndexOutOfBoundsException("Los índices están fuera del rango de la matriz.");
        }
        return matrix.get(x).get(y);
    }
    
    public void set(int i, int j, double valor) {
    	Vector fila = matrix.get(i);
    	fila.set(j, valor);
    	matrix.set(i, fila);
    }
}
