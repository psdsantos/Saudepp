package model.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DB {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("url");
				connection = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Properties loadProperties() {
		Properties props = new Properties();
		try (FileInputStream fs = new FileInputStream("src/model/db/db.properties")) {
			props.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertData(String tabela, List<String> valores) throws SQLException {
		String sql;
		Statement statement = getConnection().createStatement();
		sql = String.format("insert into %s values (", tabela);

		for (String valor : valores) {
			sql += "'" + valor + "',";
		}
		sql = sql.substring(0, sql.length() - 1) + ")";

		statement.executeUpdate(sql);
		statement.close();
	}

	public static void updateData(String table, List<String> columns, List<String> newValues, String id)
			throws SQLException {
		String sql;
		Statement statement = getConnection().createStatement();

		sql = String.format("update %s set ", table);

		for (int i = 0; i < columns.size(); i++) {
			sql += String.format("%s = '%s',", columns.get(i), newValues.get(i));
		}

		sql = sql.substring(0, sql.length() - 1) + String.format(" where id = '%s'", id);
		System.out.println(sql);
		statement.executeUpdate(sql);
		statement.close();
	}

	public static void deleteData(String table, String pk_name, String id) throws SQLException {
		Statement statement = getConnection().createStatement();
		String sql = String.format("delete from %s where %s = '%s'", table, pk_name, id);
		statement.executeUpdate(sql);
		statement.close();
	}

	// SELECT COM UMA CONDIÇÃO
	public static ResultSet showEntity(String table, String keyColumn, String keyValue) throws SQLException {
		Statement statement = getConnection().createStatement();
		String sql = String.format("select * from %s where %s = '%s'", table, keyColumn, keyValue);
		ResultSet query = statement.executeQuery(sql);
		statement.close();
		return query;
	}

	// SELECT SEM CONDICAO
	public static ResultSet showEntity(String table) throws SQLException {
		Statement statement = getConnection().createStatement();
		String sql = String.format("select * from %s", table);
		ResultSet query = statement.executeQuery(sql);
		return query;
	}
	
	public static void changeDateStyle(String dateStyle) throws SQLException {
		Statement statement = getConnection().createStatement();
		String sql = String.format("SET DATESTYLE TO %s", dateStyle);
		statement.executeUpdate(sql);
		statement.close();
	}

}
