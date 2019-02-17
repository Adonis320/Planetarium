/**
 * La classe etoile qui represent l'etoile dans le systeme 
 * 
 * Elle herite de la classe ObjetCeleste 
 */
 public class Etoile extends ObjetCeleste {
	
	/**
	 * Le constructeur
	 */ 
	public Etoile (double m, String n, int ta, FenetreUniversMere f){
		super(m,n,ta,f);
		this.x = f.getWidth()/2;
		this.y = f.getHeight()/2-25;
	}
	
}

