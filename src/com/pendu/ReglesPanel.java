package com.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReglesPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 809380096150180086L;

	public ReglesPanel(JPanel panel){

		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		
		// Le titre
		JLabel titre = new JLabel("Les regles");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
		panel.add(titre, BorderLayout.NORTH);
		
		// Le contenu
		JTextArea texte = new JTextArea();
		texte.setText("\n\nVous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !\n" +
				"Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !\n" +
				"\n\nCOMPTE DES POINTS :\n\n\tMot trouvé sans erreur\t\t100 pts\n\tMot trouvé avec une erreur\t50 pts\n" +
				"\tMot trouvé avec deux erreurs\t35 pts\n\tMot trouvé avec trois erreurs\t25 pts\n\tMot trouvé avec quatre erreurs\t15 pts\n" +
				"\tMot trouvé avec cinq erreurs\t10 pts\n\tMot trouvé avec six erreurs\t5 pts\n\n\n" +
				"Je vous souhaite bien du plaisir !\nSi vous pensez pouvoir trouver un mot en un seul essai, c'est que vous croyez que le dictionnaire est petit.\n" +
				"Or, pour votre information, il contient plus de 336 000 mots… Bonne chance ! ;)"
				);
		texte.setFont(new Font("Comics Sans MS", Font.BOLD, 15));
		panel.add(texte, BorderLayout.CENTER);

	}

}