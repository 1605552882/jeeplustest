package com.jeeplus.modules.sys.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeeplus.common.utils.DateUtils;

public class LOGunit {
	private final static Integer type=0;//0：web前台 1：csp接口 2：能力前置
	private static SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
	/**
	 * 
	 * @param d 时间
	 * @return
	 */
	public static String getTime(Date d)
	{
		return DateUtils.formatDateTime(new Date());
	}
	public static String getTime()
	{
		return DateUtils.formatDateTime(new Date());
	}
	/**
	 * 
	 * @param start
	 * @param end
	 * @return 精确到秒
	 */
	public static Integer compare_date(String start,String end)
	{
		Integer res=null;
		 try {
			Date sd=DateUtils.parseDate(start);
			Date ed=DateUtils.parseDate(end);
			res=(int) ((ed.getTime()-sd.getTime())/1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	/**   记录前台日志，并返回唯一id和后台有关联的id
	 * 
	 * @param user_id 用户id
	 * @param class_name  类名
	 * @param method_name 方法名
	 * @param request_msg 请求内容
	 * @param ip_addr ip地址
	 * @param reponse_msg 回复内容
	 * @param mdn 号码
	 * @param start_time 开始时间 string类型 格式（1993-01-01 00:00:00）
	 * @param conn_id 前后台关联id
	 * @return 返回id
	 */
	public static Integer web_log(String user_id,String class_name,String method_name,String request_msg,String ip_addr,String reponse_msg,String mdn,String start_time,Integer conn_id)
	{
		String end_time=getTime();
		Integer take_time=compare_date(start_time, end_time);
		MysqlDateBasicUnit.mysql("insert into web_log(user_id,class_name,method_name,conn_id,request_msg,ip_addr,type,reponse_msg,mdn,start_time,end_time,take_time)", 
				user_id,class_name,method_name,conn_id,request_msg,ip_addr,type,reponse_msg,mdn,start_time,end_time,take_time);
		return conn_id;
	}
	/**   记录前台日志，并返回唯一id和后台有关联的id
	 * 
	 * @param user_id 用户id
	 * @param class_name  类名
	 * @param method_name 方法名
	 * @param request_msg 请求内容
	 * @param ip_addr ip地址
	 * @param reponse_msg 回复内容
	 * @param mdn 号码
	 * @param start_time 开始时间 date类型 
	 * @param conn_id 前后台关联id
	 * @return 返回id
	 */
	public static Integer web_log(String user_id,String class_name,String method_name,String request_msg,String ip_addr,String reponse_msg,String mdn,Date start_time,Integer conn_id)
	{
		String start_time2=getTime(start_time);
	    return 	web_log(user_id, class_name, method_name, request_msg, ip_addr, reponse_msg, mdn, start_time2,conn_id);
		
	}
	/**  后台日志记录方法
	 *  
	 * @param user_id 用户id
	 * @param clt_ip  ip地址
	 * @param work_detail 发送指令详细
	 * @param work_dt  时间
	 * @param flag 是否成功
	 * @param interFace  平台类型
	 * @param mdn  号码
	 * @param work_type
	 * @param work_type_detail
	 * @param common  返回结果
	 * @param conn_id 关联id
	 */
	public static void work_log(String user_id,String clt_ip,String work_detail,String work_dt,String flag,String interFace,String mdn,String work_type,String work_type_detail,String common,Integer conn_id)
	{
		
		MysqlDateBasicUnit.mysql("insert into work_log(user_id,clt_ip,work_detail,work_dt,flag,interFace,mdn,work_type,work_type_detail,common,conn_id)", 
				user_id,clt_ip,work_detail,work_dt,flag,interFace,mdn,work_type,work_type_detail,common,conn_id);
	}
	/**
	 *  获取关联id
	 * @return
	 */
	public static Integer getConn_id(){
	
		Integer res=null;
		try {
			res=MysqlDateBasicUnit.insert_bakID("insert into conn_id_table value()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static void main(String[] args) {
		//前台记录方式： 先获取关联id然后发送给后台，到最后记录关联id  下面位样例
		//String conn_id=getConn_id();
		//socketTOOls.sendCMD("QUERYXXXXX",conn_id);
		//web_log(user_id, class_name, method_name, request_msg, ip_addr, reponse_msg, mdn, start_time, conn_id);
	}

}
