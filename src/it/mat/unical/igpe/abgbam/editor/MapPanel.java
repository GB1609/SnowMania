package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.InputException;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class MapPanel extends JPanel
{
	private static Image advertising;
	private static Image advertising1;
	private static Image bonusF1;
	private static Image bonusF11;
	private static Cursor customCursorAdvertising;
	private static Cursor customCursorBonusF1;
	private static Cursor customCursorBonusImmune;
	private static Cursor customCursorRamp;
	private static Cursor customCursorRock;
	private static Cursor customCursorSlalomDelimiter;
	private static Image finish;
	private static Map<Integer, AttributesKey> map = new HashMap<>();
	private static Image ramp;
	private static Image ramp1;
	private static Image rock;
	private static Image rock1;
	private static final long serialVersionUID = 1L;
	private static Image slalom;
	private static Image slalom1;
	private static Image start;
	private static Image bonusImmune;
	private static Image bonusImmune1;
	private float conversionRealX;
	private float conversionRealY;
	private MainFrame frame;
	private int key = 0;
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private ArrayList<Integer> keysRemove = new ArrayList<Integer>();
	private int mapInWork;
	private JLabel numberP;
	private int numberPanelNecessary;
	private int posX;
	private int posY;
	private ModifiedButton seeMap;
	private int selected = 0;
	private int sumY = 0;
	private Toolkit toolkit;
	private TrackWriter trackWriter;
	public MapPanel(MainFrame editorFrame, int length, String name)
	{
		this.setSize(966, 768);
		this.setPreferredSize(this.getSize());
		this.frame = editorFrame;
		this.frame.enableAllButtons();
		this.frame.getRightPanel().setDown(false);
		this.chargeImage();
		this.createPersonalizateMouse();
		this.createTrackWriter(name, length);
		this.createObjects(length);
		this.setLayout(null);
		this.add(this.seeMap);
		this.add(this.numberP);
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					MapPanel.this.posX = e.getX();
					MapPanel.this.posY = e.getY();
					if ((MapPanel.this.posX > 210) && (MapPanel.this.posX < 710)
							&& (!(((MapPanel.this.posY - 20) < MapPanel.finish.getHeight(null))
									&& (MapPanel.this.mapInWork == (MapPanel.this.numberPanelNecessary
											- 1))))
							&& (!((MapPanel.this.posY > (735 - MapPanel.start.getHeight(null)))
									&& (MapPanel.this.mapInWork == 0))))
						switch (MapPanel.this.selected)
						{
						case 1:
							MapPanel.this.addAdvertising(MapPanel.this.posX, MapPanel.this.posY);
							MapPanel.this.keysRemove.add(1);
							break;
						case 2:
							MapPanel.this.addRock(MapPanel.this.posX, MapPanel.this.posY);
							MapPanel.this.keysRemove.add(2);
							break;
						case 3:
							MapPanel.this.addRamp(MapPanel.this.posX, MapPanel.this.posY);
							MapPanel.this.keysRemove.add(3);
							break;
						case 4:
							MapPanel.this.addBonusF1(MapPanel.this.posX, MapPanel.this.posY);
							MapPanel.this.keysRemove.add(4);
							break;
						case 5:
							MapPanel.this.addSlalomDelimiter(MapPanel.this.posX, MapPanel.this.posY);
							MapPanel.this.keysRemove.add(5);
							break;
						case 6:
							MapPanel.this.addBonusShield(MapPanel.this.posX, MapPanel.this.posY);
							MapPanel.this.keysRemove.add(6);
							break;
						default:
							break;
						}
				}
				else if (e.getButton() == MouseEvent.BUTTON3)
					if (!MapPanel.this.keys.isEmpty())
					{
						map.remove(MapPanel.this.keys.get(MapPanel.this.keys.size() - 1));
						MapPanel.this.keys.remove(MapPanel.this.keys.size() - 1);
						MapPanel.this.key--;
						switch (MapPanel.this.keysRemove.get(MapPanel.this.keysRemove.size() - 1))
						{
						case 1:
							MapPanel.this.trackWriter.getAdvertisingPosition()
									.remove(MapPanel.this.trackWriter.getAdvertisingPosition().size() - 1);
							MapPanel.this.trackWriter.getAdvertisingPosition()
									.remove(MapPanel.this.trackWriter.getAdvertisingPosition().size() - 1);
							break;
						case 2:
							MapPanel.this.trackWriter.getRockPosition()
									.remove(MapPanel.this.trackWriter.getRockPosition().size() - 1);
							MapPanel.this.trackWriter.getRockPosition()
									.remove(MapPanel.this.trackWriter.getRockPosition().size() - 1);
							break;
						case 3:
							MapPanel.this.trackWriter.getRampPosition()
									.remove(MapPanel.this.trackWriter.getRampPosition().size() - 1);
							MapPanel.this.trackWriter.getRampPosition()
									.remove(MapPanel.this.trackWriter.getRampPosition().size() - 1);
							break;
						case 4:
							MapPanel.this.trackWriter.getBonusPositionF1()
									.remove(MapPanel.this.trackWriter.getBonusPositionF1().size() - 1);
							MapPanel.this.trackWriter.getBonusPositionF1()
									.remove(MapPanel.this.trackWriter.getBonusPositionF1().size() - 1);
							break;
						case 5:
							MapPanel.this.trackWriter.getSlalomPosition()
									.remove(MapPanel.this.trackWriter.getSlalomPosition().size() - 1);
							MapPanel.this.trackWriter.getSlalomPosition()
									.remove(MapPanel.this.trackWriter.getSlalomPosition().size() - 1);
							break;
						case 6:
							MapPanel.this.trackWriter.getBonusPositionShield()
									.remove(MapPanel.this.trackWriter.getBonusPositionShield().size() - 1);
							MapPanel.this.trackWriter.getBonusPositionShield()
									.remove(MapPanel.this.trackWriter.getBonusPositionShield().size() - 1);
							break;
						default:
							break;
						}
						MapPanel.this.keysRemove.remove(MapPanel.this.keysRemove.size() - 1);
					}
				MapPanel.this.repaint();
			}
		});
		this.setVisible(true);
	}
	private void addAdvertising(int x, int y)
	{
		this.keys.add(this.key);
		map.put(this.key,
				new AttributesKey(MapPanel.advertising, MapPanel.advertising1, x, y, this.mapInWork));
		this.key++;
		y = this.getHeight() - y;
		this.trackWriter.addAdvertisingPosition((int) ((x * this.conversionRealX) + 480));
		this.trackWriter.addAdvertisingPosition((int) ((y * this.conversionRealY) + this.sumY));
	}
	private void addBonusF1(int x, int y)
	{
		this.keys.add(this.key);
		map.put(this.key,
				new AttributesKey(MapPanel.bonusF1, MapPanel.bonusF11, x, y, this.mapInWork));
		this.key++;
		y = this.getHeight() - y;
		this.trackWriter.addBonusPositionF1((int) ((x * this.conversionRealX) + 480));
		this.trackWriter.addBonusPositionF1((int) ((y * this.conversionRealY) + this.sumY));
	}
	private void addBonusShield(int x, int y)
	{
		this.keys.add(this.key);
		map.put(this.key,
				new AttributesKey(MapPanel.bonusImmune, MapPanel.bonusImmune1, x, y, this.mapInWork));
		this.key++;
		y = this.getHeight() - y;
		this.trackWriter.addBonusPositionShield((int) ((x * this.conversionRealX) + 480));
		this.trackWriter.addBonusPositionShield((int) ((y * this.conversionRealY) + this.sumY));
	}
	private void addRamp(int x, int y)
	{
		this.keys.add(this.key);
		map.put(this.key, new AttributesKey(MapPanel.ramp, MapPanel.ramp1, x, y, this.mapInWork));
		this.key++;
		y = this.getHeight() - y;
		this.trackWriter.addRampPosition((int) ((x * this.conversionRealX) + 480));
		this.trackWriter.addRampPosition((int) ((y * this.conversionRealY) + this.sumY));
	}
	private void addRock(int x, int y)
	{
		this.keys.add(this.key);
		map.put(this.key, new AttributesKey(MapPanel.rock, MapPanel.rock1, x, y, this.mapInWork));
		this.key++;
		y = this.getHeight() - y;
		this.trackWriter.addRockPosition((int) ((x * this.conversionRealX) + 480));
		this.trackWriter.addRockPosition((int) ((y * this.conversionRealY) + this.sumY));
	}
	private void addSlalomDelimiter(int x, int y)
	{
		this.keys.add(this.key);
		map.put(this.key, new AttributesKey(MapPanel.slalom, MapPanel.slalom1, x, y, this.mapInWork));
		this.key++;
		y = this.getHeight() - y;
		this.trackWriter.addSlalomPosition((int) ((x * this.conversionRealX) + 480));
		this.trackWriter.addSlalomPosition((int) ((y * this.conversionRealY) + this.sumY));
	}
	private void chargeImage()
	{
		try
		{
			advertising = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/advertising.png"));
			bonusF1 = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/f1mode.png"));
			bonusImmune = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/immune.png"));
			rock = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/rock.png"));
			ramp = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/rampUp.png"));
			slalom = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/slalom.png"));
			finish = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/finish.png"));
			start = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/start.png"));
			advertising1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/advertising1.png"));
			bonusF11 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/f1mode1.png"));
			bonusImmune1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/immune1.png"));
			rock1 = ImageIO
					.read(ImageProvider.class.getClassLoader().getResource("ImageEditorMap/rock1.png"));
			ramp1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/rampUp1.png"));
			slalom1 = ImageIO.read(
					ImageProvider.class.getClassLoader().getResource("ImageEditorMap/slalom1.png"));
		}
		catch (IOException e)
		{
			new InputException(this.frame, 1);
		}
	}
	private void createObjects(int length)
	{
		this.mapInWork = 0;
		this.numberPanelNecessary = length / 100;
		this.conversionRealX = (float) 212 / 500;
		this.conversionRealY = (float) 100 / 735;
		this.numberP = new JLabel((this.mapInWork + 1) + " / " + this.numberPanelNecessary);
		if (this.frame.getWidth() == 1024)
		{
			this.seeMap = new ModifiedButton("", ImageProvider.getSeeMapMini(),
					ImageProvider.getSeeMapMini(), this.frame,
					e -> new MiniMapPanel(this.frame, this.mapInWork, this.numberPanelNecessary, this));
			this.seeMap.setBounds(230, 12, this.seeMap.getWidth(), this.seeMap.getHeight());
			this.numberP.setFont(new Font("Showcard Gothic", 0, 25));
			this.numberP.setBounds(this.getWidth() - 230 - this.seeMap.getWidth(), 12,
					this.seeMap.getWidth(), this.seeMap.getHeight());
		}
		else
		{
			this.seeMap = new ModifiedButton("", ImageProvider.getSeeMap(), ImageProvider.getSeeMap(),
					this.frame,
					e -> new MiniMapPanel(this.frame, this.mapInWork, this.numberPanelNecessary, this));
			this.seeMap.setBounds(10, 20, this.seeMap.getWidth(), this.seeMap.getHeight());
			this.numberP.setFont(new Font("Showcard Gothic", 0, 50));
			this.numberP.setBounds(this.getWidth() - 10 - this.seeMap.getWidth(), 20,
					this.seeMap.getWidth(), this.seeMap.getHeight());
		}
		this.numberP.setForeground(Color.BLUE.darker().darker());
		this.numberP.setBorder(new LineBorder(Color.BLUE.darker().darker(), 3));
		this.numberP.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		this.numberP.setVerticalAlignment((int) CENTER_ALIGNMENT);
	}
	private void createPersonalizateMouse()
	{
		this.toolkit = Toolkit.getDefaultToolkit();
		customCursorRock = this.toolkit.createCustomCursor(MapPanel.rock, new Point(0, 0),
				"customCursorRock");
		customCursorAdvertising = this.toolkit.createCustomCursor(MapPanel.advertising,
				new Point(0, 0), "customCursorAdvertising");
		customCursorRamp = this.toolkit.createCustomCursor(MapPanel.ramp, new Point(0, 0),
				"customCursorRock");
		customCursorSlalomDelimiter = this.toolkit.createCustomCursor(MapPanel.slalom,
				new Point(0, 0), "customCursorSlalomDelimiter");
		customCursorBonusF1 = this.toolkit.createCustomCursor(MapPanel.bonusF1, new Point(0, 0),
				"customCursorF1");
		customCursorBonusImmune = this.toolkit.createCustomCursor(MapPanel.bonusImmune,
				new Point(0, 0), "customCursorImmune");
	}
	private void createTrackWriter(String name, int length)
	{
		this.trackWriter = new TrackWriter(name);
		this.trackWriter.setLengthTrack(length);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(ImageProvider.getMapBack(), 0, 0, this.getWidth(), this.getHeight(), null);
		if (this.mapInWork == 0)
			g.drawImage(MapPanel.start, 205, 732 - (MapPanel.start.getHeight(null)), null);
		if (this.mapInWork == (this.numberPanelNecessary - 1))
			g.drawImage(MapPanel.finish, 205, 0, null);
		for (int key : this.keys)
			if (map.get(key).getMapAppartenence() == this.mapInWork)
				g.drawImage(map.get(key).getImage(), map.get(key).getX(), map.get(key).getY(),
						map.get(key).getImage().getWidth(null), map.get(key).getImage().getHeight(null),
						null);
	}
	public ArrayList<Integer> getKeys()
	{
		return this.keys;
	}
	public Map<Integer, AttributesKey> getMap()
	{
		return map;
	}
	public TrackWriter getTrackWriter()
	{
		return this.trackWriter;
	}
	public void nextStep()
	{
		if ((this.mapInWork + 1) < this.numberPanelNecessary)
		{
			this.mapInWork++;
			this.repaint();
			this.sumY += 100;
		}
		if ((this.mapInWork + 1) == this.numberPanelNecessary)
			this.frame.getRightPanel().setUp(false);
		this.frame.getRightPanel().setDown(true);
		this.numberP.setText((this.mapInWork + 1) + " / " + this.numberPanelNecessary);
	}
	public void previousStep()
	{
		if ((this.mapInWork - 1) >= 0)
		{
			this.mapInWork--;
			this.repaint();
			this.sumY -= 100;
		}
		if (this.mapInWork == 0)
			this.frame.getRightPanel().setDown(false);
		this.frame.getRightPanel().setUp(true);
		this.numberP.setText((this.mapInWork + 1) + " / " + this.numberPanelNecessary);
	}
	public void setSelected(int i)
	{
		this.selected = i;
		switch (i)
		{
		case 1:
			this.setCursor(customCursorAdvertising);
			break;
		case 2:
			this.setCursor(customCursorRock);
			break;
		case 3:
			this.setCursor(customCursorRamp);
			break;
		case 4:
			this.setCursor(customCursorBonusF1);
			break;
		case 5:
			this.setCursor(customCursorSlalomDelimiter);
			break;
		case 6:
			this.setCursor(customCursorBonusImmune);
			break;
		}
	}
}
