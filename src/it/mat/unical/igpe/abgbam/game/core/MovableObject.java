package it.mat.unical.igpe.abgbam.game.core;
public interface MovableObject
{
	int getHeight();
	int getKey();
	double getSpeed();
	int getWidth();
	float getX();
	float getY();
	boolean isUsed();
	void setKey(int key);
	void setSpeed(double speed);
	void setUsed(boolean used);
}