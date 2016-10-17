package it.mat.unical.igpe.abgbam.general;
public class ScoreBoardLine
{
	private final String line;
	private final int points;
	public ScoreBoardLine(String buffer)
	{
		int separatorIndex = buffer.indexOf('-');
		this.points = Integer.parseInt(buffer.substring(0, separatorIndex));
		this.line = buffer.substring(separatorIndex + 1);
	}
	public ScoreBoardLine(String name, int score)
	{
		this.line = name;
		this.points = score;
	}
	public String getLine()
	{
		return this.line;
	}
	public int getScore()
	{
		return this.points;
	}
}