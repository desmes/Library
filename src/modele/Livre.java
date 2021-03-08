package modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * La classe {@code Livre} est le modèle de persistence d'un livre. <br>
 * The persistent class for the livre database table.
 * 
 * @author Desebel MESNAGER
 */
@Entity // Entity implementation class for Entity: Livre
@NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l")
public class Livre implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identifiant unique du livre généré automatiquement
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;

	private short annee;

	private String editeur;

	private String isbn;

	private String titre;
	// bi-directional many-to-one association to Auteur
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numAuteur")
	private Auteur auteur;

	public Livre() {
	}

	/**
	 * Création d'un Livre
	 * 
	 * @param isbn    Le numéro international normalisé du livre.
	 * @param titre   Le titre du livre.
	 * @param editeur L'editeur du livre.
	 * @param annee   L'année de publication du livre.
	 * @param auteur  L'auteur du livre.
	 */
	public Livre(String isbn, String titre, String editeur, short annee, Auteur auteur) {

		this.isbn = isbn;
		this.titre = titre;
		this.editeur = editeur;
		this.annee = annee;
		this.auteur = auteur;

	}

	/**
	 * Retourne l'identifiant unique du livre.
	 * 
	 * @return l'identifiant du livre.
	 */

	public int getNum() {
		return this.num;
	}

	/**
	 * Modifie l'identifiant unique du livre.
	 * 
	 * @param num Nouvel identifiant pour ce livre.
	 */

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * Retourne l'année de publication du livre.
	 * 
	 * @return L'année de publication du livre.
	 */

	public short getAnnee() {
		return this.annee;
	}

	/**
	 * Modifie l'année de publication du livre.
	 * 
	 * @param annee L'année de publication du livre.
	 */

	public void setAnnee(short annee) {
		this.annee = annee;
	}

	/**
	 * Retourne l'editeur du livre.
	 * 
	 * @return L'editeur du livre.
	 */
	public String getEditeur() {
		return this.editeur;
	}

	/**
	 * Modifie l'editeur du livre.
	 * 
	 * @param editeur L'editeur du livre.
	 */

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	/**
	 * Retourne l'Isbn du livre.
	 * 
	 * @return l'Isbn du livre.
	 */

	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * Modifie l'Isbn du livre.
	 * 
	 * @param isbn l'Isbn du livre.
	 */

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Retourne le titre du livre.
	 * 
	 * @return Le titre du livre.
	 */

	public String getTitre() {
		return this.titre;
	}

	/**
	 * Modifie le titre du livre.
	 * 
	 * @param titre Le titre du livre.
	 */

	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Retourne l'auteur du livre.
	 * 
	 * @return L'auteur du livre.
	 */

	public Auteur getAuteur() {
		return this.auteur;
	}

	/**
	 * Modifie l'auteur du livre
	 * 
	 * @param auteur L'auteur du livre.
	 */

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	/**
	 * La methode {@code toString} affiche les valeurs des attributs dans la
	 * listeview livres.
	 * 
	 * @return <i> Titre Editeur ISBN Année </i>
	 */

	public String toString() {
		return titre + " " + " " + "|" + " " + " " + editeur + " " + " " + "|" + " " + " " + isbn + " " + " " + "|"
				+ " " + " " + annee;
	}

}