package it.mat.unical.igpe.abgbam.game.core;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import it.mat.unical.igpe.abgbam.game.gui.WinPanel;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.OutputException;
public class ConnectionManager implements Runnable
{
	private MainFrame mainFrame;
	private String nameTrack;
	private String playerName;
	private List<String> playerNames;
	private PrintWriter pw;
	private boolean server;
	private Socket socket;
	private WinPanel winPanel;
	public ConnectionManager(Socket socket, String name, MainFrame mainFrame, String nameTrack,
			boolean server)
	{
		this.socket = socket;
		this.playerName = name;
		this.mainFrame = mainFrame;
		this.nameTrack = nameTrack;
		this.server = server;
	}
	public void close()
	{
		try
		{
			this.socket.close();
		}
		catch (Exception e)
		{
			this.mainFrame.switchPanelStart(null);
		}
	}
	public String getPlayerName()
	{
		return this.playerName;
	}
	@Override
	public void run()
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.pw = new PrintWriter(this.socket.getOutputStream(), true);
			this.pw.println(this.getPlayerName());
			if (this.server)
			{
				this.pw.println("#Track");
				this.pw.println(this.nameTrack);
			}
			else
				this.pw.println("#Client");
			String buffer = br.readLine();
			if (!this.server)
			{
				String[] split = buffer.split("_");
				this.mainFrame.setNameTrackChoose(split[1]);
			}
			while (!buffer.equals("#START"))
			{
				final String[] split = buffer.split(";");
				if (split.length != 0)
				{
					this.playerNames = new ArrayList<>();
					for (final String name : split)
						this.playerNames.add(name);
				}
				if (buffer.contains("#ERROR"))
				{
					new OutputException(this.mainFrame, 5);
					return;
				}
				else
					buffer = br.readLine();
			}
			this.mainFrame.startEnemyGame();
			final ScrollManager gameManager = this.mainFrame.startNetworkGame();
			do
			{
				if (this.mainFrame.getEnemyPlayPanel().getScrollManager().isFinish()
						&& gameManager.getFinalScenario().getFinish())
					this.pw.println("#Time_"
							+ gameManager.crono.newInt(gameManager.crono, gameManager.penalityTot));
				this.pw.println(gameManager.statusToString());
				buffer = br.readLine();
				String[] split = buffer.split("_");
				while (!split[0].equals("#ENEMY"))
				{
					buffer = br.readLine();
					split = buffer.split("_");
				}
				if (split[1].equals("#WIN"))
				{
					if (this.winPanel == null)
						this.winPanel = new WinPanel(this.mainFrame, 1);
					gameManager.setPause(true);
					this.close();
				}
				else if (split[1].equals("#LOSE"))
				{
					if (this.winPanel == null)
						this.winPanel = new WinPanel(this.mainFrame, 2);
					gameManager.setPause(true);
					this.close();
				}
				else
				{
					this.mainFrame.getEnemyPlayPanel().getScrollManager().parseStatusString(split[1]);
					this.mainFrame.getEnemyPlayPanel().repaint();
				}
			}
			while (buffer != null);
		}
		catch (Exception e)
		{
			this.pw.println("#Disconnected");
			this.close();
		}
	}
}
