package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class Rock extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getRock().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getRock().getWidth(null);
	public Rock(float x, double speed)
	{
		super(x, 250, speed);
		if (x > 683)
			this.left = false;
		else
			this.left = true;
		this.key = 0;
	}
	public Rock(float x, float y, int key)
	{
		super(x, y, 0);
	}
	public void update()
	{
		this.y += this.speed * 2;
		if (this.left)
			this.x -= this.speed;
		else
			this.x += this.speed;
		if (this.key < (ScalingImages.rockMaps.size() - (this.speed / 2)))
			this.key += this.speed / 2;
		this.width = ScalingImages.rockMaps.get(this.key).getWidth(null);
		this.height = ScalingImages.rockMaps.get(this.key).getHeight(null);
	}
}
