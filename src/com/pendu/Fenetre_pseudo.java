package com.pendu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre_pseudo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8926358161051604901L;
	
	// Pour fenêtre de saisie du pseudo
	private JPanel container = new JPanel();
	private JTextField jtf = new JTextField("ici");;   
	private JLabel label = new JLabel("Saisir votre Pseudo");
	private JButton b = new JButton ("OK");
	
	public String pseudo;
	
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return this.pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Fenetre_pseudo() {
		this.setTitle("Votre pseudo");
	    this.setSize(300, 120);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);

	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());

	    jtf = new JTextField();
	    JPanel top = new JPanel();   
 
	    Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    jtf.setForeground(Color.BLUE);
	    //On ajoute l'écouteur à notre composant
	    b.addActionListener(action);
	    
	    top.add(label);
	    top.add(jtf);
	    top.add(b);

	    this.setContentPane(top);
	    this.setVisible(true);   
	}

	ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent evenement) { 
			setPseudo(jtf.getText());
			setVisible(false);
			System.out.println("************");
			System.out.println("Fenetre pseudo : "+pseudo);
		}
	};

}