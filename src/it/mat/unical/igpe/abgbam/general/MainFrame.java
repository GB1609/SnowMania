package it.mat.unical.igpe.abgbam.general;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import it.mat.unical.igpe.abgbam.editor.CreateCentrePanel;
import it.mat.unical.igpe.abgbam.editor.EditorPanel;
import it.mat.unical.igpe.abgbam.editor.LeftPanel;
import it.mat.unical.igpe.abgbam.editor.MapPanel;
import it.mat.unical.igpe.abgbam.editor.RequestPanelEditor;
import it.mat.unical.igpe.abgbam.editor.RightPanel;
import it.mat.unical.igpe.abgbam.game.core.ScrollManager;
import it.mat.unical.igpe.abgbam.game.core.TrackReader;
import it.mat.unical.igpe.abgbam.game.gui.EnemyPlayPanel;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.PlayPanel;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
import it.mat.unical.igpe.abgbam.game.network.ImageProviderNetwork;
public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1;
	private static Toolkit toolkit;
	private Cursor customCursor;
	private Cursor invisibleCursor;
	private boolean AUDIO = false;
	private MapPanel centrePanel;
	private EditorPanel editorPanel;
	private EnemyPlayPanel enemyPlayPanel;
	private boolean error = false;
	private LeftPanel left;
	private File listTracks;
	private String namePlayer;
	private String nameTrackChoose = "";
	private OptionPanel optionPanel;
	private PlayPanel playPanel;
	private RightPanel right;
	private List<ScoreBoardLine> scoreboard;
	private File scoreboardFile;
	private Dimension screenSize;
	private StartPanel startPage;
	public MainFrame()
	{
		super();
		this.format();
	}
	public MainFrame(String title)
	{
		super(title);
		this.namePlayer = "GB1609";
		this.loadInput();
		this.scoreboard = new ArrayList<ScoreBoardLine>();
		this.personalizateMouse();
		this.format();
		this.loadTracksFile();
		if (this.AUDIO)
			SoundProvider.getReproducerStartSong().loop(-1);
		this.createPanel();
		this.startPage.requestFocus();
		this.setContentPane(this.startPage);
		this.revalidate();
	}
	private void createPanel()
	{
		this.optionPanel = new OptionPanel(this);
		this.startPage = new StartPanel(this);
		this.editorPanel = new EditorPanel(this);
		this.left = new LeftPanel(this);
		this.right = new RightPanel(this);
	}
	private void disableAllButtons()
	{
		this.startPage.disableButtons();
		this.optionPanel.disableButtons();
		this.editorPanel.disableMenu();
		this.left.disableButtons();
		this.right.disableButtons();
	}
	private void format()
	{
		this.setExtendedState(MAXIMIZED_BOTH);
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(this.screenSize.getSize());
		this.setExtendedState(NORMAL);
		this.setUndecorated(true);
		try
		{
			this.setIconImage(ImageIO
					.read(MainFrame.class.getClassLoader().getResource("imageStart/startSkin.jpg")));
		}
		catch (Exception e1)
		{
			new InputException(this, 1);
		}
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(0);
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				MainFrame.this.setCursor(MainFrame.this.customCursor);
				MainFrame.this.showExitConfirm();
			}
		});
	}
	private void loadInput()
	{
		ImageProviderGame.chargeImage(this);
		SoundProvider.chargeMusic(this);
		ScalingImages.scalingImages(this);
		ImageProviderNetwork.chargeImage(this);
	}
	private void loadTracksFile()
	{
		this.listTracks = new File("data/tracks/trackList.txt");
		if (!this.listTracks.exists())
			try
			{
				this.listTracks.createNewFile();
			}
			catch (Exception e)
			{
				new InputException(this, 4);
			}
	}
	private void personalizateMouse()
	{
		try
		{
			Image imageCursor = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/cursor.gif"));
			toolkit = Toolkit.getDefaultToolkit();
			this.customCursor = toolkit.createCustomCursor(imageCursor, new Point(0, 0),
					"customCursor");
			Image image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
			this.invisibleCursor = toolkit.createCustomCursor(image, new Point(0, 0), "");
		}
		catch (Exception e1)
		{
			new InputException(this, 1);
		}
		this.setCursor(this.customCursor);
	}
	public void comeBackToMenu()
	{
		if (this.getAUDIO())
			SoundProvider.getReproducerMenu().loop(0);
		new RequestPanel(this, 2, null);
		this.disableAllButtons();
		SoundProvider.getReproducerMenu().setFramePosition(0);
	}
	public void createMapEditor(String nameTrack, int lengthTrack)
	{
		this.centrePanel = new MapPanel(this, lengthTrack, nameTrack);
		if (this.getWidth() == 1366)
		{
			this.editorPanel.add(this.centrePanel);
			this.centrePanel.setBounds(this.left.getWidth(), 0, this.centrePanel.getWidth(),
					this.centrePanel.getHeight());
		}
		else
		{
			JPanel comodato = new JPanel(null);
			comodato.setBounds(this.left.getWidth(), 0, this.getWidth() - (this.left.getWidth() * 2),
					this.getHeight());
			comodato.setBackground(Color.WHITE);
			this.centrePanel.setBounds((comodato.getWidth() / 2) - (this.centrePanel.getWidth() / 2),
					(comodato.getHeight() / 2) - (this.centrePanel.getHeight() / 2),
					this.centrePanel.getWidth(), this.centrePanel.getHeight());
			comodato.setVisible(true);
			comodato.add(this.centrePanel);
			this.editorPanel.add(comodato);
		}
		this.editorPanel.repaint();
		this.revalidate();
	}
	public void editorCentreCreator()
	{
		if (this.centrePanel != null)
		{
			new RequestPanelEditor(this, this.centrePanel, 0);
			this.disableAllButtons();
		}
		else
		{
			this.disableAllButtons();
			new CreateCentrePanel(this);
		}
	}
	public void enableAllButtons()
	{
		this.startPage.enableButtons();
		this.optionPanel.enableButtons();
		this.editorPanel.enableMenu();
		this.left.enableButtons();
		this.right.enableButtons();
	}
	public boolean getAUDIO()
	{
		return this.AUDIO;
	}
	public MapPanel getCentrePanel()
	{
		return this.centrePanel;
	}
	public Cursor getCustomCursor()
	{
		return this.customCursor;
	}
	public EditorPanel getEditorPanel()
	{
		return this.editorPanel;
	}
	public EnemyPlayPanel getEnemyPlayPanel()
	{
		return this.enemyPlayPanel;
	}
	public Cursor getInvisibleCursor()
	{
		return this.invisibleCursor;
	}
	public File getListTrack()
	{
		return this.listTracks;
	}
	public String getNamePlayer()
	{
		return this.namePlayer;
	}
	public String getNameTrackChoose()
	{
		return this.nameTrackChoose;
	}
	public PlayPanel getPlayPanel()
	{
		return this.playPanel;
	}
	public RightPanel getRightPanel()
	{
		return this.right;
	}
	public List<ScoreBoardLine> getScoreboard()
	{
		return this.scoreboard;
	}
	public int getScreensize()
	{
		return this.screenSize.width;
	}
	public boolean isError()
	{
		return this.error;
	}
	public void loadScoreBoard(String name, boolean inGame)
	{
		if (!this.scoreboard.isEmpty())
			this.scoreboard.removeAll(this.scoreboard);
		this.scoreboardFile = new File("data/scoreBoard/scoreboard" + name + ".txt");
		if (this.scoreboardFile.exists())
			try
			{
				List<String> allLines = Files.readAllLines(this.scoreboardFile.toPath());
				for (String buffer : allLines)
					this.scoreboard.add(new ScoreBoardLine(buffer));
			}
			catch (Exception e1)
			{
				new InputException(this, 5);
			}
		else
			try
			{
				this.scoreboardFile.createNewFile();
			}
			catch (Exception e)
			{
				new OutputException(this, 1);
			}
		if (inGame)
			new RequestPanel(this, 4, name);
		else
			new RequestPanel(this, 3, name);
		this.disableAllButtons();
	}
	public void resetCentrePanel()
	{
		this.centrePanel = null;
	}
	public void saveTrack()
	{
		if (this.centrePanel == null)
		{
			new RequestPanelEditor(this, this.centrePanel, 1);
			this.disableAllButtons();
		}
		else
		{
			this.centrePanel.getTrackWriter().saveTrack(this);
			new RequestPanelEditor(this, this.centrePanel, 2);
		}
		try
		{
			BufferedWriter write = new BufferedWriter(new FileWriter(this.listTracks, true));
			write.write(this.centrePanel.getTrackWriter().getName());
			write.newLine();
			write.flush();
			write.close();
		}
		catch (Exception e)
		{
			new OutputException(this, 2);
		}
	}
	public void setAUDIO(boolean select)
	{
		this.AUDIO = select;
		if (select)
			SoundProvider.getReproducerStartSong().loop(-1);
		else
			SoundProvider.stopAll();
	}
	public void setError()
	{
		this.error = true;
	}
	public void setFull(boolean b)
	{
		this.dispose();
		if (b)
		{
			this.setExtendedState(6);
			this.setSize(this.screenSize.width, this.screenSize.height);
		}
		else
			this.setSize(1366, 768);
		this.setLocationRelativeTo(null);
		this.optionPanel = new OptionPanel(this);
		this.startPage = new StartPanel(this);
		this.setContentPane(this.optionPanel);
		this.setLayout(null);
		this.setVisible(true);
		this.setExtendedState(0);
	}
	public void setNamePlayer(String namePlayer)
	{
		this.namePlayer = namePlayer;
	}
	public void setNameTrackChoose(String nameTrackChoose)
	{
		this.nameTrackChoose = nameTrackChoose;
	}
	public void setResolution(int width)
	{
		this.dispose();
		if (width == 1024)
			this.setSize(new Dimension(1024, 768));
		else if (width == 1366)
			this.setSize(new Dimension(1366, 768));
		else if (width == 1600)
			this.setSize(new Dimension(1600, 900));
		else if (width == 1920)
			this.setSize(new Dimension(1920, 1080));
		this.optionPanel = new OptionPanel(this);
		this.startPage = new StartPanel(this);
		this.setContentPane(this.optionPanel);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void showExitConfirm()
	{
		new RequestPanel(this, 1, null);
		this.disableAllButtons();
	}
	public void startEnemyGame()
	{
		this.enemyPlayPanel = new EnemyPlayPanel(this, new TrackReader(this.nameTrackChoose));
	}
	public ScrollManager startNetworkGame() throws IOException
	{
		this.switchPanelPlay(this.nameTrackChoose, true);
		this.playPanel.netStart(true);
		return this.playPanel.getScrollManager();
	}
	public void switchPanelEditor()
	{
		this.editorPanel = new EditorPanel(this);
		this.resetCentrePanel();
		this.add(this.editorPanel);
		this.left = new LeftPanel(this);
		this.right = new RightPanel(this);
		this.editorPanel.add(this.left);
		this.editorPanel.add(this.right);
		this.editorPanel.setBounds((this.getWidth() / 2) - 684, (this.getHeight() / 2) - 384, 1366,
				768);
		this.setContentPane(this.editorPanel);
		this.editorPanel.requestFocus();
		this.editorPanel.showMenu(true);
		this.revalidate();
	}
	public void switchPanelOption()
	{
		this.setContentPane(this.optionPanel);
		this.optionPanel.requestFocus();
		this.editorPanel.showMenu(false);
		this.revalidate();
	}
	public void switchPanelPlay(String nameTrack, boolean online)
	{
		TrackReader tr = new TrackReader(nameTrack);
		this.nameTrackChoose = nameTrack;
		this.playPanel = new PlayPanel(this, tr, online);
		if (online)
		{
			this.startEnemyGame();
			this.enemyPlayPanel.setBounds(20, 60, this.enemyPlayPanel.getWidth(),
					this.enemyPlayPanel.getHeight());
			this.playPanel.add(this.enemyPlayPanel);
		}
		if (this.getWidth() == 1024)
		{
			this.playPanel.setBounds((this.getWidth() / 2) - 684, (this.getHeight() / 2) - 384, 1366,
					768);
			JPanel comodato = new JPanel();
			comodato.setBackground(Color.BLACK);
			comodato.setLayout(null);
			comodato.add(this.playPanel);
			this.add(comodato);
			this.setContentPane(comodato);
		}
		else
			this.setContentPane(this.playPanel);
		this.playPanel.requestFocus();
		this.editorPanel.showMenu(false);
		this.setCursor(this.invisibleCursor);
		this.revalidate();
	}
	public void switchPanelStart(JDialog dialog)
	{
		if (dialog != null)
			dialog.dispose();
		this.enableAllButtons();
		this.startPage.requestFocus();
		this.playPanel = null;
		this.enemyPlayPanel = null;
		this.setContentPane(this.startPage);
		this.editorPanel.showMenu(false);
		this.setCursor(this.customCursor);
		this.revalidate();
	}
}
