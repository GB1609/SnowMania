package it.mat.unical.igpe.abgbam.game.core;
import java.awt.Dimension;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
public class Player extends AbstractMovableObject
{
	public static final int HEIGHT = ImageProviderGame.getPlayer(0).getHeight(null);
	public static final int WIDTH = ImageProviderGame.getPlayer(0).getWidth(null);
	public static int Y;
	private Direction direction;
	private int state;
	private int sumX;
	private int bonus;
	public Player(double speed, Dimension dimension, int sumX)
	{
		super(683 - (WIDTH / 2), (int) (dimension.getHeight() - (HEIGHT * 1.3)), speed);
		Y = (int) (dimension.getHeight() - (HEIGHT * 3));
		this.width = WIDTH;
		this.height = HEIGHT;
		this.sumX = sumX;
		this.direction = Direction.STOP;
		this.state = 0;
		this.bonus = 0;
	}
	public Player(float x, float y, int state, int bonus)
	{
		super(x, y, 0);
		this.state = state;
		this.bonus = bonus;
	}
	public int getBonus()
	{
		return this.bonus;
	}
	public Direction getDirection()
	{
		return this.direction;
	}
	public int getState()
	{
		return this.state;
	}
	public int getSumX()
	{
		return this.sumX;
	}
	public void setBonus(int bonus)
	{
		this.bonus = bonus;
	}
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public void update()
	{
		switch (this.direction)
		{
		case LEFT:
			if (this.x > (WIDTH))
				this.x -= this.speed * 4;
			this.state = 1;
			break;
		case RIGHT:
			if (this.x < (((ScrollManager.WIDTH - WIDTH) - this.sumX) - 65))
				this.x += this.speed * 4;
			this.state = 2;
			break;
		default:
			this.state = 0;
			break;
		}
	}
}