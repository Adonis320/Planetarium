import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * La classe SystemeMere qui herite d'un JPanel, elle contient les planetes et les etoiles et les affiche avec un paint
 * 
 * Implemente un ActionListener et un MouseListener
 * 
 */
  
public class SystemeMere extends JPanel implements ActionListener, MouseListener {
	
	/**
	 * Les attributs
	 */
	  
	protected Planete tabPlanetes [];
	protected FenetreUniversMere fenetre;
	protected Timer time;
	protected Etoile etoile;
	protected int indicePlanete = 0;
	protected int indiceVitesse = 1; // car vitesse par defaut est de 20 secondes pour une annee
	
	protected double reference = 0; //la distance de reference pour l'echelle logarithmique
	protected double reference_en_pixel = 0; //la distance de reference en pixel pour l'echelle logarithmique
	protected double puissanceMax = 0; //la puissance de dix de la plus grande distance planete-etoile
	protected double puissanceMin = 0;
	protected int distanceMaxPixels = 0; //la ditance de la planete la plus eloigneeen pixels
	protected int distanceMinPixels = 0;
	
	/**
	 * Le constructeur
	 * 
	 * @param La FenetreUniversMere qui permet un affichage relatif aux dimensions, le tableau de planetes et l'etoile
	 */
	 
	public SystemeMere(FenetreUniversMere f, Planete [] o, Etoile e){
		this.fenetre = f;
		this.setLayout(null);
		this.tabPlanetes = o;
		this.etoile = e;
		//on cree un timer
		time = new Timer(1, this);
		time.addActionListener(this);
		time.start();
		//mouse listener pour pouvoir cliquer sur les planetes
		addMouseListener(this);	
		//on se met en plein ecran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0,0,screenSize.width,screenSize.height);
		//on fixe la distance maximale en pixels planete-etoile a la moitie de la hauteur de l'ecran - 70 pixels
		distanceMaxPixels = screenSize.height/2-70;
		//la distance planete-etoile minimale a 70 pixels
		distanceMinPixels = 70;
		//on calcule la reference par rapport a laquelle on fait notre echelle logarithmique
		calculReference(distanceMin(tabPlanetes),distanceMax(tabPlanetes), distanceMinPixels, distanceMaxPixels);
		//on calcule la reference en pixels
		calculRefenrencepixels(distanceMin(tabPlanetes), 70);
		puissanceMax = calculPuissance(distanceMax(tabPlanetes))+1;
		puissanceMin = calculPuissance(distanceMin(tabPlanetes));
		for(int i = 0; i < tabPlanetes.length; i++){//on calcule les distance planete-etoile en pixels de chaque planete sur une echelle logarithmique
			tabPlanetes[i].distance = (int)calculDistanceRelative(tabPlanetes[i]);
		}
	}
	
	/**
	 * Methode paint pour afficher les differents elements 
	 * 
	 * @param un Graphics 
	 */
	  
	public void paint(Graphics g){
		g.setColor(Color.black); //on remplit l'ecran en noir
		g.fillRect(0,0,getWidth(),getHeight());
		
		for (int i = 0; i <255; i=i+1){ //on affiche l'etoile avec un style degrade
			g.setColor(new Color(i,i,0));
			g.fillOval(etoile.x-75/2+i/2,etoile.y-75/2+i/2, 75-i, 75-i);
		}
				
		for(int i = 0; i < tabPlanetes.length; i++){ //on affiche chaque planete et sa trajectoire
				g.setColor(Color.gray);
				g.drawOval(tabPlanetes[i].etoile.x-tabPlanetes[i].distance,tabPlanetes[i].etoile.y-tabPlanetes[i].distance,2*tabPlanetes[i].distance,2*tabPlanetes[i].distance);

				g.setColor(Color.blue);
				g.fillOval(tabPlanetes[i].x,tabPlanetes[i].y,tabPlanetes[i].taille,tabPlanetes[i].taille);
				
				g.setColor(Color.white);
				g.drawString(tabPlanetes[i].nom, tabPlanetes[i].x,tabPlanetes[i].y-5);
		}
		
		//on affiche les infos
		g.setColor(Color.white);
		g.drawString("Planete : "+tabPlanetes[indicePlanete].nom, getWidth()-175, 50);
		g.drawString("Masse : "+tabPlanetes[indicePlanete].masse+" kg",getWidth()-175, 100);
		g.drawString("Distance a l'etoile : "+tabPlanetes[indicePlanete].distanceReelle/1000+" km",getWidth()-175, 150);
		g.drawString("Diametre : "+tabPlanetes[indicePlanete].diametre+" km",getWidth()-175, 200);
		g.drawString("x : "+tabPlanetes[indicePlanete].x,getWidth()-175, 250);
		g.drawString("y : "+tabPlanetes[indicePlanete].y,getWidth()-175, 300);
		g.drawString("Periode : " +(double)(((int)(tabPlanetes[indicePlanete].periode()*100)))/100+" annees",getWidth()-175, 350);
		g.drawString("Nombre de satellites : "+tabPlanetes[indicePlanete].nombre_de_Satellites,getWidth()-175, 400);
		
		//la zone pour retourner a la fenetre principale
		g.drawString("Retour", 50, getHeight()-50);
	
		//l'echelle de vitesse
		g.drawString("Echelle : ", 50 ,50);
		if(indiceVitesse == 0){
			g.setColor(Color.red);
		}
		g.drawString("            vitesse*2", 67 ,50);
		g.setColor(Color.white);
		if(indiceVitesse == 1){
			g.setColor(Color.red);
		}
		g.drawString("          vitesse par defaut", 67 ,110);
		g.setColor(Color.white);
		if(indiceVitesse == 2){
			g.setColor(Color.red);
		}
		g.drawString("          vitesse/2", 67, 170);
		g.setColor(Color.white);
		g.drawString("Changer le sens de rotation", 50 ,230);
		
		//l'echelle logarithmique
		g.fillRect(getWidth()/2+distanceMaxPixels+30,getHeight()/2+distanceMinPixels,1,distanceMaxPixels-distanceMinPixels);
		
		//compteur de puissance
		int conte = (int)calculPuissance(distanceMin(tabPlanetes));
		//on diminue la taille de la police
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() / 1.4F);
		g.setFont(newFont);
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		
		//on affiche l'echelle logarithmique
		for(int i = 0; i < facteurDix()+1; i++){
			double a = distanceMin(tabPlanetes)/Math.pow(10,calculPuissance(distanceMin(tabPlanetes)));
			for(int j = 0; j < 10; j++){
					g.fillRect((int)(getWidth()/2+distanceMaxPixels+30), (int)(getHeight()/2+reference_en_pixel*i+reference_en_pixel*Math.log10(10*distanceMin(tabPlanetes)*(j+1)/reference)),5,1);
					g.drawString( nf.format(a*j) + " E"+conte+" km",getWidth()/2+distanceMaxPixels+50,(int)(getHeight()/2+reference_en_pixel*i+reference_en_pixel*Math.log10(10*distanceMin(tabPlanetes)*j/reference)));
			}
			conte++;
		}
	}
	
	/**
	 * Methode qui calcule la puissance de 10 d'un nombre
	 * 
	 * @param un double 
	 */
	   
	public double calculPuissance(double a){
		double puissance = 0;
		int b = 10;
		while(true){
			a = a/10;
			puissance++;
			if((b-a) > 0){
				break;
			}
		}
		return puissance;
	}
	
	/**
	 * Methode qui calcule la distance de reference
	 * 
	 * @param la distance minimale et maximale, et minimale et maximale en pixels
	 */
	 
	public void calculReference(double min, double max, int minpixel, int maxpixel){
		double a = ((maxpixel*Math.log(10*min))-minpixel*Math.log(10*max))/(maxpixel-minpixel);
		reference = Math.exp(a);
	}
	
	/**
	 * Methode qui calcule la distance de reference en pixels
	 * 
	 * @param la distance minimale et la distance minimale en pixels
	 */
	 
	public void calculRefenrencepixels(double min, int minpixel){
		reference_en_pixel = minpixel/Math.log10(10*min/reference);
	} 
	
	/**
	 * Methode qui calcule la distance relative d'une planete en pixels a l'etoile selon la formule suivante
	 * 
	 * @param une planete
	 */
	 
	public int calculDistanceRelative(Planete a){
		return a.distance = (int)(reference_en_pixel*Math.log10(10*a.distanceReelle/(1000*reference)));
	}
	
	/**
	 * Methode qui calcule la distance planete-etoile maximale dans le tableau des planetes
	 * 
	 * @param un tableau de planetes
	 */
	 
	public double distanceMax(Planete t []){
		double distancemax = 0;
		for (int i = 0; i < t.length; i++){
			if(t[i].distanceReelle > distancemax){
				distancemax = t[i].distanceReelle;
			}
		}
		return distancemax/1000; //on convertit de m en km
	}
	
	/**
	 * Methode qui calcule la distance planete-etoile minimale dans le tableau des planetes
	 * 
	 * @param un tableau de planetes
	 */
	 
	public double distanceMin(Planete t []){
		double distancemin = t[0].distanceReelle;
		for (int i = 0; i < t.length; i++){
			if(t[i].distanceReelle < distancemin){
				distancemin = t[i].distanceReelle;
			}
		}
		return distancemin/1000; //on convertit de m en km
	}
	
	/**
	 * Methode qui permet de savoir le nombre de multiplications (de 1 a 10) sur l'echelle logarithmique 
	 */
	 
	public int facteurDix(){
		int facteur = 1;
		int var = (int)(distanceMax(tabPlanetes)/distanceMin(tabPlanetes));
		for(double i = 1; i < 100000000; i = i*10){
			if(((int)((var/i)%10)) == 0){
				break;
			}
			facteur ++ ;
		}
		return facteur;
	}
	
	/**
	 * Methode qui permet de bouger les planetes a chaque tic du timer
	 * 
	 * @param prend en parametre un evenement
	 */
	 
	public void actionPerformed(ActionEvent e){
		for(int i = 0; i < tabPlanetes.length; i++){
			tabPlanetes[i].bougerPlanete();
		}
		repaint();
	}
	
	/**
	 * Methode qui permet d'interagir avec le programme en cliquant sur les planetes ou des zones precises
	 * 
	 * @param prend en parametre un evenement de souris
	 */
	 
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(); //coordonnee selon x de la souris
		int y=e.getY();	//coordonnee selon y de la souris
		for(int i = 0; i<tabPlanetes.length; i++){
			if((x <= (tabPlanetes[i].x + 20 ) && x >= tabPlanetes[i].x - 20)){ //si on clique dans cette zone autour d'une planete 
				if((tabPlanetes[i].y - 20) <= y && y <= (tabPlanetes[i].y + 20)){
					indicePlanete = i;
					break;
				}
			}
		}
		if(x>=50-30 && x<=50+30){ //si on appuie sur le texte retour
			if(y<=getHeight()-50+30 && y>=getHeight()-50-30){
				this.fenetre.setVisible(false);
			}
		}
		if(x>=67-50 && x<=67+200){
			if(y<=50+25 && y>=50-25){  //vitesse *2
				for(int i=0; i<tabPlanetes.length;i++){
					tabPlanetes[i].vitesse=tabPlanetes[i].vitesse*2;
					indiceVitesse = 0;
				}
			}
		}
		if(x>=67-50 && x<=67+200){
			if(y<=110+25 && y>=110-25){ //vitesse par defaut (pas reelle)
				for(int i=0; i<tabPlanetes.length;i++){
					tabPlanetes[i].vitesse=1;
					indiceVitesse = 1;
				 }
			}
		}
		
		if(x>=67-50 && x<=67+200){
			if(y<=170+25 && y>=170-25){  //vitesse /2
				for(int i=0; i<tabPlanetes.length;i++){
				  tabPlanetes[i].vitesse= tabPlanetes[i].vitesse*0.5;
				  indiceVitesse	= 2;
				}	
			}
		}
		
		if(x>=50-50 && x<=50+200){ //si on veut changer le sens de rotation des planetes
			if(y<=230+25 && y>=230-25){  
				for(int i=0; i<tabPlanetes.length;i++){
					tabPlanetes[i].vitesse=-tabPlanetes[i].vitesse;
				}	
			}
		}
		
		repaint();
	}
	
	public void mouseExited(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
	}
	
	public void mouseReleased(MouseEvent e){	
	}
	
	public void mousePressed(MouseEvent e){
	}
	
}

