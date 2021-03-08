package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe générique d'initialisation de la fenêtre permettant d'ajouter des
 * auteurs.
 * 
 * @author Desebel MESNAGER
 *
 */
public class MainAuteur {
	/**
	 * {@link Stage} La fenêtre principale.
	 */

	private Stage primaryStage;

	/**
	 * Constructeur pour la création d'une nouvelle vue pour ajouter un Auteur.
	 * 
	 * @param stage La {@link Stage} principale de l'application.
	 */

	public MainAuteur(Stage stage) {
		primaryStage = stage;
	}

	/**
	 * Initialise les composants pour la création de la fenêtre et l'affiche.
	 */

	public void montrerLaVue() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/VueAuteur.fxml"));
			GridPane page = (GridPane) loader.load();
			/**
			 * Création du formulaire comme fenêtre modale.
			 */
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajout d'un auteur");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			/**
			 * La méthode suivante montre le formulaire puis attend que l'utilisateur le
			 * ferme. Fermeture effectuée dans le contrôleur du formulaire lorsque
			 * l'utilisateur clique sur un des 2 boutons.
			 */
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
