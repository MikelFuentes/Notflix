package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import database.Sesion;
public class CrearUsuario extends JPanel  {

	private JLabel lUsuario;
	private JTextField tfusuario;
	private JButton bAceprtar;
	private JButton bCancelar;

	public CrearUsuario(Sesion sesion) {
		//construct components
		lUsuario = new JLabel ("Nuevo usuario:");
		tfusuario = new JTextField (5);
		bAceprtar = new JButton ("Aceptar");
		bCancelar = new JButton ("Cancelar");

		//adjust size and set layout
		setPreferredSize (new Dimension (399, 202));
		setLayout (null);

		//add components
		add (lUsuario);
		add (tfusuario);
		add (bAceprtar);
		add (bCancelar);

		//set component bounds (only needed by Absolute Positioning)
		lUsuario.setBounds (35, 30, 100, 25);
		tfusuario.setBounds (35, 55, 325, 30);
		bAceprtar.setBounds (35, 110, 155, 40);
		bCancelar.setBounds (205, 110, 155, 40);
		
		
	}


	public static void main (String[] args) {
		JFrame frame = new JFrame ("MyPanel");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add (new MyPanel());
		frame.pack();
		frame.setVisible (true);
	}
}
	

