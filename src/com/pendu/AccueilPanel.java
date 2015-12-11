package com.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AccueilPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6129546731571496674L;

	public AccueilPanel(JPanel panel) {

		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		
		// Affichage titre
		JLabel titre = new JLabel("Bienvenue dans le jeu du pendu\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
		panel.add(titre, BorderLayout.NORTH);
	
		// Affichage image
		// Le "/" car à la racine du jar
		JLabel image = new JLabel(new ImageIcon(getClass().getResource("/accueil.jpg")));
	    panel.add(image, BorderLayout.CENTER);
	
	    // Affichage texte
		JTextArea texte = new JTextArea("Vous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !\n" +
				"Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !\n" +
				"Proverbe :\t« Pas vu, pas pris !\n" +
					"\tPris ! PENDU ! »");
		texte.setEditable(false);
		texte.setFont(new Font("Comics Sans MS", Font.BOLD, 15));
		panel.add(texte, BorderLayout.SOUTH);
	}

}
