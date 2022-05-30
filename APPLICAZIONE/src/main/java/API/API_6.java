package API;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import db.SaveMySQL;

public class API_6 {

	public String id_macchinario;
	public String id_polizza;
	public Date data;
	
	
	public String getId_macchinario() {
		return id_macchinario;
	}
	public void setId_macchinario(String id_macchinario) {
		this.id_macchinario = id_macchinario;
	}
	public String getId_polizza() {
		return id_polizza;
	}
	public void setId_polizza(String id_polizza) {
		this.id_polizza = id_polizza;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data_inizio) {
		this.data = data_inizio;
	}
	public void setData() {
		Date date = Date.valueOf(LocalDate.now());
		this.data = date;
	}
	
	public  API_4 getPolizza(int idPolizza, int idMacchinario, int idUser) throws SQLException {

		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		API_4 api = new API_4();
		try {
			System.out.println("RICERCA Polizza");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			String sql = "SELECT * FROM api.polizza WHERE idpolizza = '"+idPolizza+"' "
					+ "AND fk_macchinario = '"+idMacchinario+"' AND fk_user = '"+idUser+"';";
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(rs.getDate("inizio"));
					System.out.println(rs.getDate("fine"));
					System.out.println(rs.getDouble("premio"));
					System.out.println(rs.getDouble("massimale"));
					System.out.println(rs.getString("pagamento"));
					api.setData_inizio(rs.getDate("inizio"));
					api.setData_fine(rs.getDate("fine"));
					api.setMassimale(rs.getDouble("massimale"));
					api.setPremio(rs.getDouble("premio"));
					api.setPag(rs.getString("pagamento"));
					return api;
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}
			conn.commit();
			return api;
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}finally {
			if(stmt != null) stmt.close();
			if(conn != null)conn.close();
		}
	}
}
