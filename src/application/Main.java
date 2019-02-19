package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * classe main s'occupant du lancement de la fen�tre initiale
 * @author Thibault
 *
 */
public class Main extends Application {
	
	/**
	 * M�thode initialisant la fen�tre avec les diff�rents param�tres
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../interfaces/Main.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("../interfaces/application.css").toExternalForm());
			primaryStage.setTitle("Logiciel M�t�o intranet MJC Rodez");
			primaryStage.getIcons().add(new Image("file:logo_mjc.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Supprime tout les fichiers contenus dans le dossier pass� en argument
	 * @param emplacement du dossier contenant les fichiers � supprimer
	 */
	private static void nettoyage(String emplacement){
	  File path = new File(emplacement);
	  if( path.exists() )
	  {
	    File[] files = path.listFiles();
	    for( int i = 0 ; i < files.length ; i++ )
	    {
	      if( files[ i ].isDirectory() )
	      {
	        nettoyage( path+"\\"+files[ i ] );
	      }
	      files[ i ].delete();
	    }
	  }
	}

/**
 * lancement de l'application	
 * @param args
 */
	public static void main(String[] args) {
		nettoyage("releves");
		launch(args);
	}
}
