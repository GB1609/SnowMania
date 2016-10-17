package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Image;
import java.util.HashMap;
import it.mat.unical.igpe.abgbam.general.InputException;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class ScalingImages
{
	public final static HashMap<Integer, Image> advertisingMaps = new HashMap<>();
	public final static HashMap<Integer, Image> advertisingMapsMini = new HashMap<>();
	public final static HashMap<Integer, Image> advertisingTrack = new HashMap<>();
	public final static HashMap<Integer, Image> bonusF1Maps = new HashMap<>();
	public final static HashMap<Integer, Image> bonusShieldMaps = new HashMap<>();
	public final static HashMap<Integer, Image> finalScenarioMaps = new HashMap<>();
	public final static HashMap<Integer, Image> finalScenarioMapsMini = new HashMap<>();
	public final static HashMap<Integer, Image> goalMaps = new HashMap<>();
	public final static HashMap<Integer, Image> leftDelimiterMaps = new HashMap<>();
	public final static HashMap<Integer, Image> leftDelimiterMapsMini = new HashMap<>();
	public final static HashMap<Integer, Image> rampMaps = new HashMap<>();
	public final static HashMap<Integer, Image> rightDelimiterMaps = new HashMap<>();
	public final static HashMap<Integer, Image> rightDelimiterMapsMini = new HashMap<>();
	public final static HashMap<Integer, Image> rockMaps = new HashMap<>();
	public final static HashMap<Integer, Image> slalomMaps = new HashMap<>();
	public final static HashMap<Integer, Image> treeMaps = new HashMap<>();
	public final static HashMap<Integer, Image> treeMapsMini = new HashMap<>();
	private static void scalingAdvertising()
	{
		int key = 0;
		for (int width = 45; width < ImageProviderGame.getAdvertising().getWidth(null); width++)
		{
			int height = width;
			advertisingMaps.put(key, ImageProviderGame.getAdvertising().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			advertisingMapsMini.put(key,
					advertisingMaps.get(key).getScaledInstance(
							(int) (advertisingMaps.get(key).getWidth(null) * 0.35),
							(int) (advertisingMaps.get(key).getHeight(null) * 0.35), Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingAdvertisingTrack()
	{
		int key = 0;
		for (int width = 15; width < (ImageProviderGame.getAdvertisingTrack()
				.getWidth(null)); width++)
		{
			int height = width;
			advertisingTrack.put(key, ImageProviderGame.getAdvertisingTrack().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingBonus()
	{
		int key = 0;
		for (int width = 15; width < (ImageProviderGame.getBonusF1().getWidth(null)); width++)
		{
			int height = width;
			bonusF1Maps.put(key,
					ImageProviderGame.getBonusF1().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			key++;
		}
		key = 0;
		for (int width = 15; width < (ImageProviderGame.getBonusShield().getWidth(null)); width++)
		{
			int height = width;
			bonusShieldMaps.put(key, ImageProviderGame.getBonusShield().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingfinalScenario()
	{
		int key = 0;
		int width = 1000;
		for (int height = 20; width <= ImageProviderGame.getFinalScenario()
				.getWidth(null); height += 2)
		{
			finalScenarioMaps.put(key, ImageProviderGame.getFinalScenario().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			finalScenarioMapsMini.put(key,
					finalScenarioMaps.get(key).getScaledInstance(
							(int) (finalScenarioMaps.get(key).getWidth(null) * 0.4),
							(int) (finalScenarioMaps.get(key).getHeight(null) * 0.4), Image.SCALE_SMOOTH));
			key++;
			width++;
		}
	}
	private static void scalingGoal()
	{
		int key = 0;
		int height = 108;
		for (int width = 250; (width < ImageProviderGame.getGoal().getWidth(null))
				|| (height < ImageProviderGame.getGoal().getHeight(null)); width += 4)
		{
			goalMaps.put(key,
					ImageProviderGame.getGoal().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			height += 2;
			key++;
		}
	}
	private static void scalingLeftDelimiter()
	{
		int key = 16;
		for (int width = 26; width < (ImageProviderGame.getLeftDelimiter().getWidth(null)); width++)
		{
			int height = width;
			leftDelimiterMaps.put(key, ImageProviderGame.getLeftDelimiter().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			leftDelimiterMapsMini.put(key,
					leftDelimiterMaps.get(key).getScaledInstance(
							(int) (leftDelimiterMaps.get(key).getWidth(null) * 0.4),
							(int) (leftDelimiterMaps.get(key).getHeight(null) * 0.4), Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingRamp()
	{
		int key = 0;
		for (int width = 15; width < (ImageProviderGame.getRamp().getWidth(null) + 50); width++)
		{
			int height = width;
			rampMaps.put(key,
					ImageProviderGame.getRamp().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingRightDelimiter()
	{
		int key = 16;
		for (int width = 26; width < (ImageProviderGame.getRightDelimiter().getWidth(null)); width++)
		{
			int height = width;
			rightDelimiterMaps.put(key, ImageProviderGame.getRightDelimiter().getScaledInstance(width,
					height, Image.SCALE_SMOOTH));
			rightDelimiterMapsMini.put(key, rightDelimiterMaps.get(key).getScaledInstance(
					(int) (rightDelimiterMaps.get(key).getWidth(null) * 0.4),
					(int) (rightDelimiterMaps.get(key).getHeight(null) * 0.4), Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingRock()
	{
		int key = 0;
		for (int width = 15; width < (ImageProviderGame.getRock().getWidth(null)); width++)
		{
			int height = width;
			rockMaps.put(key,
					ImageProviderGame.getRock().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			key++;
		}
	}
	private static void scalingSlalom()
	{
		int key = 0;
		int height = 24;
		for (int width = 12; width < (ImageProviderGame.getSlalom().getWidth(null)); width += 1)
		{
			slalomMaps.put(key,
					ImageProviderGame.getSlalom().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			key++;
			height++;
		}
	}
	private static void scalingTree()
	{
		int key = 0;
		int height = 60;
		for (int width = 180; width < (ImageProviderGame.getTree().getWidth(null) - 150); width += 5)
		{
			treeMaps.put(key,
					ImageProviderGame.getTree().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			treeMapsMini.put(key,
					treeMaps.get(key).getScaledInstance((int) (treeMaps.get(key).getWidth(null) * 0.3),
							(int) (treeMaps.get(key).getHeight(null) * 0.33), Image.SCALE_SMOOTH));
			height++;
			key++;
		}
	}
	public static void scalingImages(MainFrame mainFrame)
	{
		try
		{
			scalingLeftDelimiter();
			scalingRightDelimiter();
			scalingSlalom();
			scalingTree();
			scalingGoal();
			scalingRock();
			scalingRamp();
			scalingAdvertising();
			scalingAdvertisingTrack();
			scalingBonus();
			scalingfinalScenario();
		}
		catch (Exception e)
		{
			mainFrame.setError();
			new InputException(null, 1);
		}
	}
}
