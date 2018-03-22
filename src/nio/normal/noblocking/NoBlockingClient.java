package nio.normal.noblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;

public class NoBlockingClient implements Runnable {

	public static final int PORT = 1111;

	public static final String DEFAULT_HOSTS = "127.0.0.1";

	public static final char ops[] = { '+', '-', '*', '/' };

	private Selector selector;

	private SocketChannel channel;

	@Override
	public void run() {
		try {
			channel = SocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			selector = Selector.open();
			// 非阻塞模式下的连接
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
			channel.connect(new InetSocketAddress(DEFAULT_HOSTS, PORT));
			channel.finishConnect();  // 确认连接完成
			
//			while(!channel.finishConnect()){
//				Thread.sleep(100);
//			}
			
			//阻塞模式下的连接
//			channel.connect(new InetSocketAddress(DEFAULT_HOSTS, PORT));
//			channel.configureBlocking(false);
//			channel.register(selector, SelectionKey.OP_READ);
			System.err.println("连接成功"+channel.getRemoteAddress());
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		while (true) {
			int num = 0;
			try {
				num = selector.select(1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(num == 0) continue;
			SelectionKey key = null;
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				key = iterator.next();
				iterator.remove();
				handle(key);
			}
		}
	}

	public void handle(SelectionKey key) {
		try {
			if(key.isConnectable()){
				SocketChannel client = (SocketChannel) key.channel();  
                  // 判断此通道上是否正在进行连接操作。  
                  // 完成套接字通道的连接过程。  
				client.finishConnect();  
				System.out.println("完成连接!");  
//				sendMessage(client, getExpression());
			}else if (key.isReadable()) {
				ByteBuffer buff = ByteBuffer.allocate(512);
				SocketChannel channel = (SocketChannel) key.channel();
				channel.read(buff);
				buff.flip();
				String result = NoBlockingServer.getString(buff);
				System.err.println("结果 " + result);
			}
		} catch (Exception e) {

		}

	}

	public static String getExpression() {
		final Random random = new Random(System.currentTimeMillis());
		String expression = random.nextInt(10) + " " + ops[random.nextInt(4)] + " " + (random.nextInt(10) + 1);
		return expression;
	}

	public void sendMessage(SocketChannel channel, String str) {
		try {
			System.err.println("client 发送 : "+ str);
//			channel.register(selector, SelectionKey.OP_READ);
			byte[] strByte = str.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(strByte.length);
			buffer.put(strByte);
			buffer.flip();
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnection(){
		try {
			channel.close();
			selector.selectNow();
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		final NoBlockingClient client = new NoBlockingClient();
		new Thread(client).start();
		
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		final ByteBuffer buff = ByteBuffer.allocate(512);
		System.err.println("启动 程序！");
		while (true) {
				client.sendMessage(client.channel, getExpression());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.err.println(e);
				}catch (Exception e) {
					System.err.println(e);
				}
				
			}
		}
	}
