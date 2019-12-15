package gui;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.DefaultMetalTheme;

import database.Pelicula;
import database.Sesion;
import database.Visual;

@SuppressWarnings("serial")
public class Seleccionador extends JPanel {
	
	DefaultListModel<Visual> model = new DefaultListModel<>();
    private JList<Visual> lBuscar;
    
    private JTextField tBuscar;
    private JButton bBuscar;
    private JButton bVer;
    private JLabel lTags;
    private JTextArea areaTags;
    private JButton bEditar;
    private JButton bAnadir;
    private JScrollPane SpList;
    private Seleccionador Selecionador = this;
    private BufferedImage bufImg;
    private ImageIcon imgIc;
    private JLabel imgLbl;
    private Visual peliSel;
   
    

    public Seleccionador( Sesion sesion ) {
       
       // String[] lBuscarItems = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10" ,"Item 11" ,"Item 12" ,"Item 13","Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10" ,"Item 11" ,"Item 12" ,"Item 13"};
    	
    	actualizar(sesion);
    	imgLbl = new JLabel();
    	add (imgLbl);
        lBuscar = new JList<Visual>(model);
        
        lBuscar.addListSelectionListener(new ListSelectionListener() {
        
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if (lBuscar.getValueIsAdjusting() == false){
					peliSel = lBuscar.getSelectedValue();
					if (peliSel == null) {
						imgLbl.setIcon(null);
						return;
					}
					try {
						ImageIcon imageIcon = new ImageIcon(peliSel.getImagen());
						Image image = imageIcon.getImage();
						Image newimg = image.getScaledInstance(260, 260,  java.awt.Image.SCALE_SMOOTH);
						imageIcon = new ImageIcon(newimg); 
												
						imgLbl.setIcon(imageIcon);
					} catch (Exception e2) {
						new Error("intentelo otra vez");
					}					
	
					
					System.out.println(peliSel.getImagen());
					
				}
				
				
			}
		});
        
       
        
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
        imgLbl.setBounds(400, 110, 260, 260);
       
        
        bAnadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Cargador(sesion, Selecionador);
				
			}
		});
        bBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String busqueda = tBuscar.getText();
				ArrayList<Visual> peli_ini = sesion.buscar2("%"+ busqueda + "%");
				
				model.removeAllElements();
				
				for(int i = 0; i < peli_ini.size(); i++) {
		        	model.addElement(peli_ini.get(i));
		        }

			}
		});
        
        bEditar.addActionListener(new ActionListener () {
        	
        	public void actionPerformed(ActionEvent e) {
        		new Editor(sesion, Selecionador);
        	}
        	
        });
        
    }
    // TODO	HACER UN METODO PARA ESTO for(int i = 0; i < peli_ini.size(); i++) {
    //	model.addElement(peli_ini.get(i));
    //}
    
    public void actualizar(Sesion sesion) {
    	model.removeAllElements();
    	ArrayList<Visual> peli_ini = sesion.buscar2("%");
     	sesion.buscar2("%");
         
         for(int i = 0; i < peli_ini.size(); i++) {
         	model.addElement(peli_ini.get(i));
         }
    }
    
    public Visual getPeliSel() {
		return peliSel;
	}


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