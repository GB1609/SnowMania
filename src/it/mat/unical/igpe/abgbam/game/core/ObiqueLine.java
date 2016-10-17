package it.mat.unical.igpe.abgbam.game.core;
public class ObiqueLine
{
	private float x1, x2, y1, y2;
	public ObiqueLine(float x1, float y1, float x2, float y2)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	public float getX1()
	{
		return this.x1;
	}
	public float getX2()
	{
		return this.x2;
	}
	public float getY1()
	{
		return this.y1;
	}
	public float getY2()
	{
		return this.y2;
	}
}
