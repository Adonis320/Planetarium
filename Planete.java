/**
 * La classe Planete qui herite de la classe ObjetCeleste
 * 
 * 
 */
public class Planete extends ObjetCeleste{
        
        /**
         * Les attributs
         * 
         */ 
         
		protected Etoile etoile; //pour avoir une position relative a l'etoile autour de laquelle tourne la planete 
		protected int nombre_de_Satellites;//le nombre de satellites que possede la planete
        
        /**
         * Le constructeur
         * 
         * @param la masse, le nom, la taille en pixels, la FenetreUniversMere, l'etoile, la vitesse, la distance reelle a l'etoile, le diametre et le nombre de satellites
         */
          
        public Planete (double m, String n,int ta, FenetreUniversMere f, Etoile e, double v, double dist, double diam, int nbSat){
                super(m,n,ta,f,v,dist, diam);
                this.etoile = e;
				this.nombre_de_Satellites = nbSat;
                this.alpha = 2*Math.PI*Math.random(); //on positionne la planete a une position aleatoire au demarrage
                this.x = (int)(this.distance*(Math.cos(this.alpha)))+this.etoile.x-this.taille/2; //on calcule la coordonnee selon x
                this.y = (int)(this.distance*(Math.sin(this.alpha)))+this.etoile.y-this.taille/2; //on calcule la coordonnee selon y
        }
		
		/**
		 * Methode qui calcule la periode de revolution de la planete autour de son etoile en annees
		 * 
		 * @return la periode de revolution en annees
		 */
		  
        public double periode(){
                return (2*Math.PI*Math.sqrt(distanceReelle*distanceReelle*distanceReelle/(6.67*Math.pow(10,-11)*(this.masse+etoile.masse))))/(365*24*3600);
        }
        
        /**
         * Methode qui permet de bouger la planete d'un certain angle a chaque qu'elle est appellee, elle depend de la periode de revolution de chaque planete
         */ 
        public void bougerPlanete(){
                this.alpha = this.alpha-2*vitesse*Math.PI/((1000)*(periode()));
                this.x = (int)(this.distance*(Math.cos(this.alpha)))+this.etoile.x-this.taille/2;
                this.y = (int)(this.distance*(Math.sin(this.alpha)))+this.etoile.y-this.taille/2;
        }

}
