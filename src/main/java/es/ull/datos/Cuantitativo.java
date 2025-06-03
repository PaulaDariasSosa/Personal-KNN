package datos;

import vectores.Vector;

/**
 * @brief Clase que representa un atributo cuantitativo, que almacena valores numéricos.
 */
public class Cuantitativo extends Atributo{
	/**
	 * @brief Vector que almacena los valores del atributo cuantitativo.
	 */
	private Vector valores;

	/**
	 * @brief Constructor por defecto que inicializa el nombre del atributo a una cadena vacía
	 */
	public Cuantitativo() {
		this.nombre = "";
		this.valores = new Vector();
	}

	/**
	 * @brief Constructor que inicializa el nombre del atributo cuantitativo.
	 * @param name Nombre del atributo cuantitativo.
	 */
	public Cuantitativo(String name) {
		this();
		this.nombre = name;
	}

/**
	 * @brief Constructor que inicializa el nombre del atributo cuantitativo y un valor inicial.
	 * @param name Nombre del atributo cuantitativo.
	 * @param valor Valor inicial del atributo cuantitativo.
	 */
	public Cuantitativo(String name, Double valor) {
		this();
		this.nombre = name;
		valores.add(valor);
	}

/**
	 * @brief Constructor que inicializa el nombre del atributo cuantitativo y un vector de valores.
	 * @param name Nombre del atributo cuantitativo.
	 * @param valor Vector de valores del atributo cuantitativo.
	 */
	public Cuantitativo(String name, Vector valor) {
		this();
		this.nombre = name;
		this.valores = valor;
	}

	/**
	 * @brief Método que devuelve los valores del atributo cuantitativo.
	 * @return Vector que contiene los valores del atributo cuantitativo.
	 */
	public Vector getValores() {
		return this.valores;
	}

	/**
	 * @brief Método que establece nuevos valores para el atributo cuantitativo.
	 * @param nuevos Vector que contiene los nuevos valores del atributo cuantitativo.
	 */
	public void setValores(Vector nuevos) {
		this.valores = nuevos;
	}

	/**
	 * @brief Método que devuelve el valor mínimo del atributo cuantitativo.
	 * @return Valor mínimo del atributo cuantitativo.
	 */
	public double minimo() {
		double minimo = Integer.MAX_VALUE;
		for(int i = 0; i < this.valores.size(); ++i) {
			if(minimo > this.valores.get(i)) minimo = this.valores.get(i);
		}
		return minimo;
	}

	/**
	 * @brief Método que devuelve el valor máximo del atributo cuantitativo.
	 * @return Valor máximo del atributo cuantitativo.
	 */
	public double maximo() {
		double maximo = Integer.MIN_VALUE;
		for(int i = 0; i < this.valores.size(); ++i) {
			if(maximo < this.valores.get(i)) maximo = this.valores.get(i);
		}
		return maximo;
	}

	/**
	 * @brief Método que calcula la media de los valores del atributo cuantitativo.
	 * @return Media de los valores del atributo cuantitativo.
	 */
	public double media() {
		double media = 0;
		for(int i = 0; i < this.valores.size(); ++i) {
			media += this.valores.get(i);
		}
		return media/this.valores.size();
	}

	/**
	 * @brief Método que calcula la desviación estándar de los valores del atributo cuantitativo.
	 * @return Desviación estándar de los valores del atributo cuantitativo.
	 */
	public double desviacion() {
		double media = this.media();
		double auxiliar = 0;
		for(int i = 0; i < this.valores.size(); ++i) {
			auxiliar += (this.valores.get(i) - media) * (this.valores.get(i) - media);
		}
		auxiliar /= this.valores.size();
		return Math.sqrt(auxiliar);
	}

	/**
	 * @brief Método que devuelve el número de valores del atributo cuantitativo.
	 * @return Número de valores del atributo cuantitativo.
	 */
	public int size() {
		return this.valores.size();
	}

	/**
	 * @brief Método que estandariza los valores del atributo cuantitativo.
	 * Estandarización: (valor - media) / desviación estándar
	 */
	public void estandarizacion() {
		double desviacion = this.desviacion();
		double media = this.media();
		for (int i = 0; i < valores.size(); ++i) {
			valores.set(i, (valores.get(i)-media)/desviacion);
		}
	}

	/**
	 * @brief Método que añade un nuevo valor al atributo cuantitativo.
	 * @param valor Valor a añadir al atributo cuantitativo.
	 */
	@Override
	public void add(Object valor) {
		valores.add((double) valor);
		
	}

	/**
	 * @brief Método que devuelve el valor del atributo cuantitativo en la posición indicada.
	 * @param i Índice del valor a devolver.
	 * @return Valor del atributo cuantitativo en la posición indicada.
	 */
	@Override
	public Object getValor(int i) {
		return valores.get(i);
		
	}

	/**
	 * @brief Método que elimina el valor del atributo cuantitativo en la posición indicada.
	 * @param index Índice del valor a eliminar.
	 */
	@Override
	public void delete(int index) {
		valores.remove(index);
		
	}

	/**
	 * @brief Método que devuelve la cadena de texto que representa el atributo cuantitativo.
	 * @return Cadena de texto que representa el atributo cuantitativo.
	 */
	@Override
	public String toString() {
		return valores.toString();
		
	}

	/**
	 * @brief Método que clona el atributo cuantitativo.
	 * @return Una nueva instancia de Cuantitativo con los mismos valores.
	 */
	@Override
	public Cuantitativo clonar() {
		return new Cuantitativo(this.nombre, this.valores.clonar());
	}
}
