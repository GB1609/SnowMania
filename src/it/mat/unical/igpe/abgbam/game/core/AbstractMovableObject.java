package it.mat.unical.igpe.abgbam.game.core;
public class AbstractMovableObject implements MovableObject
{
	protected int height;
	protected boolean left;
	protected double speed;
	protected int width;
	protected float x;
	protected float y;
	protected boolean used;
	protected int key;
	public AbstractMovableObject(float x, float y, double speed)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.used = false;
		this.key = 0;
	}
	@Override
	public int getHeight()
	{
		return this.height;
	}
	@Override
	public int getKey()
	{
		return this.key;
	}
	@Override
	public double getSpeed()
	{
		return this.speed;
	}
	@Override
	public int getWidth()
	{
		return this.width;
	}
	@Override
	public float getX()
	{
		return this.x;
	}
	@Override
	public float getY()
	{
		return this.y;
	}
	@Override
	public boolean isUsed()
	{
		return this.used;
	}
	@Override
	public void setKey(int key)
	{
		this.key = key;
	}
	@Override
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	@Override
	public void setUsed(boolean used)
	{
		this.used = used;
	}
}