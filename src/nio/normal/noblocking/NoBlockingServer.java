package nio.normal.noblocking;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import util.Calculator;

public class NoBlockingServer implements Runnable {
	
	public static final int PORT = 1111;
	
	private ServerSocketChannel server;

	private Selector selector;
	
	private boolean status;
	
	public NoBlockingServer(){
	    try {
			server = ServerSocketChannel.open();
			selector = Selector.open();
			server.configureBlocking(false);
			server.register(selector, SelectionKey.OP_ACCEPT);

			server.socket().bind(new InetSocketAddress(PORT));
			
			while(true){
				int num = selector.select();
				if(num == 0){
					System.err.println("selector 异常 空转 ");
				}
				
				SelectionKey key = null;
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while(iterator.hasNext()){
					key = iterator.next();
					iterator.remove();
					handle(key);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
	public  void handle(SelectionKey key) {
		try {
			if(!key.isValid()) return;
			if(key.isAcceptable()){
				ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
				SocketChannel channel = serverChannel.accept();
				channel.configureBlocking(false);
				channel.register(selector, SelectionKey.OP_READ);
				System.err.println("连接成功  ... "+ channel.getRemoteAddress());
			}else if(key.isReadable()){
				ByteBuffer buff = ByteBuffer.allocate(512);
				SocketChannel channel = (SocketChannel)key.channel();
				channel.read(buff);
				buff.flip();
				String expression = getString(buff);
				System.err.println(expression);
				String result;
				try {
					result = Calculator.Instance.cal(expression).toString();
				} catch (Exception e) {
					result = "计算结果错误   " + e.getMessage();
				}
				
				sendMessage(channel , result);
			}
		} catch (Exception e) {
			
		}
		
	}


	@Override
	public void run() {
		
		while(status){
			int num = 0;
			try {
				num = selector.select(100);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(num == 0) continue;
			SelectionKey key = null;
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				key = iterator.next();
				iterator.remove();
				handle(key);
			}
		}
	}
	
	
	public  static String getString(ByteBuffer buffer){
	    byte[] bytes = new byte[buffer.remaining()];  
        buffer.get(bytes);  
        String result = "";
        try {
			result = new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}  
		return result;
	}

	
	public void sendMessage(SocketChannel channel, String str) {
		try {
			System.err.println("server  发送:"+str);
			byte[] strByte = str.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(strByte.length);
			buffer.put(strByte);
			buffer.flip();
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new Thread(new NoBlockingServer()).start();
	}
	
}



