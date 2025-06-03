package procesamiento;
import java.util.List;

import datos.Atributo;
import datos.Dataset;

/**
 * @brief Interfaz para el preprocesado de datos.
 */
public interface Preprocesado {
	/**
	 * @brief Procesa un conjunto de datos y devuelve una lista de atributos.
	 * @param datos El conjunto de datos a procesar.
	 * @return Una lista de atributos procesados.
	 */
	public List<Atributo> procesar(Dataset datos);
}
