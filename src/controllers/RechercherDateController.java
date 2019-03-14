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
	Label lbl_co2;
	
	@FXML
	Label lbl_pression;
	
	@FXML
	Label lbl_pluie;
	
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
			Horaire horaire = null;
			Donnees consultationRecherche;
			
			if(box_minute.getText() != null && !(box_minute.getText().isEmpty())) {
				horaire = new Horaire(Integer.parseInt(box_heure.getText()), Integer.parseInt(box_minute.getText()));
				
			} else if(box_heure.getText() != null && !(box_heure.getText().isEmpty())) {

				horaire = new Horaire(Integer.parseInt(box_heure.getText()), 0);
			}

			date.estValide();
			if(horaire != null) {
				horaire.estValide();
				consultationRecherche = new Donnees(date, horaire);
			} else {
				consultationRecherche = new Donnees(date);
			}
			
			if (consultationRecherche.getTemperature() == -9999) {
				lbl_temperature.setText("Pas de données  ");
			} else {
				lbl_temperature.setText(consultationRecherche.getTemperature() + "°C");
			}
			if (consultationRecherche.getHumidite() == -9999) {
				lbl_humidite.setText("Pas de données  ");
			} else {
				lbl_humidite.setText(consultationRecherche.getHumidite() + "%");
			}
			if (consultationRecherche.getVitesseVent() == -9999) {
				lbl_vitessevent.setText("Pas de données  ");
			} else {
				lbl_vitessevent.setText(consultationRecherche.getVitesseVent() + "km/h");
			}
			if (consultationRecherche.getCO2() == -9999) {
				lbl_co2.setText("Pas de données  ");
			} else {
				lbl_co2.setText(consultationRecherche.getCO2() + " ppm");
			}
			if (consultationRecherche.getPression() == -9999) {
				lbl_pression.setText("Pas de données  ");
			} else {
				lbl_pression.setText(consultationRecherche.getPression() + " hPA");
			}
			lbl_pluie.setText(consultationRecherche.getPluieString());
			lbl_directionvent.setText(consultationRecherche.getDirectionVentString());
			
			if (consultationRecherche.getTauxEnsoleillement() == -9999) {
				lbl_ensoleillement.setText("Pas de données  ");
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
		lbl_pluie.setText(consultation.getPluieString());
		lbl_co2.setText(consultation.getCO2() +" ppm");
		lbl_pression.setText(consultation.getPression() +" hPa");
		btn_retour.setOnAction(this::retourAccueil);
		btn_recherche.setOnAction(this::recherche);
	}
	
}
