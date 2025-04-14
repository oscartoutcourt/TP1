package jeu;

import cartes.Type;
import cartes.Botte;
import cartes.Attaque;
import cartes.Parade;
import cartes.Carte;

public interface Cartes {
    Carte PRIORITAIRE = new Botte(Type.FEU);
    Carte FEU_ROUGE = new Attaque(Type.FEU);
    Carte FEU_VERT = new Parade(Type.FEU);
}

