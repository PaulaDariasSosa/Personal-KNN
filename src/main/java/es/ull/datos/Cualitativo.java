package datos;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Clase que representa un atributo cualitativo.
 */
public class Cualitativo extends Atributo{
	/**
	 * @brief Lista de valores
	 */
	private List<String> valores;

	/**
	 * @brief Constructor por defecto que inicializa el nombre y la lista de valores.
	 */
	public Cualitativo() {
		this.nombre = "";
		this.valores = new ArrayList<String>();
	}

	/**
	 * @brief Constructor que inicializa el nombre del atributo cualitativo.
	 * @param name Nombre del atributo cualitativo.
	 */
	public Cualitativo(String name) {
		this();
		this.nombre = name;
	}

	/**
	 * @brief Constructor que inicializa el nombre del atributo cualitativo y un valor.
	 * @param name Nombre del atributo cualitativo.
	 * @param valor Valor del atributo cualitativo.
	 */
	public Cualitativo(String name, String valor) {
		this();
		this.nombre = name;
		valores.add(valor);
	}

	/**
	 * @brief Constructor que inicializa el nombre del atributo cualitativo y una lista de valores.
	 * @param name Nombre del atributo cualitativo.
	 * @param valor Lista de valores del atributo cualitativo.
	 */
	public Cualitativo(String name, List<String> valor) {
		this();
		this.nombre = name;
		this.valores = valor;
	}

	/**
	 * @brief Metodo que devuelve los valores del atributo cualitativo.
	 * @return Una lista de cadenas que representan los valores del atributo cualitativo.
	 */
	public List<String> getValores() {
		return this.valores;
	}

	/**
	 * @brief Metodo que establece los valores del atributo cualitativo.
	 * @return Lista de cadenas que representan los nuevos valores del atributo cualitativo.
	 */
	public List<String> clases() {
		ArrayList<String> clases = new ArrayList<>();
		for(int i = 0; i < this.valores.size(); ++i) {
			if(!clases.contains(this.valores.get(i))) clases.add(this.valores.get(i));
		}
		return clases;
	}

	/**
	 * @brief Metodo que devuelve el numero de clases del atributo cualitativo.
	 * @return El numero de clases del atributo cualitativo.
	 */
	public int nClases() {
		return this.clases().size();
	}

	/**
	 * @brief Metodo que devuelve la frecuencia de cada clase del atributo cualitativo.
	 * @return Una lista de dobles que representan la frecuencia de cada clase del atributo cualitativo.
	 */
	public List<Double> frecuencia() {
		List<String> clases = this.clases();
		ArrayList<Double> frecuencias = new ArrayList<>();
		for (int j = 0; j < this.nClases(); ++j) {
			double auxiliar = 0;
			for(int i = 0; i < this.valores.size(); ++i) {
				if(clases.get(j).equals(this.valores.get(i))) auxiliar++;
			}
			frecuencias.add(auxiliar/this.valores.size());
		}
		return frecuencias;
	}

	/**
	 * @brief Metodo que devuelve numero de valores del atributo cualitativo.
	 * @return El numero de valores del atributo cualitativo.
	 */
	public int size() {
		return this.valores.size();
	}

	/**
	 * @brief Metodo que añade un valor al atributo cualitativo.
	 * @param valor El valor a añadir al atributo cualitativo.
	 */
	@Override
	public void add(Object valor) {
		valores.add((String) valor);
	}

	/**
	 * @brief Metodo que devuelve un valor del atributo cualitativo.
	 * @param i El indice del valor a devolver.
	 * @return El objeto que representa el valor del atributo cualitativo en la posicion indicada.
	 */
	@Override
	public Object getValor(int i) {
		return valores.get(i);
		
	}

	/**
	 * @brief Metodo que elimina un valor del atributo cualitativo en la posicion indicada.
	 * @param index La posicion del valor a eliminar.
	 */
	@Override
	public void delete(int index) {
		valores.remove(index);
	}

	/**
	 * @brief Metodo que devuelve una representacion en cadena del atributo cualitativo.
	 * @return Una cadena que representa el atributo cualitativo.
	 */
	@Override
	public String toString() {
		return valores.toString();
		
	}

	/**
	 * @brief Metodo que clona el atributo cualitativo.
	 * @return Un nuevo objeto Cualitativo que es una copia del actual.
	 */
	@Override
	public Cualitativo clonar() {
		return new Cualitativo(this.nombre, new ArrayList<>(this.valores));
	}
}
