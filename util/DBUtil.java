package edu.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	static Properties p;
	static String url, driver;
	static {
		try {
			p = new Properties();
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("dbinfo.properties"));
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("DBUtil: static block IOE");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("DBUtil: static block CNFE");
		}
	}

	private DBUtil() {

	}

	public static Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, p);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DBUtil: connect SQLE ");
		}
		
		return con;
	}

	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DBUtil: close E ");
		}
	}
}
