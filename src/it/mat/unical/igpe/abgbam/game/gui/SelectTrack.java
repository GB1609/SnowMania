package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.general.InputException;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class SelectTrack extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton comeBack;
	private ModifiedButton createTracks;
	private JDialog dialog;
	private MainFrame frame;
	private JScrollPane pane;
	private boolean request;
	private JLabel selectTrack;
	private JLabel trackName;
	private ArrayList<String> tracks;
	private JPanel tracksPanel;
	public SelectTrack(MainFrame frame, boolean request, boolean online)
	{
		this.request = request;
		this.frame = frame;
		frame.setCursor(frame.getCustomCursor());
		this.requestFocus();
		this.tracks = new ArrayList<String>();
		this.loadTracks();
		this.dialog = new JDialog(this.frame);
		this.dialog.setSize((int) (frame.getWidth() * 0.8), (int) (this.frame.getHeight() * 0.8));
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.dialog.setUndecorated(true);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.setSize(this.dialog.getSize());
		this.setLayout(null);
		this.selectTrack = new JLabel("Select track");
		this.trackName = new JLabel("Tracks' name");
		this.selectTrack.setForeground(Color.WHITE);
		this.selectTrack.setFont(new Font("Showcard Gothic", 0, (int) (this.getWidth() * 0.08)));
		this.selectTrack.setBounds(this.getWidth() / 4, (int) (this.getHeight() * 0.014),
				this.getWidth(), (int) (this.getHeight() * 0.14));
		this.trackName.setForeground(Color.BLUE.darker().darker());
		this.trackName.setFont(new Font("Showcard Gothic", 0, (int) (this.getWidth() * 0.03)));
		this.trackName.setBounds((int) (this.getWidth() * 0.1), (int) (this.getHeight() * 0.05),
				this.getWidth(), (int) (this.getHeight() * 0.3));
		this.comeBack = new ModifiedButton("", ImageProviderGame.getClose(),
				ImageProviderGame.getClose2(), this.frame, e -> this.dialog.dispose());
		this.tracksPanel = new JPanel();
		this.tracksPanel.setLayout(new BoxLayout(this.tracksPanel, BoxLayout.Y_AXIS));
		this.tracksPanel.setOpaque(false);
		this.pane = new JScrollPane(this.tracksPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.pane.setOpaque(false);
		this.pane.getViewport().setOpaque(false);
		this.pane.setBounds(0, (int) (this.getHeight() * 0.25), (int) (this.getWidth() * 0.82),
				(int) (this.getHeight() * 0.75));
		for (String button : this.tracks)
			this.tracksPanel.add(
					new TrackButton(button, frame, e -> this.trackSelected(button, online)),
					this.tracksPanel);
		this.add(this.pane);
		this.add(this.selectTrack);
		this.add(this.trackName);
		if (request)
		{
			this.createTracks = new ModifiedButton("", ImageProviderGame.getCreateTracks(),
					ImageProviderGame.getCreateTracks2(), frame, e -> this.createTrack());
			this.createTracks.setBounds(
					(int) ((this.getWidth() * 0.91) - (this.comeBack.getWidth() / 2)),
					(int) (this.getHeight() * 0.27), this.comeBack.getWidth(),
					this.comeBack.getHeight());
			this.add(this.createTracks);
			this.comeBack.setBounds((int) ((this.getWidth() * 0.91) - (this.comeBack.getWidth() / 2)),
					(int) ((this.getHeight() * 0.73)
							- (ImageProviderGame.getClose().getIconHeight() / 2)),
					this.comeBack.getWidth(), this.comeBack.getHeight());
		}
		else
			this.comeBack.setBounds((int) ((this.getWidth() * 0.91) - (this.comeBack.getWidth() / 2)),
					(int) ((this.getHeight() * 0.5)
							- (ImageProviderGame.getClose().getIconHeight() / 2)),
					this.comeBack.getWidth(), this.comeBack.getHeight());
		this.add(this.comeBack);
		this.setVisible(true);
		this.dialog.setVisible(true);
	}
	private void createTrack()
	{
		this.dialog.dispose();
		this.frame.switchPanelEditor();
	}
	private void loadTracks()
	{
		try
		{
			List<String> allLines = Files.readAllLines(this.frame.getListTrack().toPath());
			for (String buffer : allLines)
				this.tracks.add(buffer);
		}
		catch (IOException e1)
		{
			new InputException(this.frame, 4);
		}
	}
	private void trackSelected(String button, boolean online)
	{
		this.dialog.dispose();
		if (this.request)
		{
			if (online)
				new ConnectionPanel(this.frame, true);
			else
				this.frame.switchPanelPlay(button, online);
			this.frame.setNameTrackChoose(button);
		}
		else
			this.frame.loadScoreBoard(button, false);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(ImageProviderGame.getSelectTrack().getScaledInstance(this.getWidth(),
				this.getHeight(), 0), 0, 0, null);
	}
}
