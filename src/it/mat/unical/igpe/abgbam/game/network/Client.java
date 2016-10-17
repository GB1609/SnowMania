package it.mat.unical.igpe.abgbam.game.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client extends Thread
{
	private BufferedReader br;
	private int crono;
	private String message;
	private String nameClient;
	private boolean online;
	private PrintWriter pw;
	private final NetGameManager server;
	private final Socket socket;
	private boolean isServer;
	public Client(Socket socket, NetGameManager server)
	{
		this.socket = socket;
		this.server = server;
	}
	public void dispatch(final String message)
	{
		if ((this.pw != null) && (message != null))
			if (message.equals("#START"))
				this.pw.println(message);
			else if (!message.contains("#"))
				this.pw.println("#Track_" + message);
			else
				this.pw.println("#ENEMY_" + message);
	}
	public int getCrono()
	{
		return this.crono;
	}
	public String getNameClient()
	{
		return this.nameClient;
	}
	public boolean isOnline()
	{
		return this.online;
	}
	public boolean isServer()
	{
		return this.isServer;
	}
	public void received()
	{
		String buffer;
		try
		{
			buffer = this.br.readLine();
			String[] split = buffer.split("_");
			if (split[0].equals("#Time"))
			{
				if (split.length > 1)
				{
					this.crono = Integer.parseInt(split[1]);
					if ((this.server.getEnemyTime(this) > 0)
							&& (this.server.getEnemyTime(this) > this.crono))
					{
						this.sendWin();
						this.server.sendEnemyLose(this);
						this.server.setEndGame(true);
					}
				}
			}
			else if (buffer.equals("#Disconnected"))
			{
				if (!this.server.getEnemyStatus(this))
					this.sendWin();
				this.online = false;
			}
			else
				this.message = buffer;
		}
		catch (Exception e)
		{
			this.online = false;
		}
	}
	public void send()
	{
		this.server.dispatch(this.message, this);
	}
	public void sendError()
	{
		this.dispatch("#ERROR");
		this.online = false;
	}
	public void sendLose()
	{
		this.dispatch("#LOSE");
		this.online = false;
	}
	public void sendWin()
	{
		this.server.dispatch("#WIN", this);
		this.online = false;
	}
	public void setOnline(boolean online)
	{
		this.online = online;
	}
	void setup() throws IOException
	{
		this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.pw = new PrintWriter(this.socket.getOutputStream(), true);
		this.nameClient = this.br.readLine();
		String buffer = this.br.readLine();
		if (!buffer.equals("#Client"))
		{
			buffer = this.br.readLine();
			this.server.setTrackName(buffer);
			this.isServer = true;
		}
		else
			this.isServer = false;
		this.server.dispatch(this.server.getTrackName(), null);
	}
}
