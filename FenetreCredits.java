import javax.swing.*;
import java.awt.*;
 /**
  * C'est la fenetre qui affiche les credits 
  * 
  * Elle est visible quand on appuie sur le bouton credits
  * 
  */ 
public class FenetreCredits extends JFrame {
	
	public FenetreCredits(){
		
		this.setTitle("Credits");
		this.setLayout(null);
		this.setBounds(200,200,400,400);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0,0,200,200);
		panel.setLayout(null);
		panel.setBackground(Color.black);
		panel.setVisible(true);
		
		JLabel label1 = new JLabel("Progammeurs : ");
		label1.setBounds(0,0,200,100);
		label1.setVisible(true);
		panel.add(label1);
		
		
		JLabel label3= new JLabel("BETENCOURT Lucas");
		label3.setBounds(50,75,200,100);
		label3.setVisible(true);
		panel.add(label3);
		
		JLabel label4= new JLabel("CHUNGUES Kenny");
		label4.setBounds(50,100,200,100);
		label4.setVisible(true);
		panel.add(label4);
		
		JLabel label2 = new JLabel("KATTAN Adonis");
		label2.setBounds(50,50,200,100);
		label2.setVisible(true);
		panel.add(label2);
		
		JLabel source = new JLabel("Source de la photo d'acceuil : ");
		source.setBounds(0,150,200,100);
		source.setVisible(true);
		panel.add(source);
		
		JLabel source2 = new JLabel("<html>The Explosion of Binary Stars, Debby Jo Blank., Bristol [U.K.] : Shearsman Books, 2012. (Photo de couverture)<html>");
		source2.setBounds(0,175,400,200);
		source2.setVisible(true);
		panel.add(source2);
		
		this.setContentPane(panel);
	}
}

