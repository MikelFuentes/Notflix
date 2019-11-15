package gui;



import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.DefaultMetalTheme;

import database.Sesion;

@SuppressWarnings("serial")
public class Seleccionador extends JPanel {
	
	DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> lBuscar;
    
    private JTextField tBuscar;
    private JButton bBuscar;
    private JButton bVer;
    private JLabel lTags;
    private JTextArea areaTags;
    private JButton bEditar;
    private JButton bAnadir;
    private JScrollPane SpList;
    
    

    public Seleccionador( Sesion sesion ) {
       
       // String[] lBuscarItems = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10" ,"Item 11" ,"Item 12" ,"Item 13","Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10" ,"Item 11" ,"Item 12" ,"Item 13"};
    	ArrayList<String> peli_ini = sesion.buscar("%");
    	sesion.buscar2("%");

        lBuscar = new JList<>(model);
        
        for(int i = 0; i < peli_ini.size(); i++) {
        	model.addElement(peli_ini.get(i));
        }
        
        tBuscar = new JTextField (5);
        bBuscar = new JButton ("Buscar");
        bVer = new JButton ("Ver");
        lTags = new JLabel ("newLabel");
        areaTags = new JTextArea (5, 5);
        bEditar = new JButton ("Editar");
        bAnadir = new JButton ("Añadir");

        
        areaTags.setEnabled (false);
        SpList = new JScrollPane(lBuscar);
        
        
        SpList.setViewportView(lBuscar);
        
        setPreferredSize (new Dimension (756, 574));
        setLayout (null);
        
        
        //add (lBuscar);
        add (tBuscar);
        add (bBuscar);
        add (bVer);
        add (lTags);
        add (areaTags);
        add (bEditar);
        add (bAnadir);
        add (SpList);

        
        //lBuscar.setBounds (80, 110, 250, 350);
        tBuscar.setBounds (80, 60, 250, 30);
        bBuscar.setBounds (365, 60, 100, 30);
        bVer.setBounds (85, 485, 150, 30);
        lTags.setBounds (405, 385, 255, 75);
        areaTags.setBounds (400, 385, 260, 75);
        bEditar.setBounds (305, 485, 150, 30);
        bAnadir.setBounds (515, 485, 150, 30);
        SpList.setBounds(80, 110, 250, 350);
        
        bAnadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Cargador(sesion);
				
			}
		});
        bBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String busqueda = tBuscar.getText();
				ArrayList<String> peli_ini = sesion.buscar("%"+ busqueda + "%");
				
				model.removeAllElements();
				
				for(int i = 0; i < peli_ini.size(); i++) {
		        	model.addElement(peli_ini.get(i));
		        }

			}
		});
    }
    // TODO	HACER UN METODO PARA ESTO for(int i = 0; i < peli_ini.size(); i++) {
    //	model.addElement(peli_ini.get(i));
    //}


    public static void main (String[] args) {
    	Sesion sesion = new Sesion();
    	sesion.Crear();
    	
        JFrame frame = new JFrame ("Seleccionador");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Seleccionador(sesion));
        frame.pack();
        frame.setVisible (true);
        frame.setResizable(false);
    }
}