package jeu;

import java.util.List;

import cartes.Carte;

public class MainJoueur {
	List<Carte> main;
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		for(Carte carte : main) {
			bld.append(carte+", ");
		}
		return bld.toString();
	}
}
