package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.InputException;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class MiniMapPanel extends JPanel
{
	private static Image currentMap;
	private static Image end;
	private static final long serialVersionUID = 1L;
	private static Image start;
	private JDialog dialog;
	private int draw;
	private int ht;
	private int mapCurrent;
	private MapPanel mapPanel;
	public MiniMapPanel(MainFrame frame, int mapCurrent, int mapTotal, MapPanel mapPanel)
	{
		this.chargeImage(frame);
		this.mapPanel = mapPanel;
		this.dialog = new JDialog(frame);
		this.dialog.setSize(500, frame.getHeight() - 60);
		this.requestFocus();
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.mapCurrent = mapCurrent;
		this.setSize(this.dialog.getSize());
		this.draw = this.getHeight() / mapTotal;
		this.ht = (this.draw * 2) - this.draw;
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseExited(MouseEvent e)
			{
				MiniMapPanel.this.dialog.dispose();
			}
		});
		this.dialog.setUndecorated(true);
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(frame);
		this.dialog.setCursor(frame.getCursor());
		this.dialog.setVisible(true);
	}
	private void chargeImage(MainFrame frame)
	{
		try
		{
			currentMap = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/currentMap.png"));
			start = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/start1.png"));
			end = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/finish1.png"));
		}
		catch (Exception e)
		{
			new InputException(frame, 1);
		}
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(start, (this.getWidth() / 2) - (start.getWidth(null) / 2),
				this.getHeight() - start.getHeight(null), null);
		g.drawImage(end, (this.getWidth() / 2) - (end.getWidth(null) / 2), 2, null);
		for (int key : this.mapPanel.getKeys())
		{
			int map = this.mapPanel.getMap().get(key).getMapAppartenence() + 1;
			int posY = this.getHeight()
					- (((this.mapPanel.getMap().get(key).getY()) / 100) + (map * this.ht));
			int posX = this.mapPanel.getMap().get(key).getX() - 210;
			if (posX == 2)
				posX = (this.getWidth() / 2)
						- (this.mapPanel.getMap().get(key).getImage2().getWidth(null) / 2);
			g.drawImage(this.mapPanel.getMap().get(key).getImage2(), posX, posY,
					this.mapPanel.getMap().get(key).getImage2().getWidth(null),
					this.mapPanel.getMap().get(key).getImage2().getHeight(null), null);
		}
		g.drawImage(currentMap, 0,
				this.getHeight() - ((this.draw * this.mapCurrent) + start.getHeight(null) + this.ht),
				this.getWidth(), this.ht, null);
	}
}
