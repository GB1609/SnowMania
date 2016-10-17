package it.mat.unical.igpe.abgbam.game.core;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import it.mat.unical.igpe.abgbam.game.gui.PlayPanel;
public class MovePlayerListener implements MouseMotionListener, MouseListener
{
	private PlayPanel p;
	private ScrollManager scrollManager;
	public MovePlayerListener(ScrollManager s, PlayPanel p)
	{
		this.p = p;
		this.scrollManager = s;
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.scrollManager.getPlayer().setDirection(Direction.STOP);
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	@Override
	public void mouseMoved(MouseEvent event)
	{
		if (!this.scrollManager.isPause() && this.p.getStart() && !this.scrollManager.isFinish())
		{
			if (event.getPoint().getX() < this.scrollManager.getPlayer().getX())
				this.scrollManager.getPlayer().setDirection(Direction.LEFT);
			if (event.getPoint().getX() > this.scrollManager.getPlayer().getX())
				this.scrollManager.getPlayer().setDirection(Direction.RIGHT);
		}
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
	}
}
