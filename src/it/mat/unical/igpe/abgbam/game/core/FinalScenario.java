package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class FinalScenario extends AbstractMovableObject
{
	private boolean finish;
	public FinalScenario(double speed)
	{
		super(90, 250, speed);
		this.finish = false;
	}
	public FinalScenario(float x, float y, int key)
	{
		super(x, y, 0);
		this.key = key;
	}
	public boolean getFinish()
	{
		return this.finish;
	}
	public void setFinish(boolean finish)
	{
		this.finish = finish;
	}
	public void update()
	{
		if (this.key < (ScalingImages.finalScenarioMaps.size() - this.speed))
		{
			this.key += 4;
			if (this.y > 0)
				this.y--;
			if (this.x >= 1)
				this.x--;
		}
	}
}