package it.mat.unical.igpe.abgbam.game.core;
public abstract class AbstractBonus extends AbstractMovableObject
{
	protected int key = 0;
	public AbstractBonus(float x, double speed)
	{
		super(x, 250, speed);
		if (x > 683)
			this.left = false;
		else
			this.left = true;
		this.key = 0;
	}
	public AbstractBonus(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	abstract public int getId();
	public int getKey()
	{
		return this.key;
	}
	abstract public void update();
}