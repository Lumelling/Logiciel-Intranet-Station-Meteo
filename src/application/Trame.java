package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/** 
 * Classe servant � t�l�charger les fichiers contenant les donn�es m�t�orologiques
 */
public class Trame {

	/**
	 * T�l�charge le fichier pour une date et une horaire donn�e
	 * @param date
	 * @param horaire
	 * @return le nom du fichier obtenu
	 */
	public static String getFichier(Date date, Horaire horaire) {
		String nomFichier = getNomFichier(date,horaire);
		telechargerFichier(nomFichier, date, horaire);
		return "releves/" + nomFichier;
	}
	
	/**
	 * T�l�charge le fichier pour une date donn�e
	 * @param date
	 * @param horaire
	 * @return le nom du fichier obtenu
	 */
	public static String getFichier(Date date) {
		String nomFichier = getNomFichier(date);
		telechargerFichier(nomFichier, date);
		return "releves/" + nomFichier;
	}
	

	/**
	 * T�l�charge le fichier contenant les donn�es les plus r�centes
	 * @param date
	 * @param horaire
	 * @return le nom du fichier obtenu
	 */
	public static String getFichier() {
		telechargerFichier();
		return "releves/Releve.txt";
	}
	
	/**
	 * Cr�e le nom du fichier en fonction d'une date et d'une horaire
	 * @param date
	 * @param horaire
	 * @return
	 */
	private static String getNomFichier(Date date, Horaire horaire) { 
		String dateString = date.toString();
		String horaireString = horaire.toString();
		return "Releve" + dateString + "__" + horaireString + ".txt";
	}
	
	/**
	 * Cr�e le nom du fichier en fonction d'une date et d'une horaire
	 * @param date
	 * @param horaire
	 * @return
	 */
	private static String getNomFichier(Date date) { 
		String dateString = date.toString();
		return "Releve" + dateString + ".txt";
	}
	
	/**
	 * M�thode qui contient le t�l�chargement du fichier gr�ce � l'url 
	 */
	private static void telechargerFichier() {
		
		URL website;
		try {
			website = new URL("http://www.meteo.mjcrodez.fr/pages/getInfoFic.php");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("releves/Releve.txt"); 
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�thode qui contient le t�l�chargement du fichier gr�ce � l'url pour une date donn�e
	 * @param date 
	 * @param horaire
	 */
	private static void telechargerFichier(String nomFichier, Date date) {
	
		URL website;
		try {
			website = new URL("http://www.meteo.mjcrodez.fr/pages/getInfoFic.php?date=" + date.toString());//?date=01-12-2018
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("releves/" + nomFichier); 
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�thode qui contient le t�l�chargement du fichier gr�ce � l'url pour une date et une horaire donn�e
	 * @param nomFichier le nom � donner au fichier
	 * @param date 
	 * @param horaire
	 */
	private static void telechargerFichier(String nomFichier, Date date, Horaire horaire) {
	
		URL website;
		try {
			website = new URL("http://www.meteo.mjcrodez.fr/pages/getInfoFic.php?date=" + date.toString() + "&heure=" + horaire.toString());//?date=2018-12-01&heure=10:00:00
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream("releves/" + nomFichier); 
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
