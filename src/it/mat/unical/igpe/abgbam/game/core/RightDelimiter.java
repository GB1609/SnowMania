package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class RightDelimiter extends AbstractMovableObject
{
	public static final float HEIGHT = ImageProviderGame.getRightDelimiter().getHeight(null);
	public static final float STANDARDX = 874;
	public static final float STANDARDY = 260;
	public static final int WidhtLimit = 1346;
	public static final float WIDTH = ImageProviderGame.getRightDelimiter().getWidth(null);
	private int deltaKey;
	private int deltaSpeed;
	public RightDelimiter(float x, float y, double speed)
	{
		super(x, y, speed);
		this.deltaKey = 1;
		this.deltaSpeed = 8;
	}
	public RightDelimiter(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public void addSpeed()
	{
		this.deltaKey = 2;
		this.deltaSpeed = 4;
	}
	public void resetSpeed()
	{
		this.deltaKey = 1;
		this.deltaSpeed = 8;
	}
	public void update()
	{
		if (this.x < WidhtLimit)
		{
			this.key += this.deltaKey;
			this.width = ScalingImages.leftDelimiterMaps.get(this.key).getWidth(null);
			this.x += (this.width - (this.width / 4)) * (this.speed / this.deltaSpeed);
			this.y += (this.width / 3.25f) * (this.speed / this.deltaSpeed);
		}
		else
		{
			this.x = STANDARDX;
			this.y = STANDARDY;
			this.setKey(16);
		}
	}
}
