import javax.swing.*;
import java.awt.*;

/**
 * La classe FenetreUniversMere qui gere l'affichage du systeme stellaire
 * 
 * 
 * 
 */
  
public class FenetreUniversMere extends JFrame{
	
	/**
	 * Les attributs
	 */
	protected SystemeMere systeme;
	protected Saisie fSaisie;
	
	/**
	 * Le constructeur
	 * 
	 */ 
	public FenetreUniversMere() {
		this.setTitle("Univers");
		this.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0,0,screenSize.width,screenSize.height);
		this.setResizable(false);
	}
	/**
	 * Methode d'affichage qui sera redefinie dans la FenetreUniversBis
	 * 
	 */
	  
	public void afficher(){
		
	}
}

