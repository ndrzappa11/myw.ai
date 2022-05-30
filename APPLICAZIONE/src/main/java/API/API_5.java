package API;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.SaveMySQL;
import API.pagamenti;

public class API_5 {

	public double valore;
	public String per;	//periodo ammortamento
	public int num_fraz;
	public String pagamento;	//metodo pagamento
	public int id_polizza;
	
	public int getId_polizza() {
		return id_polizza;
	}
	public void setId_polizza(int id_polizza) {
		this.id_polizza = id_polizza;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
	public int getNum_fraz() {
		return num_fraz;
	}
	public void setNum_fraz(int num_fraz) {
		this.num_fraz = num_fraz;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
	public int numPagamenti(int id_polizza) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;	
		int i =0;
		try {
			System.out.println("RICERCA pagamenti");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno


			String sql2 = "SELECT * FROM api.pagamenti WHERE fk_polizza = '"+id_polizza+"';";
			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql2); // faccio la query su uno statement
				while (rs.next() == true ) {
					
					System.out.println(rs);
					i = rs.getRow();
					
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
			return i;
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
	
	
	
	public pagamenti[] getPagamenti(int id_polizza) throws SQLException {

		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		int k = numPagamenti(id_polizza);
		pagamenti pag[] = new pagamenti[k];
		int i =0;
		try {
			System.out.println("RICERCA pagamenti");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno


			String sql2 = "SELECT * FROM api.pagamenti WHERE fk_polizza = '"+id_polizza+"';";
			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql2); // faccio la query su uno statement
				while (rs.next() == true && i<k) {
					pag[i] = new pagamenti();
					System.out.println(rs.getDate("data"));
					System.out.println(rs.getInt("idpagamenti"));
					System.out.println(rs.getString("esito"));
					pag[i].setEsito(rs.getString("esito"));
					pag[i].setData(rs.getDate("data"));
					pag[i].setId(rs.getInt("idpagamenti"));
					i++;
					
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}
			//ArrayList<EmployeeBean> employees = company.getCompanyEmployees();

			conn.commit();
			return pag;
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
