package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.game.gui.ImageProviderGame;
import it.mat.unical.igpe.abgbam.game.gui.ScalingImages;
public class BonusShield extends AbstractBonus
{
	public static final int ID = 2;
	public static final int HEIGHT = ImageProviderGame.getBonusShield().getHeight(null);
	public static final int WIDTH = ImageProviderGame.getBonusShield().getWidth(null);
	public BonusShield(float x, double speed)
	{
		super(x, speed);
	}
	public BonusShield(float x, float y, int key)
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
