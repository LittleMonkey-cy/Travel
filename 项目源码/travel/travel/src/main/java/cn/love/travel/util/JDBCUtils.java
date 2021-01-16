package cn.love.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
* @author 陈钰
* @Description 数据库连接池的工具类 DButils
* @Date  2020/12/20
* @Param
* @return
**/
/*
	1. 声明静态数据源成员变量
	2. 创建连接池对象
	3. 定义公有的得到数据源的方法
	4. 定义得到连接对象的方法
	5. 定义关闭资源的方法
 */
public class JDBCUtils {

	// 1.定义成员变量 DataSource
	private static DataSource ds ;

	static{
		try {
			// 1.加载配置文件
			Properties pro = new Properties();
			pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
			// 2.获取DataSource
			ds = DruidDataSourceFactory.createDataSource(pro);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * 释放资源
	 */
	public static void close(Statement stmt,Connection conn){


		close(null,stmt,conn);
	}


	public static void close(ResultSet rs , Statement stmt, Connection conn){


		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(conn != null){
			try {
				// 关闭资源
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取连接池方法
	 */

	public static DataSource getDataSource(){
		return  ds;
	}

}
