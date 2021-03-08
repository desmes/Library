package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Livre;
import vue.ControleurQRCode;

/**
 * Classe générique d'initialisation de la fenêtre permettant de générer des
 * QRCode.
 * 
 * @author Desebel
 *
 */
public class MainQRCode {
	/**
	 * {@link Stage} La fenêtre principale.
	 */

	private Stage primaryStage;

	/**
	 * Constructeur pour la création d'une nouvelle vue pour créer du qrcode.
	 * 
	 * @param stage La {@link Stage} principale de l'application.
	 */

	public MainQRCode(Stage stage) {
		primaryStage = stage;
	}

	/**
	 * Initialise les composants pour la création de la fenêtre et l'affiche.
	 */

	public void montrerLaVueQR(Livre livre) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/QRCodeVue.fxml"));
			GridPane page = (GridPane) loader.load();
			ControleurQRCode controler = loader.getController();
			controler.choixLivre(livre);
			// Création du formulaire comme fenêtre modale.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Génèrer du QRCode");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
