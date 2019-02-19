package controllers;



import java.net.URL;
import java.util.ResourceBundle;

import application.Date;
import application.Donnees;
import application.Horaire;
import application.Date.ErreurDateInvalide;
import application.Horaire.ErreurHoraireInvalide;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Classe controller pour la fenêtre détails humidité
 * @author Thibault
 *
 */
public class DetailsHumiditeController implements Initializable{


	
	@FXML
	Button btn_retour;
	
	@FXML
	Button btn_recherche;
	
	@FXML
	TextField box_jour;

	@FXML
	TextField box_heure;
	
	@FXML
	TextField box_mois;
	
	@FXML
	TextField box_annee;
	
	@FXML
	Label lbl_humidite;
	
	@FXML
	Label lbl_erreur;

	@FXML
	TextField box_minute;

	@FXML
	LineChart<Number, Number> graphique;
	
	
	private void retourConsultation(ActionEvent event) {
			
		((Node)(event.getSource())).getScene().getWindow().hide();

	}
	
	private void recherche(ActionEvent event) {
		
		try {
			Date date = new Date(Integer.parseInt(box_jour.getText()), Integer.parseInt(box_mois.getText()), Integer.parseInt(box_annee.getText()));
			Horaire horaire = new Horaire(Integer.parseInt(box_heure.getText()), Integer.parseInt(box_minute.getText()));
			date.estValide();
			horaire.estValide();
			Donnees consultationRecherche = new Donnees(date, horaire);

			if (consultationRecherche.getHumidite() == -9999) {
				lbl_humidite.setText("Erreur");
			} else {
				lbl_humidite.setText(consultationRecherche.getHumidite() + "°C");
			}
			lbl_erreur.setVisible(false);
		} catch (NumberFormatException | ErreurDateInvalide | ErreurHoraireInvalide erreur) {

			lbl_erreur.setVisible(true);

		}
	}
	
	public void afficheGraphique() {

		XYChart.Series<Number,Number> series = new XYChart.Series<>();
		series.setName("Humidite");

		int[] humidite = Donnees.getHumiditeJournee();
		for(int i = 0;i < humidite.length;i++) {
			series.getData().add(new XYChart.Data<>(i,humidite[i]));
		}
		graphique.getData().add(series);

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		afficheGraphique();
		btn_retour.setOnAction(this::retourConsultation);
		btn_recherche.setOnAction(this::recherche);
		
	}
	
}
