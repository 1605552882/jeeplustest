package com.jeeplus.common.socket;

/**
 * CServ10000 socket客户端 正常操作顺序new-connect-login-sendCmd...-disConnect
 * 
 * @author liws
 * 
 */
public class CS10000SocketClient extends SimpleSocketClient {
	
	private String user = "";
	private String passwd = "";
	
	public CS10000SocketClient(String host, int port, String encoding) {
		super(host, port, encoding);
	}
	
	/** 登录 */
	public boolean login(String user, String passwd, String ip) throws Exception {
		this.setUser(user);
		this.setPasswd(passwd);
		
		String loginCommand = "LOGIN" + user + "|" + passwd + "|" + ip;
		// String loginCommand = "LOGIN" + user + "|" + passwd;
		
		this.send(loginCommand.trim() + "\r\n");
		String loginInfo = this.read();
		if ("F".equals(loginInfo) || "INF".equals(loginInfo)) { return false; }
		return true;
	}
	
	/** 发送指令 */
	public synchronized String sendCmd(String cmd) throws Exception {
		String result = "";
		// cmd = cmd.trim();
		this.send(cmd + "\r\n");
		result = this.read();
		result = result.trim();
		
		if (result.equals("")) {
			result = "NRM"; // 没有返回， 即返回为空
		}
		return result.trim();
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
