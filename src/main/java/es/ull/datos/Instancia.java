package datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vectores.Vector;

public class Instancia {
	private List<Object> valores;
	
	public Instancia(List<Object> nuevos){
		this.valores = nuevos;
	}
	
	public Instancia(String nuevos){
		String[] subcadenas = nuevos.split(",");
		ArrayList<Object> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
		this.valores = arrayList;
	}
	
	public List<Object> getValores() {
		return this.valores;
	}
	
	public String toString() {
		return valores.toString();
	}
	
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
	
	public String getClase() {
		return (String) this.valores.get(valores.size()-1);
	}
	
	public void deleteClase() {
		valores.remove(valores.size() - 1);
	}

}
