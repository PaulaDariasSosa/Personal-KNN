package vectores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Vector {
	private final List<Double> coef;

    /**
     * Constructor vacio
     */
    public Vector() {
        coef = new ArrayList<>();
    }
    
    /**
     * Constructor que recibe un ArrayList de double
     * @param coef
     */
    public Vector(List<Double> coef) {
    	this.coef = new ArrayList<>(coef);
    }
    
    /**
     * Constructor que recibe un entero de tamaño
     * @param size
     */
    public Vector(int size) {
    	this();
    	for (int i = 0; i < size; ++i) {
    		coef.add(0.0);
    	}
    }

    /**
     * Método para clonar un vector
     * @return la copia del vector original
     */
    public Vector clonar() {
        return new Vector(new ArrayList<Double> (this.coef));
    }
    
    /**
     * Método para mostrar la dimensión del vector
     * @return entero con la dimansion del vector
     */
    public int size() {
        return coef.size();
    }
    
    public String toString() {
        return coef.toString();
    }

    public void print() {
        if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
            Logger.getGlobal().info(this.toString());
        }
    }

    public double get(int index) {
        return coef.get(index);
    }

    public void set(int index, double value) {
        coef.set(index, value);
    }

    public void add(double value) {
        coef.add(value);
    }

    public void remove(int index) {
        coef.remove(index);
    }

    public double getMax() {
        double max = Double.NEGATIVE_INFINITY;
        for (double value : coef) {
            if (value > max) max = value;
        }
        return max;
    }
    
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

    public double getMin() {
        double min = Double.POSITIVE_INFINITY;
        for (double value : coef) {
            if (value < min) min = value;
            
        }
        return min;
    }

    public void normalize() {
        double min  = this.getMin();
        double max = this.getMax();
        for (int i = 0; i < coef.size(); ++i) coef.set(i, (coef.get(i) - min) / (max - min));
    }
}
