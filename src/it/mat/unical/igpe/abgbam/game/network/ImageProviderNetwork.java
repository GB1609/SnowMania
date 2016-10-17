package it.mat.unical.igpe.abgbam.game.network;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import it.mat.unical.igpe.abgbam.general.Begin;
import it.mat.unical.igpe.abgbam.general.InputException;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class ImageProviderNetwork
{
	private static ImageIcon client1;
	private static ImageIcon client2;
	private static ImageIcon close1;
	private static ImageIcon close2;
	private static ImageIcon connect1;
	private static ImageIcon connect2;
	private static ImageIcon restart1;
	private static ImageIcon restart2;
	private static ImageIcon server1;
	private static ImageIcon server2;
	private static Image waitPanel;
	private static Image winPanel;
	private static Image losePanel;
	private static Image pauseEnemyPlayPanel;
	public static void chargeImage(MainFrame mainFrame)
	{
		try
		{
			client1 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/client1.png")));
			client2 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/client2.png")));
			close1 = new ImageIcon(ImageIO.read(
					ImageProviderNetwork.class.getClassLoader().getResource("imageNetwork/close1.png")));
			close2 = new ImageIcon(ImageIO.read(
					ImageProviderNetwork.class.getClassLoader().getResource("imageNetwork/close2.png")));
			restart1 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/restart1.png")));
			restart2 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/restart2.png")));
			server1 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/server1.png")));
			server2 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/server2.png")));
			waitPanel = ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/waitPanel.jpg"));
			pauseEnemyPlayPanel = ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/pauseEnemyPlayPanel.png"));
			winPanel = ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/cupWinner.png"));
			losePanel = ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/cupLoser.png"));
			connect1 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/connect1.png")));
			connect2 = new ImageIcon(ImageIO.read(ImageProviderNetwork.class.getClassLoader()
					.getResource("imageNetwork/connect2.png")));
			Begin.setEvolve(true);
		}
		catch (Exception e)
		{
			mainFrame.setError();
			new InputException(null, 1);
		}
	}
	public static ImageIcon getClient1()
	{
		return client1;
	}
	public static ImageIcon getClient2()
	{
		return client2;
	}
	public static ImageIcon getClose1()
	{
		return close1;
	}
	public static ImageIcon getClose2()
	{
		return close2;
	}
	public static ImageIcon getConnect1()
	{
		return connect1;
	}
	public static ImageIcon getConnect2()
	{
		return connect2;
	}
	public static Image getLosePanel()
	{
		return losePanel;
	}
	public static Image getPauseEnemyPlayPanel()
	{
		return pauseEnemyPlayPanel;
	}
	public static ImageIcon getRestart1()
	{
		return restart1;
	}
	public static ImageIcon getRestart2()
	{
		return restart2;
	}
	public static ImageIcon getServer1()
	{
		return server1;
	}
	public static ImageIcon getServer2()
	{
		return server2;
	}
	public static Image getWaitPanel()
	{
		return waitPanel;
	}
	public static Image getWinPanel()
	{
		return winPanel;
	}
}
