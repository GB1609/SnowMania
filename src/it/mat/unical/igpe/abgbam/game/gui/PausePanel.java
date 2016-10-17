package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.game.core.ScrollManager;
import it.mat.unical.igpe.abgbam.game.core.TrackReader;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class PausePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TrackButton closeGame;
	private TrackButton comeStart;
	private JDialog dialog;
	private MainFrame frame;
	private TrackButton newTrack;
	private boolean online;
	private TrackButton restart;
	private TrackButton resume;
	private ScrollManager scrollManager;
	private TrackReader tr;
	public PausePanel(MainFrame frame, ScrollManager scrollManager, TrackReader tr, boolean online)
	{
		frame.setCursor(frame.getCustomCursor());
		this.online = online;
		this.tr = tr;
		this.scrollManager = scrollManager;
		this.frame = frame;
		this.dialog = new JDialog(frame);
		this.requestFocus();
		this.setBackground(Color.BLUE);
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.resume = new TrackButton("resume", frame, e -> this.resume());
		if (scrollManager.isFinish())
			this.resume.setEnabled(false);
		if (!online)
		{
			this.restart = new TrackButton("restart race", frame, e -> this.restart());
			this.newTrack = new TrackButton("change track", frame, e -> this.newRace());
			this.add(this.restart);
			this.add(this.newTrack);
			this.dialog.setSize(410, 280);
		}
		else
			this.dialog.setSize(410, 180);
		this.comeStart = new TrackButton("come to start page", frame,
				e -> frame.switchPanelStart(this.dialog));
		this.closeGame = new TrackButton("Close game", frame, e -> frame.showExitConfirm());
		this.setSize(this.dialog.getSize());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		if (online && frame.getEnemyPlayPanel().getScrollManager().isFinish())
			this.comeStart.setEnabled(false);
		this.add(this.resume);
		this.add(this.comeStart);
		this.add(this.closeGame);
		scrollManager.setPause(true);
		scrollManager.crono.setCronoActive(false);
		this.setVisible(true);
		this.dialog.setUndecorated(true);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.dialog.setVisible(true);
	}
	private void newRace()
	{
		this.dialog.dispose();
		new SelectTrack(this.frame, true, this.online);
	}
	private void restart()
	{
		this.dialog.dispose();
		this.frame.setCursor(this.frame.getInvisibleCursor());
		this.frame.switchPanelPlay(this.tr.getTrack(), this.online);
	}
	private void resume()
	{
		this.dialog.dispose();
		this.frame.setCursor(this.frame.getInvisibleCursor());
		this.scrollManager.setPause(false);
		this.scrollManager.crono.setCronoActive(true);
		this.scrollManager.crono.inCronometro();
	}
}
