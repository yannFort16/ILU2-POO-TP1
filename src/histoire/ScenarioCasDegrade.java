package histoire;

import personnages.Chef;
import personnages.Druide;
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
		/*Etal etalFleur = village.rechercherEtal(bonemine);
		try{
			System.out.println(etalFleur.acheterProduit(-10, abraracourcix));
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}*/
		/*
		try{
			System.out.println(etal.acheterProduit(10, abraracourcix));
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}*/
		
		Village villageSansChef = new Village("le village sans Chef", 10, 3);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		villageSansChef.ajouterHabitant(asterix);
		villageSansChef.ajouterHabitant(obelix);
		villageSansChef.ajouterHabitant(druide);
		try{
			System.out.println(villageSansChef.afficherVillageois());
		}catch(ExceptionVillageSansChef e) {
			e.printStackTrace(System.out);
		}
		
		System.out.println("Fin du test");
	}
}
