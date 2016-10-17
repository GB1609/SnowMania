package it.mat.unical.igpe.abgbam.game.core;
import java.awt.Dimension;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class EnemyScrollManager
{
	public static int HEIGHT;
	public static final double STANDARDSPEED = 2;
	public static int WIDTH;
	private List<AdvertisingTrack> advertisingTracks;
	private StaticObject background;
	private StaticObject backgroundTrack;
	private List<AbstractBonus> bonus;
	public Crono crono;
	private FinalScenario finalScenario;
	private Goal goal;
	private boolean goalOutOfScreen;
	private List<LeftAdvertising> leftAdvertising;
	private List<LeftDelimiter> leftDelimiter;
	private List<LeftTree> leftTree;
	private int lenghtTrack;
	private List<ObiqueLine> lines;
	private boolean pause;
	public int penalityTot;
	private Player player;
	private List<Ramp> ramps;
	private List<RightAdvertising> rightAdvertising;
	private List<RightDelimiter> rightDelimiter;
	private List<RightTree> rightTree;
	private List<Rock> rocks;
	private int scrollTrack;
	private boolean showGoal;
	private List<Slalom> slalom;
	private double speed = STANDARDSPEED;
	private List<CheckStart> start;
	private TrackReader trackReader;
	public EnemyScrollManager(TrackReader tr, Dimension dimension, int sumX)
	{
		this.trackReader = tr;
		this.scrollTrack = 0;
		this.penalityTot = 0;
		this.lenghtTrack = tr.getLengthTrack();
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
		this.player = new Player(this.speed, dimension, sumX);
		this.goalOutOfScreen = false;
		this.crono = new Crono();
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
	public int getlenghtTrack()
	{
		return this.lenghtTrack;
	}
	public List<ObiqueLine> getLines()
	{
		return this.lines;
	}
	public String getNewCrono()
	{
		return this.crono.newCrono(this.crono, this.penalityTot);
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
	public int getScrollTrack()
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
		return this.trackReader;
	}
	public boolean isFinish()
	{
		if ((this.scrollTrack <= this.lenghtTrack) && (!this.goalOutOfScreen))
			return false;
		this.crono.setCronoActive(false);
		return true;
	}
	public boolean isPause()
	{
		return this.pause;
	}
	public boolean isShowGoal()
	{
		return this.showGoal;
	}
	public Boolean makePlane()
	{
		if (this.scrollTrack == (this.lenghtTrack / 3))
			return true;
		return false;
	}
	public void parseStatusString(String status)
	{
		String[] elements = status.split("#");
		String pauseStatus = elements[1];
		String speedStatus = elements[2];
		String scrollStatus = elements[3];
		String[] leftDelimiterElement = elements[4].split(";");
		String[] rightDelimiterElement = elements[5].split(";");
		String[] leftTreeElement = elements[6].split(";");
		String[] rightTreeElement = elements[7].split(";");
		String[] bonusElement = elements[9].split(";");
		String[] slalomElement = elements[10].split(";");
		String[] rockElement = elements[11].split(";");
		String[] advertisingTrackElement = elements[12].split(";");
		String[] leftAdvertisingElement = elements[13].split(";");
		String[] rightAdvertisingElement = elements[14].split(";");
		String[] rampElement = elements[15].split(";");
		String[] goalElement = elements[16].split(";");
		String[] finalScenarioElement = elements[17].split(";");
		String[] playerStatus = elements[18].split(";");
		String cronoStatus = elements[19];
		String penalityStatus = elements[20];
		String goalOutStatus = elements[21];
		String showGoalStatus = elements[22];
		this.setPause(Boolean.parseBoolean(pauseStatus));
		this.speed = Double.parseDouble(speedStatus);
		this.scrollTrack = Integer.parseInt(scrollStatus);
		this.leftDelimiter.clear();
		if (leftDelimiterElement.length > 1)
			for (String s : leftDelimiterElement)
			{
				String[] split = s.split(":");
				this.leftDelimiter.add(new LeftDelimiter(Float.parseFloat(split[0]),
						Float.parseFloat(split[1]), Integer.parseInt(split[2])));
			}
		this.rightDelimiter.clear();
		if (rightDelimiterElement.length > 1)
			for (String s : rightDelimiterElement)
			{
				String[] split = s.split(":");
				this.rightDelimiter.add(new RightDelimiter(Float.parseFloat(split[0]),
						Float.parseFloat(split[1]), Integer.parseInt(split[2])));
			}
		this.leftTree.clear();
		if (leftTreeElement.length > 1)
			for (String s : leftTreeElement)
			{
				String[] split = s.split(":");
				this.leftTree.add(new LeftTree(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
						Integer.parseInt(split[2])));
			}
		this.rightTree.clear();
		if (rightTreeElement.length > 1)
			for (String s : rightTreeElement)
			{
				String[] split = s.split(":");
				this.rightTree.add(new RightTree(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
						Integer.parseInt(split[2])));
			}
		this.bonus.clear();
		if (bonusElement.length > 0)
			for (String s : bonusElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					switch (Integer.parseInt(split[3]))
					{
					case 1:
						this.bonus.add(new BonusF1(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
								Integer.parseInt(split[2])));
						break;
					case 2:
						this.bonus.add(new BonusShield(Float.parseFloat(split[0]),
								Float.parseFloat(split[1]), Integer.parseInt(split[2])));
						break;
					}
				}
		for (CheckStart start : this.start)
			start.update();
		this.slalom.clear();
		if (slalomElement.length > 0)
			for (String s : slalomElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.slalom.add(new Slalom(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
							Float.parseFloat(split[2]), Integer.parseInt(split[3])));
				}
		this.rocks.clear();
		if (rockElement.length > 0)
			for (String s : rockElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.rocks.add(new Rock(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
							Integer.parseInt(split[2])));
				}
		this.advertisingTracks.clear();
		if (advertisingTrackElement.length > 0)
			for (String s : advertisingTrackElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.advertisingTracks.add(new AdvertisingTrack(Float.parseFloat(split[0]),
							Float.parseFloat(split[1]), Integer.parseInt(split[2])));
				}
		this.leftAdvertising.clear();
		if (leftAdvertisingElement.length > 0)
			for (String s : leftAdvertisingElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.leftAdvertising.add(new LeftAdvertising(Float.parseFloat(split[0]),
							Float.parseFloat(split[1]), Integer.parseInt(split[2])));
				}
		this.rightAdvertising.clear();
		if (rightAdvertisingElement.length > 0)
			for (String s : rightAdvertisingElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.rightAdvertising.add(new RightAdvertising(Float.parseFloat(split[0]),
							Float.parseFloat(split[1]), Integer.parseInt(split[2])));
				}
		this.ramps.clear();
		if (rampElement.length > 0)
			for (String s : rampElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.ramps.add(new Ramp(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
							Integer.parseInt(split[2])));
				}
		if (goalElement.length > 0)
			for (String s : goalElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.goal = new Goal(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
							Integer.parseInt(split[2]));
				}
		if (finalScenarioElement.length > 0)
			for (String s : finalScenarioElement)
				if (!s.equals(" "))
				{
					String[] split = s.split(":");
					this.finalScenario = new FinalScenario(Float.parseFloat(split[0]),
							Float.parseFloat(split[1]), Integer.parseInt(split[2]));
				}
		for (String s : playerStatus)
		{
			String[] split = s.split(":");
			this.player = new Player(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
					Integer.parseInt(split[2]), Integer.parseInt(split[3]));
		}
		this.crono.setCrono(cronoStatus);
		this.penalityTot = Integer.parseInt(penalityStatus);
		this.goalOutOfScreen = Boolean.parseBoolean(goalOutStatus);
		this.showGoal = Boolean.parseBoolean(showGoalStatus);
	}
	public void setPause(boolean pause)
	{
		this.pause = pause;
	}
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
}