package nio.mina.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nio.mina.codec.SimpleCodecFactory;
import nio.mina.server.Server;

public class Client {
	private static Logger logger = LoggerFactory.getLogger(Server.class);
	private static int port = 8999;
	private static String host = "192.168.1.222";

	public static void main(String[] args) {
		try {
			IoConnector connector = new NioSocketConnector();
			connector.setHandler(new ClientHandler());
			connector.getFilterChain().addFirst("CODEC", new ProtocolCodecFilter(new SimpleCodecFactory()));
			connector.connect(new InetSocketAddress(host, port));
			logger.info("connector start!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
