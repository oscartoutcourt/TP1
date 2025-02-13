package cartes;

public class JeuDeCartes {
	private Configuration typesDeCartes[]= {new Configuration(10, new Borne(25)), new Configuration(10, new Borne(50)),
			new Configuration(10, new Borne(75)), new Configuration(12, new Borne(100)), new Configuration(4, new Borne(200)), 
			new Configuration(14, new Parade(Type.FEU)), new Configuration(6, new FinLimite()), new Configuration(6, new Parade(Type.ESSENCE)),
			new Configuration(6, new Parade(Type.CREVAISON)), new Configuration(6, new Parade(Type.ACCIDENT)), 
			new Configuration(5, new Attaque(Type.FEU)), new Configuration(4, new DebutLimite()), new Configuration(3, new Attaque(Type.ESSENCE)),
			new Configuration(3, new Attaque(Type.CREVAISON)), new Configuration(3, new Attaque(Type.ACCIDENT)),
			new Configuration(1, new Botte(Type.FEU)), new Configuration(1, new Botte(Type.ESSENCE)), new Configuration(1, new Botte(Type.CREVAISON)),
			new Configuration(1, new Parade(Type.ACCIDENT))
	};
	
	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;

		public Configuration(int nbExemplaires, Carte carte) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}

	}

	public String affichageJeuDeCartes() {
		String a="";
		for(int i=0; i<19; i++) {
			a+=typesDeCartes[i].getNbExemplaires()+" "+typesDeCartes[i].getCarte().toString()+"\n";
		}
		return a;
	}
	
	public Carte[] donnerCartes() {
		int total=0;
		for(Configuration configuration : typesDeCartes) {
			total+= configuration.getNbExemplaires();
		}
		
		Carte[] cartes= new Carte[total];
		
		for(int i=0, k=0; i<19; i++) {
			for(int j=0; j<typesDeCartes[i].getNbExemplaires(); j++,k++) {
				cartes[k]=typesDeCartes[i].getCarte();
			}
		}
		return cartes;
	}

}
