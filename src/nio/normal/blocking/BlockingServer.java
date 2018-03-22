package nio.normal.blocking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingServer {

	public static final int PORT = 1111;

	private static ServerSocket server;

	private static ExecutorService executor;
	
	public static void start() throws IOException{
		startServer();
	}
	

	public static synchronized void startServer() throws IOException{
		if(server != null) return;
		executor = Executors.newFixedThreadPool(5);
		try{
			server = new ServerSocket(PORT);
			System.err.println("服务器启动!");
			while(true){
				Socket socket = server.accept();
				System.err.println("连接成功  ... "+ socket.getPort());
//				new Thread(new BlockingServerHandle(socket)).start();
				executor.execute(new BlockingServerHandler(socket));
			}
		}finally {
			if(server != null){
				server.close();
			}
		}
	}

	
	public static void main(String[] args) {
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
				try {
					BlockingServer.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
//			}
//		}).start();
	}
}
