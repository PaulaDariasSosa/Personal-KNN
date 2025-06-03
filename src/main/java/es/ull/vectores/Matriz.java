package vectores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una matriz de números reales.
 * La matriz se almacena como una lista de vectores, donde cada vector representa una fila de la matriz.
 */
public class Matriz {
    /**
     * @brief Lista que almacena las filas de la matriz, donde cada fila es un Vector.
     */
    private final List<Vector> matrix;
    /**
     * @brief Número de filas y columnas de la matriz.
     */
    private final int numRows;
    private final int numCols;

    /**
     * @brief Crea una matriz de tamaño m x n, inicializada con ceros.
     * @param m Número de filas de la matriz.
     * @param n Número de columnas de la matriz.
     */
    public Matriz(int m, int n) {
        this.numRows = m;
        this.numCols = n;
        matrix = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            matrix.add(i, (new Vector(n)));
        }
    }

    /**
     * @brief Crea una matriz a partir de una lista de vectores.
     */
    public void print() {
        for (int i = 0; i < numRows; i++) {
            matrix.get(i).print();
        }
    }

    /**
     * @brief Obtiene el valor en la posición (x, y) de la matriz.
     * @param x Índice de la fila.
     * @param y Índice de la columna.
     * @return El valor en la posición (x, y) de la matriz.
     */
    public double get(int x, int y) {
        if (x < 0 || x >= numRows || y < 0 || y >= numCols) {
            throw new IndexOutOfBoundsException("Los índices están fuera del rango de la matriz.");
        }
        return matrix.get(x).get(y);
    }

    /**
     * @brief Establece el valor en la posición (i, j) de la matriz.
     * @param i Índice de la fila.
     * @param j Índice de la columna.
     * @param valor Valor a establecer en la posición (i, j).
     */
    public void set(int i, int j, double valor) {
    	Vector fila = matrix.get(i);
    	fila.set(j, valor);
    	matrix.set(i, fila);
    }
}
