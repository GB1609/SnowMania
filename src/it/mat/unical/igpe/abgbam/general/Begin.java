package it.mat.unical.igpe.abgbam.general;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class Begin extends Thread
{
	private static BufferedImage startPoint;
	private static int evolve;
	public static void setEvolve(boolean evolve)
	{
		if (evolve)
			Begin.evolve++;
		else
			Begin.evolve--;
	}
	private JFrame frame;
	private boolean live = true;
	private ChargePanel mainSnow;
	public Begin()
	{
		Begin.evolve = 0;
		this.frame = new JFrame("Loading");
		try
		{
			startPoint = ImageIO
					.read(Begin.class.getClassLoader().getResource("imageStart/loading.jpg"));
			this.frame.setIconImage(
					ImageIO.read(Begin.class.getClassLoader().getResource("imageStart/startSkin.jpg")));
		}
		catch (Exception e)
		{
			new InputException(null, 3);
		}
		this.frame.setSize(startPoint.getWidth(), startPoint.getHeight());
		this.frame.setUndecorated(true);
		this.frame.setLocationRelativeTo(null);
		this.mainSnow = new ChargePanel(this.frame, startPoint);
		this.frame.add(this.mainSnow);
		this.frame.setContentPane(this.mainSnow);
		this.frame.setVisible(true);
	}
	@Override
	public void run()
	{
		while (this.live)
		{
			for (int i = 0; i < Begin.evolve; i++)
			{
				this.mainSnow.evolve();
				Begin.setEvolve(false);
			}
			if (this.mainSnow.getProgressBar().getValue() == this.mainSnow.getProgressBar()
					.getMaximum())
			{
				this.frame.setVisible(false);
				this.live = false;
				continue;
			}
			try
			{
				Begin.sleep(50);
				continue;
			}
			catch (Exception e)
			{
				new InputException(null, 3);
			}
		}
	}
}
