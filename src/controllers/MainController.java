package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Donnees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe controller pour la fenêtre d'accueil
 * @author Thibault
 *
 */
public class MainController implements Initializable{

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
	MenuItem btn_recherche;
	
	@FXML
	MenuItem btn_detailsTemperature;
	
	@FXML
	MenuItem btn_detailsVitesseVent;
	
	@FXML
	MenuItem btn_detailsDirectionVent;
	
	@FXML
	MenuItem btn_detailsHumidite;
	
	@FXML
	MenuItem btn_detailsLuminosite;
	
	@FXML
	MenuItem btn_detailsPA;
	
	@FXML
	MenuItem btn_detailsPluie;
	
	@FXML
	MenuItem btn_detailsCO2;
	
	
	
	private void ouvrirRecherche(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/RechercheDate.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,472);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void detailsTemperature(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsTemperature.fxml"));
			
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void detailsCO2(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsCO2.fxml"));
			
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void detailsPluie(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsPluie.fxml"));
			
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void detailsPression(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsPression.fxml"));
			
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void detailsHumidite(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsHumidite.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void detailsVitesseVent(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsVitesseVent.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void detailsDirectionVent(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsDirectionVent.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void detailsEnsoleillement(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/DetailsLuminosite.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root,600,400);
			stage.setTitle("Logiciel Météo intranet MJC Rodez");
			stage.getIcons().add(new Image("file:icone.png"));
			stage.setResizable(false);
			stage.sizeToScene();
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.show();
			
	
		} catch (IOException e) {
			e.printStackTrace();
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
		btn_recherche.setOnAction(this::ouvrirRecherche);
		btn_detailsTemperature.setOnAction(this::detailsTemperature);
		btn_detailsHumidite.setOnAction(this::detailsHumidite);
		btn_detailsLuminosite.setOnAction(this::detailsEnsoleillement);
		btn_detailsDirectionVent.setOnAction(this::detailsDirectionVent);
		btn_detailsVitesseVent.setOnAction(this::detailsVitesseVent); 
		btn_detailsPluie.setOnAction(this::detailsPluie);
		btn_detailsCO2.setOnAction(this::detailsCO2);
		btn_detailsPA.setOnAction(this::detailsPression);
	}
}
	

