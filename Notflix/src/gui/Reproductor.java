package gui;


import javax.swing.*;

import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;



import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;

import java.awt.*;
import java.awt.event.*;


public class Reproductor extends JFrame {
    private static final long serialVersionUID = 1L;

    private static String ruta_a_vlc="usr/bin/vlc";//TODO
    private static Reproductor miVentana;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JProgressBar pbReproduccion;
    public Reproductor() {
        setTitle("Player");
        setSize(800, 600);
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        add( mediaPlayerComponent, BorderLayout.CENTER );
        JPanel pBotonera = new JPanel();
        JButton bPlayPausa = new JButton( "Play/Pausa" );
        pBotonera.add( bPlayPausa );
        pbReproduccion = new JProgressBar( 0, 1000 );
        pbReproduccion.setPreferredSize( new Dimension( 200, 20 ) );
        pBotonera.add( pbReproduccion );
        add( pBotonera, BorderLayout.SOUTH );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().stop();
                mediaPlayerComponent.mediaPlayer().release();
            }
        });
        bPlayPausa.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mediaPlayerComponent.mediaPlayer().status().isPlaying())
                    mediaPlayerComponent.mediaPlayer().controls().pause();
                else
                    mediaPlayerComponent.mediaPlayer().controls().play();
            }
        });
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener( new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                pbReproduccion.setValue( (int) (1000L * newTime / mediaPlayer.status().length()) );
            }
        });
        pbReproduccion.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().setTime(
                        mediaPlayerComponent.mediaPlayer().status().length() * e.getX() / pbReproduccion.getWidth() );
            }
        });
    }


    public static void main(String[] args) {
        String ruta_vid = "/home/sweos/Downloads/shrek-01.avi.mp4";
        Reproducir(ruta_vid);
    }




    public static void Reproducir(String ruta){
        boolean found = (new NativeDiscovery()).discover();
        if (!found) System.setProperty("jna.library.path", ruta_a_vlc);
        miVentana = new Reproductor();
        miVentana.mediaPlayerComponent.mediaPlayer().audio().setVolume( 100 );
        miVentana.mediaPlayerComponent.mediaPlayer().media().play(ruta);
}

}
