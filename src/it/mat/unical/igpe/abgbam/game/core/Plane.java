package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.general.SoundProvider;
public class Plane extends Thread
{
	private ScrollManager s1;
	private EnemyScrollManager s2;
	private int x;
	private int y;
	public Plane()
	{
	}
	public Plane(int x, int y, EnemyScrollManager s)
	{
		this.x = x;
		this.y = y;
		this.s2 = s;
	}
	public Plane(int x, int y, ScrollManager s)
	{
		this.x = x;
		this.y = y;
		this.s1 = s;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	@Override
	public void run()
	{
		while (this.x > (-(ImageProviderGame.getPlane().getWidth(null))))
			try
			{
				if (this.s1 != null)
				{
					if (!this.s1.isPause())
						this.x--;
				}
				else if (!this.s2.isPause())
					this.x--;
				Thread.sleep(7);
				if (((this.x - 5) < (-(ImageProviderGame.getPlane().getWidth(null)))))
				{
					SoundProvider.getReproducerAirPlane().stop();
					SoundProvider.getReproducerAirPlane().setFramePosition(0);
				}
			}
			catch (Exception e)
			{
				this.x = (-(ImageProviderGame.getPlane().getWidth(null)));
			}
	}
}
