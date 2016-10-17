package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Image;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import it.mat.unical.igpe.abgbam.general.Begin;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.InputException;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class ImageProviderGame
{
	private static Image advertising;
	private static Image advertisingTrack;
	private static Image backGround;
	private static Image backGroundMini;
	private static Image backgroundTrack;
	private static Image bonusF1;
	private static Image bonusShield;
	private static ImageIcon close;
	private static ImageIcon close2;
	private static Image collition;
	private static Image collitionMini;
	private static ImageIcon createTracks;
	private static ImageIcon createTracks2;
	private static ImageIcon downhill;
	private static ImageIcon downhill2;
	private static Image finalScenario;
	private static Image goal;
	private static Image goalMini;
	private static Image leftDelimiter;
	private static ImageIcon multiplayer;
	private static ImageIcon multiplayer2;
	private static ImageIcon nextTracks;
	private static ImageIcon nextTracks2;
	private static Image plane;
	private static Image player;
	private static Image playerBonusF1;
	private static Image playerBonusShield;
	private static Image playerLeft;
	private static Image playerLeftBonusF1;
	private static Image playerLeftBonusShield;
	private static Image playerRight;
	private static Image playerRightBonusF1;
	private static Image playerRightBonusShield;
	private static Image ramp;
	private static Image rightDelimiter;
	private static Image rock;
	private static Image selectTrack;
	private static Image slalom;
	private static Image start;
	private static Image startMini;
	private static Image tree;
	public static void chargeImage(MainFrame mainFrame)
	{
		try
		{
			Random random = new Random();
			downhill = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/downhill.png")));
			downhill2 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/downhill2.png")));
			multiplayer = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/multiplayer.png")));
			multiplayer2 = new ImageIcon(ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGamePanel/multiplayer2.png")));
			close = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGamePanel/close.png")));
			close2 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/close2.png")));
			nextTracks = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/nextTracks.png")));
			nextTracks2 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/nextTracks2.png")));
			createTracks = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/createTrack.png")));
			createTracks2 = new ImageIcon(ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGamePanel/createTrack2.png")));
			selectTrack = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGamePanel/SelectTrack.png"));
			start = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/start.png"));
			startMini = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/mini/start.png"));
			advertisingTrack = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/advertisingTrack" + (random.nextInt(4) + 1) + ".png"));
			collition = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/collition.png"));
			slalom = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/slalom.png"));
			rock = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/rock" + (random.nextInt(4) + 1) + ".png"));
			ramp = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/ramp.png"));
			advertising = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/advertising" + (random.nextInt(6) + 1) + ".png"));
			bonusF1 = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/bonusF1.png"));
			bonusShield = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/bonusShield.png"));
			backgroundTrack = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/snow.jpg"));
			leftDelimiter = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/leftDelimiter.png"));
			rightDelimiter = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/rightDelimiter.png"));
			player = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/playerCartman.png"));
			playerLeft = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/playerCartmanLeft.png"));
			playerRight = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/playerCartmanRight.png"));
			playerBonusF1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/playerF1stop.png"));
			playerLeftBonusF1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/playerF1left.png"));
			playerRightBonusF1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/playerF1right.png"));
			playerBonusShield = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/playerCartmanShield.png"));
			playerLeftBonusShield = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/playerCartmanLeftShield.png"));
			playerRightBonusShield = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/playerCartmanRightShield.png"));
			tree = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/tree1.png"));
			goal = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/goal.png"));
			plane = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/plane.png"));
			finalScenario = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageGameGame/final.png"));
			backGround = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageGameGame/background.jpg"));
			collitionMini = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/mini/collition.png"));
			backGroundMini = ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageGameGame/mini/background.jpg"));
			Begin.setEvolve(true);
		}
		catch (Exception e)
		{
			mainFrame.setError();
			new InputException(null, 1);
		}
	}
	public static Image getAdvertising()
	{
		return advertising;
	}
	public static Image getAdvertisingTrack()
	{
		return advertisingTrack;
	}
	public static Image getBackground()
	{
		return backGround;
	}
	public static Image getBackGround()
	{
		return backGround;
	}
	public static Image getBackGroundMini()
	{
		return backGroundMini;
	}
	public static Image getBackgroundTrack()
	{
		return backgroundTrack;
	}
	public static Image getBonusF1()
	{
		return bonusF1;
	}
	public static Image getBonusShield()
	{
		return bonusShield;
	}
	public static ImageIcon getClose()
	{
		return close;
	}
	public static ImageIcon getClose2()
	{
		return close2;
	}
	public static Image getCollition()
	{
		return collition;
	}
	public static Image getCollitionMini()
	{
		return collitionMini;
	}
	public static ImageIcon getCreateTracks()
	{
		return createTracks;
	}
	public static ImageIcon getCreateTracks2()
	{
		return createTracks2;
	}
	public static ImageIcon getDownhill()
	{
		return downhill;
	}
	public static ImageIcon getDownhill2()
	{
		return downhill2;
	}
	public static Image getFinalScenario()
	{
		return finalScenario;
	}
	public static Image getGoal()
	{
		return goal;
	}
	public static Image getGoalMini()
	{
		return goalMini;
	}
	public static Image getLeftDelimiter()
	{
		return leftDelimiter;
	}
	public static ImageIcon getMultiplayer()
	{
		return multiplayer;
	}
	public static ImageIcon getMultiplayer2()
	{
		return multiplayer2;
	}
	public static ImageIcon getNextTracks()
	{
		return nextTracks;
	}
	public static ImageIcon getNextTracks2()
	{
		return nextTracks2;
	}
	public static Image getPlane()
	{
		return plane;
	}
	public static Image getPlayer(int type)
	{
		switch (type)
		{
		case 1:
			return playerBonusF1;
		case 2:
			return playerBonusShield;
		default:
			return player;
		}
	}
	public static Image getPlayerLeft(int type)
	{
		switch (type)
		{
		case 1:
			return playerLeftBonusF1;
		case 2:
			return playerLeftBonusShield;
		default:
			return playerLeft;
		}
	}
	public static Image getPlayerRight(int type)
	{
		switch (type)
		{
		case 1:
			return playerRightBonusF1;
		case 2:
			return playerRightBonusShield;
		default:
			return playerRight;
		}
	}
	public static Image getRamp()
	{
		return ramp;
	}
	public static Image getRightDelimiter()
	{
		return rightDelimiter;
	}
	public static Image getRock()
	{
		return rock;
	}
	public static Image getSelectTrack()
	{
		return selectTrack;
	}
	public static Image getSlalom()
	{
		return slalom;
	}
	public static Image getStart()
	{
		return start;
	}
	public static Image getStartMini()
	{
		return startMini;
	}
	public static Image getTree()
	{
		return tree;
	}
}