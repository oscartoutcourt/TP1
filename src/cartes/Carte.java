package cartes;

public abstract class Carte {
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Carte carte) {
			return this.getClass()==carte.getClass();
		}
		return false;
	}
}
