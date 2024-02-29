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
			for(int i=0;i<nbEtals;i++) {
				etals[i]=new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit){
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			int i=0;
			while (i<etals.length) {
				if(etals[i]==null || !etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1; 
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalsAvecProd=0;
			int i=0;
			while (i<etals.length && etals[i].isEtalOccupe()) {
				if (etals[i].contientProduit(produit)) {
					nbEtalsAvecProd++;
				}
				i++;
			}
			Etal[] etalsProduit = new Etal[nbEtalsAvecProd];
			int j=0;
			int k=0;
			while (k<nbEtalsAvecProd) {
				if (etals[k].contientProduit(produit)) {
					etalsProduit[j] = etals[k];
					j++;
				}
				k++;
			}
			return etalsProduit;	
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			int i=0;
			while (i<etals.length && etals[i]!= null) {
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
				+ " " + produit + ".\n");
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
		if (etalsProduit.length > 0) {
			chaine.append("Les vendeurs de " + produit +" sont :\n");
			for(int i=0; i<etalsProduit.length; i++) {
				chaine.append("\t- " + etalsProduit[i].getVendeur().getNom() + "\n");
			}
		}
		else {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit +" au marché.\n");
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etal = rechercherEtal(vendeur);
		if(etal !=null) {
			chaine.append(etal.libererEtal());
		}	
		return chaine.toString();
	}
	
	public String afficherMarche() {
		return marche.afficherMarcher();
	}
}