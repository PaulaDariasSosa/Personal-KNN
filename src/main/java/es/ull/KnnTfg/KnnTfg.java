package KnnTfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

import clasificacion.KNN;
import datos.*;
import procesamiento.*;
import entrenamiento.*;

public class KnnTfg {

	public static void main(String[] args) throws IOException {
		String ruta = "";
		Logger.getLogger("KnnTfg").info("Iniciando el programa");
		boolean salida = false;
		Dataset datosCrudos = new Dataset();
		Dataset datos = new Dataset();
		String archivo;
		while(!salida) {
			Logger.getLogger("KnnTfg").info("Ruta actual: " + ruta);
			Logger.getLogger("KnnTfg").info("Seleccione una opción: ");
			Logger.getLogger("KnnTfg").info("	[1] Cargar un dataset ");
			Logger.getLogger("KnnTfg").info("	[2] Guardar un dataset ");
			Logger.getLogger("KnnTfg").info("	[3] Modificar un dataset ");
			Logger.getLogger("KnnTfg").info("	[4] Mostrar información ");
			Logger.getLogger("KnnTfg").info("	[5] Salir del programa ");
			Logger.getLogger("KnnTfg").info("	[6] Realizar experimentación ");
			Logger.getLogger("KnnTfg").info("	[7] Algoritmo KNN para una instancia ");
			int opcion = 1;
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				archivo = readFile(ruta);
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
				salida = true;
				break;
			case(6):
				experimentar(datos);
				break;
			case(7):
				Logger.getLogger("KnnTfg").info("Introduce el valor de k: ");
				int k = scanner.nextInt();
				KNN intento = new KNN(k);
				String valoresString = "";
				Logger.getLogger("KnnTfg").info("Introduce los valores: ");
				Scanner scanner1 = new Scanner(System.in);
				valoresString = scanner1.nextLine();
				String[] subcadenas = valoresString.split(",");
				ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				Instancia instance = new Instancia (valoresString);
				Dataset copiaCrudos = new Dataset(datosCrudos.clone());
				if (datos.getPreprocesado() != 1) {
					arrayList.add("clase");
					copiaCrudos.add(arrayList);
					Preprocesado intento1 = new Normalizacion();
					if (datos.getPreprocesado() == 2) intento1 = new Normalizacion();
					if (datos.getPreprocesado() == 3) intento1 = new Estandarizacion();
					copiaCrudos = new Dataset (intento1.Procesar(copiaCrudos));
					instance = copiaCrudos.getInstance(copiaCrudos.NumeroCasos()-1);
					copiaCrudos.delete(copiaCrudos.NumeroCasos()-1);
					instance.deleteClase();
				}
				Logger.getLogger("KnnTfg").info("La clase elegida es: " + intento.clasificar(copiaCrudos, instance));
				break;
			default:
			}
		}
    }
	
	public static String readFile(String ruta) {
		int opcion = 2;
		String archivo = "";
		while (opcion != 4) {
			Logger.getLogger("KnnTfg").info("Se debe especificar la ruta y nombre del archivo: ");
			Logger.getLogger("KnnTfg").info("		[1] Introducir nombre");
			Logger.getLogger("KnnTfg").info("		[2] Mostrar ruta ");
			Logger.getLogger("KnnTfg").info("		[3] Cambiar ruta ");
			Logger.getLogger("KnnTfg").info("		[4] Salir ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				Logger.getLogger("KnnTfg").info("Introduzca el nombre del archivo: ");
				Scanner scanner1 = new Scanner(System.in);
				archivo = scanner1.nextLine();
				break;
			case(2):
				Logger.getLogger("KnnTfg").info("Ruta actual: " + ruta);
				break;
			case(3):
				Scanner scanner2 = new Scanner(System.in);
				ruta = scanner2.nextLine();
				break;
			default:
				Logger.getLogger("KnnTfg").info("Por defecto");
			}
		}
		return archivo;
	}
	
	public static Dataset modify(Dataset data) {
		int opcion = 2;
		String valores = "";
		while (opcion != 5) {
			Logger.getLogger("KnnTfg").info("Elija una opción de modificación ");
			Logger.getLogger("KnnTfg").info("		[1] Añadir instancia ");
			Logger.getLogger("KnnTfg").info("		[2] Eliminar instancia ");
			Logger.getLogger("KnnTfg").info("		[3] Modificar instancia ");
			Logger.getLogger("KnnTfg").info("		[4] Cambiar peso de los atributos ");
			Logger.getLogger("KnnTfg").info("		[5] Salir ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				valores = "";
				Logger.getLogger("KnnTfg").info("Introduce los valores: ");
				Scanner scanner1 = new Scanner(System.in);
				valores = scanner1.nextLine();
				String[] subcadenas = valores.split(",");
				ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				Logger.getLogger("KnnTfg").info("Añadiendo instancia: " + arrayList);
				data.add(arrayList);
				return data;
			case(2):
				int valor = 0;
				Logger.getLogger("KnnTfg").info("Introduce el indice a eliminar: ");
				scanner1 = new Scanner(System.in);
				valor = scanner1.nextInt();
				data.delete(valor);
				return data;
			case(3):
				valores = "";
				Logger.getLogger("KnnTfg").info("Introduce los valores: ");
				scanner1 = new Scanner(System.in);
				valores = scanner1.nextLine();
				subcadenas = valores.split(",");
				arrayList = new ArrayList<>(Arrays.asList(subcadenas));
				data.add(arrayList);
				valor = 0;
				Logger.getLogger("KnnTfg").info(("Introduce el indice a eliminar: "));
				scanner1 = new Scanner(System.in);
				valor = scanner1.nextInt();
				data.delete(valor);
				return data;
			case(4):
				data = CambiarPesos(data);
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
		Logger.getLogger("KnnTfg").info("Seleccione la opción de preprocesado: ");
		Logger.getLogger("KnnTfg").info("		[1] Datos crudos ");
		Logger.getLogger("KnnTfg").info("		[2] Rango 0-1 "); // por defecto
		Logger.getLogger("KnnTfg").info("		[3] Estandarización ");
		Logger.getLogger("KnnTfg").info("		[4] Salir ");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			data.setPreprocesado(1);
			return data;
		case(2):
			Normalizacion intento1 = new Normalizacion();
			data = new Dataset (intento1.Procesar(data));
			data.setPreprocesado(2);
			break;
		case(3):
			Estandarizacion intento2 = new Estandarizacion();
			data = new Dataset (intento2.Procesar(data));
			data.setPreprocesado(3);
			break;
		default:
			intento1 = new Normalizacion();
			data = new Dataset (intento1.Procesar(data));
			data.setPreprocesado(2);
		}
		return data;
	}
	
	public static Dataset CambiarPesos(Dataset data) {
		Logger.getLogger("KnnTfg").info("Seleccione la opción de cambio de pesos: ");
		Logger.getLogger("KnnTfg").info("			[1] Asignar pesos distintos a todos los atributos ");
		Logger.getLogger("KnnTfg").info("			[2] Mismo peso para todos los atributos "); // por defecto ( valor 1 )
		Logger.getLogger("KnnTfg").info("			[3] Cambiar peso un atributo");
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
			data.CambiarPeso(arrayList);
			return data;
		case(2):
			double valoresD = 1.0;
			scanner1 = new Scanner(System.in);
			valoresD = scanner1.nextDouble();
			data.CambiarPeso(valoresD);
			return data;
		case(3):
			int valorI = 0;
			Logger.getLogger("KnnTfg").info("Introduce el indice del atributo a modificar: ");
			scanner1 = new Scanner(System.in);
			valorI = scanner1.nextInt();
			Logger.getLogger("KnnTfg").info("Peso para asignar(Debe estar entre 0 y 1): ");
			valoresD = 1.0;
			valoresD = scanner1.nextDouble();
			data.CambiarPeso(valorI, valoresD);
			return data;
		default:
			break;
		}
		return data;
	}
	
	public static void info(Dataset data) {
		Logger.getLogger("KnnTfg").info("Seleccione una opción de información: ");
		Logger.getLogger("KnnTfg").info("			[1] Mostrar dataset ");
		Logger.getLogger("KnnTfg").info("			[2] Mostrar instancia ");
		Logger.getLogger("KnnTfg").info("			[3] Mostrar información atributos cuantitativos");
		Logger.getLogger("KnnTfg").info("			[4] Mostrar información atributos cualitativos");
		Logger.getLogger("KnnTfg").info("			[5] Mostrar pesos de los atributos");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			data.print();
			break;
		case(2):
			int valor = 0;
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Logger.getLogger("KnnTfg").info("Mostrando instancia: " + data.getInstance(valor).toString());
			break;
		case(3):
			infoCuantitativo(data);
			break;
		case(4):
			infoCualitativo(data);
			break;
		case(5):
			Logger.getLogger("KnnTfg").info("Mostrando pesos de los atributos: ");
			Logger.getLogger("KnnTfg").info(data.getPesos().toString());
			break;
		default:
			break;
		}
	}
	
	public static void infoCuantitativo(Dataset data) {
		Logger.getLogger("KnnTfg").info("				[1] Mostrar nombre ");
		Logger.getLogger("KnnTfg").info("				[2] Mostrar media ");
		Logger.getLogger("KnnTfg").info("				[3] Mostrar maximo");
		Logger.getLogger("KnnTfg").info("				[4] Mostrar minimo");
		Logger.getLogger("KnnTfg").info("				[5] Mostrar desviación tipica");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			int valor = 0;
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Cuantitativo auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando nombre: " + auxiliar.getNombre());
			break;
		case(2):
			valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando media: ");
			ArrayList<Double> media = new ArrayList<>();
			media.add(auxiliar.media());
			Logger.getLogger("KnnTfg").info(media.toString());
			break;
		case(3):
				valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			ArrayList<Double> maximo = new ArrayList<>();
			maximo.add(auxiliar.maximo());
			Logger.getLogger("KnnTfg").info("Mostrando máximo: ");
			Logger.getLogger("KnnTfg").info(maximo.toString());
			break;
		case(4):
			valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando mínimo: ");
			ArrayList<Double> minimo = new ArrayList<>();
			minimo.add(auxiliar.minimo());
			Logger.getLogger("KnnTfg").info(minimo.toString());
			break;
		case(5):
			valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cuantitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando desviación tipica: ");
			ArrayList<Double> desviacion = new ArrayList<>();
			desviacion.add(auxiliar.desviacion());
			Logger.getLogger("KnnTfg").info(desviacion.toString());
			break;
		default:
			break;
		}
	}
	
	public static void infoCualitativo(Dataset data) {
		Logger.getLogger("KnnTfg").info("				[1] Mostrar nombre ");
		Logger.getLogger("KnnTfg").info("				[2] Mostrar número de clases ");
		Logger.getLogger("KnnTfg").info("				[3] Mostrar clases");
		Logger.getLogger("KnnTfg").info("				[4] Mostrar frecuencia");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		switch(opcion) {
		case(1):
			int valor = 0;
			Scanner scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			try {
				Cualitativo auxiliar = (Cualitativo) data.get(valor);
				Logger.getLogger("KnnTfg").info("Mostrando nombre: " + auxiliar.getNombre());
			} catch (ClassCastException e) {
				Logger.getLogger("KnnTfg").info("Ese atributo no es cualitativo");
			}
			
			break;
		case(2):
			valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			Cualitativo auxiliar = (Cualitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando número de clases: ");
			ArrayList<Integer> nClases = new ArrayList<>();
			nClases.add(auxiliar.nClases());
			Logger.getLogger("KnnTfg").info(nClases.toString());
			break;
		case(3):
			valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cualitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando clases: ");
			Logger.getLogger("KnnTfg").info(auxiliar.clases().toString());
			break;
		case(4):
			valor = 0;
			scanner1 = new Scanner(System.in);
			valor = scanner1.nextInt();
			auxiliar = (Cualitativo) data.get(valor);
			Logger.getLogger("KnnTfg").info("Mostrando frecuencia: ");
			ArrayList<Double> frecuencia = auxiliar.frecuencia();
			Logger.getLogger("KnnTfg").info(frecuencia.toString());
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
			Logger.getLogger("KnnTfg").info("Seleccione una opción de experimentación: ");
			Logger.getLogger("KnnTfg").info("				[1] Generacion experimentación normal");
			Logger.getLogger("KnnTfg").info("				[2] Generacion experimentación aleatoria");
			Logger.getLogger("KnnTfg").info("				[3] Guardar Dataset ");
			Logger.getLogger("KnnTfg").info("				[4] Cargar Dataset ");
			Logger.getLogger("KnnTfg").info("				[5] Salir");
			opcion = scanner.nextInt();
			switch(opcion) {
			case(1):
				int valor = 0;
				Scanner scanner1 = new Scanner(System.in);
				Logger.getLogger("KnnTfg").info("Introduzca el porcentaje para el conjunto de entrenamiento");
				valor = scanner1.nextInt();
				nuevo = new Entrenamiento(datos, (double)valor/100);
				Logger.getLogger("KnnTfg").info("Introduce el valor de k: ");
				int k = scanner.nextInt();
				nuevo.generarPrediccion(k);
				nuevo.generarMatriz(k);
				break;
			case(2):
				nuevo = experimentacionAleatoria(datos);
				break;
			case(3):
				Logger.getLogger("KnnTfg").info("Introduzca el nombre para el archivo de entrenamiento: ");
				scanner1 = new Scanner(System.in);
				String archivo1 = scanner1.nextLine();
				Logger.getLogger("KnnTfg").info("Introduzca el nombre para el archivo de pruebas: ");
				scanner1 = new Scanner(System.in);
				String archivo2 = scanner1.nextLine();
				nuevo.write(archivo1, archivo2);
				break;
			case(4):
				Logger.getLogger("KnnTfg").info("Introduzca el nombre del archivo de entrenamiento: ");
				scanner1 = new Scanner(System.in);
				archivo1 = scanner1.nextLine();
				Logger.getLogger("KnnTfg").info("Introduzca el nombre del archivo de pruebas: ");
				scanner1 = new Scanner(System.in);
				archivo2 = scanner1.nextLine();
				nuevo.read(archivo1, archivo2);
				Logger.getLogger("KnnTfg").info("Introduce el valor de k: ");
				k = scanner.nextInt();
				nuevo.generarPrediccion(k);
				nuevo.generarMatriz(k);
				break;
			default:
				break;
			}
		}
	}
	
	public static Entrenamiento experimentacionAleatoria(Dataset datos) throws IOException {
		Logger.getLogger("KnnTfg").info("				[1] Semilla(Seed) por defecto");
		Logger.getLogger("KnnTfg").info("				[2] Semilla(Seed) manual");
		int opcion = 1;
		Scanner scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		Entrenamiento nuevo = new Entrenamiento();
		switch(opcion) {
		case(1):
			int valor = 0;
			Scanner scanner1 = new Scanner(System.in);
			Logger.getLogger("KnnTfg").info("Introduzca el porcentaje para el conjunto de entrenamiento");
			valor = scanner1.nextInt();
			nuevo = new Entrenamiento(datos, (double)valor/100, 1234);
			Logger.getLogger("KnnTfg").info("Introduce el valor de k: ");
			int k = scanner.nextInt();
			nuevo.generarPrediccion(k);
			nuevo.generarMatriz(k);
			return nuevo;
		case(2):
			valor = 0;
			scanner1 = new Scanner(System.in);
			Logger.getLogger("KnnTfg").info("Introduzca el porcentaje para el conjunto de entrenamiento");
			valor = scanner1.nextInt();
			scanner1 = new Scanner(System.in);
			Logger.getLogger("KnnTfg").info("Introduzca la semilla para la generacion aleatoria");
			int valor2 = scanner1.nextInt();
			nuevo = new Entrenamiento(datos, (double)valor/100, valor2);
			Logger.getLogger("KnnTfg").info("Introduce el valor de k: ");
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

