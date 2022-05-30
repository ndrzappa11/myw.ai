package API;



import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.Random;

import API.API_1;
import db.SaveMySQL;
public class API_4 {

	public int id_macchinario;
	public Date data_inizio;
	public Date data_fine;
	public Double massimale;
	public String selezione;
	public double premio;
	public int id_polizza;
	public String durata;
	public String pag; // come gestire la diversificazione tra annuale e pay to use
						// verrà gestita nelle successive api
	
	
	public API_1 ogg = new API_1();		//oggetto che serve per fornire i dati dalla api_1 a questa, utilizzando la servlet e non il db
	
	public API_1 getOgg() { 
		return ogg;
	}
	
	public void setOgg(API_1 ogg) {		//vengono instanziati i dati riguardanti il macchinario fornito precedentemente
	 
		this.ogg = ogg;
		setData_inizio();
		this.data_inizio = getData_inizio();
		this.id_macchinario = ogg.getIDMacchinario();
		this.selezione = ogg.getAss_gar();
		this.durata = ogg.getDurata();
		setData_fine();
		this.data_fine = getData_fine();
		this.selezione = ogg.getAss_gar();
		
	}
	
	// METODI AUTOGENERATI
	public int getId_polizza() {
		return id_polizza;
	}
	
	public void setId_polizza(int id_polizza) {
		this.id_polizza=id_polizza;
	}
	
	public int getId_macchinario() {
		return id_macchinario;
	}
	public void setId_macchinario(int id_macchinario) {
		this.id_macchinario = ogg.getIDMacchinario();
	}
	public void setId_mach(int macchinario) {
		this.id_macchinario=macchinario;
	}
	public Date getData_inizio() { //non funziona
		return data_inizio;
	}

	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}
	public void setData_inizio() {
		Date date = Date.valueOf(LocalDate.now());
		this.data_inizio = date;
	}
	public Date getData_fine() {
		return data_fine;
	}
	public void setData_fine() {
		LocalDate today = LocalDate.now();     //Today
		LocalDate tomorrow = today.plusMonths(getNum());
		Date date = Date.valueOf(tomorrow);
		this.data_fine = date;
	}
	
	private int getNum() { 		//modifica dalla stringa dei mesi a un intero adeguato
		 if(durata.equals("tre_mesi")) return 3;
		if(durata.equals("sei_mesi")) return 6;
		if(durata.equals("dodici_mesi")) return 12;
		if(durata.equals("ventiquattro_mesi")) return 24;
		return 0;
	}
	
	
	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}
	public Double getMassimale() {
		return massimale;
	}
	public void setMassimale(Double massimale) {
		this.massimale = massimale;
	}

	public String getSelezione() {
		return selezione;
	}
	public void setSelezione(String selezione) {
		this.selezione = selezione;
	}
	public double getPremio() {
		return premio;
	}
	public void setPremio(double premio) {
		this.premio = premio;
	}
	
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	public String getPag() {
		if(pag.equals("pay_to_use")) return "pay to use";
		return pag;
	}
	public void setPag(String pag) {
		this.pag = pag;
	}
	
	
	// METODI CON LA CONNESSIONE AL DB
	/**
	// inserimento di una nuova polizza all'interno del DB
	public void generaPolizza(API_1 api1, String pag, int offerta, API_4 api4, int idU) throws Exception {
		//SaveMySQL savePolizza = new SaveMySQL();
		Statement stmt = null;
		Connection conn = null;
		System.out.println("INSERIMENTO NUOVA POLIZZA ATTIVA");
		conn= SaveMySQL.getDBConnection();
		conn.setAutoCommit(false); // non so cosa sia
		stmt = conn.createStatement(); //nemmeno
		String sql = "INSERT INTO api.polizza(inizio, fine, massimale, premio, pagamento, fk_macchinario, fk_user)";   
		sql += " VALUES('"
				+ api4.getData_inizio()+ "','"
				+ api4.getData_fine()+ "',"
				+ api4.getMassimale(offerta)+ ","
				+ api4.getPremio(offerta)+ ",'"
				+ pag+ "','"
                + api4.getId_macchinario()+ "','"
				+ idU+ "');";
		System.out.println("INSERT QUERY: "+sql);

		stmt.execute(sql);

		conn.commit();
	}
	**/

	public double getPremio(int offerta) throws SQLException {
		String sql = "SELECT * FROM api.proposte WHERE idproposta = '"+offerta+"';";
		String varRicercata = "premio";
		double var = -2;
		try {
			 var = SaveMySQL.getDouble(sql, varRicercata);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.premio=var;		//MODIFICARE IL SETTAGGIO DELLA VARIABILE
		return var;
	}
	
	public double getMassimale(int offerta) throws SQLException {
		String sql = "SELECT * FROM api.proposte WHERE idproposta = '"+offerta+"';";
		String varRicercata = "massimale";
		double var = -2;
		try {
			 var = SaveMySQL.getDouble(sql, varRicercata);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.massimale=var;		//MODIFICARE IL SETTAGGIO DELLA VARIABILE
		return var;	
	}
	
	public int getId_Polizza(int user, int macchinario) throws SQLException {
		System.out.println("RICERCA ID POLIZZA CON fk_macchinario=" + macchinario+" and fk_user "+user);
		String sql = "SELECT * FROM api.polizza WHERE fk_macchinario = "+macchinario+" and fk_user = "+user+";";
		String varRicercata = "idpolizza";
		int var = -1;
		try {
			 var = SaveMySQL.getInt(sql, varRicercata);	//metodo all'interno della classe SaveMySQL per non dover riscrivere sempre il codice
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.id_polizza=var;		//MODIFICARE IL SETTAGGIO DELLA VARIABILE
		return var;	
	}

	//metodo utilizzato per fornire tutte le polizze relative ad un user
	public API_4[] getPolizze(int idUser) throws SQLException{
			Statement st;
			ResultSet rs;
			Statement stmt = null;
			Connection conn = null;
			int k = numPolizze(idUser);
			API_4 api[] = new API_4[k];
			int i =0;
			try {
				System.out.println("RICERCA POLIZZE");
				conn = SaveMySQL.getDBConnection();
				conn.setAutoCommit(false); // non so cosa sia
				stmt = conn.createStatement(); //nemmeno


				String sql2 = "SELECT * FROM api.polizza WHERE fk_user = '"+idUser+"';";
				// ________________________________query
				try {
					st = conn.createStatement(); 
					rs = st.executeQuery(sql2); // faccio la query su uno statement
					while (rs.next() == true && i<k) {
						api[i] = new API_4();
						System.out.println(rs.getInt("idpolizza"));
						System.out.println(rs.getDate("inizio"));
						System.out.println(rs.getDate("fine"));
						System.out.println(rs.getDouble("massimale"));
						System.out.println(rs.getDouble("premio"));
						System.out.println(rs.getString("pagamento"));
						System.out.println(rs.getInt("fk_macchinario"));
						
						api[i].setId_polizza(rs.getInt("idpolizza"));
						api[i].setData_inizio(rs.getDate("inizio"));
					    api[i].setData_fine(rs.getDate("fine"));
					    api[i].setMassimale(rs.getDouble("massimale"));
						api[i].setPremio(rs.getDouble("premio"));
						api[i].setPag(rs.getString("pagamento"));
						api[i].setId_mach(rs.getInt("fk_macchinario"));
						i++;
						
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
	
	//metodo utilizzato per rilevare il numero di polizze inerenti ad un user
	// viene utilizzato per instanziare l'array delle proposte del metodo sopra
	public int numPolizze(int id_user) throws SQLException {
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


			String sql2 = "SELECT * FROM api.polizza WHERE fk_user = '"+id_user+"';";
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
	
	
	
	
	// metodi fittizzi utilizzati per la generazione di offerte finte
	public int getRandomNumber() {
	    return (int) ((Math.random() * (1460 - 1)) + 1);
	}
	public double getRandomNumberD() {
	    double s = (((Math.random() * (100000 - 1)) + 1));
	    s= Math.round(s*100.0)/100.0;
	    return s;
	}
	public void setMassimale() {
		this.massimale = getRandomNumberD();
	}
	public void setPremio() {
		this.premio = getRandomNumberD();
	}
}
