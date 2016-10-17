package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
public class CheckStart extends AbstractMovableObject
{
	private static final int HEIGHT = ImageProviderGame.getStart().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getStart().getWidth(null);
	public CheckStart(double speed)
	{
		super(430, 0, speed);
	}
	public CheckStart(float x, float y)
	{
		super(x, y, 0);
	}
	@Override
	public int getHeight()
	{
		return HEIGHT;
	}
	@Override
	public float getY()
	{
		return this.y;
	}
	public void update()
	{
		this.y += this.speed;
	}
}