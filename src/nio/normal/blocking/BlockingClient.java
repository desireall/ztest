package nio.normal.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class BlockingClient {

	public static final int PORT = 1111;

	public static final String DEFAULT_HOSTS = "127.0.0.1";
	
	public static final char ops[] = {'+','-','*','/'};

	private static Socket socket;
      
	public static void send(String expression){
		BufferedReader read = null;
		PrintWriter write = null;
		try {
			socket = new Socket(DEFAULT_HOSTS, PORT);
			System.err.println("客户端启动!   " + socket.getRemoteSocketAddress());
			read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			write = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()) , true);
			
			
			String temp = getExpression();
			write.println(temp);
			System.err.println(temp);
			System.err.println("计算结果 "+ read.readLine());
			
			
//			while(true){
//				temp = getExpression();
//				write.println(temp);
//				System.err.println(temp);
//				System.err.println("计算结果 "+ read.readLine());
//				try {
//					Thread.currentThread().sleep(3000l);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
			
			
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (write != null) {
				write.close();
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
	
	
	public static void main(String[] args) {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
				while(true){
					String expression =getExpression();
					BlockingClient.send(expression);
					try {
						Thread.currentThread().sleep(1000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//			}
//		}).start();
	}
	
	
	
	public static String getExpression(){
		final Random random = new Random(System.currentTimeMillis());
		String expression = random.nextInt(10)+ " "+ ops[random.nextInt(4)] + " "+ (random.nextInt(10) + 1);
		return expression;
	}
}
