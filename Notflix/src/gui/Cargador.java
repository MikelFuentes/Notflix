package gui;


import database.Sesion;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Cargador extends JPanel {
    private JButton bAceptar;
    private JButton bCancelar;
    private JTextField tNombre;
    private JTextField tDirector;
    private JTextField tAño;
    private JLabel lNombre;
    private JComboBox cBTags;
    private JLabel lDirector;
    private JLabel lTags;
    private JLabel lAño;
    private JButton bImagen;
    private JButton bMedia;
    private JTextArea aTimagen;
    private JTextArea aTarchi;
    private JButton bAñadirTag;
    private JFrame frame;

    public Cargador(Sesion sesion) {
        
    	frame = new JFrame ("Cragador");
    	frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        String[] jcomp7Items = {"Item 1", "Item 2", "Item 3"};

        
        bAceptar = new JButton ("Aceptar");
        bCancelar = new JButton ("Cancelar");
        tNombre = new JTextField (5);
        tDirector = new JTextField (5);
        tAño = new JTextField (5);
        lNombre = new JLabel ("Nombre*");
        cBTags = new JComboBox (jcomp7Items);
        lDirector = new JLabel ("Director");
        lTags = new JLabel ("Tags");
        lAño = new JLabel ("Año");
        bImagen = new JButton ("Seleccionar Imagen*");
        bMedia = new JButton ("Selecionar Media*");
        aTimagen = new JTextArea (5, 5);
        aTarchi = new JTextArea (5, 5);
        bAñadirTag = new JButton ("Añadir tags");

        
        frame.setPreferredSize (new Dimension (944, 574));
        frame.setLayout (null);
        frame.setSize(new Dimension (944, 574));
        frame.setResizable(false);
        
        
        aTarchi.setEditable(false);
        aTimagen.setEditable(false);
        
        frame.add (bAceptar);
        frame.add (bCancelar);
        frame.add (bImagen);
        frame.add (bMedia);
        frame.add (bAñadirTag);
        
        frame.add (tNombre);
        frame.add (tDirector);
        frame.add (tAño);
        
        frame.add (cBTags);
        
        frame.add (lNombre);
        frame.add (lDirector);
        frame.add (lTags);
        frame.add (lAño); 
        
        frame.add (aTimagen);	//area de la imagen
        frame.add (aTarchi);	//que archivo está cargado
        
        bAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				new Error("Falta implementar aceptar");
				
				String nom = tNombre.getText();
				String dir = tDirector.getText();
				String ruta_archivo = aTarchi.getText();
				String ruta_imagen = aTimagen.getText();
				if (ruta_archivo == null || ruta_imagen == null || nom == null) {
					new Error("Por favor, rellena los campos con * ");
					return;
				}
				//ArrayList<integer> =  //TODO SACAR LAS id de los tags añadidos
				

				//sesion.meter_peli(String nom, String dir, String ruta_archivo, String ruta_imagen, ArrayList<Integer> id_tags, String año);
				
			}
		});
        
        bAñadirTag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Error("Falta implementar anadir tags");
				
			}
		});
        
        bImagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Error("Falta implementar anadir imagen");
				
			}
		});
        
        bMedia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");  													//Create this to start an explorer
                FileFilter filter = new FileNameExtensionFilter("Archivos multimedia (.mp4, .vlc)", "mp4","vlc"); 	//filter files to show only media files
                fileChooser.setFileFilter(filter);
                int valor = fileChooser.showOpenDialog(fileChooser); 												//open file explorer
                if(valor == JFileChooser.APPROVE_OPTION){            												// if a file is chosen
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
//                    File f = new File(path);
                    aTarchi.setText(path);
//                    try{
//                        File f = new File(path);
//                        //aTarchi.setText(path);
//                    }
//                    catch (FileNotFoundException err){ //chosen file couldn't be found, it isn't thrown in the Error class
//                        new Error("Fichero no encontrado.");
//
//                    }
                } else { //just closed without selecting file
                    new Error("No se ha seleccionado ningun archivo.");
                }
            }
		});
        
        bCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
				
			}
		});
        
        
        
        
        bAceptar.setBounds (100, 450, 150, 50);
        bCancelar.setBounds (300, 450, 150, 50);
        tNombre.setBounds (100, 50, 350, 50);
        tDirector.setBounds (100, 150, 350, 50);
        tAño.setBounds (100, 350, 350, 50);
        lNombre.setBounds (100, 25, 100, 25);
        cBTags.setBounds (100, 250, 235, 50);
        lDirector.setBounds (100, 125, 100, 25);
        lTags.setBounds (100, 225, 100, 25);
        lAño.setBounds (100, 325, 100, 25);
        bImagen.setBounds (490, 375, 300, 25);
        bMedia.setBounds (490, 475, 300, 25);
        aTimagen.setBounds (490, 50, 300, 330);
        aTarchi.setBounds (490, 430, 300, 55);
        bAñadirTag.setBounds (350, 250, 100, 50);
        
        frame.setVisible (true);
    }

    private void cerrar(){
    	this.frame.dispose();
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("MyPanel");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new Cargador());
//        frame.pack();
//        frame.setVisible (true);
//    }
    
}
