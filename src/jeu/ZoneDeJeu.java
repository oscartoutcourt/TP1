package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	List<Limite> pileLimite= new ArrayList<Limite>();
	List<Bataille> pileBataille= new ArrayList<Bataille>();
	Collection<Borne> bornes= new ArrayList<Borne>();
	
	public int donnerLimitationVitesse() {
		if(pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int somme=0;
		for(Borne borne : bornes) {
			somme+=borne.getKm();
		}
		return somme;
	}
	
	public void deposer(Carte c) {
		if(c instanceof Borne) {
			bornes.add((Borne) c);
		}else if(c instanceof Limite) {
			pileLimite.add((Limite) c);
		}else {
			pileBataille.add((Bataille) c);
		}
	}
	
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && pileBataille.getLast().equals(new Parade(Type.FEU));
	}
	
	public boolean estDepotFeuVertAutorise() {
		Bataille sommet = pileBataille.getLast();
		return !pileBataille.isEmpty() || sommet.equals(new Attaque(Type.FEU)) 
				|| (sommet instanceof Parade && !sommet.equals(new Parade(Type.FEU)));
	}
	
	public boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm()<=donnerLimitationVitesse() && donnerKmParcourus()<1000;
	}
	
	public boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.getLast() instanceof FinLimite;
		}
		return !pileLimite.isEmpty() && pileLimite.getLast() instanceof DebutLimite;
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque) {
			return peutAvancer();
		}else {
			if(bataille.equals(new Parade(Type.FEU))) {
				return estDepotFeuVertAutorise();
			}
			return !pileBataille.isEmpty() && pileBataille.getLast().equals(bataille);
		}
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Bataille) {
			return estDepotBatailleAutorise((Bataille) carte);
		}else if(carte instanceof Borne) {
			return estDepotBorneAutorise((Borne) carte);
		}else {
			return estDepotLimiteAutorise((Limite) carte);
		}
	}
}
