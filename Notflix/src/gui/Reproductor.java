package gui;


import javax.swing.*;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;



import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.fullscreen.FullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.fullscreen.windows.Win32FullScreenStrategy;

import java.awt.*;
import java.awt.event.*;


public class Reproductor extends JFrame {
    private static final long serialVersionUID = 1L;

    private static String ruta_a_vlc="usr/bin/vlc";//TODO
    private static Reproductor miVentana;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JProgressBar pbReproduccion;
    private JProgressBar pbVolume;
    private int savedVolume;
    public Reproductor() {
        setTitle("Player");
        setSize(800, 600);
//        MediaPlayerFactory patata = new MediaPlayerFactory();
//        MediaPlayer mediaPlayer= patata.mediaPlayers().newEmbeddedMediaPlayer();
//        ((EmbeddedMediaPlayer) mediaPlayer).fullScreen().strategy(new AdaptiveFullScreenStrategy(miVentana));
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        add(mediaPlayerComponent, BorderLayout.CENTER);

        mediaPlayerComponent.mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(this));
        JPanel pBotonera = new JPanel();
        JButton bPlayPausa = new JButton("Play/Pause");
        pBotonera.add(bPlayPausa);
        pbReproduccion = new JProgressBar(0, 1000);
        pbReproduccion.setPreferredSize(new Dimension(200, 20));
        pBotonera.add(pbReproduccion);
        JButton bMute = new JButton("Mute/Unmute");
        pbVolume = new JProgressBar(0, 100);
        pbVolume.setPreferredSize(new Dimension(100, 10));
        pBotonera.add(bMute);
        pBotonera.add(pbVolume);
        pbVolume.setValue(mediaPlayerComponent.mediaPlayer().audio().volume());
        JButton bFullscreen = new JButton("Fullscreen");
        pBotonera.add(bFullscreen);
        add(pBotonera, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

//        mediaPlayerComponent.mediaPlayer().fullScreen().;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {//TODO que coja el tiempo y lo meta en un mapa, cada vez que se abra el reproductor, coge el mapa y si el nombre de la peli esta en el mapa, pone el tiempo
                mediaPlayerComponent.mediaPlayer().controls().stop();
                mediaPlayerComponent.mediaPlayer().release();
            }
        });
        bFullscreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().fullScreen().toggle();
            }
        });
        bMute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mediaPlayerComponent.mediaPlayer().audio().isMute()){
                    mediaPlayerComponent.mediaPlayer().audio().setMute(false);
                    pbVolume.setValue(savedVolume);
                }else{
                    savedVolume = mediaPlayerComponent.mediaPlayer().audio().volume();
                    mediaPlayerComponent.mediaPlayer().audio().setMute(true);
                    pbVolume.setValue(0);
                }
            }
        });
        bPlayPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mediaPlayerComponent.mediaPlayer().status().isPlaying())
                    mediaPlayerComponent.mediaPlayer().controls().pause();
                else
                    mediaPlayerComponent.mediaPlayer().controls().play();
            }
        });
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                pbReproduccion.setValue((int) (1000L * newTime / mediaPlayer.status().length()));
            }
        });
        pbReproduccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().setTime(
                        mediaPlayerComponent.mediaPlayer().status().length() * e.getX() / pbReproduccion.getWidth());
            }
        });
        pbVolume.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                mediaPlayerComponent.mediaPlayer().audio().setVolume(mouseX);
                pbVolume.setValue(mouseX);
            }
        });
    }

    public static void main(String[] args) {
        Reproducir("/home/sweos/Downloads/shrek-01.avi.mp4");
    }




    public static void Reproducir(String ruta){
        boolean found = (new NativeDiscovery()).discover();
        if (!found) System.setProperty("jna.library.path", ruta_a_vlc);
        miVentana = new Reproductor();
        miVentana.mediaPlayerComponent.mediaPlayer().audio().setVolume( 75 );
        miVentana.pbVolume.setValue(miVentana.mediaPlayerComponent.mediaPlayer().audio().volume());
        miVentana.mediaPlayerComponent.mediaPlayer().media().play(ruta);
}

}
