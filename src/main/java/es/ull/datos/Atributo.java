package datos;

/**
 * @brief Clase abstracta que define un Atributo con un nombre y un peso.
 */
public abstract class Atributo {
	/**
	 * @brief Peso del atributo, por defecto es 1.
	 */
	protected double peso = 1;
	/**
	 * @brief Nombre del atributo.
	 */
	protected String nombre;

	/**
	 * @brief Metodo abstracto que devuelve los valores del atributo.
	 * @return Un objeto que contiene los valores del atributo.
	 */
	public abstract Object getValores();

	/**
	 * @brief Metodo que devuelve el nombre del atributo.
	 * @return El nombre del atributo.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @brief Metodo que devuelve el peso del atributo.
	 * @return El peso del atributo.
	 */
	public double getPeso() {
		return this.peso;
	}

	/**
	 * @brief Metodo que establece el nombre del atributo.
	 * @param nuevo El nuevo nombre del atributo.
	 */
	public void setNombre(String nuevo) {
		this.nombre = nuevo;
	}

	/**
	 * @brief Metodo que establece el peso del atributo.
	 * @param nuevo El nuevo peso del atributo.
	 */
	public void setPeso(double nuevo) {
		this.peso = nuevo;
	}

	/**
	 * @brief Metodo que devuelve una representacion en cadena del atributo.
	 * @return Una cadena que representa el atributo.
	 */
	public String get() {
		return (this.nombre + ": " + this.peso);
	}

	/**
	 * @brief Metodo abstracto que devuelve el numero de valores del atributo.
	 * @return El numero de valores del atributo.
	 */
	public abstract int size();

	/**
	 * @brief Metodo abstracto que añade un valor al atributo.
	 * @param valor El valor a añadir al atributo.
	 */
	public abstract void add(Object valor);

	/**
	 * @brief Metodo abstracto que elimina un valor del atributo en la posicion indicada.
	 * @param indice La posicion del valor a eliminar.
	 */
	public abstract void delete(int indice);

	/**
	 * @brief Metodo abstracto que devuelve el valor del atributo en la posicion indicada.
	 * @param i La posicion del valor a devolver.
	 * @return El valor del atributo en la posicion indicada.
	 */
	public abstract Object getValor(int i);

	/**
	 * @brief Metodo que devuelve una representacion en cadena del atributo.
	 * @return Una cadena que representa el atributo.
	 */
	public abstract String toString();

	/**
	 * @brief Metodo abstracto que clona el atributo.
	 * @return Una copia del atributo.
	 */
	public abstract Atributo clonar();
}
