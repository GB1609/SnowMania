package it.mat.unical.igpe.abgbam.general;
public class MainGame
{
	public static void main(String[] args)
	{
		Begin begin = new Begin();
		begin.start();
		MainFrame mainFrame = new MainFrame("SM");
		while (begin.isAlive())
		{
		}
		if (!mainFrame.isError())
			mainFrame.setVisible(true);
	}
}
