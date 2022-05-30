package db;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import API.API_4;

public class ConnessioneMySQL {
	public static void main(String[] args) throws SQLException {
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("FUNZIONA");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		// Creo la connessione al database
		System.out.println("funziona 1: ");
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/api?user=root&useSSL=false"); //?user=root&password=raviolo&useSSL=true
		// peer è il nome del database
		System.out.println("funziona 2 ");
		sql = "SELECT * FROM macchinario;";
		// ________________________________query
		try {
			System.out.println("funziona 3 ");
			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			rs = st.executeQuery(sql); // faccio la query su uno statement
			while (rs.next() == true) {
				System.out.println(rs.getString("idmacchinario") + "\t" + rs.getString("matricola"));
			}
			String insertMacchinario = "INSERT INTO MACCHINARIO(idmacchinario, matricola)";
			insertMacchinario += "VALUES("
					+ 50+ ","
					+ 20+ ")";
			st.execute(insertMacchinario);
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch
		cn.close(); // chiusura connessione
	}// fine main
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver" ;
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/api";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Raviolo.1234";//forse raviolo
	
	public  Connection getDBConnection() throws Exception {
		System.out.println("-------MySQL JDBC Connection---------");
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("TEST CONNESSIONE ");
		}catch(ClassNotFoundException e) {
			System.out.println("ERRORE STRANO");
			System.out.println("ERROR: MySQL JDBC Driver not found!!");
			throw new Exception(e.getMessage());
		}
		
		try {
			System.out.println("ARRIVA FINO A QUA");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/api?user=root&useSSL=false");
			//dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company-management?user=root&password=Raviolo.1234&useSSL=false");
			//dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company-management", DB_USER, DB_PASSWORD);
			System.out.println("SQL Connection to Company_Management database established");
		}catch(SQLException e) {
			System.out.println("Connection to Company_Management database faildes!");
			throw new SQLException(e.getErrorCode()+":"+e.getMessage());
		}
		return dbConnection;
	}
	public void insertMacchinario(API_4 macchinario) throws SQLException{
		Statement stmt = null;
		Connection conn = null;
		System.out.println("TEST CONNESSIONE 1");
		try {
			
			conn = getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String insertMacchinario = "INSERT INTO MACCHINARIO(idMacchinario, data_inizio, data_fine, massimale, selezione, premio, id_polizza, durata, pag)";   
			insertMacchinario += "VALUES("
					+ macchinario.getId_macchinario()+ ","
					+ macchinario.getData_inizio()+ ","
					+ macchinario.getData_fine()+ ","
					+ macchinario.getMassimale()+ ","
					+ macchinario.getSelezione()+ ","
					+ macchinario.getPremio()+ ","
					+ macchinario.getId_polizza()+ ","
					+ macchinario.getDurata()+ ","
					+ macchinario.getPag()+ ","
					+"SYSDATE())";
			System.out.println("INSERT QUERY: "+insertMacchinario);
			stmt.execute(insertMacchinario);
			/*ArrayList<EmployeeBean> employees = company.getCompanyEmployees();
			for(API_4 employee:employees) {
				String insertEmployee = "INSERT INTO EMPLOYEE (idemployee,surname,name,badge,FK_company,date_ins)";
				insertEmployee += "VALUES(" 
						+ employee.getIdemployee()+","
						+ employee.getSurname()+","
						+ employee.getName()+","
						+ employee.getBadge()+","
						+employee.getFk_company()+","
						+ "SYSDATE()";
				System.out.println("INSERT EMPLOYEE"+insertEmployee);
				stmt.executeUpdate(insertEmployee);
			}*/
			conn.commit();
		
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		}finally {
			if(stmt != null) stmt.close();
			if(conn != null)conn.close();
		}
	}
	
}// fine classe
