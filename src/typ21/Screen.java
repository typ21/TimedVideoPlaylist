package typ21;

import java.awt.Dimension;
import java.awt.Toolkit;
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
//		setExtendedState(MAXIMIZED_BOTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.release();
				System.exit(0);
			}
		});

		/*
		 * JPanel panelOfContent = new JPanel();
		 * panelOfContent.setBackground(Color.BLACK); setContentPane(panelOfContent);
		 */

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		setContentPane(mediaPlayerComponent);

		mediaPlayer = mediaPlayerComponent.mediaPlayer();

		mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
			@Override
			public void playing(MediaPlayer mediaPlayer) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

				List<? extends TrackInfo> trackInfo = mediaPlayer.media().info().tracks(TrackType.VIDEO);
				for (TrackInfo track : trackInfo) {
					VideoTrackInfo videoTrack = (VideoTrackInfo) track;

					setBounds((screenSize.width - videoTrack.width()) / 2,
							(screenSize.height - videoTrack.height()) / 2, videoTrack.width(), videoTrack.height());

				}
			}

			/*
			 * @Override public void finished(MediaPlayer mediaPlayer) { }
			 * 
			 * @Override public void error(MediaPlayer mediaPlayer) { }
			 */
		});
	}

	public void play(File file) {
		System.out.println("Now playing " + file.getName());
		setTitle(file.getName());

//		mediaPlayer.media().play(file.getAbsolutePath());
	}

}
