package com.jeeplus.common.socket;

import javax.servlet.http.HttpSession;

import com.jeeplus.common.comfig.Config;
import com.jeeplus.common.domain.PriUserinfo;
import com.jeeplus.common.model.SessionModel;
import com.jeeplus.common.utils.AppUtil;



/**
 * 全业务Socket后台连接工具类 使用短连接方式， 每次都发送登录指令 每次与后台连接后都断开
 * 
 * @author liws
 */
public class SocketTool {
	
	public static boolean login(HttpSession session, String userId, String password) throws Exception {
		String clientIp = (String) session.getAttribute(SessionModel.USERIP);
		
		return login(userId, password, clientIp);
	}
	
	public static boolean login(HttpSession session, String userId, String password, String encoding) throws Exception {
		String clientIp = (String) session.getAttribute(SessionModel.USERIP);
		
		if (AppUtil.isEmpty(encoding) == true) {
			encoding = "UTF-8";
		}
		return login(userId, password, clientIp, encoding);
	}
	
	public static boolean login(String userId, String passwd, String userIP) throws Exception {
		boolean flag = false;
		String ip = Config.getProperty("SocketService.IP");
		int port = Config.getInt("SocketService.PORT");
		int timeout = Config.getInt("SocketService.ConnectionTimedOut");
		CS10000SocketClient client = new CS10000SocketClient(ip, port, "GBK");
		
		client.setTimeout(timeout);
		client.connect();
		flag = client.login(userId, passwd, userIP);
		client.disconnect();
		return flag;
	}
	
	public static boolean login(String userId, String passwd, String userIP, String encoding) throws Exception {
		if (AppUtil.isEmpty(encoding) == true) {
			encoding = "UTF-8";
		}
		
		boolean flag = false;
		String ip = Config.getProperty("SocketService.IP");
		int port = Config.getInt("SocketService.PORT");
		int timeout = Config.getInt("SocketService.ConnectionTimedOut");
		CS10000SocketClient client = new CS10000SocketClient(ip, port, encoding);
		
		client.setTimeout(timeout);
		client.connect();
		flag = client.login(userId, passwd, userIP);
		client.disconnect();
		return flag;
	}
	
	public static String sendCmd(HttpSession session, String cmd) throws Exception {
		PriUserinfo userInfo = (PriUserinfo) session.getAttribute(SessionModel.USERINFO);
		String userId = "gzhuangxw";//userInfo.getUserId();
		String passwd = "0BF4D89CD9BB722D7CCFCD156AD9D1A5D441530FFA4563157E7600C07F365423";//userInfo.getPasswd();
		String userIP = (String) session.getAttribute(SessionModel.USERIP);
		
		return sendCmd(userId, passwd, userIP, cmd);
	}
	
	public static String sendCmd(HttpSession session, String cmd, String encoding) throws Exception {
		PriUserinfo userInfo = (PriUserinfo) session.getAttribute(SessionModel.USERINFO);
		String userId = userInfo.getUserId();
		String passwd = userInfo.getPasswd();
		String userIP = (String) session.getAttribute(SessionModel.USERIP);
		
		if (AppUtil.isEmpty(encoding) == true) {
			encoding = "UTF-8";
		}
		return sendCmd(userId, passwd, userIP, cmd, encoding);
	}
	
	public static String sendCmd(String userId, String passwd, String userIP, String cmd) throws Exception {
		String result = "";
		String ip = Config.getProperty("SocketService.IP");
		int port = Config.getInt("SocketService.PORT");
		int timeout = Config.getInt("SocketService.ConnectionTimedOut");
		CS10000SocketClient client = null;
		
		try {
			client = new CS10000SocketClient(ip, port, "GBK");
			
			client.setTimeout(timeout);
			client.connect();
			client.login(userId, passwd, userIP);
			
			result = client.sendCmd(cmd);
		} catch (Exception e1) {
			throw new Exception();
		} finally {
			if (client != null) {
				try {
					client.disconnect();
				} catch (Exception e2) {}
			}
		}
		return result;
	}
	
	public static String sendCmd(String userId, String passwd, String userIP, String cmd, String encoding) throws Exception {
		String result = "";
		String ip = Config.getProperty("SocketService.IP");
		int port = Config.getInt("SocketService.PORT");
		int timeout = Config.getInt("SocketService.ConnectionTimedOut");
		CS10000SocketClient client = null;
		
		try {
			client = new CS10000SocketClient(ip, port, encoding);
			client.setTimeout(timeout);
			client.connect();
			client.login(userId, passwd, userIP);
			
			result = client.sendCmd(cmd);
		} catch (Exception e1) {
			throw new Exception();
		} finally {
			if (client != null) {
				try {
					client.disconnect();
				} catch (Exception e2) {}
			}
		}
		return result;
	}
	
	/**
	 * <p>
	 * <strong>sendCmd</strong> - 获取telnet的查询结果
	 * </p>
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @author ljg
	 * 
	 * @param socketAddress : 远程地址 , socketPort : 远程端口 , timeout : 超时时间 , userIP : 连接者IP ， userId : 连接者帐号 , passwd : 连接者密码 , cmd
	 *            : 连接命令 , encoding : 交互编码
	 * @return 查询结果
	 * @throws
	 */
	public static String sendCmd(String socketAddress, int socketPort, int timeout, String userIP, String userId, String passwd, String cmd, String encoding)
			throws Exception {
		String result = null;
		CS10000SocketClient client = null;
		
		if (AppUtil.isEmpty(encoding) == true) {
			encoding = "UTF-8";
		}
		try {
			client = new CS10000SocketClient(socketAddress, socketPort, encoding);
			
			client.setTimeout(timeout);
			client.connect();
			client.login(userId, passwd, userIP);
			result = client.sendCmd(cmd);
		} catch (Exception e1) {
			throw new Exception();
		} finally {
			if (client != null) {
				try {
					client.disconnect();
				} catch (Exception e2) {}
			}
		}
		return result;
	}
	
	public static void sendCmdUpdateKey(String cmd) {
		String[] resetServers = Config.getProperty("IsmpFtpTimer.resetServers").split(",", -1);
		
		for (int k = 0; k < resetServers.length; k++) {
			try {
				CS10000SocketClient s = new CS10000SocketClient(resetServers[k], Config.getInt("SocketService.PORT"), "GBK");
				
				s.connect();
				s.sendCmd(cmd);
				s.disconnect();
			} catch (Exception e) {}
		}
	}
	
	public static void reset() {
		new Thread(new Runnable() {
			public void run() {
				String[] resetServers = Config.getProperty("IsmpFtpTimer.resetServers").split(",", -1);
				
				for (String ip : resetServers) {
					try {
						CS10000SocketClient s = new CS10000SocketClient(ip, Config.getInt("SocketService.PORT"), "GBK");
						String result = "";
						
						s.connect();
						result = s.sendCmd("RESET");
						System.out.println(result);
						s.sendCmd("skywinquit");
						s.disconnect();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
