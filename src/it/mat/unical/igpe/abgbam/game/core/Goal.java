package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class Goal extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getGoal().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getGoal().getWidth(null);
	public Goal(double speed)
	{
		super(430 + (WIDTH / 4), 180, speed);
		this.width = WIDTH;
		this.height = HEIGHT;
	}
	public Goal(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public void update()
	{
		if (this.y < ScrollManager.HEIGHT)
		{
			this.y += this.speed * 2;
			if (this.key < (ScalingImages.goalMaps.size() - 1))
			{
				this.key++;
				this.x -= 2;
			}
		}
	}
}
