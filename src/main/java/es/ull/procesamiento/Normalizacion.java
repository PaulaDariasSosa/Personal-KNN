package procesamiento;

import java.util.ArrayList;
import java.util.List;

import datos.*;
import vectores.Vector;

/**
 * @brief Clase que implementa la normalización de los atributos cuantitativos de un dataset.
 */
public class Normalizacion implements Preprocesado{
	/**
	 * @brief Procesa un conjunto de datos normalizando los atributos cuantitativos.
	 * @param datos El conjunto de datos a procesar.
	 * @return Una lista de atributos con los atributos cuantitativos normalizados.
	 */
	public List<Atributo> procesar(Dataset datos) {
		ArrayList<Atributo> nuevos = new ArrayList<>(datos.getAtributos());
		Cuantitativo ejemplo = new Cuantitativo();
		for (int i = 0; i < nuevos.size(); i++) {
			if (nuevos.get(i).getClass() == ejemplo.getClass()) {
				ejemplo = (Cuantitativo) nuevos.get(i);
				Vector valores = ejemplo.getValores();
				valores.normalize();
				ejemplo.setValores(valores);
				nuevos.set(i,ejemplo);
			}
		}
		return nuevos;
	}	
	
}
