package nio.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info((String)message);
	}

	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
//		for (int i = 0; i < 1; i++) {
//			session.write("client session open!");
//		}
		
	    session.write("1");
	}


	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
		
	}
	
	
	
}
