import javax.swing.*;
import java.awt.*;


/**
 * Ce code permet de creer une fenetre universbis qui herite de la FenetreUniversMere, elle permet de creer l'univers saisi pas l'utilisateur 
 */
 
public class FenetreUniversBis extends FenetreUniversMere{
	
	public FenetreUniversBis(){
		super();
		fSaisie = new Saisie(this);
	}
	
	/**
	 * Cette methode est appelee quand on termine la saisie des parametres du systeme stellaire
	 * 
	 * Elle cree un nouveau systeme stellaire a partir des donnees saisies et rend la fenetre univers visible 
	 */ 
	 
	public void afficher(){
		SystemeMere ok = new SystemeMere(this, fSaisie.tabObjet, fSaisie.star);
		ok.setBounds(0,0,600,600);
		ok.setVisible(true);
		this.setContentPane(ok);
		this.setVisible(true);
	}
	
}

