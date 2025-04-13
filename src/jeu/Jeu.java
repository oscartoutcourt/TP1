package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import utils.GestionCartes;

public class Jeu {
	Sabot sabot;
	
	public Jeu(Carte[] cartes) {
		List<Carte> listeCartes = new ArrayList<Carte>();
		Collections.addAll(listeCartes, cartes);
		List<Carte> listeCartesMelangees = GestionCartes.melanger(listeCartes);
		sabot=new Sabot((Carte[]) listeCartesMelangees.toArray());
	}
}
