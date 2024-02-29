package histoire;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		//Etal etal = new Etal();
		//etal.libererEtal();
		
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		village.ajouterHabitant(bonemine);
		System.out.println(village.installerVendeur(bonemine, "fleurs", 12));
		Etal etalFleur = village.rechercherEtal(bonemine);
		System.out.println(etalFleur.acheterProduit(-10, abraracourcix));
		
		
		System.out.println("Fin du test");
	}
}
