package jeu;

import java.util.Collection;
import java.util.LinkedList;
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
	List<Limite> pileLimite= new LinkedList<>();
	List<Bataille> pileBataille= new LinkedList<>();
	Collection<Borne> bornes= new LinkedList<>();
	
	public int donnerLimitationVitesse() {
		if(pileLimite.isEmpty() || pileLimite.get(0) instanceof FinLimite) {
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
	
	public void deposer(Carte carte) {
		if(carte instanceof Borne borne) {
			bornes.add(borne);
		}else if(carte instanceof Limite limite) {
			pileLimite.add(0, limite);
		}else {
			pileBataille.add(0, (Bataille) carte);
		}
	}
	
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && pileBataille.get(0).equals(new Parade(Type.FEU));
	}
	
	public boolean estDepotFeuVertAutorise() {
		return pileBataille.isEmpty() || pileBataille.get(0).equals(new Attaque(Type.FEU)) 
				|| (pileBataille.get(0) instanceof Parade && !pileBataille.get(0).equals(new Parade(Type.FEU)));
	}
	
	public boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm()<=donnerLimitationVitesse() && donnerKmParcourus()<1000;
	}
	
	public boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.get(0) instanceof FinLimite;
		}
		return !pileLimite.isEmpty() && pileLimite.get(0) instanceof DebutLimite;
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque) {
			return peutAvancer();
		}else {
			if(bataille.equals(new Parade(Type.FEU))) {
				return estDepotFeuVertAutorise();
			}
			return !pileBataille.isEmpty() && pileBataille.get(0).equals(bataille);
		}
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}else if(carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}else {
			return estDepotLimiteAutorise((Limite) carte);
		}
	}
}
