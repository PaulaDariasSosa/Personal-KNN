package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @brief Clase que representa un conjunto de datos, compuesto por una lista de atributos.
 */
public class Dataset {
	/**
	 * @brief Lista de atributos que componen el dataset.
	 */
	private List<Atributo> atributos;
	/**
	 * @brief Variable que indica el preprocesado realizado en el dataset.
	 */
	int preprocesado;

	/**
	 * @brief Constructor por defecto que inicializa la lista de atributos.
	 */
	public Dataset() {
		this.atributos = new ArrayList<Atributo>();
	}

	/**
	 * @brief Constructor que recibe una lista de atributos para inicializar el dataset.
	 * @param nuevos Lista de atributos a añadir al dataset.
	 */
	public Dataset(List<Atributo> nuevos) {
		this();
		this.atributos = nuevos;
	}

	/**
	 * @brief Constructor que recibe el nombre de un archivo y carga los datos desde él.
	 * @param filename Nombre del archivo desde el cual se cargarán los datos.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 */
	public Dataset(String filename) throws IOException {
		this();
		this.read(filename);
	}

	/**
	 * @brief Constructor que recibe otro dataset y copia sus atributos.
	 * @param datos Dataset del cual se copiarán los atributos.
	 */
	public Dataset(Dataset datos) {
		this();
		this.atributos = new ArrayList<>(datos.atributos);
	}

	/**
	 * @brief Metodo para cambiar el peso de los atributos del dataset.
	 * @param arrayList Lista de pesos a asignar a cada atributo.
	 */
	public void cambiarPeso(List<String> arrayList) {
		if ( arrayList.size() != atributos.size()) {
			throw new IllegalArgumentException("El número de pesos para asignar debe ser igual al número de atributos");

		} else {
			for (int i = 0; i <  arrayList.size(); i++) {
				Atributo aux = atributos.get(i);
				aux.setPeso(Double.parseDouble(arrayList.get(i)));
				this.atributos.set(i, aux);
			}
		}
	}

	/**
	 * @brief Metodo para cambiar el peso de un atributo en particular.
	 * @param index
	 * @param peso
	 */
	public void cambiarPeso(int index, double peso) {
		Atributo aux = this.atributos.get(index);
		aux.setPeso(peso);
		this.atributos.set(index, aux);
	}

	/**
	 * @brief Metodo para cambiar el peso de todos los atributos del dataset.
	 * @param peso
	 */
	public void cambiarPeso(double peso) {
	       for (int i = 0; i <  atributos.size(); i++) {
	        Atributo aux = atributos.get(i);
	        aux.setPeso(peso);
	        this.atributos.set(i, aux);
	       }
	}

	/**
	 * @brief Imprime el dataset en la consola.
	 */
	public void print() {
		if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
			Logger.getGlobal().info(this.toString());
		}
	}

	/**
	 * @brief Convierte el dataset a una representación en cadena de texto.
	 * @return Una cadena de texto que representa el dataset, con los nombres de los atributos y sus valores.
	 */
	public String toString() {
		StringBuilder data = new StringBuilder();
		List<String> valores = this.nombreAtributos();
		valores.addAll(this.getValores());
		int contador = 1;
		for (int i = 0; i < valores.size(); ++i) { 
			data.append(valores.get(i));
			if (contador == this.numeroAtributos()) {
				data.append("\n");
				contador = 1;
			} else {
				data.append(",");
				++contador;
			}
		}
	    return data.toString();
	}

	/**
	 * @brief Añade una nueva instancia al dataset.
	 * @param nueva Instancia que se añadirá al dataset.
	 */
	public void add(Instancia nueva) {
		for (int i = 0; i < atributos.size(); ++i) {
			Atributo aux =  atributos.get(i);
			aux.add(nueva.getValores().get(i));
			atributos.set(i, aux);
		}	
	}

	/**
	 * @brief Añade una nueva instancia al dataset a partir de una lista de valores.
	 * @param nueva Lista de valores que representan los atributos de la nueva instancia.
	 */
	public void add(List<String> nueva) {
		for (int i = 0; i < atributos.size(); ++i) {
			Atributo aux =  atributos.get(i);
			try {
				aux.add(Double.parseDouble(nueva.get(i)));
    		} catch (NumberFormatException e) {
    			aux.add(nueva.get(i));
    		}
			atributos.set(i, aux);
		}	
	}

	/**
	 * @brief Elimina una instancia del dataset basándose en el índice de la instancia.
	 * @param nueva Índice de la instancia que se eliminará del dataset.
	 */
	public void delete(int nueva) {
		for (int i = 0; i < atributos.size(); ++i) {
			Atributo aux = atributos.get(i);
			aux.delete(nueva);
			atributos.set(i, aux);
		}
	}

	/**
	 * @brief Escribe el dataset en un archivo.
	 * @param filename
	 * @throws IOException
	 */
    public void write(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(this.toString());
        }
    }

	/**
	 * @brief Lee un archivo CSV y carga sus datos en el dataset.
	 * @param filename
	 * @throws IOException
	 */
	public void read(String filename) throws IOException {
		if (filename == null || filename.isEmpty()) {
			throw new IllegalArgumentException("El nombre del archivo no puede ser nulo o vacío");
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Leer la primera línea para obtener los nombres de los atributos
			// llamar al constructor vacio
            String[] attributeNamesArray = reader.readLine().split(",");
            String line;
            if ((line = reader.readLine()) != null) {
            	String[] values = line.split(",");
            	for (int i = 0; i < attributeNamesArray.length ; ++i) {
            		try {
            			this.atributos.add(new Cuantitativo(attributeNamesArray[i], Double.parseDouble(values[i]))); // sino poner encima Double.parseDouble(values[i])
            		} catch (NumberFormatException e) {
            			this.atributos.add(new Cualitativo(attributeNamesArray[i], values[i]));
            		}
            	}
            }
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < attributeNamesArray.length ; ++i) {
                	Atributo nuevo = this.atributos.get(i);
            		try {
            			nuevo.add(Double.parseDouble(values[i]));
            		} catch (NumberFormatException e) {
            			nuevo.add(values[i]);
            		}
            		this.atributos.set(i, nuevo);
            	}
            }
        }
		if (atributos.isEmpty()) {
			throw new IOException("El archivo está vacío o no contiene datos válidos.");
		}
	}

	/**
	 * @brief Devuelve el número de atributos en el dataset.
	 * @return Número de atributos.
	 */
	public int numeroAtributos() {
		return atributos.size();
	}

	/**
	 * @brief Devuelve una lista con los nombres de los atributos del dataset.
	 * @return Lista de nombres de atributos.
	 */
	public List<String> nombreAtributos(){
		ArrayList<String> nombres = new ArrayList<>();
		for(int i = 0; i < atributos.size(); ++i) nombres.add(atributos.get(i).getNombre());
		return nombres;
	}

	/**
	 * @brief Devuelve una lista con los nombres de los atributos del dataset.
	 * @return Lista de nombres de atributos
	 */
	public List<Atributo> getAtributos(){
		return atributos;
	}

	/**
	 * @brief Devuelve una lista de atributos vacíos, manteniendo los nombres y pesos de los atributos originales.
	 * @return Lista de atributos vacíos.
	 */
	public List<Atributo> getAtributosEmpty() {
		ArrayList<Atributo> aux = new ArrayList<> (atributos.size());
		for (int i = 0; i < atributos.size(); ++i) {
			try {
				Cualitativo auxiliar = (Cualitativo) atributos.get(i);
				aux.add(new Cualitativo(auxiliar.getNombre()));
			} catch (ClassCastException e) {
				Cuantitativo auxiliar = (Cuantitativo) atributos.get(i);
				aux.add(new Cuantitativo(auxiliar.getNombre()));
			}
		}
		for (int i = 0; i < atributos.size(); ++i) {
			Atributo prov = aux.get(i);
			prov.setPeso(atributos.get(i).getPeso());
			aux.set(i, prov);
		}
		return aux;
	}

	/**
	 * @brief Devuelve el número de casos (instancias) en el dataset.
	 * @return Número de casos.
	 */
	public int numeroCasos() {
		return atributos.get(0).size();
	}

	/**
	 * @brief Devuelve una lista con los valores de todos los atributos en el dataset.
	 * @return Lista de valores de atributos.
	 */
	public List<String> getValores(){
		ArrayList<String> valores = new ArrayList<String>();
		 for (int i = 0; i < atributos.get(0).size(); ++i) {
	        	for (int j = 0; j < atributos.size(); ++j) valores.add(String.valueOf(atributos.get(j).getValor(i)));
		}
		return valores;
	}

	/**
	 * @brief Devuelve un atributo específico del dataset.
	 * @param index Índice del atributo a devolver.
	 * @return Atributo en la posición especificada.
	 */
	public Atributo get(int index) {
		return atributos.get(index);
	}

	/**
	 * @brief Devuelve una instancia del dataset en una posición específica.
	 * @param index Índice de la instancia a devolver.
	 * @return Instancia en la posición especificada.
	 */
	public Instancia getInstance(int index){
	 	ArrayList<Object> auxiliar = new ArrayList<>();
		for (int i = 0; i < atributos.size(); ++i) auxiliar.add(atributos.get(i).getValor(index));
		return new Instancia (auxiliar);
	}

	/**
	 * @brief Devuelve una lista con los pesos de los atributos del dataset.
	 * @return Lista de pesos de atributos.
	 */
	public List<String> getPesos() {
		ArrayList<String> valores = new ArrayList<>();
		for (Atributo valor : this.atributos) valores.add(valor.get());
		return valores;
	}

	/**
	 * @brief Devuelve una lista con las clases del atributo cualitativo del dataset.
	 * @return Lista de clases del atributo cualitativo.
	 */
	public List<String> getClases() {
		return ((Cualitativo) this.atributos.get(atributos.size()-1)).clases();
	}

	/**
	 * @brief Devuelve el preprocesado realizado en el dataset.
	 * @return Preprocesado realizado.
	 */
	public int getPreprocesado() {
		return preprocesado;
	}

	/**
	 * @brief Establece el preprocesado realizado en el dataset.
	 * @param opcion Opción del preprocesado realizado.
	 */
	public void setPreprocesado(int opcion) {
		this.preprocesado = opcion;
	}

	/**
	 * @brief Establece una nueva lista de atributos para el dataset.
	 * @param nuevos Lista de nuevos atributos a establecer.
	 */
	public void setAtributos(List<Atributo> nuevos) {
		this.atributos = nuevos;
	}

	/**
	 * @brief Clona el dataset creando una copia profunda de sus atributos.
	 * @return Una nueva instancia de Dataset que es una copia del original.
	 */
	public Dataset clonar() {
		Dataset copia = new Dataset();
	    // Realizar una copia profunda de los elementos de la lista
		ArrayList<Atributo> copiaAtributos = new ArrayList<>();
	    for (Atributo atributo : this.atributos) {
	        copiaAtributos.add(atributo.clonar());
	    }
	    copia.setAtributos(copiaAtributos);
	    return copia;
	}
}