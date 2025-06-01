package knntfg;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import clasificacion.KNN;
import datos.*;
import procesamiento.*;
import entrenamiento.*;

public class KnnTfg {
	static final String KNN_TFG = "knntfg";
	static Dataset datosCrudos = new Dataset();
	static Dataset datos = new Dataset();

	public static void main(String[] args) throws IOException {
		print("Iniciando el programa");
		boolean salida = false;
		while(!salida) {
			Logger.getLogger("Seleccione una opción: ");
			print("[1] Cargar un dataset ");
			print("[2] Guardar un dataset ");
			print("[3] Modificar un dataset ");
			print("[4] Mostrar información ");
			print("[5] Salir del programa ");
			print("[6] Realizar experimentación ");
			print("[7] Algoritmo KNN para una instancia ");
			int opcion = 1;
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			salida = opciones(opcion);
		}
    }

	public static boolean opciones(Integer opcion) throws IOException {
		String ruta = "";
		String archivo = "";
		Scanner scanner = new Scanner(System.in);
		switch(opcion) {
			case(1):
				archivo = readFile(ruta);
				print("Cargando el dataset: ");
				checkLogger(archivo);
				datosCrudos = new Dataset(ruta+archivo);
				datos = new Dataset(ruta+archivo);
				datos = preprocesar(datos);
				break;
			case(2):
				archivo = readFile(ruta);
				datos.write(ruta+archivo);
				break;
			case(3):
				datos = modify(datos);
				break;
			case(4):
				info(datos);
				break;
			case(5):
				print("Saliendo del programa.");
				return true;
			case(6):
				experimentar(datos);
				break;
			case(7):
				print("Añada el valor de k aquí: ");
				int k = scanner.nextInt();
				KNN intento = new KNN(k);
				String valoresString = "";
				print("Introduce los valores, por favor: ");
				Scanner scanner1 = new Scanner(System.in);
				valoresString = scanner1.nextLine();
				String[] subcadenas = valoresString.split(",");
				ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				Instancia instance = new Instancia (valoresString);
				Dataset copiaCrudos = new Dataset(datosCrudos.clonar());
				if (datos.getPreprocesado() != 1) {
					arrayList.add("clase");
					copiaCrudos.add(arrayList);
					Preprocesado intento1 = new Normalizacion();
					if (datos.getPreprocesado() == 2) intento1 = new Normalizacion();
					if (datos.getPreprocesado() == 3) intento1 = new Estandarizacion();
					copiaCrudos = new Dataset (intento1.procesar(copiaCrudos));
					instance = copiaCrudos.getInstance(copiaCrudos.numeroCasos()-1);
					copiaCrudos.delete(copiaCrudos.numeroCasos()-1);
					instance.deleteClase();
				}
				print("La clase elegida es: ");
				checkLogger(intento.clasificar(copiaCrudos, instance));
				break;
			default:
		}
		return false;
	}
	
	public static String readFile(String ruta) {
		int opcion = 2;
		String archivo = "";
		while (opcion != 4) {
			print("Se debe especificar la ruta y nombre del archivo: ");
			print("[1] Introducir nombre");
			print("[2] Mostrar ruta ");
			print("[3] Cambiar ruta ");
			print("[4] Salir ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				print("Agregue el nombre del archivo: ");
				Scanner scanner1 = new Scanner(System.in);
				archivo = scanner1.nextLine();
				break;
			case(2):
				print("Ruta actual: ");
				Logger.getLogger(KNN_TFG).info(ruta);
				break;
			case(3):
				Scanner scanner2 = new Scanner(System.in);
				ruta = scanner2.nextLine();
				break;
			default:
				print("Saliendo de la selección de archivo.");
			}
		}
		return archivo;
	}
	
	public static Dataset modify(Dataset data) {
		int opcion = 2;
		String valores = "";
		while (opcion != 5) {
			print("Elija una opción de modificación ");
			print("[1] Añadir instancia ");
			print("[2] Eliminar instancia ");
			print("[3] Modificar instancia ");
			print("[4] Cambiar peso de los atributos ");
			print("[5] Salir ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				valores = "";
				print("Proporcione los valores: ");
				Scanner scanner1 = new Scanner(System.in);
				valores = scanner1.nextLine();
				String[] subcadenas = valores.split(",");
				ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				data.add(arrayList);
				print("Instancia añadida correctamente.");
				return data;
			case(2):
				int valor = 0;
				print("Introduce el indice a eliminar: ");
				scanner1 = new Scanner(System.in);
				valor = scanner1.nextInt();
				data.delete(valor);
				print("Instancia eliminada correctamente.");
				return data;
			case(3):
				valores = "";
				print("Introduce los valores: ");
				scanner1 = new Scanner(System.in);
				valores = scanner1.nextLine();
				subcadenas = valores.split(",");
				arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				data.add(arrayList);
				valor = 0;
				print(("Introduce el indice a eliminar: "));
				scanner1 = new Scanner(System.in);
				valor = scanner1.nextInt();
				data.delete(valor);
				print("Instancia modificada correctamente.");
				return data;
			case(4):
				data = cambiarPesos(data);
			return data;
			case(5):
				break;
			default:
				break;
			}
		}
		return data;
	}
	
	public static Dataset preprocesar(Dataset data) {
		print("Seleccione la opción de preprocesado: ");
		print("[1] Datos crudos ");
		print("[2] Rango 0-1 "); // por defecto
		print("[3] Estandarización ");
		print("[4] Salir ");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			data.setPreprocesado(1);
			print("Datos crudos seleccionados, no se aplicará preprocesado.");
			return data;
		case(2):
			Normalizacion intento1 = new Normalizacion();
			data = new Dataset (intento1.procesar(data));
			data.setPreprocesado(2);
			print("Normalización seleccionada, datos normalizados al rango 0-1.");
			break;
		case(3):
			Estandarizacion intento2 = new Estandarizacion();
			data = new Dataset (intento2.procesar(data));
			print("Estandarización seleccionada, datos estandarizados.");
			data.setPreprocesado(3);
			break;
		default:
			intento1 = new Normalizacion();
			print("Por defecto, se aplicará normalización al rango 0-1.");
			data = new Dataset (intento1.procesar(data));
			data.setPreprocesado(2);
		}
		return data;
	}
	
	public static Dataset cambiarPesos(Dataset data) {
		print("Seleccione la opción de cambio de pesos: ");
		print("[1] Asignar pesos distintos a todos los atributos ");
		print("[2] Mismo peso para todos los atributos "); // por defecto ( valor 1 )
		print("[3] Cambiar peso un atributo");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		scanner.nextLine();
		switch(opcion) {
		case(1):
			String valores = "";
			Scanner scanner1 = new Scanner(System.in);
			valores = scanner1.nextLine();
			String[] subcadenas = valores.split(",");
			ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
			data.cambiarPeso(arrayList);
			print("Pesos asignados correctamente.");
			return data;
		case(2):
			double valoresD = 1.0;
			scanner1 = new Scanner(System.in);
			valoresD = scanner1.nextDouble();
			data.cambiarPeso(valoresD);
			print("Peso asignado correctamente a todos los atributos.");
			return data;
		case(3):
			int valorI = 0;
			print("Introduce el indice del atributo a modificar: ");
			scanner1 = new Scanner(System.in);
			valorI = scanner1.nextInt();
			print("Peso para asignar(Debe estar entre 0 y 1): ");
			valoresD = 1.0;
			valoresD = scanner1.nextDouble();
			data.cambiarPeso(valorI, valoresD);
			print("Peso asignado correctamente al atributo ");
			return data;
		default:
			break;
		}
		return data;
	}
	
	public static void info(Dataset data) {
		print("Seleccione una opción de información: ");
		print("[1] Mostrar dataset ");
		print("[2] Mostrar instancia ");
		print("[3] Mostrar información atributos cuantitativos");
		print("[4] Mostrar información atributos cualitativos");
		print("[5] Mostrar pesos de los atributos");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			data.print();
			break;
		case(2):
			int valor = 0;
			print("Agregue el indice de la instancia a mostrar: ");
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			print("Mostrando instancia: ");
			checkLogger(data.getInstance(valor).toString());
			break;
		case(3):
			infoCuantitativo(data);
			break;
		case(4):
			infoCualitativo(data);
			break;
		case(5):
			print("Mostrando pesos de los atributos: ");
			checkLogger(data.getPesos().toString());
			break;
		default:
			break;
		}
	}
	
	public static void infoCuantitativo(Dataset data) {
		print("[1] Mostrar nombre ");
		print("[2] Mostrar media ");
		print("[3] Mostrar maximo");
		print("[4] Mostrar minimo");
		print("[5] Mostrar desviación tipica");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			int valor = 0;
			print("Introduce el indice del atributo: ");
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Cuantitativo auxiliar = (Cuantitativo) data.get(valor);
			print("Mostrando nombre: " + auxiliar.getNombre());
			break;
		case(2):
			valor = 0;
			print("Incorpore el indice del atributo: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			print("Mostrando media: ");
			ArrayList<Double> media = new ArrayList<>();
			media.add(auxiliar.media());
			checkLogger(media.toString());
			break;
		case(3):
			valor = 0;
			print("Introduce el indice: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			ArrayList<Double> maximo = new ArrayList<>();
			maximo.add(auxiliar.maximo());
			print("Mostrando máximo: ");
			checkLogger(maximo.toString());
			break;
		case(4):
			valor = 0;
			print("Introduce el indice: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			print("Mostrando mínimo: ");
			ArrayList<Double> minimo = new ArrayList<>();
			minimo.add(auxiliar.minimo());
			checkLogger(minimo.toString());
			break;
		case(5):
			valor = 0;
			print("Introduce un indice: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			print("Mostrando desviación tipica: ");
			ArrayList<Double> desviacion = new ArrayList<>();
			desviacion.add(auxiliar.desviacion());
			checkLogger(desviacion.toString());
			break;
		default:
			break;
		}
	}
	
	public static void infoCualitativo(Dataset data) {
		print("[1] Mostrar nombre ");
		print("[2] Mostrar número de clases ");
		print("[3] Mostrar clases");
		print("[4] Mostrar frecuencia");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			int valor = 0;
			print("Incorpore un indice: ");
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			try {
				Cualitativo auxiliar = (Cualitativo) data.get(valor);
				print("Mostrando nombre: " + auxiliar.getNombre());
			} catch (ClassCastException e) {
				print("Ese atributo no es cualitativo");
			}
			
			break;
		case(2):
			valor = 0;
			print("Inserte un indice a mostrar: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Cualitativo auxiliar = (Cualitativo) data.get(valor);
			print("Mostrando número de clases: ");
			ArrayList<Integer> nClases = new ArrayList<>();
			nClases.add(auxiliar.nClases());
			checkLogger(nClases.toString());
			break;
		case(3):
			valor = 0;
			print("Introduce un indice a mostrar: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cualitativo) data.get(valor);
			print("Mostrando clases: ");
			checkLogger(auxiliar.clases().toString());
			break;
		case(4):
			valor = 0;
			print("Introduce un indice del atributo a mostrar : ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cualitativo) data.get(valor);
			print("Mostrando frecuencia: ");
			List<Double> frecuencia = auxiliar.frecuencia();
			checkLogger(frecuencia.toString());
			break;
		default:
			break;
		}
	}
	
	public static void experimentar(Dataset datos) throws IOException {
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		Entrenamiento nuevo = new Entrenamiento();
		while (opcion != 5) {
			print("Seleccione una opción de experimentación: ");
			print("[1] Generacion experimentación normal");
			print("[2] Generacion experimentación aleatoria");
			print("[3] Guardar Dataset ");
			print("[4] Cargar Dataset ");
			print("[5] Salir");
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				int valor = 0;
				Scanner scanner1 = new Scanner(System.in);
				print("Introduzca el porcentaje de entrenamiento");
				valor = scanner1.nextInt();
				nuevo = new Entrenamiento(datos, (double)valor/100);
				print("Introduce el valor de k: ");
				int k = scanner.nextInt();
				nuevo.generarPrediccion(k);
				nuevo.generarMatriz(k);
				break;
			case(2):
				nuevo = experimentacionAleatoria(datos);
				break;
			case(3):
				print("Inserte el nombre para el archivo de entrenamiento: ");
				scanner1 = new Scanner(System.in);
				String archivo1 = scanner1.nextLine();
				print("Introduzca el nombre para el archivo de pruebas: ");
				scanner1 = new Scanner(System.in);
				String archivo2 = scanner1.nextLine();
				nuevo.write(archivo1, archivo2);
				break;
			case(4):
				print("Introduzca el nombre del archivo de entrenamiento: ");
				scanner1 = new Scanner(System.in);
				archivo1 = scanner1.nextLine();
				print("Inserte el nombre del archivo de pruebas: ");
				scanner1 = new Scanner(System.in);
				archivo2 = scanner1.nextLine();
				nuevo.read(archivo1, archivo2);
				print("Proporcione  el valor de k: ");
				k = scanner.nextInt();
				nuevo.generarPrediccion(k);
				nuevo.generarMatriz(k);
				break;
			default:
				break;
			}
		}
	}
	
	public static Entrenamiento experimentacionAleatoria(Dataset datos) {
		print("[1] Semilla(Seed) por defecto");
		print("[2] Semilla(Seed) manual");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		Entrenamiento nuevo = new Entrenamiento();
		switch(opcion) {
		case(1):
			int valor = 0;
			Scanner scanner1 = new Scanner(System.in);
			print("Introduzca el porcentaje para el conjunto de entrenamiento");
			valor = scanner1.nextInt();
			nuevo = new Entrenamiento(datos, (double)valor/100, 1234);
			print("Introduce el valor de K: ");
			int k = scanner.nextInt();
			nuevo.generarPrediccion(k);
			nuevo.generarMatriz(k);
			return nuevo;
		case(2):
			valor = 0;
			scanner1 = new Scanner(System.in);
			print("Introduzca el porcentaje para el conjunto de entrenamiento");
			valor = scanner1.nextInt();
			scanner1 = new Scanner(System.in);
			print("Introduzca la semilla para la generacion aleatoria");
			int valor2 = scanner1.nextInt();
			nuevo = new Entrenamiento(datos, (double)valor/100, valor2);
			print("Introduce el valor de k, por favor: ");
			k = scanner.nextInt();
			nuevo.generarPrediccion(k);
			nuevo.generarMatriz(k);
			return nuevo;
		default:
			break;
		}
		return nuevo;
	}

	public static void print(String mensaje) {
		Logger.getLogger(KNN_TFG).info(mensaje);
	}

	public static void checkLogger(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			print("No se ha introducido un nombre de archivo válido.");
			return;
		}
		if (Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
			print(cadena);
		}
	}
}

