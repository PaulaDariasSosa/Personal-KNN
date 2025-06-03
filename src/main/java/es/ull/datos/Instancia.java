package datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vectores.Vector;

/**
 * @brief Clase que representa una instancia de datos.
 */
public class Instancia {
	/**
	 * @brief Lista que contiene los valores de la instancia.
	 */
	private List<Object> valores;

	/**
	 * @brief Constructor de la clase Instancia.
	 * @param nuevos Lista de valores que componen la instancia.
	 */
	public Instancia(List<Object> nuevos){
		this.valores = nuevos;
	}

	/**
	 * @brief Constructor de la clase Instancia.
	 * @param nuevos Cadena de texto que contiene los valores de la instancia, separados por comas.
	 */
	public Instancia(String nuevos){
		String[] subcadenas = nuevos.split(",");
		ArrayList<Object> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
		this.valores = arrayList;
	}

	/**
	 * @brief Devuelve los valores de la instancia.
	 * @return Lista de objetos que representan los valores de la instancia.
	 */
	public List<Object> getValores() {
		return this.valores;
	}

	/**
	 * @brief Devuelve una representación en cadena de la instancia.
	 * @return Cadena de texto que representa los valores de la instancia.
	 */
	public String toString() {
		return valores.toString();
	}

	/**
	 * @brief Devuelve un vector con los valores de la instancia, excluyendo la clase.
	 * @return Vector que contiene los valores de la instancia, sin incluir la clase.
	 */
	public Vector getVector() {
		Vector aux = new Vector();
		for (int i = 0; i < valores.size()-1; ++i) {
			 if (valores.get(i) instanceof Double) {
				 aux.add((Double) valores.get(i));
	         } else if (valores.get(i) instanceof Integer) {
	             aux.add((int) valores.get(i));
	         }
		}
		return aux;
	}

	/**
	 * @brief Devuelve la clase de la instancia.
	 * @return Cadena de texto que representa la clase de la instancia.
	 */
	public String getClase() {
		return (String) this.valores.get(valores.size()-1);
	}

	/**
	 * @brief Elimina la clase de la instancia.
	 */
	public void deleteClase() {
		valores.remove(valores.size() - 1);
	}

}
