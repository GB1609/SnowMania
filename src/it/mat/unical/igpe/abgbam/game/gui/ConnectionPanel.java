package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.editor.RequestPanelEditor;
import it.mat.unical.igpe.abgbam.game.core.ConnectionManager;
import it.mat.unical.igpe.abgbam.game.network.ImageProviderNetwork;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class ConnectionPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton close;
	private ModifiedButton connectButton;
	private JDialog dialog;
	private JLabel ip;
	private JTextArea ipTextField;
	private MainFrame mainFrame;
	private JLabel name;
	private JTextArea nameTextField;
	private JLabel port;
	private JTextArea portTextField;
	private final int separation = 30;
	private boolean server;
	public ConnectionPanel(MainFrame mainFrame, boolean server)
	{
		mainFrame.setCursor(mainFrame.getCustomCursor());
		this.mainFrame = mainFrame;
		this.setLayout(null);
		this.dialog = new JDialog(mainFrame);
		this.dialog.setSize(400, 400);
		this.requestFocus();
		this.setSize(this.dialog.getSize());
		this.setBackground(Color.BLUE);
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 4));
		this.ip = new JLabel("INSERT IP ADRESS");
		this.ipTextField = new JTextArea("169.254.49.193");
		this.name = new JLabel("insert your name");
		this.nameTextField = new JTextArea(mainFrame.getNamePlayer());
		this.port = new JLabel("insert number of port");
		this.portTextField = new JTextArea("2727");
		this.connectButton = new ModifiedButton("", ImageProviderNetwork.getConnect1(),
				ImageProviderNetwork.getConnect2(), mainFrame, e -> this.connectToServer());
		this.close = new ModifiedButton("", ImageProvider.getX1(), ImageProvider.getX2(), mainFrame,
				e -> this.dialog.dispose());
		this.close.setBorderPainted(false);
		this.close.setEvidence(false);
		this.ip.setBounds(10, this.separation * 2, 380, 28);
		this.ipTextField.setBounds(10, this.separation * 3, 380, 25);
		this.name.setBounds(10, this.separation * 8, 380, 28);
		this.nameTextField.setBounds(10, this.separation * 9, 380, 25);
		this.port.setBounds(10, this.separation * 5, 380, 28);
		this.portTextField.setBounds(10, this.separation * 6, 380, 25);
		this.connectButton
				.setBounds(
						(this.getWidth() / 2) - (ImageProviderNetwork.getConnect1().getIconWidth() / 2),
						(int) (this.getHeight()
								- (ImageProviderNetwork.getConnect1().getIconHeight() * 1.5)),
						ImageProviderNetwork.getConnect1().getIconWidth(),
						ImageProviderNetwork.getConnect1().getIconHeight());
		this.close.setBounds(this.getWidth() - (ImageProvider.getX1().getIconWidth() + 7), 7,
				ImageProvider.getX1().getIconWidth(), ImageProvider.getX1().getIconHeight());
		this.setTextArea(this.ipTextField);
		this.setTextArea(this.nameTextField);
		this.setTextArea(this.portTextField);
		this.setLabel(this.ip);
		this.setLabel(this.name);
		this.setLabel(this.port);
		this.add(this.connectButton);
		this.add(this.close);
		this.add(this.ipTextField);
		this.add(this.nameTextField);
		this.add(this.portTextField);
		this.add(this.ip);
		this.add(this.name);
		this.add(this.port);
		this.dialog.setUndecorated(true);
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(mainFrame);
		this.dialog.setCursor(this.mainFrame.getCursor());
		this.dialog.setVisible(true);
		this.server = server;
	}
	private void connectToServer()
	{
		try
		{
			final Socket socket = new Socket(this.ipTextField.getText(),
					Integer.parseInt(this.portTextField.getText()));
			final ConnectionManager connectionManager = new ConnectionManager(socket,
					this.nameTextField.getText(), this.mainFrame, this.mainFrame.getNameTrackChoose(),
					this.server);
			this.mainFrame.setContentPane(new WaitPanel(this.mainFrame));
			new Thread(connectionManager, "Connection Manager").start();
		}
		catch (IOException e)
		{
			new RequestPanelEditor(this.mainFrame, null, 4);
		}
		this.dialog.dispose();
	}
	private void setLabel(JLabel modified)
	{
		modified.setForeground(Color.WHITE);
		modified.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.06)));
	}
	private void setTextArea(JTextArea modified)
	{
		modified.setBackground(Color.white);
		modified.setForeground(Color.BLUE.darker());
		modified.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.04)));
		modified.setBorder(new LineBorder(Color.BLUE.darker().darker(), 3));
		modified.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				modified.setText("");
			}
		});
	}
}
