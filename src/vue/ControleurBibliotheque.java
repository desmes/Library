package vue;

import java.util.List;
import java.util.Optional;

import application.DaoBibliotheque;
import application.MainAuteur;
import application.MainQRCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modele.Auteur;
import modele.Livre;

/**
 * Il s'agit de la fenêtre principale de l'application qui va regrouper toutes
 * les vues.
 * 
 * @author Desebel MESNAGER
 *
 */
public class ControleurBibliotheque {
	private static final Stage Stage = null;
	private Livre livre;
	private DaoBibliotheque daoBibliotheque;
	private Stage primaryStage;
	private MainAuteur mainauteur;
	public MainQRCode mainQrcode;

	/**
	 * Le contrôleur crée l'objet dao.
	 */
	public ControleurBibliotheque() {
		daoBibliotheque = new DaoBibliotheque();
		mainQrcode = new MainQRCode(Stage);
		livre = new Livre();
	}

	/**
	 * La référence de {@link isbn} sera injectée par le chargeur FXML.
	 */
	@FXML
	private TextField isbn;

	/**
	 * La référence de {@link titre} sera injectée par le chargeur FXML.
	 */
	@FXML
	private TextField titre;

	/**
	 * La référence de {@link editeur} sera injectée par le chargeur FXML.
	 */
	@FXML
	private TextField editeur;

	/**
	 * La référence de {@link annee} sera injectée par le chargeur FXML.
	 */
	@FXML
	private TextField annee;

	/**
	 * La référence de {@link auteursComboBox} affichera les auteurs enregistrés
	 * dans la base de données.
	 */
	@FXML
	private ComboBox<Auteur> auteursComboBox;

	/**
	 * La référence de {@link auteurs} affichera tous auteurs enregistrés dans la
	 * base de données.
	 */
	@FXML
	private ListView<Auteur> auteurs;

	/**
	 * Créer une liste avec tous les auteurs.
	 */
	private ObservableList<Auteur> listeAuteurs = FXCollections.observableArrayList();

	/**
	 * La référence de {@link livres} affichera tous livres enregistrés dans la base
	 * de données.
	 */
	@FXML
	private ListView<Livre> livres;

	/**
	 * Créer une liste avec tous les livres.
	 */
	private ObservableList<Livre> listeLivres = FXCollections.observableArrayList();

	/**
	 * La vue de tous auteurs enregistrés dans la base de données
	 */
	@FXML
	private void voirTousLesAuteurs() {
		listeAuteurs.clear();
		List<Auteur> lesAuteurs = daoBibliotheque.voirLesAuteurs();
		for (Auteur a : lesAuteurs)
			listeAuteurs.add(a);
	}

	/**
	 * Permet de voir tous les livres d'un auteur lorsqu'on sélectionne un auteur
	 * dans {@link ListView.auteurs}
	 * 
	 * @param auteur List des auteurs.
	 */

	@FXML
	private void voirTousLesLivresAuteur(Auteur auteur) {
		listeLivres.clear();
		Auteur selectAuteur = (auteurs.getSelectionModel().getSelectedItem());
		if (selectAuteur != null) {
			List<Livre> lesLivres = daoBibliotheque.voirLesLivresAuteur(auteur);
			for (Livre livre : lesLivres) {
				listeLivres.add(livre);

			}
		}
	}

	/**
	 * Appelé pour initialiser un contrôleur une fois que son élément racine a été
	 * complètement traité.
	 */
	@FXML
	private void initialize() {
		auteurs.setItems(listeAuteurs);
		voirTousLesAuteurs();
		auteursComboBox.setItems(listeAuteurs);
		auteursComboBox.setPromptText("Auteur");
		livres.setItems(listeLivres);
		auteurs.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Auteur auteur = auteurs.getSelectionModel().getSelectedItem();
				voirTousLesLivresAuteur(auteur);
			}
		});

	}

	/**
	 * Le {@code supprimerLivre} permet de supprimer des livres dans la bases de
	 * données.
	 */
	@FXML
	private void supprimerLivre() {
		Livre select = livres.getSelectionModel().getSelectedItem();
		if (select != null) {
			daoBibliotheque.supprimer(select);
			voirTousLesAuteurs();
		}
		/**
		 * La fenêtre qui affiche une notification avant la supression un livre.
		 */
		try {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Attention, suppression de livre");
			alert.setHeaderText("Voulez-vous vraiment supprimer?");
			alert.setContentText("Ce livre ne pourra plus visible dans la liste!");
			ButtonType ok = new ButtonType("OK");
			ButtonType annule = new ButtonType("ANNULER");
			alert.getButtonTypes().setAll(ok, annule);
			Optional<ButtonType> result = alert.showAndWait();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param mainQrcode
	 */
	public void setMainQRCode(MainQRCode mainQrcode) {
		this.mainQrcode = mainQrcode;

	}

	/**
	 * La methode {@link selectionDuLivre} verifier la selectionne d'un livre pour
	 * la creation de son prope qrcode.
	 * 
	 * @return La fenêtre de notification s'affiche si false ou la fenêtre principal
	 *         {@link mainQrcode} s'affiche si true.
	 */
	private boolean selectionDuLivre() {
		if (livres.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Veuillez selectionner un livre pour creer un Qrcode");
			alert.showAndWait();
			return false;
		} else
			return true;
	}

	/**
	 * Lorsque la selection du livre est fait, en cliquant le button "Créer" la
	 * fenêtre principal {@link mainQrcode} s'affiche.
	 */
	@FXML
	private void creerQRCode() {
		if (selectionDuLivre() == true) {
			Livre livre = livres.getSelectionModel().getSelectedItem();
			if (livre != null)
				mainQrcode.montrerLaVueQR(livre);
		}
	}

	public void setMainAuteur(MainAuteur mainauteur) {
		this.mainauteur = mainauteur;

	}

	/**
	 * Lors qu'on clic le button "Ajouter" , le formulaire ajouter un auteur
	 * s'affiche.
	 */
	@FXML
	public void ajouterUnAuteur() {
		mainauteur.montrerLaVue();
	}

	/**
	 * La methode qui permet de verifier s'il y a bien 13 caractéres.
	 * 
	 * @return Si false la fenêtre alert s'affiche et ajouterUnLivre si true.
	 */
	private boolean tailleISBN() {
		String Isbn = isbn.getText();
		if (Isbn.length() < 13 || Isbn.length() > 13) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ISBN Invalide");
			alert.setHeaderText("Veuillez entrer 13 caractéres");
			alert.showAndWait();
			return false;
		} else
			return true;
	}

	/**
	 * La methode {@code ajouterUnLivre} permet d'ajouter des livres dans la bases
	 * de données et d'activer le bouton "Ajouter" aprés avoir rempli le formulaire.
	 *
	 */
	@FXML
	private void ajouterUnLivre() {
		if (tailleISBN() == true) {
			String Isbn = isbn.getText();
			String Titre = titre.getText();
			String Editeur = editeur.getText();
			short Annee = Short.parseShort(annee.getText());
			Auteur Auteur = auteursComboBox.getSelectionModel().getSelectedItem();

			Livre l = new Livre(Isbn, Titre, Editeur, Annee, Auteur);
			daoBibliotheque.stockerLivre(l);
			isbn.setText("");
			titre.setText("");
			editeur.setText("");
			annee.setText("");
			auteursComboBox.setItems(null);
		}
	}

}
