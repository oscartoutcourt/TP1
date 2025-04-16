package jeu;


import cartes.Carte;

public class Joueur {
	String nom;
	ZoneDeJeu zone;
	MainJoueur main;
	
	public Joueur(String nom, ZoneDeJeu zone) {
		this.nom = nom;
		this.zone = zone;
	}
	public String getNom() {
		return nom;
	}
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	public Carte prendreCarte(Sabot sabot) {
		if(!sabot.estVide()) {
			Carte nouvelle=sabot.piocher();
			donner(nouvelle);
			return nouvelle;
		}
		return null;
	}
	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}
	public void deposer(Carte c) {
		zone.deposer(c);
	}
	public boolean estDepotAutorise(Carte carte) {
		return zone.estDepotAutorise(carte);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Joueur joueur) {
			return this.nom==joueur.getNom();
		}
		return false;
	}
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + "]";
	}
}
