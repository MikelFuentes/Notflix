package gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Error extends JPanel {
    private JLabel lError;
    private JButton bAceptar;
    private JFrame frame;

    public Error(String textoerror) {
        
    	frame = new JFrame ("ERROR");
        lError = new JLabel (textoerror);
        bAceptar = new JButton ("Aceptar");



        frame.setPreferredSize (new Dimension (341, 139));
        frame.setLayout (null);

        //add components
        frame.add (lError);
        frame.add (bAceptar);
        lError.setHorizontalAlignment(JLabel.CENTER);


        lError.setBounds (115, 35, 100, 25);
        bAceptar.setBounds (115, 85, 100, 25);
        frame.setSize(340, 200);
        this.frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible (true);
        frame.setResizable(false);
        
        bAceptar.addActionListener(new ActionListener() {
			
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
				
			}
		});
    }


	
    private void cerrar() {
    	this.frame.dispose();
    }
    
   


//    public static void crearError() {
//        JFrame frame = new JFrame ("MyPanel");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
////        frame.getContentPane().add (new Error(textoerror));
//        frame.pack();
//        frame.setVisible (true);
//    }
}