package nio.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProtocolCodecFilter extends ProtocolCodecFilter{
	private static final Logger logger = LoggerFactory.getLogger(TestProtocolCodecFilter.class);

	public TestProtocolCodecFilter(ProtocolCodecFactory factory) {
		super(factory);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		super.messageSent(nextFilter, session, writeRequest);
		logger.info("messageSent : "+ writeRequest.getMessage());
	}
	
}
