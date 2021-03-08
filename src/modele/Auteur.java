package modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * 
 * La classe {@code Auteur} est le modèle de persistence de l'auteur. <br>
 * The persistent class for the auteur database table.
 * 
 * @author Desebel MESNAGER
 * 
 */
@Entity
@NamedQuery(name = "Auteur.findAll", query = "SELECT a FROM Auteur a")
public class Auteur implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identifiant unique de l'auteur généré automatiquement
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;

	private String nationalite;

	private String nom;

	private String prenom;

	// bi-directional many-to-one association to Livre
	@OneToMany(mappedBy = "auteur")
	private List<Livre> livres;

	public Auteur() {
	}

	/**
	 * Création de l'Auteur
	 * 
	 * @param nom         Le nom de famille de l'auteur
	 * @param prenom      Le prenom de l'auteur
	 * @param nationalite La nationalite de l'auteur
	 */

	public Auteur(String nom, String prenom, String nationalite) {
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;

	}

	/**
	 * Retourne l'identifiant unique de l'auteur.
	 * 
	 * @return l'identifiant de l'auteur.
	 */

	public int getNum() {
		return this.num;
	}

	/**
	 * Modifie l'identifiant unique de l'auteur.
	 * 
	 * @param num Nouvel identifiant pour cet auteur.
	 */

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * Retourne la nationalité de l'auteur.
	 * 
	 * @return Nationalité de l'auteur.
	 */

	public String getNationalite() {
		return this.nationalite;
	}

	/**
	 * Modifie la nationalité de l'auteur.
	 * 
	 * @param nationalite Nationalité de l'auteur.
	 */

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	/**
	 * Retourne le de famille nom de l'auteur.
	 * 
	 * @return Nom de famille de l'auteur.
	 */

	public String getNom() {
		return this.nom;
	}

	/**
	 * Modifie le nom de famille de l'auteur.
	 * 
	 * @param nom Nom de famille de l'auteur.
	 */

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le prénom de l'auteur.
	 * 
	 * @return Prénom de l'auteur.
	 */

	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * Modifie le prénom de l'auteur.
	 * 
	 * @param prenom Prénom de l'auteur.
	 */

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Retourne la liste des livres de la classe Livre.
	 * 
	 * @return Liste des livres.
	 */

	public List<Livre> getLivres() {
		return this.livres;
	}

	/**
	 * Modifie la liste des livres de la classe Livre.
	 * 
	 * @param livres Liste des livres.
	 */

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	/**
	 * Retourne l'ajoute du livre.
	 * 
	 * @param livre la selection de l'auteur.
	 * @return La liste du livre.
	 */

	public Livre addLivre(Livre livre) {
		getLivres().add(livre);
		livre.setAuteur(this);

		return livre;
	}

	/**
	 * Retourne la suppression du livre.
	 * 
	 * @param livre la selection du livre.
	 * @return La liste livre.
	 */

	public Livre removeLivre(Livre livre) {
		getLivres().remove(livre);
		livre.setAuteur(null);

		return livre;
	}

	/**
	 * La methode {@code toString} affiche les valeurs des attributs dans la
	 * listeview auteurs.
	 * 
	 * @return <i> Nom Prénom Nationalité </i>
	 */

	public String toString() {
		return nom + " " + " " + "|" + " " + " " + prenom + " " + " " + "|" + " " + " " + nationalite;
	}

}