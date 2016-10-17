package it.mat.unical.igpe.abgbam.general;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
public abstract class GeneralPanel extends JPanel
{
	private static final long serialVersionUID = 1;
	protected ArrayList<ModifiedButton> buttons;
	protected MainFrame frame;
	public GeneralPanel(MainFrame frame)
	{
		this.frame = frame;
		this.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.setVisible(true);
		this.buttons = new ArrayList<ModifiedButton>();
	}
	protected void addButtons()
	{
		for (ModifiedButton button : this.buttons)
			this.add(button);
	}
	public void disableButtons()
	{
		for (ModifiedButton button : this.buttons)
		{
			button.setEnabled(false);
			button.removeMouseListener(button.getPassOn());
		}
	}
	public void enableButtons()
	{
		for (ModifiedButton button : this.buttons)
		{
			button.setEnabled(true);
			button.addMouseListener(button.getPassOn());
		}
	}
}
