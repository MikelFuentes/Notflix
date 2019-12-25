package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Idioma;
import database.Pelicula;
import database.Sesion;
import database.Tag;
import database.Tema;
import database.Visual;
import gui.Error;

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
    
    private ArrayList<Tema> temas;
    private ArrayList<Idioma> idiomas;
    private JRadioButton rbDoc;
    private JRadioButton rbPeli;
    private ButtonGroup grupo;
    private JComboBox<String> cbTema;
    private JComboBox<String> cbIdioma;
    
    

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
//TODO EL ERROR ESTA AQUI VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV    	
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


        frame.setPreferredSize (new Dimension (944, 574));
        frame.setLayout (null);
        frame.setSize(new Dimension (944, 574));
        frame.setResizable(false);

        
        aTarchi.setEditable(false);
        aTimagen.setEditable(false);
        
        rbDoc = new JRadioButton("Documental");
        rbPeli = new JRadioButton("Pelicula");
        
        
        grupo = new ButtonGroup();
        grupo.add(rbDoc);
        grupo.add(rbPeli);
        
        idiomas = sesion.buscartaClaTem("%");
        cbTema = new JComboBox(idiomas.toArray());
		
        idiomas = sesion.buscartaClaIdioma("%");
		cbIdioma = new JComboBox(idiomas.toArray());
        
    
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
        
        frame.add(rbDoc);
        frame.add(rbPeli);
        frame.add(cbTema);
        frame.add(cbIdioma);
       
        tNombre.setText(pelSel.getNombre());
        tDirector.setText(pelSel.getDirector());
        tAño.setText(pelSel.getAño());
        aTimagen.setText(pelSel.getImagen());
        aTarchi.setText(pelSel.getArchi());
        
        
        
        
        
        
        bAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
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
                    aTarchi.setText(path);
                } else {
                    new Error("No se ha seleccionado ningun archivo.");
                }
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
                    aTimagen.setText(path);
                } else { 
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
        
        rbDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
						
				cbTema.setBounds(600, 100, 235, 50 );
				frame.getContentPane().revalidate();
				cbTema.setVisible(true);
				if (cbIdioma.isVisible()) {
					cbIdioma.setVisible(false);
				}
			}
		});
        
        rbPeli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cbIdioma.setBounds(600, 100, 235, 50 );
				frame.getContentPane().revalidate();
				cbIdioma.setVisible(true);
				if (cbTema.isVisible()){
					cbTema.setVisible(false);
				}
				
			}
		});
        
        
        
        
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
        		String tema = (String) cBTags.getSelectedItem().toString();
        		String idioma = (String) cbIdioma.getSelectedItem().toString();
        		ArrayList <Integer> tags = new ArrayList<Integer>();
        		for (int i = 0; i < modelTag2.getSize(); i++) {
        			tags.add(modelTag2.getElementAt(i).getId_tag());
        		}



        		System.out.println(nom); 
        		if (ruta_archivo.equals("")	) {
        			new Error("<html>Por favor, selecciona ruta del archivo</html>");
        			return;
        		}
        		if (ruta_imagen.equals("")) {
        			new Error("<html>Por favor, seleccione ruta de la imagen</html>");
        			return;
        		}
        		if (nom.equals("") || dir.equals("") || anyo.equals("")) {
        			new Error("<html>Por favor, rellene todos los campos</html>");
        			return;
        		}
        		if (tema.equals(null) && idioma.equals(null)) {
        			new Error("<html>por favor selecciones tema o Idioma</html>");
        			return;

        		}
        		try {
        			Integer.parseInt(anyo);
        		} catch (Exception e2) {
        			new Error("<html>Por favor selecciones introduzca números para el año en el que se filmó la película</html>");
        			return;
        		}
        		//ArrayList<integer> =  //TODO SACAR LAS id de los tags añadidos
        		if (rbDoc.isSelected()) {
        			sesion.Editar_peli(nom, dir, ruta_archivo, ruta_imagen, tags, anyo, tema, "tema", pelSel.getId());
        		}else if (rbPeli.isSelected()) {
        			sesion.Editar_peli(nom, dir, ruta_archivo, ruta_imagen, tags, anyo, idioma, "idioma", pelSel.getId());
        		}

        		sel.actualizar(sesion);
        		cerrar();
        	}
        });
        
        
        
        if (pelSel instanceof Pelicula) {
        	rbPeli.setSelected(true);
        	cbIdioma.setBounds(600, 100, 235, 50 );
        	frame.getContentPane().revalidate();
        	cbIdioma.setVisible(true);
        	// TODO QUE COJA EL IDIOMA DE LA PELICULA
        	if (cbTema.isVisible()){
        		cbTema.setVisible(false);
        	}
		}
  

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
        rbPeli.setBounds(600, 25, 100, 15 );
        rbDoc.setBounds(800, 25, 100, 15 );
       
        frame.setVisible (true);
    }
    private void cerrar(){
    	this.frame.dispose();
    }
}

