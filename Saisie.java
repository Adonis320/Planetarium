import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La fenetre de saisie qui gere la saisie des donnees du systeme stellaire cree par l'utilisateur
 * 
 * Implemente un ActionListener pour gere l'interaction programme-utilisateur
 * 
 */
  
public class Saisie extends JFrame implements ActionListener{
	
	/**
	 * les attributs
	 * 
	 */
	 
	protected JButton droite; //le bouton suivant
	protected JButton gauche; //le bouton precedent
	protected JButton conf1; //le bouton confirmation pour l'etoile
	protected JButton conf2; //le bouton confirmation pour les planetes
	
	protected JTextField masseTEtoile; //les textfields
	protected JTextField PersoEtoile;
	protected JTextField nbplanetes;
	protected JTextField nbSatellites;
	protected JTextField nomPlanete;
	protected JTextField massePlanete;
	protected JTextField diametrePlanete;
	protected JTextField distanceRPlanete;
	
	protected JLabel planetes;
	
	protected JTextField puissanceMasseEtoile;
	
	protected JTextField puissanceMassePlanete;
	protected JTextField puissanceDistancePlanete;
	protected JTextField puissanceDiametrePlanete;
	
	protected FenetreUniversMere fu;
	
	protected int compteur = 0; //differents compteurs nous permettant de naviguer dans les tableaux de planetes et de couleurs pour les boutons de confirmation
	protected int compteurbis = 1;
	protected int compteurcouleur = 0;
	protected int tableauValidation []; //tableau pour les couleurs du bouton de confirmation pour les planetes
	
	protected Planete [] tabObjet; //le tableau des planetes
	protected Etoile star; //l'etoile
	
	/**
	 * Le constructeur
	 * 
	 * 
	 */
	  
	public Saisie(FenetreUniversMere h){
		this.fu = h;
		this.setLayout(null);
		this.setTitle("Saisir les informations");
		this.setBounds(0,0,1000,400);
		this.setResizable(false);
		
		JPanel cont = new JPanel();
		cont.setLayout(null);
		cont.setBackground(Color.black);
		cont.setVisible(true);
		
		JLabel tranconneuse = new JLabel(); //la barre qui separe au milieu la zone planete de la zone etoile
		tranconneuse.setBounds(499,0,2,400);
		tranconneuse.setOpaque(true);
		tranconneuse.setBackground(Color.white);
		tranconneuse.setVisible(true);
		cont.add(tranconneuse);
		
		
		droite = new JButton();
		droite.setBounds(900,300,100,100);
		droite.setText("Suivant");
		droite.setBorder(null);
		droite.setContentAreaFilled(false);
		droite.setFocusPainted(false);
		droite.setOpaque(false);
		droite.setForeground(Color.white);
		droite.setVisible(true);
		droite.addActionListener(this);
		cont.add(droite);
		
		conf1 = new JButton();
		conf1.setBounds(200,300,100,100);
		conf1.setText("Confirmer");
		conf1.setBorder(null);
		conf1.setContentAreaFilled(false);
		conf1.setFocusPainted(false);
		conf1.setOpaque(false);
		conf1.setForeground(Color.white);
		conf1.setVisible(true);
		conf1.addActionListener(this);
		cont.add(conf1);
		
		conf2 = new JButton();
		conf2.setBounds(700,300,100,100);
		conf2.setText("Confirmer");
		conf2.setBorder(null);
		conf2.setContentAreaFilled(false);
		conf2.setFocusPainted(false);
		conf2.setOpaque(false);
		conf2.setForeground(Color.white);
		conf2.setVisible(true);
		conf2.addActionListener(this);
		cont.add(conf2);
		
		gauche = new JButton();
		gauche.setBounds(500,300,100,100);
		gauche.setText("Precedent");
		gauche.setBorder(null);
		gauche.setContentAreaFilled(false);
		gauche.setFocusPainted(false);
		gauche.setOpaque(false);
		gauche.setForeground(Color.black);
		gauche.setVisible(true);
		gauche.addActionListener(this);
		cont.add(gauche);
		
		JLabel etoile = new JLabel();
		etoile.setText("Etoile");
		etoile.setBounds(225,0,50,50);
		etoile.setVisible(true);
		etoile.setForeground(Color.white);
		cont.add(etoile);
		
		JLabel nomEtoile = new JLabel();
		nomEtoile.setBounds(25,50,200,50);
		nomEtoile.setText("Nom");
		nomEtoile.setVisible(true);
		nomEtoile.setForeground(Color.white);
		cont.add(nomEtoile);
		
		PersoEtoile = new JTextField();
		PersoEtoile.setBounds(225,60,100,30);
		PersoEtoile.setVisible(true);
		cont.add(PersoEtoile);
		
		
		JLabel masseEtoile = new JLabel();
		masseEtoile.setBounds(25,100,200,50);
		masseEtoile.setText("Masse en kg");
		masseEtoile.setVisible(true);
		masseEtoile.setForeground(Color.white);
		cont.add(masseEtoile);
		
		masseTEtoile = new JTextField();
		masseTEtoile.setBounds(225,110,100,30);
		masseTEtoile.setVisible(true);
		cont.add(masseTEtoile);
		
		JLabel nPlanetes = new JLabel();
		nPlanetes.setBounds(25,150,200,50);
		nPlanetes.setText("Nombre de planetes");
		nPlanetes.setVisible(true);
		nPlanetes.setForeground(Color.white);
		cont.add(nPlanetes);
		
		nbplanetes = new JTextField();
		nbplanetes.setBounds(225,160,100,30);
		nbplanetes.setVisible(true);
		cont.add(nbplanetes);
		
		planetes = new JLabel();
		planetes.setBounds(725,0,200,50);
		planetes.setText("Planete "+compteurbis);
		planetes.setVisible(true);
		planetes.setForeground(Color.white);
		cont.add(planetes);
		
		JLabel nomPPlanete = new JLabel();
		nomPPlanete.setBounds(525,50,200,50);
		nomPPlanete.setText("Nom");
		nomPPlanete.setVisible(true);
		nomPPlanete.setForeground(Color.white);
		cont.add(nomPPlanete);
		
		nomPlanete = new JTextField();
		nomPlanete.setBounds(725,60,100,30);
		nomPlanete.setVisible(true);
		cont.add(nomPlanete);
		
		JLabel massePPlanete = new JLabel();
		massePPlanete.setBounds(525,100,200,50);
		massePPlanete.setText("Masse en kg");
		massePPlanete.setVisible(true);
		massePPlanete.setForeground(Color.white);
		cont.add(massePPlanete);
		
		massePlanete = new JTextField();
		massePlanete.setBounds(725,110,100,30);
		massePlanete.setVisible(true);
		cont.add(massePlanete);
		
		JLabel diametrePPlanete = new JLabel();
		diametrePPlanete.setBounds(525,150,200,50);
		diametrePPlanete.setText("Diametre en km");
		diametrePPlanete.setVisible(true);
		diametrePPlanete.setForeground(Color.white);
		cont.add(diametrePPlanete);
		
		diametrePlanete = new JTextField();
		diametrePlanete.setBounds(725,160,100,30);
		diametrePlanete.setVisible(true);
		cont.add(diametrePlanete);
		
		JLabel distancePlanete = new JLabel();
		distancePlanete.setBounds(525,200,200,50);
		distancePlanete.setText("Distance a l'etoile en m");
		distancePlanete.setVisible(true);
		distancePlanete.setForeground(Color.white);
		cont.add(distancePlanete);
		
		distanceRPlanete = new JTextField();
		distanceRPlanete.setBounds(725,210,100,30);
		distanceRPlanete.setVisible(true);
		cont.add(distanceRPlanete);
		
		JLabel nbdesat = new JLabel();
		nbdesat.setBounds(525,250,200,50);
		nbdesat.setText("Nombre de Satellites");
		nbdesat.setVisible(true);
		nbdesat.setForeground(Color.white);
		cont.add(nbdesat);
		
		nbSatellites = new JTextField();
		nbSatellites.setBounds(725,260,100,30);
		nbSatellites.setVisible(true);
		cont.add(nbSatellites);
		
		JLabel labelPuissanceME = new JLabel();
		labelPuissanceME.setBounds(340,100,200,50);
		labelPuissanceME.setText("x10^");
		labelPuissanceME.setVisible(true);
		labelPuissanceME.setForeground(Color.white);
		cont.add(labelPuissanceME);
		
		puissanceMasseEtoile = new JTextField();
		puissanceMasseEtoile.setBounds(375,110,100,30);
		puissanceMasseEtoile.setVisible(true);
		cont.add(puissanceMasseEtoile);
		
		
		JLabel labelPuissanceMP = new JLabel();
		labelPuissanceMP.setBounds(840,100,200,50);
		labelPuissanceMP.setText("x10^");
		labelPuissanceMP.setVisible(true);
		labelPuissanceMP.setForeground(Color.white);
		cont.add(labelPuissanceMP);
		
		puissanceMassePlanete = new JTextField();
		puissanceMassePlanete.setBounds(880,110,100,30);
		puissanceMassePlanete.setVisible(true);
		cont.add(puissanceMassePlanete);
		
		JLabel labelPuissanceDiaP = new JLabel();
		labelPuissanceDiaP.setBounds(840,150,200,50);
		labelPuissanceDiaP.setText("x10^");
		labelPuissanceDiaP.setVisible(true);
		labelPuissanceDiaP.setForeground(Color.white);
		cont.add(labelPuissanceDiaP);
		
		puissanceDiametrePlanete = new JTextField();
		puissanceDiametrePlanete.setBounds(880,160,100,30);
		puissanceDiametrePlanete.setVisible(true);
		cont.add(puissanceDiametrePlanete);
		
		JLabel labelDistanceP = new JLabel();
		labelDistanceP.setBounds(840,200,200,50);
		labelDistanceP.setText("x10^");
		labelDistanceP.setVisible(true);
		labelDistanceP.setForeground(Color.white);
		cont.add(labelDistanceP);
		
		puissanceDistancePlanete = new JTextField();
		puissanceDistancePlanete.setBounds(880,210,100,30);
		puissanceDistancePlanete.setVisible(true);
		cont.add(puissanceDistancePlanete);

		this.setContentPane(cont);
	}
	
	/**
	 * Methode qui permet de raffiner l'affichage des boutons selon les choix de l'utilisateur
	 * 
	 * @param prend en parametre un int et permet de regler les couleur selon sa valeur
	 */
	 
	public void rafraichirCouleurBoutons(int c){
		if(c == tabObjet.length-1){
			droite.setForeground(Color.black); //on n'affiche pas le bouton suivant pour la derniere planete
		}else{
			droite.setForeground(Color.white); //on affiche le bouton suivant pour les autres planetes
		}
		if( c == 0){
			gauche.setForeground(Color.black); //on n'affiche pas le bouton precedent pour la premiere planete
		}else{
			gauche.setForeground(Color.white); //on affiche le bouton precedent pour les autres planetes
		}
		if(tableauValidation[c] == 1){
			conf2.setForeground(Color.green); //vert si c'est valide
		}else{
			conf2.setForeground(Color.white); //sinon blanc
		}
	}
	
	/**
	 * La methode qui gere l'interaction utilisateur-programme
	 * 
	 * @param prend en parametre un evenement
	 */
	  
	public void actionPerformed (ActionEvent e){
		int nombre = 0; //le nombre de planetes
		if(e.getSource() == conf1){ //si on cofirme nos donnees de l'etoile
			double masse = 0;
			String nom = "";
			
			masse = Double.parseDouble(masseTEtoile.getText()); //la masse
			nom = PersoEtoile.getText(); //le nom
			nombre = Integer.parseInt(nbplanetes.getText()); //le nombre de planetes
			tabObjet = new Planete[nombre]; //on cree le tableau de planetes de la taille du nombre des planetes
			tableauValidation = new int[nombre]; //on a autant de confirmations que de planetes
			for(int i = 0; i < tableauValidation.length; i++){
				tableauValidation[i] = 0; //le tableau qui permet de gerer la couleur du bouton de confirmation pour chaque planete
			}
			star = new Etoile(masse*Math.pow(10,Integer.parseInt(puissanceMasseEtoile.getText())),nom,50,this.fu); //on cree l'etoile a partir des donnees rentrees
			conf1.setForeground(Color.green); //on met le bouton en vert
		}
		if(e.getSource() == conf2){ // si on confirme pour une planete
			String nom = "";
			double masse = 0;
			double distance = 0;
			double diametre = 0;
			int nsat = 0;
			
			nom = nomPlanete.getText();
			nsat = Integer.parseInt(nbSatellites.getText());
			masse = Double.parseDouble(massePlanete.getText());
			distance = Double.parseDouble(distanceRPlanete.getText());
			diametre = Double.parseDouble(diametrePlanete.getText());
			//on cree une planete dans la case correspondante avec les donnees rentrees
			tabObjet[compteur] = new Planete(masse*Math.pow(10,Integer.parseInt(puissanceMassePlanete.getText())), nom, 10, this.fu, this.star, 1, distance*Math.pow(10,Integer.parseInt(puissanceDistancePlanete.getText())), diametre*Math.pow(10,Integer.parseInt(puissanceDiametrePlanete.getText())),nsat);
			if(compteur == tabObjet.length-1){
				this.fu.afficher(); //si on rentre toutes les planetes on affiche le systeme
			}
			tableauValidation[compteur] = 1; //la couleur du bouton de confirmation devient verte pour cette planete
			conf2.setForeground(Color.green);
		}
		if(e.getSource() == droite){ //si on appuie sur suivant
			if(compteur < tabObjet.length-1){ //on gere le cas des outofbounds
				if(compteurcouleur >= 0){
					compteurcouleur++; // on se place sur la couleur du bouton de confirmation correspondant a la planete suivante
				}
				compteur++; //on se place sur la case suivante de la tableau des planetes
				compteurbis = compteur +1; //le tableau part de 0, l'ordre de la planete commence de 1
				planetes.setText("Planete "+compteurbis);
			}
			rafraichirCouleurBoutons(compteurcouleur); //on rafraichit les couleurs des boutons
		}
		if(e.getSource() == gauche){ //si on appuit sur precedent
			if(compteur >= 1){//on gere le cas des outofbounds
				compteurcouleur--;
				compteur--;
				compteurbis = compteur + 1;
				planetes.setText("Planete "+compteurbis);
			}
			rafraichirCouleurBoutons(compteurcouleur);
		}
	}
	
}

