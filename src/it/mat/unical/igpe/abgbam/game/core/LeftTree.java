package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class LeftTree extends AbstractMovableObject
{
	public static final int WIDTH = ImageProviderGame.getTree().getWidth(null);
	public static final int HEIGHT = ImageProviderGame.getTree().getHeight(null);
	public static final int STANDARDY = 280 - (HEIGHT / 2);
	public static final int STANDARDX = (WIDTH / 2);
	public LeftTree(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public LeftTree(int x, int y, double speed)
	{
		super(x, y, speed);
	}
	public void update()
	{
		if (this.x < (-WIDTH))
		{
			this.y = STANDARDY;
			this.x = STANDARDX;
			this.setKey(0);
		}
		else
		{
			this.y += this.speed;
			this.x -= this.speed * 3;
			int test = ScalingImages.treeMaps.get(this.key).getWidth(null);
			if (this.key < (ScalingImages.treeMaps.size() - (this.speed / 2)))
			{
				this.key += this.speed / 2;
				this.x -= ScalingImages.treeMaps.get(this.key).getWidth(null) - test;
			}
		}
	}
}
