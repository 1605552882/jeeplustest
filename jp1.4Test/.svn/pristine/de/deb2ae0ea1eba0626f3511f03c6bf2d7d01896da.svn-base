package com.jeeplus.common.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 简单socket客户端
 * 
 * @author liws
 * 
 */
public class SimpleSocketClient {
	
	private Socket socket;
	
	private String host;
	
	private int port;
	
	private int timeout = 180000; // 三分钟读超时
	
	private String encoding;
	
	private BufferedReader reader;
	
	private PrintWriter writer;
	
	/**
	 * 创建一个 socek 客户端
	 * 
	 * @param host 主机名
	 * @param port 端口
	 * @param encoding 编码
	 */
	public SimpleSocketClient(String host, int port, String encoding) {
		this.host = host;
		this.port = port;
		this.encoding = encoding;
	}
	
	/** 连接 */
	public void connect() throws Exception {
		this.socket = new Socket(this.host, this.port);
		this.socket.setSoTimeout(this.timeout);
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), this.encoding));
		this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), this.encoding));
	}
	
	/** 向客户端发送消息 */
	public void send(String msg) throws Exception {
		if (isConnected()) {
			this.writer.print(msg);
			this.writer.flush();
		} else {
			throw new Exception("no connection");
		}
	}
	
	/** 读取一行 */
	public String readLine() throws Exception {
		this.socket.setSoTimeout(this.timeout); // 设置读超时
		try {
			String line = this.reader.readLine();
			return line;
		} catch (Exception e) {
			this.disconnect();
			return "NRM"; // 没有返回
		}
	}
	
	/** 读取对方输出 */
	public String read() throws Exception {
		String result = "";
		this.socket.setSoTimeout(this.timeout); // 设置读超时
		try {
			// this.reader.read(this.buffer);
			
			result += this.reader.readLine();
			// result += this.buffer.flip().toString();
			
			// 如果还没有读完， 则继续读
			while (this.reader.ready()) {
				
				result += this.reader.readLine();
				// buffer.rewind();
				// this.reader.read(this.buffer);
				// result += this.buffer.flip().toString();
			}
		} catch (Exception e) {
			this.disconnect();
		}
		return result;
	}
	
	/** 断开连接 */
	public void disconnect() {
		try {
			this.socket.close();
		} catch (Exception e) {
			// do nothing
		}
	}
	
	/** 设置读超时时长 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	/** 获得超时时长 */
	public int getTimeout() {
		return timeout;
	}
	
	/** 是否连接 */
	public boolean isConnected() {
		if (this.socket == null || this.socket.isClosed() || !this.socket.isConnected()) {
			return false;
		} else {
			return true;
		}
	}
	
}