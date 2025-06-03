package procesamiento;

import java.util.List;

import datos.Atributo;
import datos.Cuantitativo;
import datos.Dataset;

/**
 * @brief Clase que implementa el preprocesado de estandarización.
 */
public class Estandarizacion implements Preprocesado{
	/**
	 * @ brief Procesa el conjunto de datos para estandarizar los atributos cuantitativos.
	 * @param datos El conjunto de datos a procesar.
	 * @return Una lista de atributos con los atributos cuantitativos estandarizados.
	 */
	public List<Atributo> procesar(Dataset datos) {
		List<Atributo> nuevos = datos.getAtributos();
		Cuantitativo ejemplo = new Cuantitativo();
		for (int i = 0; i < nuevos.size(); i++) {
			if (nuevos.get(i).getClass() == ejemplo.getClass()) {
				ejemplo = (Cuantitativo) nuevos.get(i);
				ejemplo.estandarizacion();
				nuevos.set(i,ejemplo);
			}
		}
		return nuevos;
	}
	
}
