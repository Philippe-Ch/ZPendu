package com.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel implements Serializable {
		
	/**
	* 
	*/
	private static final long serialVersionUID = 9168474439124411284L;
	private String scores;

	public ScorePanel(JPanel panel) {

		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		
		JLabel titre = new JLabel("Voici les scores\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(new Font("Comics Sans MS", Font.BOLD, 20));
		panel.add(titre, BorderLayout.NORTH);
		
		scores = lireFichier("score.txt");
		System.out.println(scores);

	}

	public String lireFichier(String nomFic) {
		
		// on va chercher le chemin et le nom du fichier et on me tout ca dans un String
		String adressedufichier = System.getProperty("user.dir") + "/"+ nomFic;
		String ligne = null;
		
		// on met try si jamais il y a une exception
		try
		{
			FileReader fr = new FileReader(adressedufichier);
			BufferedReader br = new BufferedReader(fr);
			ligne = br.readLine();
			br.close();
		}
		catch(IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
		
		return ligne;
	}
	
}
