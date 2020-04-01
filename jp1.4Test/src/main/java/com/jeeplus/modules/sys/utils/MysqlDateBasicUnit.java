package com.jeeplus.modules.sys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MysqlDateBasicUnit {
	// private static String name="com.sybase.jdbc3.jdbc.SybDriver";
	// private static String
	// url="jdbc:sybase:Tds:132.96.191.35:5000/swsydb?charset=cp936";
	// private static String user="admin";
	// private static String password="lsdx915@";

	private static String name = "com.mysql.jdbc.Driver";
	// private static String
	// url="jdbc:mysql://localhost:3306/jeeplus_ani_big?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true";
//	private static String url = "jdbc:mysql://192.168.100.246:3306/jeeplus_ani_big?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true";
//
//	private static String user = "root";
//	private static String password = "skywin@123!";
	
	private static String url = "jdbc:mysql://localhost:3306/jeeplus_ani_big?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true";

	private static String user = "root";
	private static String password = "root";

	private static Connection createConnect() throws ClassNotFoundException, SQLException {
		Class.forName(name);
		Properties sysProps = System.getProperties();
		sysProps.put("user", user);
		sysProps.put("password", password);
		Connection connect = DriverManager.getConnection(url, sysProps);
		return connect;
	}

	private static Connection StartDateBasic() throws ClassNotFoundException, SQLException {

		return createConnect();

	}

	private static Statement getStatement(Connection connect) throws Exception {
		Statement s = connect.createStatement();
		return s;
	}

	/**
	 * 
	 * @param tableName 表名
	 * @param params    请求参数和值
	 * @return
	 * @throws Exception
	 */
	public static Integer insert_bakID(String sql, Object... params) throws Exception {

		Connection conn = StartDateBasic();
		sql = ftoparam(sql, params);
		Statement sta = getStatement(conn);
		Integer k = null;
		sta.executeUpdate(sql, sta.RETURN_GENERATED_KEYS);
		ResultSet rs = sta.getGeneratedKeys();
		if (rs.next()) {
			k = rs.getInt(1);
		}
		sta.close();
		conn.close();
		return k;

	}

	public static Integer insert_bakID(String sql) throws Exception {

		Connection conn = StartDateBasic();
		Statement sta = getStatement(conn);
		Integer k = null;
		sta.executeUpdate(sql, sta.RETURN_GENERATED_KEYS);
		ResultSet rs = sta.getGeneratedKeys();
		if (rs.next()) {
			k = rs.getInt(1);
		}
		sta.close();
		conn.close();
		return k;

	}

	public static List<String> RegFind(String msg, String... reg) {
		List<String> lists = new ArrayList<String>();
		for (int i = 0; i < reg.length; i++) {
			String res = "";
			Pattern pattern = Pattern.compile(reg[i]);
			Matcher match = pattern.matcher(msg);
			while (match.find()) {
				lists.add(match.group());

			}

		}
		if (lists.size() == 0) {
			return null;
		}
		return lists;

	}

	public static boolean isINT(String classname) {
		String[] classnames = { Integer.class.getName(), Byte.class.getName(), Long.class.getName(),
				Double.class.getName(), Float.class.getName() };
		for (String c : classnames) {
			if (c.equals(classname)) {
				return true;
			}
		}
		return false;
	}

	public static String ftoparam(String sql, List<Object> lists) {
		sql = sql.replaceAll("( ){1,}", " ");
		int index = sql.indexOf("?");
		int i = 0;
		int end = lists.size();
		String where = " where ";
		int whereindex = sql.toLowerCase().indexOf(where);
		List<String> sqllists = ContainsString(sql,
				"[^ ]+( ){0,1}(=|!=|like|not like|in|not in|>|>=|<|<=)( ){0,1}('|\"){0,1}(%){0,1}(\\?)(%){0,1}('|\"){0,1}( ){0,1}(and|or){0,1}");
		while (index != -1) {
			Object o = lists.get(i);
			String param = null;
			if (o != null) {

				param = o.toString();
			}
			if (param != null && ValidteSQLparams(param)) {
				return null;
			}
			if (o != null) {
				if (!isINT(o.getClass().getName())) {
					param = "'" + param + "'";
				}
			} else {
				param = "'" + "'";
			}
			if (o != null && param == null) {
				sql = sql.replace(sqllists.get(i), "");
			} else {

				if ("#null#".equals(param.toLowerCase())) {
					param = "null";
				}
				sql = sql.substring(0, index) + param + sql.substring(index + 1);

			}

			index = sql.indexOf("?");
			i++;

		}
		if (sql != null && (sql.trim().toLowerCase().endsWith("and") || sql.trim().toLowerCase().endsWith("or"))) {
			if (sql.trim().toLowerCase().endsWith("or")) {
				sql += "o";
			}
			sql = sql.trim().substring(0, sql.length() - 4);
		}
		if (sql != null && sql.trim().toLowerCase().endsWith("where")) {
			sql += " 1=1 ";
		}
		return sql;
	}

	public static String ftoparam(String sql, Object... lists) {
		Datalist param = getDataList();
		for (Object p : lists) {
			param.push(p);
		}
		return ftoparam(sql, param);
	}

	// 例如 sql:insert into xxx(q,w,e)
	public static String checkInsertaddW(String sql) {
		sql = sql.trim();
		List<String> list = RegFind(sql, "(insert)( ){1,}(into)");
		String usql = " values (";
		if (list != null && list.size() > 0) {
			int s = sql.indexOf("(");
			int e = sql.indexOf(")");
			if (s == -1 || e == -1) {
				return sql;
			}
			String fied = sql.substring(s + 1, e);
			String[] fieds = fied.split(",");
			int i = 0;
			String f = "";
			while (i < fieds.length) {
				usql += f + "?";
				f = ",";
				i++;
			}
			usql += ")";
			sql += usql;

		}
		return sql;
	}

	public static List<Map> mysql(String sql, Object... param) {
		List<Map> lists = new ArrayList<Map>();
		try {
			Connection conn = StartDateBasic();

			Statement sta = getStatement(conn);
			sql = checkInsertaddW(sql);
			sql = ftoparam(sql, param);
			boolean isc = sta.execute(sql);
			if (isc) {
				ResultSet res = sta.getResultSet();
				ResultSetMetaData data = res.getMetaData();
				int count = data.getColumnCount();
				while (res.next()) {

					Map m = new HashMap();
					for (int i = 1; i <= count; i++) {

						String name = data.getColumnName(i);
						Object val = res.getObject(i);

						m.put(name, val);
					}
					lists.add(m);

				}
				sta.close();
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
				sta.close();

			}
			conn.close();

		} catch (Exception e) {
			String mess = ((SQLException) e).getSQLState();
			System.out.println(e.getMessage());
			System.out.println(mess);
			if (!mess.trim().equals("JZ0R2")) {
				Map m = new HashMap();
				m.put("SQLerror", e.getMessage());
				lists.add(m);
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
			}
		}
		return lists;

	}

	public static List<Map> mysql(String sql, Integer start, Integer limit, Object... param) {
		List<Map> lists = new ArrayList<Map>();
		try {
			Connection conn = StartDateBasic();

			Statement sta = getStatement(conn);
			sql = ftoparam(sql, param);
			boolean isc = sta.execute(sql);
			if (isc) {
				ResultSet res = sta.getResultSet();
				ResultSetMetaData data = res.getMetaData();
				int count = data.getColumnCount();
				while (res.next()) {
					if (start >= limit) {
						break;
					}
					Map m = new HashMap();
					for (int i = 1; i <= count; i++) {

						String name = data.getColumnName(i);
						Object val = res.getObject(i);

						m.put(name, val);
					}
					lists.add(m);
					start++;

				}
				sta.close();
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
				sta.close();
			}
			conn.close();

		} catch (Exception e) {
			String mess = ((SQLException) e).getSQLState();
			System.out.println(mess);
			if (!mess.trim().equals("JZ0R2")) {
				Map m = new HashMap();
				m.put("SQLerror", e.getMessage());
				lists.add(m);
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
			}
		}
		return lists;

	}

	public static List<String> ContainsString(String m, String reg) {
		ArrayList<String> lists = new ArrayList<String>();
		Pattern matter = Pattern.compile(reg);
		Matcher res = matter.matcher(m);
		while (res.find()) {
			lists.add(res.group());

		}
		return lists;
	}

	public static boolean ValidteSQLparams(String sql) {
		String validate = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		String nsql = sql.replaceAll(validate, "");
		if (nsql.equals(sql)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean ValidteSQLparamsMap(Map m) {
		for (Object o : m.keySet()) {
			String r = (String) o;
			if (ValidteSQLparams(r)) {
				return true;
			}
		}
		return false;
	}

	public static List<Map> SQL(String sql, Integer start, Integer limit) {
		List<Map> lists = new ArrayList<Map>();
		try {
			Connection conn = StartDateBasic();

			Statement sta = getStatement(conn);

			boolean isc = sta.execute(sql);
			if (isc) {
				ResultSet res = sta.getResultSet();
				ResultSetMetaData data = res.getMetaData();
				int count = data.getColumnCount();
				while (res.next()) {
					if (start >= limit) {
						break;
					}
					Map m = new HashMap();
					for (int i = 1; i <= count; i++) {

						String name = data.getColumnName(i);
						Object val = res.getObject(i);

						m.put(name, val);
					}
					lists.add(m);
					start++;

				}
				sta.close();
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
				sta.close();
			}
			conn.close();

		} catch (Exception e) {
			String mess = ((SQLException) e).getSQLState();
			System.out.println(mess);
			if (!mess.trim().equals("JZ0R2")) {
				Map m = new HashMap();
				m.put("SQLerror", e.getMessage());
				lists.add(m);
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
			}
		}
		return lists;

	}

	public static List<Map> SQL(String sql) {
		List<Map> lists = new ArrayList<Map>();
		try {
			Connection conn = StartDateBasic();

			Statement sta = getStatement(conn);

			boolean isc = sta.execute(sql);
			if (isc) {
				ResultSet res = sta.getResultSet();
				ResultSetMetaData data = res.getMetaData();
				int count = data.getColumnCount();
				while (res.next()) {

					Map m = new HashMap();
					for (int i = 1; i <= count; i++) {

						String name = data.getColumnName(i);
						Object val = res.getObject(i);

						m.put(name, val);
					}
					lists.add(m);

				}
				sta.close();
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
				sta.close();
			}
			conn.close();

		} catch (Exception e) {
			String mess = ((SQLException) e).getSQLState();
			System.out.println(mess);
			if (!mess.trim().equals("JZ0R2")) {
				Map m = new HashMap();
				m.put("SQLerror", e.getMessage());
				lists.add(m);
			} else {
				Map m = new HashMap();
				m.put("SQRight", "执行成功");
				lists.add(m);
			}
		}
		return lists;

	}

	public static Datalist getDataList() {
		return new Datalist();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(checkInsertaddW("insert into ww  from work_log2017"));

	}

}
