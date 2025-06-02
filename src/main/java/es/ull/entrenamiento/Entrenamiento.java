package entrenamiento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Logger;

import clasificacion.KNN;
import datos.*;
import vectores.Matriz;

public class Entrenamiento {
	Dataset train;
	Dataset test;
	List<String> clases;
	
	public Entrenamiento() {
	}
	
	public Entrenamiento(Dataset datos, double porcentaje) {
		Dataset trainset = new Dataset(datos.getAtributosEmpty());
		Dataset testset = new Dataset(datos.getAtributosEmpty());
		clases = datos.getClases();
		int indice = 0;
		while(indice < datos.numeroCasos()*porcentaje) {
			trainset.add(datos.getInstance(indice));
			indice += 1;
		}
		for (int i = indice; i < datos.numeroCasos(); ++i) {
			testset.add(datos.getInstance(i));
		}
		this.test = testset;
		this.train = trainset;
		this.test.setPreprocesado(datos.getPreprocesado());
		this.train.setPreprocesado(datos.getPreprocesado());
	}
	
	public Entrenamiento(Dataset datos, double porcentaje, int semilla) {
		Dataset trainset = new Dataset(datos.getAtributosEmpty());
		Dataset testset = new Dataset(datos.getAtributosEmpty());
		clases = datos.getClases();
		ArrayList<Integer> indices = new ArrayList<>();
		byte[] semillaBytes = new byte[semilla];
		SecureRandom random = new SecureRandom(semillaBytes);
		while(indices.size() < datos.numeroCasos()*porcentaje) {
			int randomNumber = random.nextInt(datos.numeroCasos());
			if (!indices.contains(randomNumber)) {
				trainset.add(datos.getInstance(randomNumber));
				indices.add(randomNumber);
			}
		}
		for (int i = 0; i < datos.numeroCasos(); ++i) {
			if (!indices.contains(i)) {
				testset.add(datos.getInstance(i));
			}
		}
		this.test = testset;
		this.train =  trainset;
		this.test.setPreprocesado(datos.getPreprocesado());
		this.train.setPreprocesado(datos.getPreprocesado());
	}
	
	public void generarPrediccion(int valorK) {
		Dataset pruebas = new Dataset(test);
		Double aciertos = 0.0;
		for (int i = 0; i < pruebas.numeroCasos(); ++i) {
			ArrayList<Object> instance = new ArrayList<>();
			for (int j = 0; j < pruebas.numeroAtributos()-1; ++j) {
				instance.add(pruebas.getInstance(i).getValores().get(j));
			}
			Instancia nueva = new Instancia(instance);
			String clase = (new KNN(valorK).clasificar(train, nueva));
			if (clase.equals(test.getInstance(i).getClase())) aciertos += 1;
		}
		Logger.getLogger(Entrenamiento.class.getName()).info("La precisión predictiva: ");
		if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
			Logger.getLogger(Entrenamiento.class.getName()).info(String.valueOf(aciertos));
		}
		Logger.getLogger(Entrenamiento.class.getName()).info(" / ");
		if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
			Logger.getLogger(Entrenamiento.class.getName()).info(String.valueOf(test.numeroCasos()));
		}
		Double precision = (aciertos/test.numeroCasos())*100;
		if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
			Logger.getLogger(Entrenamiento.class.getName()).info(String.valueOf(precision));
		}
	}
	
	public void generarMatriz(int valorK) {
		Dataset pruebas = new Dataset(test);
		Matriz confusion = new Matriz (clases.size(), clases.size());
		for (int i = 0; i < pruebas.numeroCasos(); ++i) {
			ArrayList<Object> instance = new ArrayList<>();
			for (int j = 0; j < pruebas.numeroAtributos()-1; ++j) {
				instance.add(pruebas.getInstance(i).getValores().get(j));
			}
			Instancia nueva = new Instancia(instance);
			String clase = (new KNN(valorK).clasificar(train, nueva));
			confusion.set( clases.indexOf(test.getInstance(i).getClase()),clases.indexOf(clase),confusion.get(clases.indexOf(test.getInstance(i).getClase()),clases.indexOf(clase))+1);
		}
		if(Logger.getGlobal().isLoggable(java.util.logging.Level.INFO)) {
			Logger.getLogger(Entrenamiento.class.getName()).info(clases.toString());
		}
		confusion.print();
	}
	
	public void write(String filename1, String filename2) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename1))) {
            train.write(filename1);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename2))) {
            test.write(filename2);
        }
    }
	
	public void read(String filename1, String filename2) throws IOException {
		train = new Dataset(filename1);
        test = new Dataset(filename2);
        List<String> clasesA = train.getClases();
        List<String> clasesB = test.getClases();
        for (int i = 0; i < clasesB.size(); i++) {
        	if (!clasesA.contains(clasesB.get(i))) clasesA.add(clasesB.get(i));
        }
        clases = clasesA;
    }
	
}
