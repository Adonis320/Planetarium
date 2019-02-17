/**
 * La classe ObjetCeleste dont va heriter les classes Planete et Etoile
 * 
 * 
 */
 
public abstract class ObjetCeleste{
	
	/**
	 * Les attributs
	 * 
	 * 
	 */
	  
	protected double masse; //la masse
	protected String nom; //le nom
	protected int taille; //la taille en pixels
	protected int x; //les coordonnees selon l'axe des x
	protected int y; //les coordonnees selon l'axe des y
	protected int distance; //la distance a l'etoile en pixels et qui va etre calculee selon une echelle logarithmique
	protected FenetreUniversMere fenetre; //la FeneteUniversMere qui peut une FenetreUniversBis ou une FenetreUnivers
	protected double vitesse; //la vitesse de la planete (en facteur selon le timer) et qu'on pourra modifier plus tard
	protected double distanceReelle; //la distance reelle de la planete a l'etoile
	protected double diametre; //le diametre de l'ObjetCeleste
	protected double alpha; //l'angle que fait la planete avec l'axe des x
	
	/**
	 * Le constructeur d'une planete (le super) 
	 * 
	 * @param la masse, le nom, la taille en pixels, la FenetreUniversMere pour avoir un affichage relatif, la vitesse, la distance reelle et le diametre
	 */ 
	 
	public ObjetCeleste (double m, String n,int ta, FenetreUniversMere f, double vitesse, double dist, double dia) {
		this.masse = m;
		this.nom = n;
		this.taille = ta;
		this.fenetre = f;
		this.vitesse = vitesse;
		this.distanceReelle = dist;
		this.diametre = dia;
	}
	
	/**
	 * Le constructeur d'une etoile 
	 * 
	 * @param la masse, le nom, la taille en pixels et la FenetreUniversMere pour pouvoir la positionner au milieu
	 */
	  
	public ObjetCeleste (double m, String n,int ta, FenetreUniversMere f) {
		this.masse = m;
		this.nom = n;
		this.taille = ta;
		this.distance = 0;
		this.fenetre = f;
	}
	
}

