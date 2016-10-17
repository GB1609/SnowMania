package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.game.core.AbstractBonus;
import it.mat.unical.igpe.abgbam.game.core.AdvertisingTrack;
import it.mat.unical.igpe.abgbam.game.core.CheckStart;
import it.mat.unical.igpe.abgbam.game.core.EnemyScrollManager;
import it.mat.unical.igpe.abgbam.game.core.LeftAdvertising;
import it.mat.unical.igpe.abgbam.game.core.LeftDelimiter;
import it.mat.unical.igpe.abgbam.game.core.LeftTree;
import it.mat.unical.igpe.abgbam.game.core.ObiqueLine;
import it.mat.unical.igpe.abgbam.game.core.Plane;
import it.mat.unical.igpe.abgbam.game.core.Ramp;
import it.mat.unical.igpe.abgbam.game.core.RightAdvertising;
import it.mat.unical.igpe.abgbam.game.core.RightDelimiter;
import it.mat.unical.igpe.abgbam.game.core.RightTree;
import it.mat.unical.igpe.abgbam.game.core.Rock;
import it.mat.unical.igpe.abgbam.game.core.Slalom;
import it.mat.unical.igpe.abgbam.game.core.TrackReader;
import it.mat.unical.igpe.abgbam.game.network.ImageProviderNetwork;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.SoundProvider;
public class EnemyPlayPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Color color;
	private ArrayList<ObiqueLine> lines = new ArrayList<ObiqueLine>();
	private Plane plane;
	private EnemyScrollManager scrollManager;
	private boolean start = false;
	private int sumY = 0;
	protected boolean mouseActivated = false;
	private MainFrame frame;
	public EnemyPlayPanel(MainFrame gameFrame, TrackReader tr)
	{
		this.frame = gameFrame;
		this.color = new Color(0, 255, 255, 120);
		this.setSize(480, 270);
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 4));
		this.sumY = 14;
		this.lines.add(new ObiqueLine((int) (this.getWidth() * 0.073), this.getHeight(),
				(int) (this.getWidth() * 0.4268), (int) ((this.getHeight() * 0.3515) - this.sumY)));
		this.lines.add(new ObiqueLine((int) (this.getWidth() * 0.9487), this.getHeight(),
				(int) (this.getWidth() * 0.5732), (int) (this.getHeight() * 0.3515) - this.sumY));
		this.plane = new Plane();
		this.scrollManager = new EnemyScrollManager(tr, new Dimension(1366, 768), 0);
		this.setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.setColor(this.color);
		g.drawImage(ImageProviderGame.getBackgroundTrack(), 0, 0, this.getWidth(), this.getHeight(),
				null);
		g.setFont(new Font("Showcard Gothic", 0, (int) (this.getHeight() * 0.06)));
		if (!this.scrollManager.isFinish())
		{
			((Graphics2D) g).draw(new Line2D.Float(this.lines.get(0).getX1(),
					this.lines.get(0).getY1(), this.lines.get(0).getX2(), this.lines.get(0).getY2()));
			((Graphics2D) g).draw(new Line2D.Float(this.lines.get(1).getX1(),
					this.lines.get(1).getY1(), this.lines.get(1).getX2(), this.lines.get(1).getY2()));
			for (RightAdvertising d : this.scrollManager.getRightAdvertising())
				g.drawImage(ScalingImages.advertisingMapsMini.get(d.getKey()), (int) (d.getX() * 0.35),
						(int) (d.getY() * 0.34), null);
			for (LeftAdvertising d : this.scrollManager.getLeftAdvertising())
				g.drawImage(ScalingImages.advertisingMapsMini.get(d.getKey()), (int) (d.getX() * 0.35),
						(int) (d.getY() * 0.34), null);
			for (LeftDelimiter l : this.scrollManager.getLeftDelimiter())
				g.drawImage(ScalingImages.leftDelimiterMapsMini.get(l.getKey()),
						(int) (l.getX() * 0.35), (int) (l.getY() * 0.34), null);
			for (RightDelimiter r : this.scrollManager.getRightDelimiter())
				g.drawImage(ScalingImages.rightDelimiterMapsMini.get(r.getKey()),
						(int) (r.getX() * 0.35), (int) (r.getY() * 0.34), null);
			for (Rock r : this.scrollManager.getRocks())
				g.drawImage(ScalingImages.rockMaps.get(r.getKey()), (int) (r.getX() * 0.33),
						(int) (r.getY() * 0.34), null);
			for (AbstractBonus b : this.scrollManager.getBonus())
				switch (b.getId())
				{
				case 1:
					g.drawImage(
							ScalingImages.bonusF1Maps.get(b.getKey()).getScaledInstance(
									(int) (ScalingImages.bonusF1Maps.get(b.getKey()).getWidth(null) * 0.30),
									(int) (ScalingImages.bonusF1Maps.get(b.getKey()).getHeight(null) * 0.30),
									0),
							(int) (b.getX() * 0.33), (int) (b.getY() * 0.34), null);
					break;
				case 2:
					g.drawImage(
							ScalingImages.bonusShieldMaps.get(b.getKey()).getScaledInstance(
									(int) (ScalingImages.bonusShieldMaps.get(b.getKey()).getWidth(null)
											* 0.30),
									(int) (ScalingImages.bonusShieldMaps.get(b.getKey()).getHeight(null)
											* 0.30),
									0),
							(int) (b.getX() * 0.33), (int) (b.getY() * 0.34), null);
					break;
				}
			for (AdvertisingTrack r : this.scrollManager.getAdvertisingTracks())
				g.drawImage(
						ScalingImages.advertisingTrack.get(r.getKey()).getScaledInstance(
								(int) (ScalingImages.advertisingTrack.get(r.getKey()).getWidth(null)
										* 0.30),
								(int) (ScalingImages.advertisingTrack.get(r.getKey()).getHeight(null)
										* 0.30),
								0),
						(int) (r.getX() * 0.33), (int) (r.getY() * 0.34), null);
			for (Ramp r : this.scrollManager.getRamps())
				g.drawImage(ScalingImages.rampMaps.get(r.getKey()).getScaledInstance(
						(int) (ScalingImages.rampMaps.get(r.getKey()).getWidth(null) * 0.30),
						(int) (ScalingImages.rampMaps.get(r.getKey()).getHeight(null) * 0.30), 0),
						(int) (r.getX() * 0.33), (int) (r.getY() * 0.34), null);
		}
		g.drawImage(ImageProviderGame.getBackGroundMini(),
				this.scrollManager.getBackgroundTrack().getX(),
				this.scrollManager.getBackgroundTrack().getY(),
				ImageProviderGame.getBackGroundMini().getWidth(null),
				ImageProviderGame.getBackGroundMini().getHeight(null), null);
		if (this.scrollManager.isFinish())
			g.drawImage(
					ScalingImages.finalScenarioMapsMini
							.get(this.scrollManager.getFinalScenario().getKey()),
					((this.getWidth() / 2) - (ScalingImages.finalScenarioMapsMini
							.get(this.scrollManager.getFinalScenario().getKey()).getWidth(null) / 2)),
					(int) (this.scrollManager.getFinalScenario().getY() * 0.34), null);
		for (LeftTree t : this.scrollManager.getLeftTree())
			g.drawImage(ScalingImages.treeMapsMini.get(t.getKey()), (int) (t.getX() * 0.35),
					(int) (t.getY() * 0.34), null);
		for (RightTree t : this.scrollManager.getRightTree())
			g.drawImage(ScalingImages.treeMapsMini.get(t.getKey()), (int) (t.getX() * 0.35),
					(int) (t.getY() * 0.34), null);
		if (this.scrollManager.isShowGoal())
			g.drawImage(
					ScalingImages.goalMaps.get(this.scrollManager.getGoal().getKey()).getScaledInstance(
							(int) (ScalingImages.goalMaps.get(this.scrollManager.getGoal().getKey())
									.getWidth(null) * 0.30),
							(int) (ScalingImages.goalMaps.get(this.scrollManager.getGoal().getKey())
									.getHeight(null) * 0.30),
							0),
					(int) (this.scrollManager.getGoal().getX() * 0.37),
					(int) (this.scrollManager.getGoal().getY() * 0.38), null);
		int state = this.scrollManager.getPlayer().getBonus();
		switch (this.scrollManager.getPlayer().getState())
		{
		case 1:
			g.drawImage(
					ImageProviderGame.getPlayerLeft(state).getScaledInstance(
							(int) (ImageProviderGame.getPlayerLeft(state).getWidth(null) * 0.30),
							(int) (ImageProviderGame.getPlayerLeft(state).getHeight(null) * 0.30), 0),
					(int) (this.scrollManager.getPlayer().getX() * 0.35), (int) (this.getHeight()
							- (ImageProviderGame.getPlayerLeft(state).getHeight(null) * 0.30)),
					null);
			break;
		case 2:
			g.drawImage(
					ImageProviderGame.getPlayerRight(state).getScaledInstance(
							(int) (ImageProviderGame.getPlayerRight(state).getWidth(null) * 0.30),
							(int) (ImageProviderGame.getPlayerRight(state).getHeight(null) * 0.30), 0),
					(int) (this.scrollManager.getPlayer().getX() * 0.35), (int) (this.getHeight()
							- (ImageProviderGame.getPlayerRight(state).getHeight(null) * 0.30)),
					null);
			break;
		case 3:
			g.drawImage(ImageProviderGame.getCollitionMini(),
					(int) (this.scrollManager.getPlayer().getX() * 0.35),
					this.getHeight() - ImageProviderGame.getCollitionMini().getHeight(null), null);
			break;
		case 0:
			g.drawImage(
					ImageProviderGame.getPlayer(state).getScaledInstance(
							(int) (ImageProviderGame.getPlayer(state).getWidth(null) * 0.30),
							(int) (ImageProviderGame.getPlayer(state).getHeight(null) * 0.30), 0),
					(int) (this.scrollManager.getPlayer().getX() * 0.35), (int) (this.getHeight()
							- (ImageProviderGame.getPlayer(state).getHeight(null) * 0.30)),
					null);
			break;
		}
		for (CheckStart s : this.scrollManager.getStart())
			g.drawImage(ImageProviderGame.getStartMini(),
					(int) ((this.getWidth() * 0.5)
							- (ImageProviderGame.getStartMini().getWidth(null) / 2)),
					(int) ((this.getHeight() - ImageProviderGame.getStartMini().getHeight(null))
							+ s.getY()),
					null);
		for (Slalom s : this.scrollManager.getSlalom())
		{
			g.drawImage(ScalingImages.slalomMaps.get(s.getKey()).getScaledInstance(
					(int) (ScalingImages.slalomMaps.get(s.getKey()).getWidth(null) * 0.30),
					(int) (ScalingImages.slalomMaps.get(s.getKey()).getHeight(null) * 0.30), 0),
					(int) (s.getLeftX() * 0.33), (int) (s.getY() * 0.34), null);
			g.drawImage(ScalingImages.slalomMaps.get(s.getKey()).getScaledInstance(
					(int) (ScalingImages.slalomMaps.get(s.getKey()).getWidth(null) * 0.30),
					(int) (ScalingImages.slalomMaps.get(s.getKey()).getHeight(null) * 0.30), 0),
					(int) (s.getRightX() * 0.33), (int) (s.getY() * 0.34), null);
		}
		if (this.scrollManager.makePlane())
		{
			this.plane = new Plane(this.getWidth(), (int) (this.getHeight() * 0.05),
					this.scrollManager);
			if (this.frame.getAUDIO())
			{
				SoundProvider.getReproducerAirPlane().loop(0);
				SoundProvider.getReproducerAirPlane().setFramePosition(0);
			}
			this.plane.start();
		}
		if (this.plane.isAlive())
			g.drawImage(
					ImageProviderGame.getPlane().getScaledInstance(
							(int) (ImageProviderGame.getPlane().getWidth(null) * 0.30),
							(int) (ImageProviderGame.getPlane().getHeight(null) * 0.30), 0),
					this.plane.getX(), this.plane.getY(), null);
		if (this.scrollManager.isFinish())
		{
			g.setColor(Color.BLUE.darker().darker());
			g.drawString("New:" + this.scrollManager.crono.getCrono(), 10,
					(int) (this.getHeight() * 0.878));
		}
		else if (this.scrollManager.isPause())
			g.drawImage(ImageProviderNetwork.getPauseEnemyPlayPanel(), 0, 0, null);
		else
		{
			g.setColor(Color.BLUE.darker().darker());
			g.drawString(
					"scroll: " + this.scrollManager.getScrollTrack() + " / "
							+ this.scrollManager.getlenghtTrack(),
					(int) (this.getWidth() * 0.37), (int) (this.getHeight() * 0.6));
			g.setColor(this.color);
		}
	}
	public EnemyScrollManager getScrollManager()
	{
		return this.scrollManager;
	}
	public boolean getStart()
	{
		return this.start;
	}
}
