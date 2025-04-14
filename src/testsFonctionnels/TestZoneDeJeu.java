package testsFonctionnels;

import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;
import jeu.Joueur;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	
	public static void main(String[] args) {
		ZoneDeJeu zone=new ZoneDeJeu();
		Joueur joueur = new Joueur("J1", zone);
		System.out.println("Deposer carte : 25km");
		joueur.deposer(new Borne(25));
		System.out.println("Deposer carte : 50km");
		joueur.deposer(new Borne(50));
		System.out.println("Deposer carte : 75km");
		joueur.deposer(new Borne(75));
		System.out.println("Total des bornes : "+joueur.donnerKmParcourus());
		System.out.println("Limite : "+zone.donnerLimitationVitesse());
		joueur.deposer(new DebutLimite());
		System.out.println("Limite : "+zone.donnerLimitationVitesse());
		joueur.deposer(new FinLimite());
		System.out.println("Limite : "+zone.donnerLimitationVitesse());
	}
}
