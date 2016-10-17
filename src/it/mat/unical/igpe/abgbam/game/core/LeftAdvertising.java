package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class LeftAdvertising extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getAdvertising().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getAdvertising().getWidth(null);
	public LeftAdvertising(double speed)
	{
		super(523, 250, speed);
		this.key = 0;
	}
	public LeftAdvertising(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public void update()
	{
		this.y += this.speed * 2;
		this.x -= this.speed * 3;
		if (this.key < (ScalingImages.advertisingMaps.size() - (this.speed / 3)))
			this.key += this.speed / 3;
		this.width = ScalingImages.advertisingMaps.get(this.key).getWidth(null);
		this.height = ScalingImages.advertisingMaps.get(this.key).getHeight(null);
	}
}
