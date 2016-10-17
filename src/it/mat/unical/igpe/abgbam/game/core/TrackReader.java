package it.mat.unical.igpe.abgbam.game.core;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import it.mat.unical.igpe.abgbam.general.InputException;
public class TrackReader
{
	private int lengthTrack;
	private String nameTrack;
	private int numberAdvertising;
	private int numberBonusF1;
	private int numberBonusShield;
	private int numberRamp;
	private int numberRock;
	private int numberSlalom;
	private List<Integer> advertisingPosition = new ArrayList<>();
	private List<Integer> bonusPositionF1 = new ArrayList<>();
	private List<Integer> bonusPositionShield = new ArrayList<>();
	private List<Integer> rampPosition = new ArrayList<>();
	private List<Integer> rockPosition = new ArrayList<>();
	private List<Integer> slalomPosition = new ArrayList<>();
	public TrackReader(String nameTrack)
	{
		this.nameTrack = nameTrack;
		try
		{
			BufferedReader reader = new BufferedReader(
					new FileReader("data/tracks/" + nameTrack + ".txt"));
			this.lengthTrack = Integer.parseInt(reader.readLine());
			this.numberSlalom = Integer.parseInt(reader.readLine());
			for (int i = 0; i < this.numberSlalom; i++)
				this.slalomPosition.add(Integer.parseInt(reader.readLine()));
			this.numberBonusF1 = Integer.parseInt(reader.readLine());
			for (int i = 0; i < this.numberBonusF1; i++)
				this.bonusPositionF1.add(Integer.parseInt(reader.readLine()));
			this.numberBonusShield = Integer.parseInt(reader.readLine());
			for (int i = 0; i < this.numberBonusShield; i++)
				this.bonusPositionShield.add(Integer.parseInt(reader.readLine()));
			this.numberRock = Integer.parseInt(reader.readLine());
			for (int i = 0; i < this.numberRock; i++)
				this.rockPosition.add(Integer.parseInt(reader.readLine()));
			this.numberRamp = Integer.parseInt(reader.readLine());
			for (int i = 0; i < this.numberRamp; i++)
				this.rampPosition.add(Integer.parseInt(reader.readLine()));
			this.numberAdvertising = Integer.parseInt(reader.readLine());
			for (int i = 0; i < this.numberAdvertising; i++)
				this.advertisingPosition.add(Integer.parseInt(reader.readLine()));
			reader.close();
		}
		catch (Exception e)
		{
			new InputException(null, 4);
		}
	}
	public List<Integer> getAdvertisingPosition()
	{
		return this.advertisingPosition;
	}
	public List<Integer> getBonusPositionF1()
	{
		return this.bonusPositionF1;
	}
	public List<Integer> getBonusPositionShield()
	{
		return this.bonusPositionShield;
	}
	public int getLengthTrack()
	{
		return this.lengthTrack;
	}
	public List<Integer> getRampPosition()
	{
		return this.rampPosition;
	}
	public List<Integer> getRockPosition()
	{
		return this.rockPosition;
	}
	public List<Integer> getSlalomPosition()
	{
		return this.slalomPosition;
	}
	public String getTrack()
	{
		return this.nameTrack;
	}
	public void setSlalomPosition(List<Integer> slalomPosition)
	{
		this.slalomPosition = slalomPosition;
	}
}