package it.mat.unical.igpe.abgbam.game.network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server
{
	public static void main(final String[] args) throws IOException
	{
		final Server server;
		server = new Server(2727);
		server.runServer();
	}
	private final int port;
	private final boolean running = true;
	private ServerSocket serverSocket;
	public Server(final int port)
	{
		this.port = port;
	}
	private void runServer() throws IOException
	{
		this.serverSocket = new ServerSocket(this.port);
		while (this.running)
		{
			final NetGameManager gameManager = new NetGameManager();
			final Socket socket1 = this.serverSocket.accept();
			final Client cm1 = new Client(socket1, gameManager);
			gameManager.add(cm1);
			final Socket socket2 = this.serverSocket.accept();
			final Client cm2 = new Client(socket2, gameManager);
			gameManager.add(cm2);
		}
	}
}
