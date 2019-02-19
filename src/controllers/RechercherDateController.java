package controllers;


import java.net.URL;
import java.util.ResourceBundle;
import application.Date;
import application.Date.ErreurDateInvalide;
import application.Horaire;
import application.Horaire.ErreurHoraireInvalide;
import application.Donnees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe controller pour la fenêtre recherche données pour une date précise
 * @author Thibault
 *
 */
public class RechercherDateController implements Initializable{

	@FXML
	Label lbl_temperature;
	
	@FXML
	Label lbl_humidite;
	
	@FXML
	Label lbl_directionvent;
	
	@FXML
	Label lbl_vitessevent;
	
	@FXML
	Label lbl_ensoleillement;
	
	@FXML
	Button btn_retour;
	
	@FXML
	TextField box_heure;
	
	@FXML
	TextField box_jour;
	
	@FXML
	TextField box_mois;
	
	@FXML
	TextField box_annee;

	@FXML
	TextField box_minute;
	
	@FXML
	Button btn_recherche;
	
	@FXML
	Label lbl_erreur;
	
	
	private void retourAccueil(ActionEvent event) {
			(((Node) event.getSource())).getScene().getWindow().hide();

	}
	
	private void recherche(ActionEvent event) {
		
		try {
			Date date = new Date(Integer.parseInt(box_jour.getText()), Integer.parseInt(box_mois.getText()), Integer.parseInt(box_annee.getText()));
			Horaire horaire = new Horaire(Integer.parseInt(box_heure.getText()), Integer.parseInt(box_minute.getText()));
			date.estValide();
			horaire.estValide();
			Donnees consultationRecherche = new Donnees(date, horaire);
			if (consultationRecherche.getTemperature() == -9999) {
				lbl_temperature.setText("Erreur");
			} else {
				lbl_temperature.setText(consultationRecherche.getTemperature() + "°C");
			}
			if (consultationRecherche.getHumidite() == -9999) {
				lbl_humidite.setText("Erreur");
			} else {
				lbl_humidite.setText(consultationRecherche.getHumidite() + "%");
			}
			if (consultationRecherche.getVitesseVent() == -9999) {
				lbl_vitessevent.setText("Erreur");
			} else {
				lbl_vitessevent.setText(consultationRecherche.getVitesseVent() + "km/h");
			}
			lbl_directionvent.setText(consultationRecherche.getDirectionVentString() +"");
			
			if (consultationRecherche.getTauxEnsoleillement() == -9999) {
				lbl_ensoleillement.setText("Erreur");
			} else {
				lbl_ensoleillement.setText(consultationRecherche.getTauxEnsoleillement() +"%");
			}
			
			lbl_erreur.setVisible(false);
			
			
		} catch (NumberFormatException | ErreurDateInvalide | ErreurHoraireInvalide erreur) {
			
			lbl_erreur.setVisible(true);
			
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Donnees consultation = new Donnees();
		lbl_temperature.setText(consultation.getTemperature() + "°C");
		lbl_humidite.setText(consultation.getHumidite() + "%");
		lbl_vitessevent.setText(consultation.getVitesseVent() + "km/h");
		lbl_directionvent.setText(consultation.getDirectionVentString() +"");
		lbl_ensoleillement.setText(consultation.getTauxEnsoleillement() +"%");
		btn_retour.setOnAction(this::retourAccueil);
		btn_recherche.setOnAction(this::recherche);
	}
	
}
