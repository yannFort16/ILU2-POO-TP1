package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit){
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);;
		}
		
		private int trouverEtalLibre() {
			for (int i=0;i<etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			Etal[] etalsProduit = new Etal[etals.length];
			int j=0;
			for (int i=0;i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsProduit[j] = etals[i];
					j++;
				}
			}
			return etalsProduit;	
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0;i<etals.length; i++) {
				if(etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarcher(){
			StringBuilder chaine = new StringBuilder();
			int nbEtalsVide = 0;
			for (int i=0;i<etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				nbEtalsVide++;
			}
			chaine.append("Il reste " + nbEtalsVide + " étals non utilisés dans le marché.\n.");
			return chaine.toString();
		}
			
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit
				+ produit + ".\n");
		int indiceEtal = marche.trouverEtalLibre();
		if (indiceEtal == -1){
			chaine.append("Malheureusement, Il n'y a aucun étal de disponible.\n");
		}
		else{
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit
					+ " à l'étal n°" + indiceEtal);
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit[0] != null) {
			chaine.append("Les vendeurs de " + produit +" sont :\n");
			int i=0;
			while (etalsProduit[i] != null && i<etalsProduit.length) {
				chaine.append("\t- " + etalsProduit[i].getVendeur() + "\n");
				i++;
			}
		}
		else {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit +"au marché.\n");
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		
		return chaine.toString();
	}
}