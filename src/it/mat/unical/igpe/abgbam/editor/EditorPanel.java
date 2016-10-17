package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class EditorPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JMenuItem comeBackToMenuItem;
	private JMenuItem exitMenuItem;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem newMenuItem;
	private MainFrame frame;
	public EditorPanel(MainFrame editorFrame)
	{
		this.frame = editorFrame;
		this.setSize(this.frame.getWidth(), this.frame.getHeight());
		this.setLayout(null);
		this.setupMenu(editorFrame);
		this.setBackground(Color.white);
	}
	private void setupMenu(MainFrame eFrame)
	{
		this.menuBar = new JMenuBar();
		this.menuBar.setBackground(Color.BLUE.darker().darker());
		this.menuBar.setSize(eFrame.getWidth(), 30);
		this.menu = new JMenu("File");
		this.menu.setFont(new Font("Showcard Gothic", 0, 17));
		this.menu.setForeground(Color.WHITE);
		this.newMenuItem = new JMenuItem("New");
		this.newMenuItem.setFont(new Font("Showcard Gothic", 0, 17));
		this.newMenuItem.setForeground(Color.BLUE.darker().darker());
		this.comeBackToMenuItem = new JMenuItem("Return to StartPage");
		this.comeBackToMenuItem.setFont(new Font("Showcard Gothic", 0, 17));
		this.comeBackToMenuItem.setForeground(Color.BLUE.darker().darker());
		this.exitMenuItem = new JMenuItem("Exit");
		this.exitMenuItem.setFont(new Font("Showcard Gothic", 0, 17));
		this.exitMenuItem.setForeground(Color.BLUE.darker().darker());
		this.menu.add(this.newMenuItem);
		this.menu.add(this.comeBackToMenuItem);
		this.menu.add(this.exitMenuItem);
		this.menuBar.add(this.menu);
		this.newMenuItem.addActionListener(e -> eFrame.editorCentreCreator());
		this.comeBackToMenuItem.addActionListener(e -> eFrame.comeBackToMenu());
		this.exitMenuItem.addActionListener(e -> eFrame.showExitConfirm());
		eFrame.setJMenuBar(this.menuBar);
		this.menu.setVisible(false);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(ImageProvider.getEditorBack(), 0, 0, this.frame.getWidth(),
				this.frame.getHeight(), null);
	}
	public void disableMenu()
	{
		this.menu.setEnabled(false);
	}
	public void enableMenu()
	{
		this.menu.setEnabled(true);
	}
	public JMenuBar getMenuBar()
	{
		return this.menuBar;
	}
	public void showMenu(boolean enable)
	{
		if (enable)
			this.menu.setVisible(true);
		else
			this.menu.setVisible(false);
	}
}
