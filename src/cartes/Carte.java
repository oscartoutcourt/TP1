package cartes;

public abstract class Carte {
	@Override
	public boolean equals(Object obj) {
		return obj!=null && this.getClass()==obj.getClass();
	}
}
