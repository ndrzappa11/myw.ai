package API;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.SaveMySQL;

public class API_1 {

	// API selezione prodotto

	private int IDMacchinario;
	private String matricola;
	private String tip;	//tipologia macchinario
	private double valore;
	private String ass_gar;
	private String durata;

	// può essere modificato con un singolo array di polizze, ma dovrei creare un altra classe
	// classe esistente solo nel database ma non nelle api java
	// tabella assestante a tutti, non collegata con nessuno, utilizzata solo per vista delle polizze adeguate che si possono proporre
	private int id_polizza[] = {-1, -1, -1};
	private double massimale[] = {-1, -1, -1};
	private double premio[] = {-1, -1, -1};
	
	//per visionare le offerte bisogna prima instanziarle tramite la chimata al metodo genera_offerta, bisogna anche aver salvato un macchinario
	// nel db, così da ver instanziato un oggetto


	//	METODI AUTOGENERATI
	public int getIDMacchinario() {
		return IDMacchinario;
	}

	public void setIDMacchinario(int iDMacchinario) {
		this.IDMacchinario = iDMacchinario; 
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getDurata() {

		if(durata.equals("tre")) return "tre_mesi";
		if(durata.equals("sei")) return "sei_mesi";
		if(durata.equals("dodici")) return "dodici_mesi";
		if(durata.equals("ventqua")) return "ventiquattro_mesi";

		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	public String getTip() {
		if(tip.equals("ven"))
			return "verniciatura";
		if(tip.equals("eco"))
			return "ecografia";
		if(tip.equals("elettr"))
			return "elettrodomestici";
		if(tip.equals("altro"))
			return "altro ...";
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
	public String getAss_gar() {
		if(ass_gar.equals("ass"))
			return "assicurazione";
		if(ass_gar.equals("gar"))
			return "estensione di garanzia";
		if(ass_gar.equals("ass_gar"))
			return "assicurazione e estensione di garanzia";
		return ass_gar;
	}
	public void setAss_gar(String ass_gar) {
		this.ass_gar = ass_gar;
	}


	//		settaggio array dati delle polizze
	public int[] getId_polizza() {
		return id_polizza;
	}

	public void setId_polizza(int id_polizza, int i) {
		this.id_polizza[i] = id_polizza;
	}

	public double[] getMassimale() {
		return massimale;
	}

	public void setMassimale(double massimale, int i) {
		this.massimale[i] = massimale;
	}

	public double[] getPremio() {
		return premio;
	}

	public void setPremio(double premio, int i) {
		this.premio[i] = premio;
	}


	/*
	 * 
	 * METODI CON LA CONNESSIONE AL DB
	 * 
	 */

	//	INSERIMENTO MACCHINARIO NEL DATABASE
	public void setMacchinario(API_1 macchinario) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			System.out.println("INSERIMENTO MACCHINARIO NEL DB");
			conn = SaveMySQL.getDBConnection();
			//System.out.println("funziona la connessione");
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno


			String insertMacch = "INSERT INTO api.macchinario(matricola,tip,valore,ass_gar,durata)";   
			insertMacch += " VALUES('"
					+ macchinario.getMatricola()+ "','"
					+ macchinario.getTip()+ "',"
					+ macchinario.getValore()+ ",'"
					+ macchinario.getAss_gar()+ "','"
					+ macchinario.getDurata()+ "');";
			System.out.println("INSERT QUERY: "+insertMacch);

			stmt.execute(insertMacch);
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


	//		STAMPAGGIO ID MACCHINARIO, tramite l'oggetto api_1
	//		siccome viene generato dal database
	public int getId(API_1 macchinario) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		try {
			int id = -1;
			System.out.println("RICERCA ID MACCHINARIO");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			String sql2 = "SELECT * FROM macchinario WHERE matricola = '"+macchinario.getMatricola()+"';";
			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql2); // faccio la query su uno statement
				while (rs.next() == true) {
					id = rs.getInt("idmacchinario");
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}
			conn.commit();
			return id;
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
	
	//		STAMPAGGIO ID MACCHINARIO, tramite la stringa di matricola
	//		siccome viene generato dal database
	public int getId(String idMacchinario) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		try {
			int id = -1;
			System.out.println("RICERCA ID MACCHINARIO");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			String sql2 = "SELECT * FROM macchinario WHERE matricola = '"+idMacchinario+"';";
			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql2); // faccio la query su uno statement
				while (rs.next() == true) {
					id = rs.getInt("idmacchinario");
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}
			conn.commit();
			return id;
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

	/**
	 * la generazione dell'offerta avverra' tramite query al database 
	 * si incroceranno i vari dati inseriti dall'utente per poter 
	 * fornire l'offerta migliore
	 * @throws SQLException 
	 */
	// può essere modicato in metodo void siccome non viene utilizzato il ritorno del metodo
	public void genera_offerta() throws SQLException {
		/*
		 * tramite chiamate al database saremo capaci di fornire l'offerta migliore / le offerte migliori
		 */
		//int id_polizza[] = {-1, -1, -1};
		Statement st;
		ResultSet rs;
		String sql;
		Statement stmt = null;
		Connection conn = null;
		int i=0;
		try {
			//int id = -1;
			System.out.println("RICERCA OFFERTE");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			
			// bisogna visionare che ricerca devo fare per offrire la polizza migliore all'user, per adesso solo tramite il valore del macchinario
			sql = "SELECT * FROM api.proposte WHERE valore <= "+getValore()+" and "
					+ "tip_mach = '"+getTip()+"' and "
					+ "durata = '" +getDurata()+"' and "
					+ "tip_polizza = '"+getAss_gar()+ "';";

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(rs.getDouble("premio") + "\t" + rs.getInt("idproposta") + "\t" +  rs.getDouble("massimale"));
					if(i<3) {
						// vengono instanziati i tre array delle polizze fornite all'user
						setId_polizza(rs.getInt("idproposta"), i);
						setMassimale(rs.getDouble("massimale"), i);
						setPremio(rs.getDouble("premio"), i);
					}
					i++;
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
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



