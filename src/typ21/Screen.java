package typ21;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import uk.co.caprica.vlcj.media.TrackInfo;
import uk.co.caprica.vlcj.media.TrackType;
import uk.co.caprica.vlcj.media.VideoTrackInfo;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import java.awt.event.KeyAdapter;

public class Screen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6628981357155700559L;

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private MediaPlayer mediaPlayer;

	/**
	 * Create the panel.
	 */
	public Screen() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setSize(854, 480);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitProgram();
			}
		});

		/*
		 * JPanel panelOfContent = new JPanel();
		 * panelOfContent.setBackground(Color.BLACK); setContentPane(panelOfContent);
		 */

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		setContentPane(mediaPlayerComponent);

		mediaPlayer = mediaPlayerComponent.mediaPlayer();

//		mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			/*
			 * @Override public void playing(MediaPlayer mediaPlayer) { List<? extends
			 * TrackInfo> trackInfo = mediaPlayer.media().info().tracks(TrackType.VIDEO);
			 * for (TrackInfo track : trackInfo) { VideoTrackInfo videoTrack =
			 * (VideoTrackInfo) track;
			 * 
			 * mediaPlayerComponent.setPreferredSize(new Dimension(videoTrack.width(),
			 * videoTrack.height())); pack(); } }
			 */

			/*
			 * @Override public void finished(MediaPlayer mediaPlayer) { }
			 * 
			 * @Override public void error(MediaPlayer mediaPlayer) { }
			 */
//		});

		Component videoSurface = mediaPlayerComponent.videoSurfaceComponent();
		videoSurface.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getExtendedKeyCode()) {
				case 70: // F
					break;
				case 32: // Space
					mediaPlayer.controls().pause();
					break;

				default:
			    	System.out.println(e.getExtendedKeyCode());
					break;
				}
			}
		});

		videoSurface.requestFocus();
	}

	public void play(File file, long beginning, long ending) {
		System.out.println("Now playing " + file.getName());
		setTitle(file.getName());

		mediaPlayer.media().play(file.getAbsolutePath());
		mediaPlayer.controls().setTime(beginning);
	}

	public void exitProgram() {
		mediaPlayerComponent.release();
		System.exit(0);
	}

}