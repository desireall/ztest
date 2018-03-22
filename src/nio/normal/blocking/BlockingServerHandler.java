package nio.normal.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import util.Calculator;

public class BlockingServerHandler implements Runnable {

	private Socket socket;

	public BlockingServerHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		if (socket == null)
			return;
		BufferedReader read = null;
		PrintWriter write = null;
		try {
			System.err.println("当前线程  "+ Thread.currentThread().getName());
			read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			write = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()) , true);

			String expression;
			String result;

			while (true) {
				if ((expression = read.readLine()) == null) break;
				System.err.println("收到 服务器消息: " + expression);
				try {
					result = Calculator.Instance.cal(expression).toString();
				} catch (Exception e) {
					result = "计算结果错误   " + e.getMessage();
				}

				write.println(result);
			}
			System.err.println("没有收到消息");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

}
