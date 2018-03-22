package nio.mina.server;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {

	public LinkedBlockingQueue<IoSession> task = new LinkedBlockingQueue<>();

	public Thread thread = null;

	private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	public ServerHandler() {
		super();
	    init();
	}
	
	public void init(){
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				IoSession session = null;
				while(true){
				try {
					session = task.take();
					if(session != null){
						session.write("do task" +System.currentTimeMillis());
						logger.info("do task!"+ System.currentTimeMillis());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		} , "task-thread");
		thread.start();
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info((String) message);
		session.write("server recived message !");
		task.add(session);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
	}

}
