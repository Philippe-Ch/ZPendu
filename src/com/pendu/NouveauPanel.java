package com.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NouveauPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6300025365416282891L;
	
	private JPanel panel = new JPanel();
	
	private String[] lettres = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
      "t", "u", "v", "w", "x", "y", "z", "-"};
	private JButton[] tab_button = new JButton[lettres.length];

	private JLabel motSecret = new JLabel();
	
	private JPanel imagePanel = new JPanel();
		
	private boolean trouve; // mot trouvé O/N
	private int nbLettre; // nombre de lettres trouvées
	private int pendu; // indice de l'image à afficher
	private int points; // score
	
	private JTextArea score = new JTextArea();
	
	// Le mot a trouver et son masque
	private String mot;
	private String mot_maj;
	private String mot_masq = "";
	
	public NouveauPanel(){
		trouve = false;
		nbLettre = 0;
		pendu = 0;
		points = 100;
		
		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		
		// Affichage du titre
		JLabel titre = new JLabel("A vous de jouer\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setBackground(Color.white);
		titre.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
		panel.add(titre, BorderLayout.NORTH);
		
		initMot();
		
		// Affichage du mot a trouver
		motSecret.setText(mot_masq);
		motSecret.setHorizontalAlignment(JLabel.CENTER);
		motSecret.setBackground(Color.white);
		motSecret.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
		panel.add(motSecret, BorderLayout.EAST);		
				
		// Affichage image
		// Le "/" car à la racine du jar
		imagePanel.add(new JLabel(new ImageIcon(getClass().getResource("/pendu0.jpg"))));
		imagePanel.setBackground(Color.white);
		panel.add(imagePanel, BorderLayout.CENTER);
	
		// Affichage des boutons
		JPanel boutonsPanel = new JPanel();
		boutonsPanel.setBackground(Color.white);
		boutonsPanel.setLayout(new GridLayout(7, 3, 10, 10));
		boutonsPanel.setSize(150, 200);
		boutonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
	    for(int i = 0; i < lettres.length; i++) {
	    	tab_button[i] = new JButton(lettres[i].toUpperCase());
	    	boutonsPanel.add(tab_button[i]);
	    	tab_button[i].addActionListener(new LettreListener());
	    	tab_button[i].setPreferredSize(new Dimension(50,30));
	    }
	    panel.add(boutonsPanel, BorderLayout.WEST);
	    
		// Affichage du score
		score.setText("Vous avez 7 tentatives");
		score.setBackground(Color.white);
		score.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
		panel.add(score, BorderLayout.SOUTH);	
	}
	
	protected JPanel getPanel(){
		return this.panel;
	}
	
	class LettreListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	if (pendu < 7 && trouve == false) {
	    		// si on n'est pas pendu et si on n'a pas trouvé 
	    		
		        // Pour les lettres
		    	// On récupère la touche choisie
		    	char c = ((JButton)e.getSource()).getText().charAt(0);
		    	String s = Character.toString(c);
		    	boolean testLettre = false;
	
		    	// On grise la touche
		    	((JButton)e.getSource()).setEnabled(false);
		    	
		    	// On recherche les occurences de s dans mot
		    	int index = mot_maj.indexOf(s);
		    	while (index >= 0) {  // indexOf retourne 1 si non trouvé
		    		// On remplace le caratère trouvé dans le mot masqué et on affiche
		    	    mot_masq = mot_masq.substring(0,index) + c + mot_masq.substring(index+1);
		    	    motSecret.setText(mot_masq);
		    	    nbLettre++;
		    	    testLettre = true;
		    	    score.setText(nbLettre + " lettre(s) OK ; Il reste : " + (7-pendu) + " tentatives");
		    	    
		    	    // On recherche une éventuel autre occurence de s dans mot
		    	    index = mot_maj.indexOf(s, index + 1);
		    	}
		    	// Si la lettre ne match pas, on change l'image du pendu
		    	if (testLettre == false) {
		    		pendu++;
		    		imagePanel.removeAll();
		    		// Le "/" car à la racine du jar
		    		imagePanel.add(new JLabel(new ImageIcon(getClass().getResource("/pendu"+pendu+".jpg"))));
		    		score.setText(nbLettre + " lettre(s) OK ; Il reste : " + (7-pendu) + " tentatives");
		    		imagePanel.revalidate();    	
		    	}
		    	
		    	// S'il n'y a plus d'*' dans mot_masq alors le mot est trouvé
		    	if (mot_masq.indexOf('*') != -1) {
		    		trouve = false;
		    	} else {
		    		trouve = true;
		    		switch (7-pendu)
			    		{
			    		  case 7:
				    		    points = 100;
				    		    break;
			    		  case 6:
				    		    points = 50;
				    		    break;  
			    		  case 5:
				    		    points = 35;
				    		    break;  
			    		  case 4:
				    		    points = 25;
				    		    break;  
			    		  case 3:
				    		    points = 15;
				    		    break;  
			    		  case 2:
				    		    points = 10;
				    		    break;  
			    		  case 1:
				    		    points = 5;
				    		    break;  
			    		}
		    		score.setText("Bravo vous avez trouvé !\nVotre score est de " + points + " points");
		    		ecrireFichier("score.txt", Integer.toString(points)+";");
					
		    		saisie_pseudo();
		    	}
	
		    	// La derniere image a été affiché, le joueur a perdu
		    	if (pendu == 7) {
		    		score.setText("Vous avez perdu !\n" + "Le mot était : " + mot);
			   	}
	    	}
	    }    
	}
	
	private int getNombre() {
		// Donne un entier au hasard entre 0 et 336529 (nb de ligne du dictionnaire)
		int nbre = (int)(Math.random()*336529);
		return nbre;
	}
	
	private String getMot(int nb) {
		
		// Recherche le mot dans le fichier à la ligne nb
		String str = null;
		// Le "/" car à la racine du jar
		String fichier = "/dictionnaire.txt";
		
		//lecture du fichier texte et récupération du mot
		try{
			InputStream ips = getClass().getResourceAsStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			for (int i = 0 ; i < nb ; i++) {
				str = br.readLine();
			}

			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return str;
	}
	
	private void initMot() {
		
		// Initialise le mot à trouver
		mot = sansAccent(getMot(getNombre()));
		mot_maj = mot.toUpperCase();
		
		System.out.println(mot);
	    
	    // Constitution du masque
	    for (int i = 0 ; i < mot.length() ; i++) {
	    	mot_masq += "*";
	    }
	}
	
	private static String sansAccent(String s) {
		
		// Enleve les éventuels accents de la string s
		final String accents = "ÀÁÂÃÄÅàáâãäåÈÉÊËèéêëÇçÖÔöôÎÏîïÛÜûü";
		final String letters = "AAAAAAaaaaaaEEEEeeeeCcOOooIIiiUUuu";
 
		StringBuffer buffer = null;
		for(int i = s.length()-1 ; i >= 0; i--) {
			int index = accents.indexOf(s.charAt(i));
			if (index >= 0) {
				if (buffer == null) {
					buffer = new StringBuffer(s);
				}
				buffer.setCharAt(i, letters.charAt(index));
			}
		}
		return buffer == null ? s : buffer.toString();
	}
	
	public void ecrireFichier(String nomFic, String texte)
	{
		//on va chercher le chemin et le nom du fichier et on me tout ca dans un String
		String adressedufichier = System.getProperty("user.dir") + "/"+ nomFic;
	
		//on met try si jamais il y a une exception
		try
		{
			/**
			 * BufferedWriter a besoin d un FileWriter, 
			 * les 2 vont ensemble, on donne comme argument le nom du fichier
			 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 
			 
			 */
			FileWriter fw = new FileWriter(adressedufichier, true);
			
			// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			output.write(texte);
			//on peut utiliser plusieurs fois methode write
			
			output.flush();
			//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
			
			output.close();
			//et on le ferme
		}
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
			}

	}
	
	private void saisie_pseudo () {
		Fenetre_pseudo fp = new Fenetre_pseudo();
		
		String le_pseudo = fp.getPseudo();


		try {
		    Thread.sleep(60000);                 //1 minute
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		
		System.out.println("************");
		System.out.println("Nouveau Panel : "+le_pseudo);
	}
}
