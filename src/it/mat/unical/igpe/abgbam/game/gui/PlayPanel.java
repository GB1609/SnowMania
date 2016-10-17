package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.mat.unical.igpe.abgbam.game.core.AbstractBonus;
import it.mat.unical.igpe.abgbam.game.core.AdvertisingTrack;
import it.mat.unical.igpe.abgbam.game.core.CheckStart;
import it.mat.unical.igpe.abgbam.game.core.CountDown;
import it.mat.unical.igpe.abgbam.game.core.Direction;
import it.mat.unical.igpe.abgbam.game.core.LeftAdvertising;
import it.mat.unical.igpe.abgbam.game.core.LeftDelimiter;
import it.mat.unical.igpe.abgbam.game.core.LeftTree;
import it.mat.unical.igpe.abgbam.game.core.MovePlayerListener;
import it.mat.unical.igpe.abgbam.game.core.ObiqueLine;
import it.mat.unical.igpe.abgbam.game.core.Plane;
import it.mat.unical.igpe.abgbam.game.core.Ramp;
import it.mat.unical.igpe.abgbam.game.core.RightAdvertising;
import it.mat.unical.igpe.abgbam.game.core.RightDelimiter;
import it.mat.unical.igpe.abgbam.game.core.RightTree;
import it.mat.unical.igpe.abgbam.game.core.Rock;
import it.mat.unical.igpe.abgbam.game.core.ScrollManager;
import it.mat.unical.igpe.abgbam.game.core.Slalom;
import it.mat.unical.igpe.abgbam.game.core.TrackReader;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.SoundProvider;
public class PlayPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int beginWrite;
	private Color color;
	private CountDown countDown;
	private Dimension dimension = new Dimension(1366, 768);
	private Font font;
	private MainFrame gameFrame;
	private boolean going;
	private JLabel label;
	private ArrayList<ObiqueLine> lines = new ArrayList<ObiqueLine>();
	private MovePlayerListener listener;
	private Plane plane;
	private Runnable runnable;
	private ScrollManager scrollManager;
	private boolean start = false;
	private boolean startIntro = false;
	private int sumX = 0;
	private int sumYLine = 0;
	protected boolean mouseActivated = false;
	public PlayPanel(MainFrame gameFrame, TrackReader tr, boolean online)
	{
		this.gameFrame = gameFrame;
		this.setLayout(null);
		this.color = new Color(0, 255, 255, 120);
		this.label = new JLabel("Press Space to play");
		this.label.setSize(gameFrame.getWidth(), gameFrame.getHeight());
		this.label.setFont(new Font("Showcard Gothic", 0, (int) (this.gameFrame.getHeight() * 0.06)));
		this.label.setForeground(Color.BLUE.darker().darker());
		this.label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		this.label.setVerticalAlignment((int) CENTER_ALIGNMENT);
		this.add(this.label);
		if (gameFrame.getWidth() == 1600)
		{
			this.setSize(1600, 900);
			this.sumX = 234;
			this.sumYLine = 40;
		}
		else if (gameFrame.getWidth() == 1920)
		{
			this.setSize(1920, 1080);
			this.sumX = 544;
			this.sumYLine = 110;
		}
		else
			this.setSize(1366, 768);
		this.going = true;
		if (gameFrame.getWidth() == 1024)
		{
			this.beginWrite = (int) (this.getWidth() * 0.13);
			this.font = new Font("Showcard Gothic", 0, (int) (1024 * 0.017));
		}
		else
		{
			this.beginWrite = (int) (this.getWidth() * 0.014);
			this.font = new Font("Showcard Gothic", 0, (int) (this.getWidth() * 0.023));
		}
		if (gameFrame.getSize().getWidth() > 1366)
			this.dimension = gameFrame.getSize();
		this.scrollManager = new ScrollManager(tr, gameFrame.getNamePlayer(), this.dimension,
				this.sumX);
		this.runnable = () -> {
			if (PlayPanel.this.going)
				PlayPanel.this.repaint();
		};
		this.countDown = new CountDown(this.runnable, gameFrame);
		this.plane = new Plane();
		this.lines.add(new ObiqueLine((int) (this.getWidth() * 0.073), this.getHeight(),
				(int) (this.getWidth() * 0.4268), (int) ((this.getHeight() * 0.3515) - this.sumYLine)));
		this.lines.add(new ObiqueLine((int) (this.getWidth() * 0.9487), this.getHeight(),
				(int) (this.getWidth() * 0.5732), (int) (this.getHeight() * 0.3515) - this.sumYLine));
		this.listener = new MovePlayerListener(this.scrollManager, this);
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(final KeyEvent e)
			{
				switch (e.getKeyCode())
				{
				case KeyEvent.VK_LEFT:
					if (!PlayPanel.this.scrollManager.isFinish())
						PlayPanel.this.scrollManager.getPlayer().setDirection(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					if (!PlayPanel.this.scrollManager.isFinish())
						PlayPanel.this.scrollManager.getPlayer().setDirection(Direction.RIGHT);
					break;
				case KeyEvent.VK_SPACE:
					if (!PlayPanel.this.startIntro)
					{
						PlayPanel.this.countDown.start();
						PlayPanel.this.startIntro = true;
					}
					if (PlayPanel.this.scrollManager.getFinalScenario().getFinish())
					{
						new PausePanel(gameFrame, PlayPanel.this.scrollManager, tr, online);
						gameFrame.loadScoreBoard(PlayPanel.this.scrollManager.getTrackReader().getTrack(),
								true);
						PlayPanel.this.going = false;
					}
					break;
				case KeyEvent.VK_M:
					if (PlayPanel.this.mouseActivated)
					{
						PlayPanel.this.removeMouseListener(PlayPanel.this.listener);
						PlayPanel.this.removeMouseMotionListener(PlayPanel.this.listener);
						PlayPanel.this.mouseActivated = false;
					}
					else
					{
						PlayPanel.this.addMouseMotionListener(PlayPanel.this.listener);
						PlayPanel.this.addMouseListener(PlayPanel.this.listener);
						PlayPanel.this.mouseActivated = true;
					}
					break;
				case KeyEvent.VK_ESCAPE:
					gameFrame.setCursor(gameFrame.getCustomCursor());
					new PausePanel(gameFrame, PlayPanel.this.scrollManager, tr, online);
					break;
				}
			}
			@Override
			public void keyReleased(final KeyEvent e)
			{
				PlayPanel.this.scrollManager.getPlayer().setDirection(Direction.STOP);
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(this.font);
		g.drawImage(ImageProviderGame.getBackgroundTrack(),
				this.scrollManager.getBackgroundTrack().getX(),
				this.scrollManager.getBackgroundTrack().getY(), this.getWidth(), this.getHeight(),
				null);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(12));
		g2.setPaint(this.color);
		if (!this.scrollManager.isFinish())
		{
			g2.draw(new Line2D.Float(this.lines.get(0).getX1(), this.lines.get(0).getY1(),
					this.lines.get(0).getX2(), this.lines.get(0).getY2()));
			g2.draw(new Line2D.Float(this.lines.get(1).getX1(), this.lines.get(1).getY1(),
					this.lines.get(1).getX2(), this.lines.get(1).getY2()));
		}
		g.drawImage(ImageProviderGame.getBackground(), 0, 0, this.getWidth(), 280, null);
		if (this.scrollManager.isFinish())
		{
			this.scrollManager.setSpeed(ScrollManager.STANDARDSPEED);
			g.drawImage(
					ScalingImages.finalScenarioMaps.get(this.scrollManager.getFinalScenario().getKey()),
					((this.getWidth() / 2) - (ScalingImages.finalScenarioMaps
							.get(this.scrollManager.getFinalScenario().getKey()).getWidth(null) / 2)),
					(int) this.scrollManager.getFinalScenario().getY(), null);
		}
		for (LeftDelimiter l : this.scrollManager.getLeftDelimiter())
			g.drawImage(ScalingImages.leftDelimiterMaps.get(l.getKey()), (int) l.getX(),
					(int) l.getY(), null);
		for (RightDelimiter r : this.scrollManager.getRightDelimiter())
			g.drawImage(ScalingImages.rightDelimiterMaps.get(r.getKey()), (int) r.getX() + this.sumX,
					(int) r.getY(), null);
		for (LeftTree t : this.scrollManager.getLeftTree())
			g.drawImage(ScalingImages.treeMaps.get(t.getKey()), (int) t.getX(), (int) t.getY(), null);
		for (RightTree t : this.scrollManager.getRightTree())
			g.drawImage(ScalingImages.treeMaps.get(t.getKey()), (int) t.getX() + this.sumX,
					(int) t.getY(), null);
		for (Rock r : this.scrollManager.getRocks())
			g.drawImage(ScalingImages.rockMaps.get(r.getKey()), (int) r.getX() + (this.sumX / 2),
					(int) r.getY(), null);
		for (AdvertisingTrack r : this.scrollManager.getAdvertisingTracks())
			g.drawImage(ScalingImages.advertisingTrack.get(r.getKey()),
					(int) r.getX() + (this.sumX / 2), (int) r.getY(), null);
		for (Slalom s : this.scrollManager.getSlalom())
		{
			g.drawImage(ScalingImages.slalomMaps.get(s.getKey()), (int) s.getLeftX() + (this.sumX / 2),
					(int) s.getY(), null);
			g.drawImage(ScalingImages.slalomMaps.get(s.getKey()),
					(int) s.getRightX() + (this.sumX / 2), (int) s.getY(), null);
		}
		for (Rock r : this.scrollManager.getRocks())
			g.drawImage(ScalingImages.rockMaps.get(r.getKey()), (int) r.getX() + (this.sumX / 2),
					(int) r.getY(), null);
		for (AbstractBonus b : this.scrollManager.getBonus())
			switch (b.getId())
			{
			case 1:
				g.drawImage(ScalingImages.bonusF1Maps.get(b.getKey()), (int) b.getX() + (this.sumX / 2),
						(int) b.getY(), null);
				break;
			case 2:
				g.drawImage(ScalingImages.bonusShieldMaps.get(b.getKey()),
						(int) b.getX() + (this.sumX / 2), (int) b.getY(), null);
				break;
			}
		for (AdvertisingTrack r : this.scrollManager.getAdvertisingTracks())
			g.drawImage(ScalingImages.advertisingTrack.get(r.getKey()),
					(int) r.getX() + (this.sumX / 2), (int) r.getY(), null);
		for (Ramp r : this.scrollManager.getRamps())
			g.drawImage(ScalingImages.rampMaps.get(r.getKey()), (int) r.getX() + (this.sumX / 2),
					(int) r.getY(), null);
		for (RightAdvertising d : this.scrollManager.getRightAdvertising())
			g.drawImage(ScalingImages.advertisingMaps.get(d.getKey()),
					(int) d.getX() + (this.sumX / 2), (int) d.getY(), null);
		for (LeftAdvertising d : this.scrollManager.getLeftAdvertising())
			g.drawImage(ScalingImages.advertisingMaps.get(d.getKey()),
					(int) d.getX() + (this.sumX / 2), (int) d.getY(), null);
		if (this.scrollManager.intersection() && (this.scrollManager.getPlayer().getBonus() != 2))
		{
			if (((this.scrollManager.getPlayer().getBonus() == 0) && this.gameFrame.getAUDIO()))
			{
				SoundProvider.getReproducerDoh().loop(0);
				SoundProvider.getReproducerDoh().setFramePosition(0);
			}
			g.drawImage(ImageProviderGame.getCollition(),
					(int) this.scrollManager.getPlayer().getX() + (this.sumX / 2),
					(int) this.scrollManager.getPlayer().getY(), null);
		}
		else
			switch (this.scrollManager.getPlayer().getDirection())
			{
			case LEFT:
				g.drawImage(ImageProviderGame.getPlayerLeft(this.scrollManager.getPlayer().getBonus()),
						(int) this.scrollManager.getPlayer().getX() + (this.sumX / 2),
						(int) this.scrollManager.getPlayer().getY(), null);
				break;
			case RIGHT:
				g.drawImage(ImageProviderGame.getPlayerRight(this.scrollManager.getPlayer().getBonus()),
						(int) this.scrollManager.getPlayer().getX() + (this.sumX / 2),
						(int) this.scrollManager.getPlayer().getY(), null);
				break;
			default:
				g.drawImage(ImageProviderGame.getPlayer(this.scrollManager.getPlayer().getBonus()),
						(int) this.scrollManager.getPlayer().getX() + (this.sumX / 2),
						(int) this.scrollManager.getPlayer().getY(), null);
				break;
			}
		if (this.scrollManager.makePlane())
		{
			if (this.gameFrame.getAUDIO())
			{
				SoundProvider.getReproducerAirPlane().loop(0);
				SoundProvider.getReproducerAirPlane().setFramePosition(0);
			}
			this.plane = new Plane(this.getWidth(), (int) (this.getHeight() * 0.05),
					this.scrollManager);
			this.plane.start();
		}
		for (Slalom s : this.scrollManager.getSlalom())
			if (s.getY() >= this.scrollManager.getPlayer().getY())
			{
				g.drawImage(ScalingImages.slalomMaps.get(s.getKey()),
						(int) s.getLeftX() + (this.sumX / 2), (int) s.getY(), null);
				g.drawImage(ScalingImages.slalomMaps.get(s.getKey()),
						(int) s.getRightX() + (this.sumX / 2), (int) s.getY(), null);
			}
		if (this.scrollManager.showGoal())
			g.drawImage(ScalingImages.goalMaps.get(this.scrollManager.getGoal().getKey()),
					(int) this.scrollManager.getGoal().getX() + (this.sumX / 2),
					(int) this.scrollManager.getGoal().getY(), null);
		for (CheckStart s : this.scrollManager.getStart())
			g.drawImage(ImageProviderGame.getStart(), (int) s.getX() + (this.sumX / 2),
					(int) ((this.gameFrame.getHeight() - s.getHeight()) + s.getY()), null);
		g.setFont(this.font);
		g.setColor(Color.white);
		g.drawString(
				"scroll: " + (int) this.scrollManager.getScrollTrack() + " / "
						+ this.scrollManager.getLenghtTrack(),
				this.beginWrite, (int) (this.getHeight() * 0.039));
		g.drawString(this.scrollManager.getTrackReader().getTrack(), (int) (this.getWidth() * 0.45),
				(int) (this.getHeight() * 0.039));
		g.drawString("Current Speed: " + this.scrollManager.getSpeed(),
				(int) (this.getWidth() * 0.75), (int) (this.getHeight() * 0.039));
		g.setFont(this.font);
		g.setColor(Color.BLUE.darker().darker());
		g.drawString("Penality", this.beginWrite, (int) (this.getHeight() * 0.8));
		g.drawString("" + this.scrollManager.penalityTot, this.beginWrite,
				(int) (this.getHeight() * 0.84));
		g.drawString("Current", this.beginWrite, (int) (this.getHeight() * 0.72));
		g.drawString(this.scrollManager.getCrono(), this.beginWrite, (int) (this.getHeight() * 0.76));
		if (this.countDown.getTimer() >= 0)
			this.timer();
		else
			g.setFont(this.font);
		if (this.scrollManager.isFinish())
		{
			g.drawString(
					"New:" + this.scrollManager.crono.newCrono(this.scrollManager.crono,
							this.scrollManager.penalityTot),
					this.beginWrite, (int) (this.getHeight() * 0.878));
			if (this.gameFrame.getAUDIO())
				SoundProvider.getReproducerApplauseSound().loop(0);
		}
		if (this.plane.isAlive())
			g.drawImage(ImageProviderGame.getPlane(), this.plane.getX(), this.plane.getY(), null);
	}
	public Dimension getDimension()
	{
		return this.dimension;
	}
	public ScrollManager getScrollManager()
	{
		return this.scrollManager;
	}
	public boolean getStart()
	{
		return this.start;
	}
	public int getSumX()
	{
		return this.sumX;
	}
	public void netStart(boolean startIntro)
	{
		this.startIntro = startIntro;
		this.countDown.start();
	}
	public void start()
	{
		this.requestFocus();
		this.scrollManager.crono.inCronometro();
		this.scrollManager.start(this.runnable);
	}
	public void timer()
	{
		if (this.startIntro && !this.start)
			switch (this.countDown.getTimer())
			{
			case 1:
				this.label.setText("Go!!!");
				break;
			case 2:
				this.label.setText("1");
				break;
			case 3:
				this.label.setText("2");
				break;
			case 4:
				this.label.setFont(
						new Font("Showcard Gothic", 0, (int) (this.gameFrame.getHeight() * 0.4)));
				this.label.setText("3");
				break;
			case 0:
				this.start();
				this.start = true;
				this.remove(this.label);
				break;
			default:
				break;
			}
	}
}
