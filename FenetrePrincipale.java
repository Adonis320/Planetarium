import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe qui sert a l'affichage de l'ecran d'acceuil principal avec tous les boutons et les options necessaires 
 * 
 * Elle implemente l'ActionListener pour gerer les actions de l'utilisateur sur les boutons
 * 
 * Les attributs sont les boutons,les jpanel, l'AudioPlayer pour jouer la musique, les fenetres qui gerent l'affichage de chaque option de systeme et aussi la fenetre de credits
 */
 
public class FenetrePrincipale extends JFrame implements ActionListener{

	protected JButton boutonsolaire;
	protected JButton arretMusique;
	protected FenetreUnivers f;
	protected JButton boutoncredits;
	protected JPanel credits;
	protected JPanel fond;
	protected FenetreUniversBis f1;
	protected JButton boutonutilisateur;
	protected FenetreCredits fc;
	protected AudioPlayer ap = new AudioPlayer();
	
	/**
	 * Le constructeur de la classe FenetrePrincipale
	 * 
	 * @param prend en parametre la fenetre du systeme solaire, l'AudioPlayer, la fenetre de credits et la fenetre du systeme stellaire cree par l'utilisateur
	 */ 
	 
	public FenetrePrincipale (FenetreUnivers fe, FenetreUniversBis fUtilisateur, FenetreCredits cred, AudioPlayer p) {
		this.f = fe;
		this.f1 = fUtilisateur;
		this.fc = cred;
		this.ap = p;
		
		this.setTitle("Planetarium");
		this.setBounds(0,0,400,400);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel fond = new JPanel();
		fond.setLayout(null);
		fond.setVisible(true);
		
		JLabel fondecran = new JLabel(new ImageIcon("./space6.jpg"));
		fondecran.setBounds(0,0,400,400);
		fondecran.setVisible(true);
		fond.add(fondecran);
		
		boutonutilisateur = new JButton();
		boutonutilisateur.setBounds(100,0,200,50);
		boutonutilisateur.setText("Cr\u00e9er votre propre syst\u00e8me !");
		boutonutilisateur.setBorder(null);
		boutonutilisateur.setContentAreaFilled(false);
		boutonutilisateur.setFocusPainted(false);
		boutonutilisateur.setOpaque(false);
		boutonutilisateur.setForeground(Color.white);
		boutonutilisateur.setVisible(true);
		boutonutilisateur.addActionListener(this);
		fondecran.add(boutonutilisateur);
		
		arretMusique = new JButton();
		arretMusique.setBounds(100,250,200,40);
		arretMusique.setText("Arreter la musique");
		arretMusique.setBorder(null);
		arretMusique.setContentAreaFilled(false);
		arretMusique.setFocusPainted(false);
		arretMusique.setOpaque(false);
		arretMusique.setForeground(Color.white);
		arretMusique.setVisible(true);
		arretMusique.addActionListener(this);
		fondecran.add(arretMusique);	
		
		boutonsolaire = new JButton();
		boutonsolaire.setBounds(100,75,200,50);
		boutonsolaire.setText("D\u00e9couvrir le syst\u00e8me solaire");
		boutonsolaire.setBorder(null);
		boutonsolaire.setContentAreaFilled(false);
		boutonsolaire.setFocusPainted(false);
		boutonsolaire.setOpaque(false);
		boutonsolaire.setForeground(Color.white);
		boutonsolaire.setVisible(true);
		boutonsolaire.addActionListener(this);
		fondecran.add(boutonsolaire);
		
		boutoncredits = new JButton();
		boutoncredits.setBounds(0,300,400,100);
		boutoncredits.setText("Cr\u00e9dits");
		boutoncredits.setBorder(null);
		boutoncredits.setContentAreaFilled(false);
		boutoncredits.setFocusPainted(false);
		boutoncredits.setOpaque(false);
		boutoncredits.setForeground(Color.white);
		boutoncredits.setVisible(true);
		boutoncredits.addActionListener(this);
		fondecran.add(boutoncredits);
		
		this.setContentPane(fond);
		this.setVisible(true); Window(x,y
		
		ap.load("Interstellar - Main Theme - Hans Zimmer");
		ap.play();
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == boutonsolaire){ //on affiche le systeme solaire
			f.setVisible(true);
		}
		
		if(e.getSource() == boutoncredits){ //on affiche la fenetre de credits
			fc.setVisible(true);
		}
		if(e.getSource() == boutonutilisateur){
			f1.fSaisie.setVisible(true); //on affiche la fenetre de saisie
		}
		if(e.getSource() == arretMusique){ //on arrete la musique quand on appuie sur le bouton arret
			ap.stop();
		}
	}
	
	
	public static void main (String[] args) {
		FenetreCredits fenetre_de_credits = new FenetreCredits();
		FenetreUnivers fenetre_univers_solaire = new FenetreUnivers();
		FenetreUniversBis fenetre_univers_saisie = new FenetreUniversBis();
		AudioPlayer p = new AudioPlayer();
		new FenetrePrincipale(fenetre_univers_solaire, fenetre_univers_saisie, fenetre_de_credits,p);
	}
}

