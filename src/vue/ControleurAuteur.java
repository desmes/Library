package vue;

import application.DaoBibliotheque;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Auteur;

/**
 * La classe {@code ControleurAuteur} est la vue d'un formulaire auteur.
 * 
 * @author Desebel MESNAGER
 *
 */
public class ControleurAuteur {
	private DaoBibliotheque daoBibliotheque;

	public ControleurAuteur() {
		/**
		 * Le contrôleur crée l'objet dao
		 */
		daoBibliotheque = new DaoBibliotheque();
	}

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField nationalite;

	@FXML
	private Button annuler;

	@FXML
	private Button ok;

	/**
	 * Il permet d'ajouter un auteur dans la base de données. La boite de dialogue
	 * se ferme lors qu'on clic "OK".
	 */
	@FXML
	private void okFormulaire() {
		String Nom = nom.getText();
		String Prenom = prenom.getText();
		String Nationalite = nationalite.getText();

		Auteur a = new Auteur(Nom, Prenom, Nationalite);
		daoBibliotheque.ajoutUnAuteur(a);
		nom.setText("");
		prenom.setText("");
		nationalite.setText("");

		Stage dialogStage = (Stage) ok.getScene().getWindow();
		dialogStage.close();
	}

	/**
	 * Lors qu'on clic le button "Annuler", il annule l'enregistrement d'un auteur
	 * et la boite de dialoque se ferme.
	 */
	@FXML
	private void annulerFormulaire() {
		nom.setText("");
		prenom.setText("");
		nationalite.setText("");
		Stage dialogStage = (Stage) annuler.getScene().getWindow();
		dialogStage.close();
	}

}
