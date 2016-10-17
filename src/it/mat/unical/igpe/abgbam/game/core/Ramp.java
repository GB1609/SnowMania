package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class Ramp extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getRamp().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getRamp().getWidth(null);
	public Ramp(float x, double speed)
	{
		super(x, 250, speed);
		if (x > 683)
			this.left = false;
		else
			this.left = true;
		this.key = 0;
	}
	public Ramp(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public void update()
	{
		this.y += this.speed * 2;
		if (this.left)
			this.x -= this.speed;
		else
			this.x += this.speed;
		if (this.key < (ScalingImages.rampMaps.size() - (this.speed / 2)))
			this.key += this.speed / 2;
		this.width = ScalingImages.rampMaps.get(this.key).getWidth(null);
		this.height = ScalingImages.rampMaps.get(this.key).getHeight(null);
	}
}
