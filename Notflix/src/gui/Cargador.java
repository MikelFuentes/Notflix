package gui;


import database.Sesion;
import database.Tag;

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
    private ArrayList<Tag> tags;
    private JLabel ltags2;
    private JButton bEliminarTags;
    private JTextArea tAtags;

    public Cargador(Sesion sesion) {
        
    	frame = new JFrame ("Cragador");
    	frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    	
    	tags = sesion.buscartaCla("%");
        //String[] jcomp7Items = {"Item 1", "Item 2", "Item 3"};
    	ArrayList<String> nomtags = new ArrayList<String>();
    	for (int i = 0; i < tags.size(); i++ ) {
    		  nomtags.add(tags.get(i).getNombre_tag());
    	}
        
        bAceptar = new JButton ("Aceptar");
        bCancelar = new JButton ("Cancelar");
        tNombre = new JTextField (5);
        tDirector = new JTextField (5);
        tAño = new JTextField (5);
        lNombre = new JLabel ("Nombre");
        cBTags = new JComboBox (nomtags.toArray());
        lDirector = new JLabel ("Director");
        lTags = new JLabel ("Tags");
        lAño = new JLabel ("Año");
        bImagen = new JButton ("Seleccionar Imagen");
        bMedia = new JButton ("Selecionar Media");
        aTimagen = new JTextArea (5, 5);
        aTarchi = new JTextArea (5, 5);
        bAñadirTag = new JButton ("Añadir tags");
        ltags2 = new JLabel ("Tags");
        bEliminarTags = new JButton ("Eliminar Tag");
        tAtags = new JTextArea (5, 5);

        

        
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
        
        frame.add (ltags2);
        frame.add (bEliminarTags);
        frame.add (tAtags);
        
        
        bAceptar.addActionListener(new ActionListener() {
			
			@Override
			
			//Recopila los datos del creador y lo pasa a la base de datos
			public void actionPerformed(ActionEvent e) {
//				new Error("Falta implementar aceptar");
				
				String nom = tNombre.getText();
				String dir = tDirector.getText();
				String anyo = tAño.getText();
				String ruta_archivo = aTarchi.getText();
				String ruta_imagen = aTimagen.getText();
				ArrayList <Integer> tags = new ArrayList<Integer>();
				tags.add(cBTags.getSelectedIndex());
				
				System.out.println(nom); 
				if (ruta_archivo.equals("")	) {
					new Error("Por favor, selecciona ruta del archivo");
					return;
				}
				if (ruta_imagen.equals("")) {
					new Error("Por favor, seleccione ruta de la imagen");
				}
				if (nom.equals("") || dir.equals("") || anyo.equals("")) {
					System.out.println("entro");
					new Error("Por favor, rellene todos los campos");
				}
				//ArrayList<integer> =  //TODO SACAR LAS id de los tags añadidos
				

				sesion.meter_peli(nom, dir, ruta_archivo, ruta_imagen, tags, anyo);
				
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
//				new Error("Falta implementar anadir imagen");
                JFileChooser fileChooser = new JFileChooser(".");  																	//Create this to start an explorer
                FileFilter filter = new FileNameExtensionFilter("Archivos multimedia (.png, .jpg, .jpeg)", "png","jpeg","jpg"); 	//filter files to show only media files
                fileChooser.setFileFilter(filter);
                int valor = fileChooser.showOpenDialog(fileChooser); 																//open file explorer
                if(valor == JFileChooser.APPROVE_OPTION){            																// if a file is chosen
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
//                    File f = new File(path);
                    aTimagen.setText(path);
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
        
        
        
        
        bAceptar.setBounds (60, 450, 150, 50);
        bCancelar.setBounds (260, 450, 150, 50);
        tNombre.setBounds (60, 50, 350, 50);
        tDirector.setBounds (60, 150, 350, 50);
        tAño.setBounds (60, 350, 350, 50);
        lNombre.setBounds (60, 25, 100, 25);
        cBTags.setBounds (60, 250, 235, 50);
        lDirector.setBounds (60, 125, 100, 25);
        lTags.setBounds (60, 225, 100, 25);
        lAño.setBounds (60, 325, 100, 25);
        bImagen.setBounds (580, 375, 300, 25);
        bMedia.setBounds (580, 475, 300, 25);
        aTimagen.setBounds (580, 50, 300, 330);
        aTarchi.setBounds (580, 430, 300, 55);
        bAñadirTag.setBounds (310, 250, 100, 50);
        ltags2.setBounds (440, 115, 100, 25);
        bEliminarTags.setBounds (440, 350, 105, 50);
        tAtags.setBounds (440, 140, 105, 215);
        
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
