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
	private JFrame frame;

	public CrearUsuario(Sesion sesion, Seleccionador sele) {
		//construct components
		frame = new JFrame("Crear Usuario");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		lUsuario = new JLabel ("Nuevo usuario:");
		tfusuario = new JTextField (5);
		bAceprtar = new JButton ("Aceptar");
		bCancelar = new JButton ("Cancelar");

		//adjust size and set layout
		frame.setPreferredSize (new Dimension (399, 202));
		frame.setLayout (null);
		frame.setSize(new Dimension (399, 202));
		setLayout (null);
		frame.setResizable(false);

		//add components
		frame.add (lUsuario);
		frame.add (tfusuario);
		frame.add (bAceprtar);
		frame.add (bCancelar);

		//set component bounds (only needed by Absolute Positioning)
		lUsuario.setBounds (35, 30, 100, 25);
		tfusuario.setBounds (35, 55, 325, 30);
		bCancelar.setBounds (205, 110, 155, 40);
		bAceprtar.setBounds (35, 110, 155, 40);
		
		
		bAceprtar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = tfusuario.getText();
				if (nombre.equals("")) {
					new Error("Añada nombre del usuario");
				}else {
					sesion.anadirUsuario(nombre);
					sele.actualizarUsuarios(sesion);
					cerrar();
				}
				
				
			}
		});
		frame.setVisible (true);
	}
	private void cerrar() {
		this.frame.dispose();

	}


//	public static void main (String[] args) {
//		JFrame frame = new JFrame ("MyPanel");
//		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
////		frame.getContentPane().add (new MyPanel());
//		frame.pack();
//		frame.setVisible (true);
//	}
}
	

