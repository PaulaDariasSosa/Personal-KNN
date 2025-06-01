package knntfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
		Logger.getLogger(KNN_TFG).info("Iniciando el programa");
		boolean salida = false;
		while(!salida) {
			Logger.getLogger(KNN_TFG).info("Seleccione una opción: ");
			Logger.getLogger(KNN_TFG).info("[1] Cargar un dataset ");
			Logger.getLogger(KNN_TFG).info("[2] Guardar un dataset ");
			Logger.getLogger(KNN_TFG).info("[3] Modificar un dataset ");
			Logger.getLogger(KNN_TFG).info("[4] Mostrar información ");
			Logger.getLogger(KNN_TFG).info("[5] Salir del programa ");
			Logger.getLogger(KNN_TFG).info("[6] Realizar experimentación ");
			Logger.getLogger(KNN_TFG).info("[7] Algoritmo KNN para una instancia ");
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
				Logger.getLogger(KNN_TFG).info("Cargando el dataset: ");
				if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
					Logger.getLogger(KNN_TFG).info(archivo);
				}
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
				Logger.getLogger(KNN_TFG).info("Saliendo del programa.");
				return true;
			case(6):
				experimentar(datos);
				break;
			case(7):
				Logger.getLogger(KNN_TFG).info("Añada el valor de k aquí: ");
				int k = scanner.nextInt();
				KNN intento = new KNN(k);
				String valoresString = "";
				Logger.getLogger(KNN_TFG).info("Introduce los valores, por favor: ");
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
				Logger.getLogger(KNN_TFG).info("La clase elegida es: ");
				if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
					Logger.getLogger(KNN_TFG).info(intento.clasificar(copiaCrudos, instance));
				}
				break;
			default:
		}
		return false;
	}
	
	public static String readFile(String ruta) {
		int opcion = 2;
		String archivo = "";
		while (opcion != 4) {
			Logger.getLogger(KNN_TFG).info("Se debe especificar la ruta y nombre del archivo: ");
			Logger.getLogger(KNN_TFG).info("[1] Introducir nombre");
			Logger.getLogger(KNN_TFG).info("[2] Mostrar ruta ");
			Logger.getLogger(KNN_TFG).info("[3] Cambiar ruta ");
			Logger.getLogger(KNN_TFG).info("[4] Salir ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				Logger.getLogger(KNN_TFG).info("Agregue el nombre del archivo: ");
				Scanner scanner1 = new Scanner(System.in);
				archivo = scanner1.nextLine();
				break;
			case(2):
				Logger.getLogger(KNN_TFG).info("Ruta actual: ");
				Logger.getLogger(KNN_TFG).info(ruta);
				break;
			case(3):
				Scanner scanner2 = new Scanner(System.in);
				ruta = scanner2.nextLine();
				break;
			default:
				Logger.getLogger(KNN_TFG).info("Saliendo de la selección de archivo.");
			}
		}
		return archivo;
	}
	
	public static Dataset modify(Dataset data) {
		int opcion = 2;
		String valores = "";
		while (opcion != 5) {
			Logger.getLogger(KNN_TFG).info("Elija una opción de modificación ");
			Logger.getLogger(KNN_TFG).info("[1] Añadir instancia ");
			Logger.getLogger(KNN_TFG).info("[2] Eliminar instancia ");
			Logger.getLogger(KNN_TFG).info("[3] Modificar instancia ");
			Logger.getLogger(KNN_TFG).info("[4] Cambiar peso de los atributos ");
			Logger.getLogger(KNN_TFG).info("[5] Salir ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				valores = "";
				Logger.getLogger(KNN_TFG).info("Proporcione los valores: ");
				Scanner scanner1 = new Scanner(System.in);
				valores = scanner1.nextLine();
				String[] subcadenas = valores.split(",");
				ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				data.add(arrayList);
				Logger.getLogger(KNN_TFG).info("Instancia añadida correctamente.");
				return data;
			case(2):
				int valor = 0;
				Logger.getLogger(KNN_TFG).info("Introduce el indice a eliminar: ");
				scanner1 = new Scanner(System.in);
				valor = scanner1.nextInt();
				data.delete(valor);
				Logger.getLogger(KNN_TFG).info("Instancia eliminada correctamente.");
				return data;
			case(3):
				valores = "";
				Logger.getLogger(KNN_TFG).info("Introduce los valores: ");
				scanner1 = new Scanner(System.in);
				valores = scanner1.nextLine();
				subcadenas = valores.split(",");
				arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				data.add(arrayList);
				valor = 0;
				Logger.getLogger(KNN_TFG).info(("Introduce el indice a eliminar: "));
				scanner1 = new Scanner(System.in);
				valor = scanner1.nextInt();
				data.delete(valor);
				Logger.getLogger(KNN_TFG).info("Instancia modificada correctamente.");
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
		Logger.getLogger(KNN_TFG).info("Seleccione la opción de preprocesado: ");
		Logger.getLogger(KNN_TFG).info("[1] Datos crudos ");
		Logger.getLogger(KNN_TFG).info("[2] Rango 0-1 "); // por defecto
		Logger.getLogger(KNN_TFG).info("[3] Estandarización ");
		Logger.getLogger(KNN_TFG).info("[4] Salir ");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			data.setPreprocesado(1);
			Logger.getLogger(KNN_TFG).info("Datos crudos seleccionados, no se aplicará preprocesado.");
			return data;
		case(2):
			Normalizacion intento1 = new Normalizacion();
			data = new Dataset (intento1.procesar(data));
			data.setPreprocesado(2);
			Logger.getLogger(KNN_TFG).info("Normalización seleccionada, datos normalizados al rango 0-1.");
			break;
		case(3):
			Estandarizacion intento2 = new Estandarizacion();
			data = new Dataset (intento2.procesar(data));
			Logger.getLogger(KNN_TFG).info("Estandarización seleccionada, datos estandarizados.");
			data.setPreprocesado(3);
			break;
		default:
			intento1 = new Normalizacion();
			Logger.getLogger(KNN_TFG).info("Por defecto, se aplicará normalización al rango 0-1.");
			data = new Dataset (intento1.procesar(data));
			data.setPreprocesado(2);
		}
		return data;
	}
	
	public static Dataset cambiarPesos(Dataset data) {
		Logger.getLogger(KNN_TFG).info("Seleccione la opción de cambio de pesos: ");
		Logger.getLogger(KNN_TFG).info("[1] Asignar pesos distintos a todos los atributos ");
		Logger.getLogger(KNN_TFG).info("[2] Mismo peso para todos los atributos "); // por defecto ( valor 1 )
		Logger.getLogger(KNN_TFG).info("[3] Cambiar peso un atributo");
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
			ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(subcadenas));
			data.cambiarPeso(arrayList);
			Logger.getLogger(KNN_TFG).info("Pesos asignados correctamente.");
			return data;
		case(2):
			double valoresD = 1.0;
			scanner1 = new Scanner(System.in);
			valoresD = scanner1.nextDouble();
			data.cambiarPeso(valoresD);
			Logger.getLogger(KNN_TFG).info("Peso asignado correctamente a todos los atributos.");
			return data;
		case(3):
			int valorI = 0;
			Logger.getLogger(KNN_TFG).info("Introduce el indice del atributo a modificar: ");
			scanner1 = new Scanner(System.in);
			valorI = scanner1.nextInt();
			Logger.getLogger(KNN_TFG).info("Peso para asignar(Debe estar entre 0 y 1): ");
			valoresD = 1.0;
			valoresD = scanner1.nextDouble();
			data.cambiarPeso(valorI, valoresD);
			Logger.getLogger(KNN_TFG).info("Peso asignado correctamente al atributo ");
			return data;
		default:
			break;
		}
		return data;
	}
	
	public static void info(Dataset data) {
		Logger.getLogger(KNN_TFG).info("Seleccione una opción de información: ");
		Logger.getLogger(KNN_TFG).info("[1] Mostrar dataset ");
		Logger.getLogger(KNN_TFG).info("[2] Mostrar instancia ");
		Logger.getLogger(KNN_TFG).info("[3] Mostrar información atributos cuantitativos");
		Logger.getLogger(KNN_TFG).info("[4] Mostrar información atributos cualitativos");
		Logger.getLogger(KNN_TFG).info("[5] Mostrar pesos de los atributos");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			data.print();
			break;
		case(2):
			int valor = 0;
			Logger.getLogger(KNN_TFG).info("Agregue el indice de la instancia a mostrar: ");
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Logger.getLogger(KNN_TFG).info("Mostrando instancia: ");
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(data.getInstance(valor).toString());
			}
			break;
		case(3):
			infoCuantitativo(data);
			break;
		case(4):
			infoCualitativo(data);
			break;
		case(5):
			Logger.getLogger(KNN_TFG).info("Mostrando pesos de los atributos: ");
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(data.getPesos().toString());
			}
			break;
		default:
			break;
		}
	}
	
	public static void infoCuantitativo(Dataset data) {
		Logger.getLogger(KNN_TFG).info("[1] Mostrar nombre ");
		Logger.getLogger(KNN_TFG).info("[2] Mostrar media ");
		Logger.getLogger(KNN_TFG).info("[3] Mostrar maximo");
		Logger.getLogger(KNN_TFG).info("[4] Mostrar minimo");
		Logger.getLogger(KNN_TFG).info("[5] Mostrar desviación tipica");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			int valor = 0;
			Logger.getLogger(KNN_TFG).info("Introduce el indice del atributo: ");
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Cuantitativo auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando nombre: " + auxiliar.getNombre());
			break;
		case(2):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Incorpore el indice del atributo: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando media: ");
			ArrayList<Double> media = new ArrayList<>();
			media.add(auxiliar.media());
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(media.toString());
			}
			break;
		case(3):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Introduce el indice: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			ArrayList<Double> maximo = new ArrayList<>();
			maximo.add(auxiliar.maximo());
			Logger.getLogger(KNN_TFG).info("Mostrando máximo: ");
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(maximo.toString());
			}
			break;
		case(4):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Introduce el indice: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando mínimo: ");
			ArrayList<Double> minimo = new ArrayList<>();
			minimo.add(auxiliar.minimo());
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(minimo.toString());
			}
			break;
		case(5):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Introduce un indice: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando desviación tipica: ");
			ArrayList<Double> desviacion = new ArrayList<>();
			desviacion.add(auxiliar.desviacion());
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(desviacion.toString());
			}
			break;
		default:
			break;
		}
	}
	
	public static void infoCualitativo(Dataset data) {
		Logger.getLogger(KNN_TFG).info("[1] Mostrar nombre ");
		Logger.getLogger(KNN_TFG).info("[2] Mostrar número de clases ");
		Logger.getLogger(KNN_TFG).info("[3] Mostrar clases");
		Logger.getLogger(KNN_TFG).info("[4] Mostrar frecuencia");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			int valor = 0;
			Logger.getLogger(KNN_TFG).info("Incorpore un indice: ");
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			try {
				Cualitativo auxiliar = (Cualitativo) data.get(valor);
				Logger.getLogger(KNN_TFG).info("Mostrando nombre: " + auxiliar.getNombre());
			} catch (ClassCastException e) {
				Logger.getLogger(KNN_TFG).info("Ese atributo no es cualitativo");
			}
			
			break;
		case(2):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Inserte un indice a mostrar: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Cualitativo auxiliar = (Cualitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando número de clases: ");
			ArrayList<Integer> nClases = new ArrayList<>();
			nClases.add(auxiliar.nClases());
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(nClases.toString());
			}
			break;
		case(3):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Introduce un indice a mostrar: ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cualitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando clases: ");
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(auxiliar.clases().toString());
			}
			break;
		case(4):
			valor = 0;
			Logger.getLogger(KNN_TFG).info("Introduce un indice del atributo a mostrar : ");
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cualitativo) data.get(valor);
			Logger.getLogger(KNN_TFG).info("Mostrando frecuencia: ");
			List<Double> frecuencia = auxiliar.frecuencia();
			if(Logger.getLogger(KNN_TFG).isLoggable(java.util.logging.Level.INFO)) {
				Logger.getLogger(KNN_TFG).info(frecuencia.toString());
			}
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
			Logger.getLogger(KNN_TFG).info("Seleccione una opción de experimentación: ");
			Logger.getLogger(KNN_TFG).info("[1] Generacion experimentación normal");
			Logger.getLogger(KNN_TFG).info("[2] Generacion experimentación aleatoria");
			Logger.getLogger(KNN_TFG).info("[3] Guardar Dataset ");
			Logger.getLogger(KNN_TFG).info("[4] Cargar Dataset ");
			Logger.getLogger(KNN_TFG).info("[5] Salir");
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				int valor = 0;
				Scanner scanner1 = new Scanner(System.in);
				Logger.getLogger(KNN_TFG).info("Introduzca el porcentaje de entrenamiento");
				valor = scanner1.nextInt();
				nuevo = new Entrenamiento(datos, (double)valor/100);
				Logger.getLogger(KNN_TFG).info("Introduce el valor de k: ");
				int k = scanner.nextInt();
				nuevo.generarPrediccion(k);
				nuevo.generarMatriz(k);
				break;
			case(2):
				nuevo = experimentacionAleatoria(datos);
				break;
			case(3):
				Logger.getLogger(KNN_TFG).info("Inserte el nombre para el archivo de entrenamiento: ");
				scanner1 = new Scanner(System.in);
				String archivo1 = scanner1.nextLine();
				Logger.getLogger(KNN_TFG).info("Introduzca el nombre para el archivo de pruebas: ");
				scanner1 = new Scanner(System.in);
				String archivo2 = scanner1.nextLine();
				nuevo.write(archivo1, archivo2);
				break;
			case(4):
				Logger.getLogger(KNN_TFG).info("Introduzca el nombre del archivo de entrenamiento: ");
				scanner1 = new Scanner(System.in);
				archivo1 = scanner1.nextLine();
				Logger.getLogger(KNN_TFG).info("Inserte el nombre del archivo de pruebas: ");
				scanner1 = new Scanner(System.in);
				archivo2 = scanner1.nextLine();
				nuevo.read(archivo1, archivo2);
				Logger.getLogger(KNN_TFG).info("Proporcione  el valor de k: ");
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
		Logger.getLogger(KNN_TFG).info("[1] Semilla(Seed) por defecto");
		Logger.getLogger(KNN_TFG).info("[2] Semilla(Seed) manual");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		Entrenamiento nuevo = new Entrenamiento();
		switch(opcion) {
		case(1):
			int valor = 0;
			Scanner scanner1 = new Scanner(System.in);
			Logger.getLogger(KNN_TFG).info("Introduzca el porcentaje para el conjunto de entrenamiento");
			valor = scanner1.nextInt();
			nuevo = new Entrenamiento(datos, (double)valor/100, 1234);
			Logger.getLogger(KNN_TFG).info("Introduce el valor de K: ");
			int k = scanner.nextInt();
			nuevo.generarPrediccion(k);
			nuevo.generarMatriz(k);
			return nuevo;
		case(2):
			valor = 0;
			scanner1 = new Scanner(System.in);
			Logger.getLogger(KNN_TFG).info("Introduzca el porcentaje para el conjunto de entrenamiento");
			valor = scanner1.nextInt();
			scanner1 = new Scanner(System.in);
			Logger.getLogger(KNN_TFG).info("Introduzca la semilla para la generacion aleatoria");
			int valor2 = scanner1.nextInt();
			nuevo = new Entrenamiento(datos, (double)valor/100, valor2);
			Logger.getLogger(KNN_TFG).info("Introduce el valor de k, por favor: ");
			k = scanner.nextInt();
			nuevo.generarPrediccion(k);
			nuevo.generarMatriz(k);
			return nuevo;
		default:
			break;
		}
		return nuevo;
	}
}

