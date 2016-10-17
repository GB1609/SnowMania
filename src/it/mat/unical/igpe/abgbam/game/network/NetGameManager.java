package it.mat.unical.igpe.abgbam.game.network;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class NetGameManager extends Thread
{
	private final Set<Client> clients = new HashSet<Client>();
	private boolean endGame = false;
	private boolean running = false;
	private String trackName;
	private int serverConnected = 0;
	public void add(final Client cm) throws IOException
	{
		this.clients.add(cm);
		if (this.clients.size() == 2)
		{
			this.setup();
			this.dispatch("#START", null);
		}
	}
	public void disconnectClients()
	{
		this.clients.clear();
	}
	public void dispatch(final String message, final Client senderClient)
	{
		for (final Client cm : this.clients)
			if ((message != null) && message.equals("#WIN") && !senderClient.equals(null))
				senderClient.dispatch(message);
			else if (!cm.equals(senderClient))
				cm.dispatch(message);
	}
	private void enemyWin(Client client)
	{
		for (final Client cm : this.clients)
			if (!cm.equals(client))
				cm.sendWin();
	}
	public String getConnectedClientNames()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("#Players");
		for (final Client cm : this.clients)
			if (cm.getNameClient() != null)
			{
				sb.append(cm.getNameClient());
				sb.append(";");
			}
		return sb.toString();
	}
	public boolean getEnemyStatus(Client client)
	{
		for (final Client cm : this.clients)
			if (!cm.equals(client))
				return cm.isOnline();
		return false;
	}
	public int getEnemyTime(Client client)
	{
		for (final Client cm : this.clients)
			if (!cm.equals(client))
				return cm.getCrono();
		return 0;
	}
	public String getTrackName()
	{
		return this.trackName;
	}
	public boolean isEndGame()
	{
		return this.endGame;
	}
	@Override
	public void run()
	{
		Set<Client> clientsOffline = new HashSet<Client>();
		while (this.running)
		{
			for (final Client cm : this.clients)
				if (cm.isOnline())
				{
					cm.received();
					cm.send();
				}
				else
				{
					if (!clientsOffline.contains(cm))
						clientsOffline.add(cm);
					if (clientsOffline.size() == 1)
						this.enemyWin(cm);
				}
			if ((clientsOffline.size() == 2) || this.endGame)
				this.running = false;
		}
		this.disconnectClients();
	}
	public void sendEnemyLose(Client client)
	{
		for (final Client cm : this.clients)
			if (!cm.equals(client))
				cm.sendLose();
	}
	private void sendError()
	{
		for (final Client cm : this.clients)
			cm.sendError();
	}
	public void setEndGame(boolean endGame)
	{
		this.endGame = endGame;
	}
	public void setTrackName(String trackName)
	{
		this.trackName = trackName;
	}
	public void setup() throws IOException
	{
		List<String> names = new ArrayList<>();
		for (Client cm : this.clients)
		{
			cm.setOnline(true);
			cm.setup();
			if (cm.isServer())
				this.serverConnected++;
			new Thread(cm, cm.toString()).start();
			names.add(cm.getName());
		}
		if (this.serverConnected == 2)
			this.sendError();
		this.running = true;
		this.start();
	}
}
