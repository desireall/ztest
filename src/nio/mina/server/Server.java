package nio.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nio.mina.codec.SimpleCodecFactory;
import nio.mina.codec.TestProtocolCodecFilter;

public class Server {
	private static Logger logger = LoggerFactory.getLogger(Server.class);
	private static int port = 8999;

	public static void main(String[] args) {
		try {
			IoAcceptor accepter = new NioSocketAcceptor();
			accepter.setHandler(new ServerHandler());
//			System.err.println(accepter.getFilterChain().getClass());
			accepter.getFilterChain().addLast("CODEC", new TestProtocolCodecFilter(new SimpleCodecFactory()));
//			accepter.getFilterChain().addLast("LOG", new LoggingFilter());
			accepter.getFilterChain().addLast("executor", new ExecutorFilter());
//			accepter.addListener(listener);
			accepter.bind(new InetSocketAddress(port));
			logger.info("server start!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
