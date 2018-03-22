package nio.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioUtil {
	
	public static void sendMessage(SocketChannel channel, String str) {
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
}
