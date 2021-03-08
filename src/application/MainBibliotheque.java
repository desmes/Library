package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vue.ControleurBibliotheque;

/**
 * Classe principale : point d'entrée de l'application
 * 
 * @author Desebel MESNAGER
 *
 */
public class MainBibliotheque extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/MaBibliotheque.fxml"));
			// Création de la racine - decors-

			GridPane root = (GridPane) loader.load();
			// Création de la scène, association du décors et de la scène
			Scene scene = new Scene(root);
			// Titre de la fenêtre
			primaryStage.setTitle("Ma bibliotheque");
			// On place la scène dans la fenêtre
			primaryStage.setScene(scene);
			// On affiche la fenêtre
			primaryStage.show();
			// Insertion d'une image à gauche dans la barre de titre de la fenêtre
			primaryStage.getIcons().add(new Image("/images/logoB.png"));
			/*
			 * frame.setIconImage( new
			 * ImageIcon(getClass().getClassLoader().getResource("PATH/TO/YourImage.png"))
			 * );
			 */

			/**
			 * Affiche la formulaire pour ajouter un auteur
			 */
			MainAuteur mainauteur = new MainAuteur(primaryStage);
			ControleurBibliotheque controleur = loader.getController();
			controleur.setMainAuteur(mainauteur);
			/**
			 * Affiche la fenêtre pour genérer du QRCode
			 */
			MainQRCode mainQrcode = new MainQRCode(primaryStage);
			ControleurBibliotheque controler = loader.getController();
			controler.setMainQRCode(mainQrcode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
