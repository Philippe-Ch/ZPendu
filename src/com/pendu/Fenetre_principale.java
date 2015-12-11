package com.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
 
public class Fenetre_principale extends JFrame {

  /**
  * 
  */
  private static final long serialVersionUID = 6025030854209917672L;
  
  ///////////////////////////////////
  // Création de la barre de menus
  ///////////////////////////////////    
  private JMenuBar menuBar = new JMenuBar();
  
  // Les items des menus
  private JMenu fichier = new JMenu("Fichier"),
    apropos = new JMenu("A propos");
    
  // Les items des sous menus
  private JMenuItem nouveau = new JMenuItem("Nouveau"),
    score = new JMenuItem("Score"),
    regles = new JMenuItem("Règles"),
    quitter = new JMenuItem("Quitter"),
    point_inter = new JMenuItem("?");

  public JPanel panelAccueil = new JPanel(), panelNouveau = new JPanel(), panelScore = new JPanel(), panelRegles = new JPanel();
   
  ///////////////////////////////////////////////////////////////////
  //                         Constructeur                          //
  ///////////////////////////////////////////////////////////////////
  
  public Fenetre_principale() {
    this.setTitle("Le Pendu");
    this.setSize(900, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null); 
    
    // Pour le menu
    this.initMenu();
    
    // création des pages statiques
    new AccueilPanel(panelAccueil);
    new ScorePanel(panelScore);
    new ReglesPanel(panelRegles);
    
    setContentPane(panelAccueil);
   
    this.setVisible(true);         
  }
 
  private void initMenu(){
        ///////////////
	    // Menu fichier
	    ///////////////
	  
	  	// Ajout du listener pour nouveau
	    nouveau.addActionListener(new NouveauListener());
	    nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
	    fichier.add(nouveau);
	    
	  	// Ajout du listener pour score
	    score.addActionListener(new ScoreListener());
	    score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
	    fichier.add(score);
	    
	  	// Ajout du listener pour regles
	    regles.addActionListener(new ReglesListener());
	    regles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
	    fichier.add(regles);
	    
	    fichier.addSeparator();
	    
	    // Ajout du listener pour quitter
	    quitter.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		        System.exit(0);
		      }
		});    
	    quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
	    fichier.add(quitter);
	    
	  	// Ajout du listener pour a propos
	    point_inter.addActionListener(new PointInterListener());
	    apropos.add(point_inter);	    
	    
	    // Ajout des menus dans la barre de menus et ajout de mnémoniques !
	    fichier.setMnemonic('F');
	    menuBar.add(fichier);

	    apropos.setMnemonic('A');
	    menuBar.add(apropos);

	    //  Ajout de la barre de menus sur la fenêtre
	    this.setJMenuBar(menuBar);
	  }  

  class NouveauListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	// pour la page nouveau
	    	panelNouveau.removeAll();
	    	NouveauPanel p = new NouveauPanel();
	    	panelNouveau.add(p.getPanel());
	    	setContentPane(panelNouveau);
	        panelNouveau.revalidate();    	
	    }    
  }
  
  class ScoreListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        // pour la page des scores
	        setContentPane(panelScore);
	        panelScore.revalidate();
	    }    
  }

  class ReglesListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        // pour la page des regles
	        setContentPane(panelRegles);
	        panelRegles.revalidate();
	    }    
  }
 
  class PointInterListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        // pour la page a propos
	        JFrame ap = new JFrame();
	        ap.setTitle("A propos du Pendu");
	        ap.setSize(300, 150);
	        ap.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        ap.setLocationRelativeTo(null);
	    	
	        ap.setBackground(Color.white);
	    	ap.setLayout(new BorderLayout());
	    	
	    	JLabel titre = new JLabel("Créateur : Philippe\n");
	    	titre.setHorizontalAlignment(JLabel.CENTER);
	    	titre.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
	    	ap.add(titre, BorderLayout.CENTER);
	        
	        ap.setVisible(true);
	    }    
  }
  
}