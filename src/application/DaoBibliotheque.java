package application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;
import modele.Auteur;
import modele.Livre;

/**
 * Cette classe est une <i>Factory</i> et permet de récupérer la connexion à la
 * base de données et de la transmettre aux classes souhaitant communiquer avec
 * la base de données.
 * 
 * @author Desebel MESNAGER
 *
 */
public class DaoBibliotheque {

	private EntityManager em;

	/**
	 * Crée la connexion à la base de données.
	 */
	public DaoBibliotheque() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bibliotheque");
		em = factory.createEntityManager();
	}

	/**
	 * Retourne tous les auteurs enregistrés dans la base de données.
	 * 
	 * @return La {@link List} des auteurs enregistrés.
	 */

	public List<Auteur> voirLesAuteurs() {
		TypedQuery<Auteur> query = em.createQuery("SELECT a FROM Auteur a ORDER by a.nom", Auteur.class);
		List<Auteur> liste = query.getResultList();
		return liste;
	}

	/**
	 * Retourne tous les livres enregistrés dans la base de données.
	 * 
	 * @return La {@link List} des livres enregistrés.
	 */

	public List<Livre> voirLesLivres() {
		TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l", Livre.class);
		List<Livre> listeLivresAuteur = query.getResultList();
		return listeLivresAuteur;
	}

	/**
	 * Retourne tous les livres d'un auteur.
	 * 
	 * @param auteur Affiche des livres d'un auteur une fois selectionné.
	 * @return La {@link List} des livres d'un auteur.
	 */

	public List<Livre> voirLesLivresAuteur(Auteur auteur) {
		Livre livre = new Livre();
		String request = "select l from Livre l  where l.auteur=?1 ORDER by l.titre";
		Query query = em.createQuery(request);
		List<Livre> listeLivresAuteur = null;
		query.setParameter(1, auteur);
		listeLivresAuteur = query.getResultList();
		return listeLivresAuteur;
	}

	/**
	 * Retourne tous les auteurs enregistrés dans la base de données.
	 * 
	 * @return La {@link List} des auteurs enregistrés.
	 */

	public List<Auteur> auteursComboBox() {
		TypedQuery<Auteur> query = em.createQuery("SELECT a FROM Auteur a", Auteur.class);
		List<Auteur> liste = query.getResultList();
		return liste;
	}

	/**
	 * Supprime un livre existante dans la base de données.
	 * 
	 * @param livre Le livre à supprimer de la base de données.
	 */

	public void supprimer(Livre livre) {
		em.getTransaction().begin();
		em.remove(livre);
		em.getTransaction().commit();

	}

	/**
	 * Crée un enregistrement pour un nouveau livre dans la base de données.
	 * 
	 * @param livre Le nouveau livre à enregistrer.
	 */
	public void stockerLivre(Livre livre) {
		em.getTransaction().begin();
		em.persist(livre);
		em.getTransaction().commit();
	}

	/**
	 * Crée un enregistrement pour un nouveau auteur dans la base de données.
	 * 
	 * @param auteur Le nouveau auteur à enregistrer.
	 */

	public void ajoutUnAuteur(Auteur auteur) {
		em.getTransaction().begin();
		em.persist(auteur);
		em.getTransaction().commit();
	}

}