package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private int nbCartes;
	private Carte[] cartes;
	private int modCount = 0;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException("Le sabot est plein, impossible d'ajouter une carte.");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		modCount++;
	}

	public Carte piocher() {
		Iterator<Carte> it = iterator();
		if (estVide()) {
			throw new NoSuchElementException("Le sabot est vide, impossible de piocher.");
		}
		if (it.hasNext()) {
			Carte carte = it.next();
			it.remove();
			return carte;
		}
		return null;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new SabotIterator();
	}

	private class SabotIterator implements Iterator<Carte> {
		private int index = 0;
		private int expectedModCount = modCount;
		private boolean canRemove = false;

		@Override
		public boolean hasNext() {
			return index < nbCartes;
		}

		@Override
		public Carte next() {
			checkConcurrence();
			if (!hasNext()) {
				throw new NoSuchElementException("Plus de cartes dans le sabot.");
			}
			canRemove = true;
			return cartes[index++];
		}

		private void checkConcurrence() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException("Le sabot a été modifié pendant l'itération.");
			}
		}

		@Override
		public void remove() {
			checkConcurrence();
			if (!canRemove) {
				throw new IllegalStateException("Impossible de supprimer sans appel à next().");
			}
			System.arraycopy(cartes, index, cartes, index - 1, nbCartes - index);
			nbCartes--;
			index--;
			modCount++;
			expectedModCount++;
			canRemove = false;
		}
	}

}
