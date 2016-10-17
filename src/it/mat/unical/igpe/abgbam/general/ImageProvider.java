package it.mat.unical.igpe.abgbam.general;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
public class ImageProvider
{
	private static ImageIcon close;
	private static ImageIcon close1;
	private static Image editorBack;
	private static ImageIcon iconAdvertising;
	private static ImageIcon iconBonusF1;
	private static ImageIcon iconBonusImmune;
	private static ImageIcon iconComeBack;
	private static ImageIcon iconComeToMenu1;
	private static ImageIcon iconComeToMenu2;
	private static ImageIcon iconDown;
	private static ImageIcon iconEditor1;
	private static ImageIcon iconEditor2;
	private static ImageIcon iconExit1;
	private static ImageIcon iconExit2;
	private static ImageIcon iconNoExit1;
	private static ImageIcon iconNoExit2;
	private static ImageIcon iconNoMenu1;
	private static ImageIcon iconNoMenu2;
	private static ImageIcon iconOk1;
	private static ImageIcon iconOk2;
	private static ImageIcon iconOption1;
	private static ImageIcon iconOption2;
	private static ImageIcon iconPlay1;
	private static ImageIcon iconPlay2;
	private static ImageIcon iconRampUp;
	private static ImageIcon iconRock;
	private static ImageIcon iconSave;
	private static ImageIcon iconScoreBoard1;
	private static ImageIcon iconScoreBoard2;
	private static ImageIcon iconSlalomDelimiter;
	private static ImageIcon iconUp;
	private static ImageIcon iconYesExit1;
	private static ImageIcon iconYesExit2;
	private static ImageIcon iconYesMenu1;
	private static ImageIcon iconYesMenu2;
	private static Image mapBack;
	private static Image optionImage;
	private static Image requestBackGroundExit;
	private static Image requestBackGroundMenu;
	private static Image scorePanel;
	private static ImageIcon seeMap;
	private static ImageIcon seeMapMini;
	private static Image startImage;
	private static ImageIcon x1;
	private static ImageIcon x2;
	static
	{
		try
		{
			startImage = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/startImage.jpg"));
			scorePanel = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/scorePanel.png"));
			requestBackGroundExit = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageStart/backGroundExit.png"));
			requestBackGroundMenu = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageStart/backGroundMenu.png"));
			optionImage = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageOption/optionImage.jpg"));
			editorBack = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageEditor/editorBack.jpg"));
			seeMap = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/seeMap.png")));
			seeMapMini = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageEditor/seeMapMini.png")));
			mapBack = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/grid.png"));
			iconEditor1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/work1.png")));
			iconComeToMenu1 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageOption/comeToMenu1.png")));
			iconComeToMenu2 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageOption/comeToMenu2.png")));
			iconPlay1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/load1.png")));
			iconExit1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/exit1.png")));
			x1 = new ImageIcon(
					ImageIO.read(ImageProvider.class.getClassLoader().getResource("imageStart/x1.png")));
			x2 = new ImageIcon(
					ImageIO.read(ImageProvider.class.getClassLoader().getResource("imageStart/x2.png")));
			close = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/close.png")));
			close1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/close1.png")));
			iconOption1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/option1.png")));
			iconEditor2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/work2.png")));
			iconPlay2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/load2.png")));
			iconExit2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/exit2.png")));
			iconOption2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/option2.png")));
			iconUp = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageEditor/frecciaUp.png")));
			iconDown = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageEditor/frecciaDown.png")));
			iconSave = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/save.png")));
			iconOk1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/ok1.png")));
			iconOk2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/ok2.png")));
			iconAdvertising = new ImageIcon(ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageEditor/advertisingSign.png")));
			iconSlalomDelimiter = new ImageIcon(ImageIO.read(ImageProvider.class.getClassLoader()
					.getResource("imageEditor/SlalomDelimiter.png")));
			iconRock = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/rock.png")));
			iconRampUp = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/rampUP.png")));
			iconBonusF1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/f1mode.png")));
			iconBonusImmune = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/immune.png")));
			iconComeBack = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageEditor/comeBack.png")));
			iconScoreBoard1 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageStart/scoreBoard1.png")));
			iconScoreBoard2 = new ImageIcon(ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("imageStart/scoreBoard2.png")));
			iconYesExit1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/yesExit1.png")));
			iconYesMenu1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/yesMenu1.png")));
			iconYesExit2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/yesExit2.png")));
			iconYesMenu2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/yesMenu2.png")));
			iconNoExit1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/noExit1.png")));
			iconNoMenu1 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/noMenu1.png")));
			iconNoExit2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/noExit2.png")));
			iconNoMenu2 = new ImageIcon(ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("imageStart/noMenu2.png")));
			Begin.setEvolve(true);
		}
		catch (Exception e)
		{
			new InputException(null, 1);
		}
	}
	public static ImageIcon getClose()
	{
		return close;
	}
	public static ImageIcon getClose1()
	{
		return close1;
	}
	public static Image getEditorBack()
	{
		return editorBack;
	}
	public static ImageIcon getIconAdvertising()
	{
		return iconAdvertising;
	}
	public static ImageIcon getIconBonusF1()
	{
		return iconBonusF1;
	}
	public static ImageIcon getIconBonusImmune()
	{
		return iconBonusImmune;
	}
	public static ImageIcon getIconComeBack()
	{
		return iconComeBack;
	}
	public static ImageIcon getIconComeToMenu1()
	{
		return iconComeToMenu1;
	}
	public static ImageIcon getIconComeToMenu2()
	{
		return iconComeToMenu2;
	}
	public static ImageIcon getIconDown()
	{
		return iconDown;
	}
	public static ImageIcon getIconEditor1()
	{
		return iconEditor1;
	}
	public static ImageIcon getIconEditor2()
	{
		return iconEditor2;
	}
	public static ImageIcon getIconExit1()
	{
		return iconExit1;
	}
	public static ImageIcon getIconExit2()
	{
		return iconExit2;
	}
	public static ImageIcon getIconNoExit1()
	{
		return iconNoExit1;
	}
	public static ImageIcon getIconNoExit2()
	{
		return iconNoExit2;
	}
	public static ImageIcon getIconNoMenu1()
	{
		return iconNoMenu1;
	}
	public static ImageIcon getIconNoMenu2()
	{
		return iconNoMenu2;
	}
	public static ImageIcon getIconOk1()
	{
		return iconOk1;
	}
	public static ImageIcon getIconOk2()
	{
		return iconOk2;
	}
	public static ImageIcon getIconOption1()
	{
		return iconOption1;
	}
	public static ImageIcon getIconOption2()
	{
		return iconOption2;
	}
	public static ImageIcon getIconPlay1()
	{
		return iconPlay1;
	}
	public static ImageIcon getIconPlay2()
	{
		return iconPlay2;
	}
	public static ImageIcon getIconRampUp()
	{
		return iconRampUp;
	}
	public static ImageIcon getIconRock()
	{
		return iconRock;
	}
	public static ImageIcon getIconSave()
	{
		return iconSave;
	}
	public static ImageIcon getIconScoreBoard1()
	{
		return iconScoreBoard1;
	}
	public static ImageIcon getIconScoreBoard2()
	{
		return iconScoreBoard2;
	}
	public static ImageIcon getIconSlalomDelimiter()
	{
		return iconSlalomDelimiter;
	}
	public static ImageIcon getIconUp()
	{
		return iconUp;
	}
	public static ImageIcon getIconYesExit1()
	{
		return iconYesExit1;
	}
	public static ImageIcon getIconYesExit2()
	{
		return iconYesExit2;
	}
	public static ImageIcon getIconYesMenu1()
	{
		return iconYesMenu1;
	}
	public static ImageIcon getIconYesMenu2()
	{
		return iconYesMenu2;
	}
	public static Image getMapBack()
	{
		return mapBack;
	}
	public static Image getOptionImage()
	{
		return optionImage;
	}
	public static Image getRequestBackGroundExit()
	{
		return requestBackGroundExit;
	}
	public static Image getRequestBackGroundMenu()
	{
		return requestBackGroundMenu;
	}
	public static Image getScorePanel()
	{
		return scorePanel;
	}
	public static ImageIcon getSeeMap()
	{
		return seeMap;
	}
	public static ImageIcon getSeeMapMini()
	{
		return seeMapMini;
	}
	public static Image getStartImage()
	{
		return startImage;
	}
	public static ImageIcon getX1()
	{
		return x1;
	}
	public static ImageIcon getX2()
	{
		return x2;
	}
}
