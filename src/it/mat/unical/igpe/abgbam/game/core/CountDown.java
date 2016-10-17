package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.SoundProvider;
public class CountDown extends Thread
{
	private MainFrame frame;
	private Runnable runnable;
	private int timer = 6;
	public CountDown(Runnable runnable, MainFrame frame)
	{
		this.frame = frame;
		this.runnable = runnable;
	}
	public int getTimer()
	{
		return this.timer;
	}
	@Override
	public void run()
	{
		while (this.timer > 0)
		{
			try
			{
				if ((this.timer == 5) && this.frame.getAUDIO())
				{
					SoundProvider.getReproducerTimer().loop(0);
					SoundProvider.getReproducerTimer().setFramePosition(0);
				}
				this.timer--;
				Thread.sleep(1000);
			}
			catch (Exception e)
			{
				this.frame.getPlayPanel().start();
			}
			this.runnable.run();
		}
	}
}
