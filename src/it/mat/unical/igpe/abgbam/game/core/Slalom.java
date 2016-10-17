package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class Slalom extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getSlalom().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getSlalom().getWidth(null);
	float rightX;
	public Slalom(float x, double speed)
	{
		super(x, 250, speed);
		this.rightX = x + 40;
		if (x > 683)
			this.left = false;
		else
			this.left = true;
		this.key = 0;
	}
	public Slalom(float x, float rightX, float y, int key)
	{
		super(x, y, 0);
		this.rightX = rightX;
		this.key = key;
	}
	public float getLeftX()
	{
		return this.getX();
	}
	public float getRightX()
	{
		return this.rightX;
	}
	public void update()
	{
		if (this.key < (ScalingImages.slalomMaps.size() - (this.speed / 2)))
		{
			this.key += this.speed / 2;
			this.y += this.speed * 2;
		}
		else
			this.y += this.speed * 2.5;
		if (this.left)
		{
			this.x -= this.speed * 2;
			this.rightX -= this.speed;
		}
		else
		{
			this.x += this.speed;
			this.rightX += this.speed * 2;
		}
	}
}