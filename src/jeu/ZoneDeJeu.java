package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

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
}
