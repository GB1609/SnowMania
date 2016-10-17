package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class AdvertisingTrack extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getAdvertisingTrack().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getAdvertisingTrack().getWidth(null);
	private int key;
	public AdvertisingTrack(float x, double speed)
	{
		super(x, 250, speed);
		if (x > 683)
			this.left = false;
		else
			this.left = true;
		this.key = 0;
	}
	public AdvertisingTrack(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public int getKey()
	{
		return this.key;
	}
	public void update()
	{
		this.y += this.speed * 2;
		if (this.left)
			this.x -= this.speed;
		else
			this.x += this.speed;
		if (this.key < (ScalingImages.advertisingTrack.size() - (this.speed / 2)))
			this.key += this.speed / 2;
		this.width = ScalingImages.advertisingTrack.get(this.key).getWidth(null);
		this.height = ScalingImages.advertisingTrack.get(this.key).getHeight(null);
	}
}
