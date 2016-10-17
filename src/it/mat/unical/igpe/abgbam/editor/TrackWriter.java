package it.mat.unical.igpe.abgbam.editor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.OutputException;
public class TrackWriter
{
	private ArrayList<Integer> advertisingPosition;
	private ArrayList<Integer> bonusPositionF1;
	private ArrayList<Integer> bonusPositionShield;
	private File file;
	private int lengthTrack;
	private String name;
	private FileWriter newTrack;
	private ArrayList<Integer> rampPosition;
	private ArrayList<Integer> rockPosition;
	private File scoreboard;
	private ArrayList<Integer> slalomPosition;
	private BufferedWriter write;
	public TrackWriter(String name)
	{
		this.name = name;
		this.lengthTrack = 0;
		this.rockPosition = new ArrayList<Integer>();
		this.bonusPositionF1 = new ArrayList<Integer>();
		this.bonusPositionShield = new ArrayList<Integer>();
		this.slalomPosition = new ArrayList<Integer>();
		this.rampPosition = new ArrayList<Integer>();
		this.advertisingPosition = new ArrayList<Integer>();
		this.file = new File("data/tracks/" + name + ".txt");
		this.scoreboard = new File("data/scoreBoard/scoreboard" + name + ".txt");
	}
	public void addAdvertisingPosition(int advertisingPosition)
	{
		this.advertisingPosition.add(advertisingPosition);
	}
	public void addBonusPositionF1(int f1Position)
	{
		this.bonusPositionF1.add(f1Position);
	}
	public void addBonusPositionShield(int shieldPosition)
	{
		this.bonusPositionShield.add(shieldPosition);
	}
	public void addRampPosition(int rampPosition)
	{
		this.rampPosition.add(rampPosition);
	}
	public void addRockPosition(int rockPosition)
	{
		this.rockPosition.add(rockPosition);
	}
	public void addSlalomPosition(int slalomPosition)
	{
		this.slalomPosition.add(slalomPosition);
	}
	public ArrayList<Integer> getAdvertisingPosition()
	{
		return this.advertisingPosition;
	}
	public ArrayList<Integer> getBonusPositionF1()
	{
		return this.bonusPositionF1;
	}
	public ArrayList<Integer> getBonusPositionShield()
	{
		return this.bonusPositionShield;
	}
	public int getLengthTrack()
	{
		return this.lengthTrack;
	}
	public String getName()
	{
		return this.name;
	}
	public ArrayList<Integer> getRampPosition()
	{
		return this.rampPosition;
	}
	public ArrayList<Integer> getRockPosition()
	{
		return this.rockPosition;
	}
	public ArrayList<Integer> getSlalomPosition()
	{
		return this.slalomPosition;
	}
	public void saveTrack(MainFrame mainFrame)
	{
		if (!this.file.exists())
			try
			{
				this.scoreboard.createNewFile();
				this.file.createNewFile();
			}
			catch (Exception e)
			{
				new OutputException(mainFrame, 1);
			}
		try
		{
			this.newTrack = new FileWriter(this.file);
			this.write = new BufferedWriter(this.newTrack);
			this.write.write(Integer.toString(this.lengthTrack) + "\n");
			this.write.write(Integer.toString(this.slalomPosition.size()) + "\n");
			for (int pos : this.slalomPosition)
				this.write.write(Integer.toString(pos) + "\n");
			this.write.write(Integer.toString(this.bonusPositionF1.size()) + "\n");
			for (int pos : this.bonusPositionF1)
				this.write.write(Integer.toString(pos) + "\n");
			this.write.write(Integer.toString(this.bonusPositionShield.size()) + "\n");
			for (int pos : this.bonusPositionShield)
				this.write.write(Integer.toString(pos) + "\n");
			this.write.write(Integer.toString(this.rockPosition.size()) + "\n");
			for (int pos : this.rockPosition)
				this.write.write(Integer.toString(pos) + "\n");
			this.write.write(Integer.toString(this.rampPosition.size()) + "\n");
			for (int pos : this.rampPosition)
				this.write.write(Integer.toString(pos) + "\n");
			this.write.write(Integer.toString(this.advertisingPosition.size()) + "\n");
			for (int pos : this.advertisingPosition)
				this.write.write(Integer.toString(pos) + "\n");
			this.write.flush();
			this.write.close();
		}
		catch (Exception e1)
		{
			new OutputException(mainFrame, 2);
		}
	}
	public void setLengthTrack(int lengthTrack)
	{
		this.lengthTrack = lengthTrack;
	}
}
