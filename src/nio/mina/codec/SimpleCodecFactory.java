package nio.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCodecFactory implements ProtocolCodecFactory {
	private static final Logger logger = LoggerFactory.getLogger(SimpleCodecFactory.class);

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new ProtocolEncoderAdapter() {

			@Override
			public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
				String str = (String) message;
				byte[] bytes = str.getBytes();
				IoBuffer ioBuffer = IoBuffer.allocate(bytes.length + 4);
				ioBuffer.putInt(bytes.length);
				ioBuffer.put(bytes);
				ioBuffer.flip();
				session.write(ioBuffer);
			}
		};
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new ProtocolDecoderAdapter() {

			@Override
			public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
				in.mark();
				int length = in.getInt();
				if (in.remaining() >= length) {
					byte[] inbytes = new byte[length];
					in.get(inbytes);
					out.write(new String(inbytes));
					return;
				}
				in.reset();
			}
		};
	}

}
