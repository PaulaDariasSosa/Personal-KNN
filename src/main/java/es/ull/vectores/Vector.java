package vectores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @brief Clase Vector que representa un vector de coeficientes
 */
public class Vector {
        /**
        * @brief Lista de coeficientes del vector
        */
	private final List<Double> coef;

    /**
     * @brief Constructor vacio
     */
    public Vector() {
        coef = new ArrayList<>();
    }
    
    /**
     * @brief Constructor que recibe un ArrayList de double
     * @param coef
     */
    public Vector(List<Double> coef) {
    	this.coef = new ArrayList<>(coef);
    }
    
    /**
     * @brief  Constructor que recibe un entero de tamaño
     * @param size
     */
    public Vector(int size) {
    	this();
    	for (int i = 0; i < size; ++i) {
    		coef.add(0.0);
    	}
    }

    /**
     * @brief  Método para clonar un vector
     * @return la copia del vector original
     */
    public Vector clonar() {
        return new Vector(new ArrayList<> (this.coef));
    }
    
    /**
     * @brief Método para mostrar la dimensión del vector
     * @return entero con la dimansion del vector
     */
    public int size() {
        return coef.size();
    }

    /**
     * @brief Método para convertir el vector a una cadena de texto
     * @return cadena de texto con los coeficientes del vector
     */
    public String toString() {
        return coef.toString();
    }

    /**
     * @brief Método para imprimir el vector
     * Si el logger está configurado para mostrar mensajes de nivel INFO, imprime el vector
     */
    public void print() {
        if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
            Logger.getGlobal().info(this.toString());
        }
    }

    /**
     * @brief Método para obtener el coeficiente en una posición concreta
     * @param index posición del coeficiente
     * @return el coeficiente en la posición indicada
     */
    public double get(int index) {
        return coef.get(index);
    }

    /**
     * @brief Método para establecer un coeficiente en una posición concreta
     * @param index posición del coeficiente
     * @param value valor a establecer en la posición indicada
     */
    public void set(int index, double value) {
        coef.set(index, value);
    }

    /**
     * @brief Método para añadir un coeficiente al vector
     * @param value valor a añadir al vector
     */
    public void add(double value) {
        coef.add(value);
    }

    /**
     * @brief Método para eliminar un coeficiente en una posición concreta
     * @param index posición donde se eliminara el coeficiente
     */
    public void remove(int index) {
        coef.remove(index);
    }

    /**
     * @brief Método para obtener el coeficiente máximo del vector
     * @return el valor máximo del vector
     */
    public double getMax() {
        double max = Double.NEGATIVE_INFINITY;
        for (double value : coef) {
            if (value > max) max = value;
        }
        return max;
    }

    /**
     * @brief Método para obtener el índice del coeficiente máximo del vector
     * @return el índice del valor máximo del vector
     */
    public int getMaxInt() {
        double max = Double.NEGATIVE_INFINITY;
        int maxint = -1;
        for (int i = 0; i < coef.size(); ++i) {
            if (coef.get(i) > max) {
            	max = coef.get(i);
            	maxint = i;
            }
        }
        return maxint;
    }

    /**
     * @brief Método para obtener el coeficiente mínimo del vector
     * @return el valor mínimo del vector
     */
    public double getMin() {
        double min = Double.POSITIVE_INFINITY;
        for (double value : coef) {
            if (value < min) min = value;
            
        }
        return min;
    }

    /**
     * @brief Método para normalizar el vector
     */
    public void normalize() {
        double min  = this.getMin();
        double max = this.getMax();
        for (int i = 0; i < coef.size(); ++i) coef.set(i, (coef.get(i) - min) / (max - min));
    }
}
