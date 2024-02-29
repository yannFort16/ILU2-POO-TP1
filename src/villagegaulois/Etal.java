package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		StringBuilder chaine = new StringBuilder();
		try {
			etalOccupe = false;
			chaine.append("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
			int produitVendu = quantiteDebutMarche - quantite;
			if (produitVendu > 0) {
				chaine.append(
						"il a vendu " + produitVendu + " parmi " + produit + ".\n");
			} else {
				chaine.append("il n'a malheureusement rien vendu.\n");
			}
		}catch (NullPointerException e) {
			chaine.append("L'étal n'est pour l'instant pas utilisé");
		}
		return chaine.toString();
		
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) throws IllegalArgumentException {
		if (etalOccupe) {
			StringBuilder chaine = new StringBuilder();
			String nomAcheteur = "";
			try{
				nomAcheteur = acheteur.getNom();
			}catch(NullPointerException e) {
				return "Il y a un fantôme qui cherche à acheter des " + produit;
			}
			if (quantiteAcheter<1) {
				throw new IllegalArgumentException("La quantité acheté est " +
			"inferieur ou égal à 0.\n");
			}
			chaine.append(nomAcheteur + " veut acheter " + quantiteAcheter
					+ " " + produit + " à " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ nomAcheteur + " vide l'étal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + nomAcheteur
						+ ", est ravi de tout trouver sur l'étal de "
						+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
		}
		throw new IllegalStateException("L'étal n'est pas utilisé par un Gaulois");
	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
