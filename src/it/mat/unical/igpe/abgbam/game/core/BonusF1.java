package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class BonusF1 extends AbstractBonus
{
	private static final int ID = 1;
	public static final int HEIGHT = ImageProviderGame.getBonusF1().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getBonusF1().getWidth(null);
	public BonusF1(float x, double speed)
	{
		super(x, speed);
	}
	public BonusF1(float x, float y, int key)
	{
		super(x, y, key);
	}
	@Override
	public int getId()
	{
		return ID;
	}
	@Override
	public void update()
	{
		this.y += this.speed * 2;
		if (this.left)
			this.x -= this.speed;
		else
			this.x += this.speed;
		if (this.key < (ScalingImages.bonusF1Maps.size() - (this.speed / 2)))
			this.key += this.speed / 2;
		this.width = ScalingImages.bonusF1Maps.get(this.key).getWidth(null);
		this.height = ScalingImages.bonusF1Maps.get(this.key).getHeight(null);
	}
}
