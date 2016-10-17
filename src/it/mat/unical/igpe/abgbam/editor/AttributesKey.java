package it.mat.unical.igpe.abgbam.editor;
import java.awt.Image;
public class AttributesKey
{
	private final Image image;
	private final Image image2;
	private final int mapAppartenence;
	private final int x;
	private final int y;
	public AttributesKey(Image image, Image image2, int x, int y, int mapAppartenence)
	{
		this.image = image;
		this.image2 = image2;
		this.mapAppartenence = mapAppartenence;
		this.x = x;
		this.y = y - image.getHeight(null);
	}
	public Image getImage()
	{
		return this.image;
	}
	public Image getImage2()
	{
		return this.image2;
	}
	public int getMapAppartenence()
	{
		return this.mapAppartenence;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
}