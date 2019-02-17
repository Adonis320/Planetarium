import javax.swing.*;
import java.awt.*;

/**
 * La classe FenetreUnivers qui herite la classe FenetreUniversMere
 * 
 * Elle cree et affiche le systeme solaire qu'on connait
 */ 

public class FenetreUnivers extends FenetreUniversMere{
	
	public FenetreUnivers() {
		super();
		Etoile soleil = new Etoile (1.99*Math.pow(10,30),"Soleil",50,this);
		Planete [] objet = {new Planete(3.3*Math.pow(10,23),"Mercure",10,this,soleil,1,57.91*Math.pow(10,9),4878,0),new Planete(4.9*Math.pow(10,24),"Venus",10,this,soleil,1,108.2*Math.pow(10,9),12104,0),new Planete(6*Math.pow(10,24),"Terre",10,this,soleil,1,149.6*Math.pow(10,9),12756,1),new Planete(64*Math.pow(10,22),"Mars",10,this,soleil,1,227.9*Math.pow(10,9),6779,2),new Planete(19*Math.pow(10,26),"Jupiter",10,this,soleil,1,778.3*Math.pow(10,9),139822,69),new Planete(57*Math.pow(10,25),"Saturne",10,this,soleil,1,1504*Math.pow(10,9),116464,60),new Planete(87*Math.pow(10,24),"Uranus",10,this,soleil,1,29*Math.pow(10,11),51300,27),new Planete(1*Math.pow(10,26),"Neptune",10,this,soleil,1,45*Math.pow(10,11),49244,14)};	
		SystemeMere ok = new SystemeMere(this, objet, soleil);
		ok.setBounds(0,0,600,600);
		this.setContentPane(ok);
	}
}
