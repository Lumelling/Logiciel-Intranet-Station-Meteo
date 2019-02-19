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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe controller pour la fenêtre détails direction vent
 * @author Thibault
 *
 */
public class DetailsDirectionVentController implements Initializable{


	
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
	Label lbl_direction;
	
	@FXML
	Label lbl_erreur;

	@FXML
	TextField box_minute;

	
	
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

			lbl_direction.setText(consultationRecherche.getDirectionVentString());
			lbl_erreur.setVisible(false);
		} catch (NumberFormatException | ErreurDateInvalide | ErreurHoraireInvalide erreur) {

			lbl_erreur.setVisible(true);

		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		btn_retour.setOnAction(this::retourConsultation);
		btn_recherche.setOnAction(this::recherche);
		
	}
	
}
