/*
 * Donnees.java                                                          
 */
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

/**
 * Classe s'occupant de la récupération des données
 * @author Thibault
 *
 */
public class Donnees {
	
	/** Attribut contenant le nom du fichier csv dans lequel on va chercher les données */
	private String nomFichier;
	
	/** Attribut contenant la température */
	private int temperature;
	
	/** Attribut contenant le ressenti */
	private int ressenti;
	
	/** Attribut contenant l'humidité */
	private int humidite;
	
	/** Attribut contenant la vitesse du vent */
	private int vitesseVent;
	
	/** Attribut contenant la direction du vent sous 360° */
	private int directionVent;
	
	/** Attribut contenant la direction du vent sous forme litéralle */
	private String directionVentString;
	
	/** Attribut contenant le taux d'ensoleillement */
	private int tauxEnsoleillement;
	
	/**
	 * Constructeur qui va initialiser un objet Donnees par défaut sur le dernier relevé disponible
	 */
	public Donnees() {
		this.nomFichier = Trame.getFichier();
		this.temperature = recupererTemperature(this.nomFichier);
		this.humidite = recupererHumidite(this.nomFichier);
		this.directionVent = recupererDirectionVent(this.nomFichier);
		this.vitesseVent = recupererVitesseVent(this.nomFichier);
		this.tauxEnsoleillement = recupererEnsoleillement(this.nomFichier);
		this.ressenti = recupererRessenti(this.nomFichier);
		this.directionVentString = convertirDirection(this.directionVent);
	}
	
	/**
	 * Constructeur qui va initialiser un objet Donnees sur l'horaire et la date passés en arguments
	 * @param date
	 * @param horaire
	 */
	public Donnees(Date date, Horaire horaire) {
		this.nomFichier = Trame.getFichier(date, horaire);
		this.temperature = recupererTemperature(this.nomFichier);
		this.humidite = recupererHumidite(this.nomFichier);
		this.directionVent = recupererDirectionVent(this.nomFichier);
		this.vitesseVent = recupererVitesseVent(this.nomFichier);
		this.tauxEnsoleillement = recupererEnsoleillement(this.nomFichier);
		this.ressenti = recupererRessenti(this.nomFichier);
		this.directionVentString = convertirDirection(this.directionVent); 
	}
	
	/**
	 * Constructeur qui va initialiser un objet Donnees sur l'horaire et la date passés en arguments
	 * @param date
	 * @param horaire
	 */
	public Donnees(Date date) {
		this.nomFichier = Trame.getFichier(date);
		this.temperature = recupererMoyenneTemperature(this.nomFichier);
		this.humidite = recupererHumidite(this.nomFichier);
		this.directionVent = recupererDirectionVent(this.nomFichier);
		this.vitesseVent = recupererMoyenneVitesseVent(this.nomFichier);
		this.tauxEnsoleillement = recupererEnsoleillement(this.nomFichier);
		this.ressenti = recupererRessenti(this.nomFichier);
		this.directionVentString = convertirDirection(this.directionVent); 
	}
	
	/**
	 * Récupère la valeur correspondant à la moyenne de la temperature sur une journée dans un fichier
	 * @param nomFichier
	 * @return la temperature sous forme d'int, ou -9999 si la température n'est pas trouvée
	 */
	private static int recupererMoyenneTemperature(String nomFichier) {
		String ligne;
		int temperature = -9999;
		int tempMax = -9999;
		int tempMin = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("tempmax")) {
		    		tempMax = (int) Double.parseDouble(ligne.split(";")[1]);
		    	}
		    	if(ligne.contains("tempmin")) {
		    		tempMin = (int) Double.parseDouble(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}
		temperature = (tempMin + tempMax) / 2;
		return temperature;
	}
	
	/**
	 * Récupère la valeur correspondant à la moyenne de la vitesse du vent sur une journée dans un fichier
	 * @param nomFichier
	 * @return la vitesse du vent sous forme d'int, ou -9999 si la vitesse du vent n'est pas trouvée
	 */
	private static int recupererMoyenneVitesseVent(String nomFichier) {
		String ligne;
		int vitesse = -9999;
		int vitesseMax = -9999;
		int vitesseMin = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("ventvitessemax")) {
		    		vitesseMax = Integer.parseInt(ligne.split(";")[1]);
		    	}
		    	if(ligne.contains("ventvitessemin")) {
		    		vitesseMin = Integer.parseInt(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}
		vitesse = (vitesseMin + vitesseMax) / 2;
		return vitesse;
	}
	
	

	/**
	 * Retourne les températures de la journée
	 * @return tableau d'entiers contenant les temperatures de la journée, i correspondant à l'heure correspondante
	 */
	public static int[] getTemperatureJournee() {

		
		Calendar cal = Calendar.getInstance();
		int jourCourant = cal.get(Calendar.DAY_OF_MONTH);
		int moisCourant = cal.get(Calendar.MONTH)+1;
		int anneeCourante = cal.get(Calendar.YEAR);
		int heureCourante = cal.get(Calendar.HOUR_OF_DAY);
		int[] temperature = new int[heureCourante+1];
		for(int i = heureCourante; i >= 0; i--) {
			Horaire horaire = new Horaire(heureCourante, 0);
			Date date = new Date(jourCourant,moisCourant, anneeCourante);
			Donnees donnees = new Donnees(date, horaire);
			temperature[i] = donnees.getTemperature();
			heureCourante--;
			
		}
		return temperature;
	}
	
	/**
	 * Retourne l'humidité de la journée
	 * @return tableau d'entiers contenant l'humidité de la journée, i correspondant à l'heure correspondante
	 */
	public static int[] getHumiditeJournee() {

		
		Calendar cal = Calendar.getInstance();
		int jourCourant = cal.get(Calendar.DAY_OF_MONTH);
		int moisCourant = cal.get(Calendar.MONTH)+1;
		int anneeCourante = cal.get(Calendar.YEAR);
		int heureCourante = cal.get(Calendar.HOUR_OF_DAY);
		int[] humidite = new int[heureCourante+1];
		for(int i = heureCourante; i >= 0; i--) {
			Horaire horaire = new Horaire(heureCourante, 0);
			Date date = new Date(jourCourant,moisCourant, anneeCourante);
			Donnees donnees = new Donnees(date, horaire);
			humidite[i] = donnees.getHumidite();
			heureCourante--;
			
		}
		return humidite;
	}
	
	/**
	 * Retourne l'ensoleillement de la journée
	 * @return tableau d'entiers contenant l'ensoleillement de la journée, i correspondant à l'heure correspondante
	 */
	public static int[] getEnsoleillementJournee() {

		
		Calendar cal = Calendar.getInstance();
		int jourCourant = cal.get(Calendar.DAY_OF_MONTH);
		int moisCourant = cal.get(Calendar.MONTH)+1;
		int anneeCourante = cal.get(Calendar.YEAR);
		int heureCourante = cal.get(Calendar.HOUR_OF_DAY);
		int[] ensoleillement = new int[heureCourante+1];
		for(int i = heureCourante; i >= 0; i--) {
			Horaire horaire = new Horaire(heureCourante, 0);
			Date date = new Date(jourCourant,moisCourant, anneeCourante);
			Donnees donnees = new Donnees(date, horaire);
			ensoleillement[i] = donnees.getTauxEnsoleillement();
			heureCourante--;
			
		}
		return ensoleillement;
	}
	
	/**
	 * Retourne la vitesse du vent de la journée
	 * @return tableau d'entiers contenant la vitesse du vent de la journée, i correspondant à l'heure correspondante
	 */
	public static int[] getVitesseVentJournee() {

		
		Calendar cal = Calendar.getInstance();
		int jourCourant = cal.get(Calendar.DAY_OF_MONTH);
		int moisCourant = cal.get(Calendar.MONTH)+1;
		int anneeCourante = cal.get(Calendar.YEAR);
		int heureCourante = cal.get(Calendar.HOUR_OF_DAY);
		int[] vitesse = new int[heureCourante+1];
		for(int i = heureCourante; i >= 0; i--) {
			Horaire horaire = new Horaire(heureCourante, 0);
			Date date = new Date(jourCourant,moisCourant, anneeCourante);
			Donnees donnees = new Donnees(date, horaire);
			vitesse[i] = donnees.getVitesseVent();
			heureCourante--;
			
		}
		return vitesse;
	}
	
	/**
	 *  Récupère la valeur correspondant à la temperature dans un fichier
	 * @param nomFichier
	 * @return la temperature sous forme d'int, ou -9999 si la température n'est pas trouvée
	 */
	private static int recupererTemperature(String nomFichier) {
		String ligne;
		int temperature = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("temperature")) {
		    		temperature = (int ) Double.parseDouble(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}   
		return temperature;
	}
	
	/**
	 * Récupère la valeur correspondant à l'humidité dans un fichier
	 * @param nomFichier
	 * @return l'humidité sous forme d'int, ou -9999 si l'humidité n'est pas trouvée
	 */
	private static int recupererHumidite(String nomFichier) {
		String ligne;
		int humidite = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("humidite")) {
		    		humidite = (int) Double.parseDouble(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}   
		return humidite;
	}
	
	/**
	 *  Récupère la valeur correspondant à la vitesse du vent dans un fichier
	 * @param nomFichier
	 * @return la vitesse du vent sous forme d'int, ou -9999 si la vitesse du vent n'est pas trouvée
	 */
	private static int recupererVitesseVent(String nomFichier) {
		String ligne;
		int vitesseVent = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("ventvitesse")) {
		    		vitesseVent = Integer.parseInt(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}   
		return vitesseVent;
	}
	
	/**
	 * Récupère la valeur correspondant à la direction du vent dans un fichier
	 * @param nomFichier
	 * @return la temperature sous forme d'int de 0 à 360, ou -9999 si la direction du vent n'est pas trouvée
	 */
	private static int recupererDirectionVent(String nomFichier) {
		String ligne;
		int directionVent = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("ventdirection")) {
		    		directionVent = Integer.parseInt(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}   
		return directionVent;
	}
	
	/**
	 * Récupère la valeur correspondant à la luminosité dans un fichier
	 * @param nomFichier
	 * @return la luminosité sous forme d'int, ou -9999 si la luminosité n'est pas trouvée
	 */
	private static int recupererEnsoleillement(String nomFichier) {
		String ligne;
		int ensoleillement = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("ensoleillement")) {
		    		ensoleillement = (int) Double.parseDouble(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}   
		return ensoleillement;
	}
	
	/**
	 * Récupère la valeur correspondant au ressenti dans un fichier
	 * @param nomFichier
	 * @return le ressenti sous forme d'int, ou -9999 si le ressenti n'est pas trouvée
	 */
	private static int recupererRessenti(String nomFichier) {
		String ligne;
		int ressenti = -9999;
		try {
		    BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
		    
		    while ((ligne = fichier.readLine()) != null){
		    	if(ligne.contains("ressenti")) {
		    		ressenti = Integer.parseInt(ligne.split(";")[1]);
		    	}
		    }
		    
		    fichier.close();
		} catch (IOException ex) {
		    System.out.println("Problème accès fichier");
		}   
		return ressenti;
	}
	
	/**
	 * Convertit la direction du vent en int en String
	 * @param directionVent
	 * @return La direction sous la forme d'un string sinon "erreur"
	 */
	private static String convertirDirection(int directionVent) {
		String directionVentString = "Pas de données  ";
		
		if((directionVent <= 360 && directionVent > 315) || (directionVent >= 0 && directionVent <= 45)) {
			directionVentString = "Nord";
		}
		if(directionVent > 45 && directionVent <= 135) {
			directionVentString = "Est";
		}
		if(directionVent > 135 && directionVent <= 225) {
			directionVentString = "Sud";
		}
		if(directionVent > 225 && directionVent <= 315) {
			directionVentString = "Ouest";
		}
		return directionVentString;
		
	}
	
	/**
	 * Getter pour le ressenti
	 * @return le ressenti
	 */
	public int getRessenti() {
		return ressenti;
	}

	/**
	 * Getter pour l'humidité
	 * @return l'humidité
	 */
	public int getHumidite() {
		return humidite;
	}

	/**
	 * Getter pour la vitesse du vent
	 * @return la vitesse du vent
	 */
	public int getVitesseVent() {
		return vitesseVent;
	}

	/**
	 * Getter pour la direction du vent
	 * @return la direction du vent
	 */
	public int getDirectionVent() {
		return directionVent;
	}

	/**
	 * Getter pour la direction du vent en String
	 * @return la direction du vent en String
	 */
	public String getDirectionVentString() {
		return directionVentString;
	}

	/**
	 * Getter pour le taux d'ensoleillement
	 * @return l'ensoleillement
	 */
	public int getTauxEnsoleillement() {
		return tauxEnsoleillement;
	}

	/**
	 * Getter pour la température
	 * @return la température
	 */
	public int getTemperature() {
		return temperature;
		
	}
}
