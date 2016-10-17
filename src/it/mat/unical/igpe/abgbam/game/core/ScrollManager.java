package it.mat.unical.igpe.abgbam.game.core;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
import it.mat.unical.igpe.abgbam.general.InputException;
public class ScrollManager
{
	private static final int BONUSLENGHT = 100;
	public static int HEIGHT;
	public static final double STANDARDSPEED = 2;
	public static int WIDTH;
	private int advertisingAdd;
	private List<AdvertisingTrack> advertisingTracks;
	private StaticObject background;
	private StaticObject backgroundTrack;
	private boolean speedBonus;
	private int changeSpeed;
	private List<AbstractBonus> bonus;
	private FinalScenario finalScenario;
	private boolean alreadyOut;
	private Goal goal;
	private List<LeftAdvertising> leftAdvertising;
	private List<LeftDelimiter> leftDelimiter;
	private List<LeftTree> leftTree;
	private int lenghtTrack;
	private List<ObiqueLine> lines;
	private String namePlayer;
	private boolean pause;
	private int penality;
	private int penalityOut;
	private Player player;
	private List<Ramp> ramps;
	private boolean ranked;
	private List<RightAdvertising> rightAdvertising;
	private List<RightDelimiter> rightDelimiter;
	private List<RightTree> rightTree;
	private List<Rock> rocks;
	private float scrollTrack;
	private List<Slalom> slalom;
	private double speed = STANDARDSPEED;
	private double speedBeforeBonus;
	private int lenghtChangeSpeed;
	private List<CheckStart> start;
	private int startBonus;
	private TrackReader tr;
	public Crono crono;
	public int penalityTot;
	private boolean shieldBonus;
	public ScrollManager(TrackReader tr, String namePlayer, Dimension dimension, int sumX)
	{
		this.tr = tr;
		this.namePlayer = namePlayer;
		this.crono = new Crono();
		WIDTH = dimension.width;
		HEIGHT = dimension.height;
		this.lenghtTrack = tr.getLengthTrack();
		this.ranked = false;
		this.penality = 0;
		this.penalityOut = 0;
		this.scrollTrack = 0;
		this.penalityTot = 0;
		this.advertisingAdd = 100;
		this.speed = STANDARDSPEED;
		this.alreadyOut = true;
		this.speedBonus = false;
		this.lenghtChangeSpeed = (int) (50 * (this.speed / 2));
		this.changeSpeed = this.lenghtChangeSpeed;
		this.backgroundTrack = new Background();
		this.background = new Background();
		this.goal = new Goal(this.speed);
		this.finalScenario = new FinalScenario(this.speed);
		this.start = new CopyOnWriteArrayList<>();
		this.leftDelimiter = new CopyOnWriteArrayList<>();
		this.rightDelimiter = new CopyOnWriteArrayList<>();
		this.leftTree = new CopyOnWriteArrayList<>();
		this.rightTree = new CopyOnWriteArrayList<>();
		this.lines = new CopyOnWriteArrayList<>();
		this.slalom = new CopyOnWriteArrayList<>();
		this.bonus = new CopyOnWriteArrayList<>();
		this.rocks = new CopyOnWriteArrayList<>();
		this.advertisingTracks = new CopyOnWriteArrayList<>();
		this.leftAdvertising = new CopyOnWriteArrayList<>();
		this.rightAdvertising = new CopyOnWriteArrayList<>();
		this.ramps = new CopyOnWriteArrayList<>();
		this.start.add(new CheckStart(this.speed + 1));
		this.makeTree(this.speed);
		this.makeDelimitator(this.speed);
		this.player = new Player(this.speed, dimension, sumX);
		this.makeAdditionalObject();
	}
	private void endBonus()
	{
		if ((this.startBonus + (BONUSLENGHT * (this.speed / 2))) <= this.scrollTrack)
		{
			if (this.speedBonus)
			{
				this.speed = this.speedBeforeBonus + 1;
				this.setSpeed();
				this.speedBonus = false;
			}
			if (this.shieldBonus)
				this.shieldBonus = false;
			if (this.player.getBonus() != 0)
				this.player.setBonus(0);
		}
	}
	private void makeAdditionalObject()
	{
		int x;
		for (int i = 0; i < this.tr.getRockPosition().size(); i++)
		{
			x = this.tr.getRockPosition().get(i);
			i++;
			if ((int) this.scrollTrack == this.tr.getRockPosition().get(i))
			{
				this.rocks.add(new Rock(x, this.speed));
				this.tr.getRockPosition().remove(i);
				this.tr.getRockPosition().remove(i - 1);
			}
		}
		for (int i = 0; i < this.tr.getAdvertisingPosition().size(); i++)
		{
			x = this.tr.getAdvertisingPosition().get(i);
			i++;
			if ((int) this.scrollTrack == this.tr.getAdvertisingPosition().get(i))
			{
				this.advertisingTracks.add(new AdvertisingTrack(x, this.speed));
				this.tr.getAdvertisingPosition().remove(i);
				this.tr.getAdvertisingPosition().remove(i - 1);
			}
		}
		for (int i = 0; i < this.tr.getRampPosition().size(); i++)
		{
			x = this.tr.getRampPosition().get(i);
			i++;
			if ((int) this.scrollTrack == this.tr.getRampPosition().get(i))
			{
				this.ramps.add(new Ramp(x, this.speed));
				this.tr.getRampPosition().remove(i);
				this.tr.getRampPosition().remove(i - 1);
			}
		}
		if (((int) this.scrollTrack == this.advertisingAdd) && !this.isFinish())
		{
			this.advertisingAdd += (int) this.scrollTrack;
			this.leftAdvertising.add(new LeftAdvertising(this.speed));
			this.rightAdvertising.add(new RightAdvertising(this.speed));
		}
		for (int i = 0; i < this.tr.getBonusPositionF1().size(); i++)
		{
			x = this.tr.getBonusPositionF1().get(i);
			i++;
			if ((int) this.scrollTrack == this.tr.getBonusPositionF1().get(i))
			{
				this.bonus.add(new BonusF1(x, this.speed));
				this.tr.getBonusPositionF1().remove(i);
				this.tr.getBonusPositionF1().remove(i - 1);
			}
		}
		for (int i = 0; i < this.tr.getBonusPositionShield().size(); i++)
		{
			x = this.tr.getBonusPositionShield().get(i);
			i++;
			if ((int) this.scrollTrack == this.tr.getBonusPositionShield().get(i))
			{
				this.bonus.add(new BonusShield(x, this.speed));
				this.tr.getBonusPositionShield().remove(i);
				this.tr.getBonusPositionShield().remove(i - 1);
			}
		}
		for (int i = 0; i < this.tr.getSlalomPosition().size(); i++)
		{
			x = this.tr.getSlalomPosition().get(i);
			i++;
			if ((int) this.scrollTrack == this.tr.getSlalomPosition().get(i))
			{
				this.slalom.add(new Slalom(x, this.speed));
				this.tr.getSlalomPosition().remove(i);
				this.tr.getSlalomPosition().remove(i - 1);
			}
		}
	}
	private void makeDelimitator(double speed)
	{
		float x;
		int key = 16;
		float y = LeftDelimiter.STANDARDY;
		float delta = 3.25f;
		x = LeftDelimiter.STANDARDX;
		float width = ScalingImages.leftDelimiterMaps.get(key).getWidth(null);
		for (int cont = 0; cont < 13; cont++)
		{
			LeftDelimiter left = new LeftDelimiter(x, y, speed);
			left.setKey(key);
			this.leftDelimiter.add(left);
			key += 4;
			width = ScalingImages.leftDelimiterMaps.get(key).getWidth(null);
			x -= width - (width / 6);
			y += width / delta;
		}
		key = 16;
		x = RightDelimiter.STANDARDX;
		y = RightDelimiter.STANDARDY;
		width = ScalingImages.rightDelimiterMaps.get(key).getWidth(null);
		for (int cont = 0; cont < 13; cont++)
		{
			RightDelimiter right = new RightDelimiter(x, y, speed);
			right.setKey(key);
			this.rightDelimiter.add(right);
			key += 4;
			width = ScalingImages.leftDelimiterMaps.get(key).getWidth(null);
			x += width - (width / 4);
			y += width / delta;
		}
	}
	private void makeTree(double speed)
	{
		int width = ImageProviderGame.getTree().getWidth(null);
		int height = ImageProviderGame.getTree().getHeight(null);
		int x = LeftTree.STANDARDX;
		int key = 0;
		for (int y = LeftTree.STANDARDY; y < 500; y += height / 3)
		{
			LeftTree tree = new LeftTree(x, y, speed);
			tree.setKey(key);
			this.leftTree.add(tree);
			x -= width / 3;
			key++;
		}
		x = RightTree.STANDARDX;
		key = 0;
		for (int y = RightTree.STANDARDY; y < 500; y += height / 3)
		{
			RightTree tree = new RightTree(x, y, speed);
			tree.setKey(key);
			this.rightTree.add(tree);
			x += width / 3;
			key++;
		}
	}
	private boolean Out(Player p, Slalom s)
	{
		if (!this.shieldBonus)
			for (Slalom slalom : this.slalom)
				if (slalom.equals(s))
					if (!s.isUsed())
						if (((p.getY() + p.getHeight()) <= (s.getY() + s.getHeight())) && this.alreadyOut)
							if ((p.getX() < s.getLeftX()) || (p.getX() > (s.getRightX() + s.getWidth())))
							{
								s.setUsed(true);
								return true;
							}
							else
							{
								s.setUsed(true);
								return false;
							}
		return false;
	}
	private boolean outOfScreen(AbstractMovableObject obj)
	{
		if (obj instanceof Rock)
		{
			if ((obj.getY() - Rock.HEIGHT) >= HEIGHT)
				return true;
		}
		else if (obj instanceof LeftAdvertising)
		{
			if ((obj.getY() - LeftAdvertising.HEIGHT) >= HEIGHT)
				return true;
		}
		else if (obj instanceof RightAdvertising)
		{
			if ((obj.getY() - RightAdvertising.HEIGHT) >= HEIGHT)
				return true;
		}
		else if (obj instanceof LeftTree)
		{
			if (obj.getX() <= -obj.getWidth())
				return true;
		}
		else if (obj instanceof Slalom)
		{
			if ((obj.getY() - Slalom.HEIGHT) >= HEIGHT)
				return true;
		}
		else if (obj instanceof Ramp)
		{
			if ((obj.getY() - Ramp.HEIGHT) >= HEIGHT)
				return true;
		}
		else if (obj instanceof BonusF1)
		{
			if ((obj.getY() - BonusF1.HEIGHT) >= HEIGHT)
				return true;
		}
		else if (obj instanceof RightDelimiter)
		{
			RightDelimiter rDel = (RightDelimiter) obj;
			if (rDel.getX() > (RightDelimiter.WidhtLimit - 20))
				return true;
		}
		else if ((obj.getY() >= HEIGHT) || (obj.getX() <= 0) || (obj.getX() >= (WIDTH - 30)))
			return true;
		return false;
	}
	private void resetSpeed()
	{
		this.speed = STANDARDSPEED;
		this.changeSpeed = (int) this.scrollTrack;
		this.lenghtChangeSpeed = (int) (50 * (this.speed / 2));
		this.changeSpeed += this.lenghtChangeSpeed;
		this.setSpeed();
	}
	private void setSpeed()
	{
		for (LeftTree t : this.leftTree)
			t.setSpeed(this.speed);
		for (RightTree t : this.rightTree)
			t.setSpeed(this.speed);
		if (!this.bonus.isEmpty())
			for (AbstractBonus b : this.bonus)
				b.setSpeed(this.speed);
		if (!this.ramps.isEmpty())
			for (Ramp r : this.ramps)
				r.setSpeed(this.speed);
		if (!this.rocks.isEmpty())
			for (Rock r : this.rocks)
				r.setSpeed(this.speed);
		if (!this.leftAdvertising.isEmpty())
			for (LeftAdvertising a : this.leftAdvertising)
				a.setSpeed(this.speed);
		if (!this.rightAdvertising.isEmpty())
			for (RightAdvertising a : this.rightAdvertising)
				a.setSpeed(this.speed);
		this.player.setSpeed(this.speed);
		this.goal.setSpeed(this.speed);
		this.finalScenario.setSpeed(this.speed);
	}
	private void startShieldBonus()
	{
		this.shieldBonus = true;
	}
	private void update()
	{
		this.penalityTot = this.penality + this.penalityOut;
		if (!this.pause && !this.outOfScreen(this.goal))
		{
			if (this.showGoal())
			{
				this.goal.update();
				for (LeftDelimiter l : this.leftDelimiter)
					if (this.outOfScreen(l))
						this.leftDelimiter.remove(l);
					else
						l.update();
				for (RightDelimiter r : this.rightDelimiter)
					if (this.outOfScreen(r))
						this.rightDelimiter.remove(r);
					else
						r.update();
				for (LeftTree t : this.leftTree)
					if (this.outOfScreen(t))
						this.leftTree.remove(t);
					else
						t.update();
				for (RightTree t : this.rightTree)
					if (this.outOfScreen(t))
						this.rightTree.remove(t);
					else
						t.update();
				for (Slalom s : this.slalom)
					if (this.outOfScreen(s))
						this.slalom.remove(s);
					else
						s.update();
			}
			if (this.isFinish())
			{
				if (!this.ranked)
				{
					this.ranked = true;
					this.crono.setCronoActive(false);
					new UpdateRank(this.getTrackReader().getTrack(),
							this.crono.newCrono(this.crono, this.penalityTot), this.namePlayer);
				}
				this.resetSpeed();
				this.finalScenario.update();
			}
			else
			{
				this.makeAdditionalObject();
				if (this.speedBonus || this.shieldBonus)
					this.endBonus();
				this.scrollTrack += (this.speed / 10);
				if (this.scrollTrack >= this.changeSpeed)
				{
					this.addSpeed();
					this.lenghtChangeSpeed = (int) (50 * (this.speed / 2));
					this.changeSpeed += this.lenghtChangeSpeed;
				}
				for (LeftDelimiter l : this.leftDelimiter)
					l.update();
				for (RightDelimiter r : this.rightDelimiter)
					r.update();
				for (LeftTree t : this.leftTree)
					t.update();
				for (RightTree t : this.rightTree)
					t.update();
				for (AbstractBonus b : this.bonus)
					if (this.outOfScreen(b))
						this.bonus.remove(b);
					else
						b.update();
				for (Ramp r : this.ramps)
					if (this.outOfScreen(r))
						this.ramps.remove(r);
					else
						r.update();
				for (Rock r : this.rocks)
					if (this.outOfScreen(r))
						this.rocks.remove(r);
					else
						r.update();
				for (AdvertisingTrack r : this.advertisingTracks)
					if (this.outOfScreen(r))
						this.advertisingTracks.remove(r);
					else
						r.update();
				for (LeftAdvertising a : this.leftAdvertising)
					if (this.outOfScreen(a))
						this.leftAdvertising.remove(a);
					else
						a.update();
				for (RightAdvertising a : this.rightAdvertising)
					if (this.outOfScreen(a))
						this.rightAdvertising.remove(a);
					else
						a.update();
				for (Slalom s : this.slalom)
				{
					if (this.Out(this.player, s))
					{
						this.alreadyOut = false;
						this.penalityOut += 2;
					}
					if (this.outOfScreen(s))
					{
						this.slalom.remove(s);
						this.alreadyOut = true;
					}
					else
						s.update();
				}
				for (CheckStart s : this.start)
					if (this.outOfScreen(s))
						this.start.remove(s);
					else
						s.update();
			}
			this.player.update();
		}
		else
			this.finalScenario.setFinish(true);
	}
	public void addSpeed()
	{
		if (this.speed < 10)
		{
			this.speed++;
			this.setSpeed();
		}
	}
	public void addSpeed(float speed)
	{
		this.speed += speed;
		this.setSpeed();
	}
	public List<AdvertisingTrack> getAdvertisingTracks()
	{
		return this.advertisingTracks;
	}
	public StaticObject getBackground()
	{
		return this.background;
	}
	public StaticObject getBackgroundTrack()
	{
		return this.backgroundTrack;
	}
	public List<AbstractBonus> getBonus()
	{
		return this.bonus;
	}
	public String getCrono()
	{
		return this.crono.getCrono();
	}
	public FinalScenario getFinalScenario()
	{
		return this.finalScenario;
	}
	public Goal getGoal()
	{
		return this.goal;
	}
	public List<LeftAdvertising> getLeftAdvertising()
	{
		return this.leftAdvertising;
	}
	public List<LeftDelimiter> getLeftDelimiter()
	{
		return this.leftDelimiter;
	}
	public List<LeftTree> getLeftTree()
	{
		return this.leftTree;
	}
	public int getLenghtTrack()
	{
		return this.lenghtTrack;
	}
	public List<ObiqueLine> getLines()
	{
		return this.lines;
	}
	public Player getPlayer()
	{
		return this.player;
	}
	public List<Ramp> getRamps()
	{
		return this.ramps;
	}
	public List<RightAdvertising> getRightAdvertising()
	{
		return this.rightAdvertising;
	}
	public List<RightDelimiter> getRightDelimiter()
	{
		return this.rightDelimiter;
	}
	public List<RightTree> getRightTree()
	{
		return this.rightTree;
	}
	public List<Rock> getRocks()
	{
		return this.rocks;
	}
	public float getScrollTrack()
	{
		return this.scrollTrack;
	}
	public List<Slalom> getSlalom()
	{
		return this.slalom;
	}
	public double getSpeed()
	{
		return this.speed;
	}
	public List<CheckStart> getStart()
	{
		return this.start;
	}
	public TrackReader getTrackReader()
	{
		return this.tr;
	}
	public boolean intersection()
	{
		Rectangle playerRectangle = new Rectangle(
				(int) this.player.getX() + (this.player.getWidth() / 2),
				(int) this.player.getY() + (this.player.getHeight() / 2), this.player.getWidth(),
				this.player.getHeight());
		if (!this.shieldBonus)
			for (Slalom slalom : this.slalom)
			{
				final float x = this.player.getX() + (this.player.getWidth() / 2);
				final float y = this.player.getY() + (this.player.getHeight() / 2);
				if (((x > slalom.getLeftX()) && (x < (slalom.getLeftX() + Slalom.WIDTH)))
						|| ((x > slalom.getRightX()) && (x < (slalom.getRightX() + Slalom.WIDTH))))
					if ((y < (slalom.getY() + Slalom.HEIGHT))
							&& (y > (slalom.getY() + (Slalom.HEIGHT / 2))))
					{
						this.player.setState(3);
						if (!slalom.isUsed())
						{
							this.penality++;
							slalom.setUsed(true);
						}
						this.resetSpeed();
						if (this.getPlayer().getBonus() != 0)
							this.getPlayer().setBonus(0);
						if (this.speedBonus)
						{
							this.speedBonus = false;
							this.speed = this.speedBeforeBonus;
							this.setSpeed();
						}
						return true;
					}
			}
		for (Ramp r : this.ramps)
		{
			Rectangle rampRectangle = new Rectangle((int) r.getX(), (int) r.getY(), r.getWidth(),
					r.getHeight());
			if (playerRectangle.intersects(rampRectangle))
				if (!r.isUsed())
				{
					this.startBonus = (int) this.scrollTrack;
					this.speedBeforeBonus = this.speed;
					this.addSpeed(2);
					this.speedBonus = true;
					r.setUsed(true);
				}
		}
		if (!this.shieldBonus)
		{
			for (Rock r : this.rocks)
			{
				Rectangle rockRectangle = new Rectangle((int) r.getX(), (int) r.getY(), r.getWidth(),
						r.getHeight());
				if (playerRectangle.intersects(rockRectangle))
				{
					this.player.setState(3);
					if (!r.isUsed())
					{
						this.penality++;
						r.setUsed(true);
					}
					if (this.getPlayer().getBonus() != 0)
						this.getPlayer().setBonus(0);
					this.resetSpeed();
					if (this.speedBonus)
					{
						this.speedBonus = false;
						this.speed = this.speedBeforeBonus;
						this.setSpeed();
					}
					return true;
				}
			}
			for (AbstractBonus b : this.bonus)
			{
				Rectangle bonusRectangle = new Rectangle((int) b.getX(), (int) b.getY(), b.getWidth(),
						b.getHeight());
				if (playerRectangle.intersects(bonusRectangle))
				{
					this.bonus.remove(b);
					switch (b.getId())
					{
					case 1:
						this.speedBeforeBonus = this.speed;
						this.addSpeed(10);
						this.speedBonus = true;
						break;
					case 2:
						this.startShieldBonus();
						this.startBonus = (int) this.scrollTrack;
						break;
					}
					this.player.setBonus(b.getId());
					this.startBonus = (int) this.scrollTrack;
					return true;
				}
			}
			for (AdvertisingTrack a : this.advertisingTracks)
			{
				Rectangle advertisingRectangle = new Rectangle((int) a.getX(), (int) a.getY(),
						a.getWidth(), a.getHeight());
				if (playerRectangle.intersects(advertisingRectangle))
				{
					this.player.setState(3);
					if (!a.isUsed())
					{
						this.penality++;
						a.setUsed(true);
					}
					this.resetSpeed();
					if (this.getPlayer().getBonus() != 0)
						this.getPlayer().setBonus(0);
					if (this.speedBonus)
					{
						this.speedBonus = false;
						this.speed = this.speedBeforeBonus;
						this.setSpeed();
					}
					return true;
				}
			}
		}
		return false;
	}
	public boolean isFinish()
	{
		if ((this.scrollTrack < this.lenghtTrack) && (!this.outOfScreen(this.goal)))
			return false;
		this.crono.setCronoActive(false);
		return true;
	}
	public boolean isPause()
	{
		return this.pause;
	}
	public Boolean makePlane()
	{
		if ((int) this.scrollTrack == (this.lenghtTrack / 3))
			return true;
		return false;
	}
	public void setPause(boolean pause)
	{
		this.pause = pause;
		if (pause)
			this.crono.setCronoActive(false);
		else
			this.crono.setCronoActive(false);
	}
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	public boolean showGoal()
	{
		if ((this.scrollTrack + 20) >= this.lenghtTrack)
			return true;
		return false;
	}
	public void start(Runnable runnable)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(30);
						ScrollManager.this.update();
					}
					catch (Exception e)
					{
						e.printStackTrace();
						new InputException(null, 6);
						break;
					}
					runnable.run();
				}
			};
		}.start();
	}
	public String statusToString()
	{
		StringBuilder status = new StringBuilder();
		status.append("#");
		status.append(this.pause);
		status.append("#");
		status.append(this.speed);
		status.append("#");
		status.append((int) this.scrollTrack);
		status.append("#");
		if (!this.leftDelimiter.isEmpty())
			for (LeftDelimiter delimiter : this.leftDelimiter)
				status.append(
						delimiter.getX() + ":" + delimiter.getY() + ":" + delimiter.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.rightDelimiter.isEmpty())
			for (RightDelimiter delimiter : this.rightDelimiter)
				status.append(
						delimiter.getX() + ":" + delimiter.getY() + ":" + delimiter.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.leftTree.isEmpty())
			for (LeftTree tree : this.leftTree)
				status.append(tree.getX() + ":" + tree.getY() + ":" + tree.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.rightTree.isEmpty())
			for (RightTree tree : this.rightTree)
				status.append(tree.getX() + ":" + tree.getY() + ":" + tree.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.lines.isEmpty())
			for (ObiqueLine line : this.lines)
				status.append(line.getX1() + ":" + line.getY1() + ":" + line.getX2() + ":"
						+ line.getY2() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.bonus.isEmpty())
			for (AbstractBonus b : this.bonus)
				status.append(b.getX() + ":" + b.getY() + ":" + b.getKey() + ":" + b.getId() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.slalom.isEmpty())
			for (Slalom s : this.slalom)
				status.append(
						s.getLeftX() + ":" + s.getRightX() + ":" + s.getY() + ":" + s.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.rocks.isEmpty())
			for (Rock r : this.rocks)
				status.append(r.getX() + ":" + r.getY() + ":" + r.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.advertisingTracks.isEmpty())
			for (AdvertisingTrack adv : this.advertisingTracks)
				status.append(adv.getX() + ":" + adv.getY() + ":" + adv.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.leftAdvertising.isEmpty())
			for (LeftAdvertising adv : this.leftAdvertising)
				status.append(adv.getX() + ":" + adv.getY() + ":" + adv.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.rightAdvertising.isEmpty())
			for (RightAdvertising adv : this.rightAdvertising)
				status.append(adv.getX() + ":" + adv.getY() + ":" + adv.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (!this.ramps.isEmpty())
			for (Ramp ramp : this.ramps)
				status.append(ramp.getX() + ":" + ramp.getY() + ":" + ramp.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (this.goal != null)
			status.append(this.goal.getX() + ":" + this.goal.getY() + ":" + this.goal.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (this.finalScenario != null)
			status.append(this.finalScenario.getX() + ":" + this.finalScenario.getY() + ":"
					+ this.finalScenario.getKey() + ";");
		else
			status.append(" ");
		status.append("#");
		if (this.player != null)
			status.append(this.player.getX() + ":" + this.player.getY() + ":" + this.player.getState()
					+ ":" + this.player.getBonus() + ";");
		else
			status.append(" ");
		status.append("#");
		status.append(this.crono.newCrono(this.crono, this.penalityTot));
		status.append("#");
		status.append(this.penalityTot);
		status.append("#");
		status.append(this.outOfScreen(this.goal));
		status.append("#");
		status.append(this.showGoal());
		return status.toString();
	}
}