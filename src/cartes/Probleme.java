package cartes;

public abstract class Probleme extends Carte {
	private Type type;

	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			Probleme prblme= (Probleme) obj;
			return type == prblme.getType();
		}
		return false;
	}
}
