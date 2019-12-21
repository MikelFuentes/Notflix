package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import database.Pelicula;
import database.Sesion;
import database.Tag;
import database.Visual;

public class Editor extends JPanel {
	
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
    private JLabel ltags2;
    private JButton bEliminarTags;
    private JList<Tag> tAtags;
    
    private Visual visual;
    private JFrame frame;
    private ArrayList<Tag> tags;
    private Tag selTag;
    DefaultListModel<Tag> modelTag2 = new DefaultListModel<>();
    private JScrollPane SpList;
    
    

    public Editor(Sesion sesion, Seleccionador sel, Visual pelSel) {
        //construct preComponents
//    	pelicula = sel.getPeliSel();
    	
    	frame = new JFrame ("Cragador");
    	frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    	
    	this.visual = pelSel;
    	tags = sesion.cogerTagsNoSeleccionados(pelSel.getId()); //saco todos los tags que tengo en la base de datos
    	
//    	for (int i = 0; i < tags.size(); i++) {
//			Tag tagAcomparar = tags.get(i);			//meto uno a uno en 
//			System.out.println(visual.getTags().size());
//			
//			for (int j = 0; j < visual.getTags().size(); j++) {
//				
//				System.out.println("Comparo > " + tagAcomparar + " con > " + visual.getTags().get(j));
//				System.out.println(tagAcomparar.getNombre_tag().equals(visual.getTags().get(j).getNombre_tag()));
//				
//				if(tagAcomparar.getNombre_tag().equals(visual.getTags().get(j).getNombre_tag())) {
//					
//					System.out.println("entor y elimino >" + tags.get(i) + "< porque = " + visual.getTags().get(j).getNombre_tag());
//					tags.remove(i);
//					j = visual.getTags().size() - 1;
//					
//					
//				}
//			}
//			
//		}
    	
    	for (int i = 0; i < visual.getTags().size(); i++) {
    		System.out.println(visual.getTags().get(i));
			modelTag2.addElement(visual.getTags().get(i));
    	}
    	
    	
    	tAtags = new JList<Tag>(modelTag2);
    	tAtags.addListSelectionListener(new ListSelectionListener() {
    		
    		@Override
    		public void valueChanged(ListSelectionEvent e) {
    			
    			if (tAtags.getValueIsAdjusting() == false){
    				selTag = tAtags.getSelectedValue();
    				System.out.println(tAtags.getSelectedValue());
    			}
    			
    			
    		}
    	});


        //construct components
        bAceptar = new JButton ("Aceptar");
        bCancelar = new JButton ("Cancelar");
        tNombre = new JTextField (5);
        tDirector = new JTextField (5);
        tAño = new JTextField (5);
        lNombre = new JLabel ("Nombre");
        cBTags = new JComboBox (tags.toArray());
        lDirector = new JLabel ("Director");
        lTags = new JLabel ("Tags");
        lAño = new JLabel ("Año");
        bImagen = new JButton ("Seleccionar Imagen");
        bMedia = new JButton ("Selecionar Media");
        aTimagen = new JTextArea (5, 5);
        aTarchi = new JTextArea (5, 5);
        bAñadirTag = new JButton ("Añadir Tag");
        ltags2 = new JLabel ("Tags");
        bEliminarTags = new JButton ("Eliminar Tag");
        
        SpList = new JScrollPane(tAtags);
        SpList.setViewportView(tAtags);

        //adjust size and set layout
        frame.setPreferredSize (new Dimension (944, 574));
        frame.setLayout (null);
        frame.setSize(new Dimension (944, 574));
        frame.setResizable(false);


        //add components
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
        
        frame.add (tAtags);
        frame.add (bEliminarTags);
        frame.add (ltags2);
       

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
        aTimagen.setBounds (580, 250, 300, 130);
        aTarchi.setBounds (580, 430, 300, 55);
        bAñadirTag.setBounds (310, 250, 100, 50);
        ltags2.setBounds (440, 115, 100, 25);
        bEliminarTags.setBounds (440, 350, 105, 50);
        tAtags.setBounds (440, 140, 105, 210);
//        rbPeli.setBounds(600, 25, 100, 15 );
//        rbDoc.setBounds(800, 25, 100, 15 );
       
        frame.setVisible (true);
    }
}

