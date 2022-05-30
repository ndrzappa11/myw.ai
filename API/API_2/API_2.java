package API;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import db.SaveMySQL;

public class API_2 {

	//API DI LOGIN

	private int Iduser;
	private String nome;
	private String cognome;
	private String email;
	private String pass;
	private Date nascita;
	private String telefono;
	private Date inserimento;

	//API_2 test = (API_2) request.getSession().getAttribute("testLogin");


	// METODI AUTOGENERATI
	public int getId() {
		return Iduser;
	}
	public void setId(int id) {
		this.Iduser = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getNascita() {
		return nascita;
	}
	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getInserimento() {
		return inserimento;
	}
	public void setInserimento(Date inserimento) {
		this.inserimento = inserimento;
	} 

	// FORNISCE LA DATA ATTUALE, quando viene richiamato il metodo ritorna la data nel quale viene richiamato
	public void setInserimento() {
		Date utilDate = new Date();;
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		this.inserimento = sqlDate;
	}
	public Date getInserimento2() {
		Date utilDate = new Date();;
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	/*
	 * 
	 * METODI CON ALL'INTERNO LA CONNESSIONE AL DB 
	 * 
	 */

	// METODO DI LOGIN
	public boolean login (String email, String password, API_2 User) throws SQLException {
		Statement st;
		ResultSet rs=null;
		String sql = "no"; //posso non instaziare la stringa
		Statement stmt = null;
		Connection conn = null;
		boolean ok = false;	//se viene registrato ritorna true, se no rimane false
		String nome = null;
		try {
			System.out.println("RICERCA USER PER IL LOGIN");
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			sql = "SELECT * FROM public.user WHERE email = '"+email+"' and password = '"+password+"';";

			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				//System.out.println(rs.getString("nome") + "\t" + rs.getString("cognome") );
				while(rs.next()) {
					System.out.println(rs.getString("nome") + "\t" + rs.getString("cognome") +"\t"+ rs.getString("password")+"\t"+ rs.getString("email") );
					nome = rs.getString("nome");
					User.setNome(rs.getString("nome"));
					User.setCognome(rs.getString("cognome"));
					User.setPass(rs.getString("password"));
					User.setEmail(rs.getString("email"));
					User.setNascita(rs.getDate("nascita"));
					User.setTelefono(rs.getString("telefono"));
					User.setInserimento(rs.getDate("inserimento"));
					//testLogin.setId(rs.getInt("iduser"));
				}
			} catch (SQLException e) {
				System.out.println("questo?");
				System.out.println("errore:" + e.getMessage());
			}
			conn.commit();
			if(nome != null) ok = true;
			return ok;

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

	// RICERCA ID USER
	public int getIdU(API_2 user) throws SQLException {
		System.out.println("RICERCA ID USER USER");
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		try {
			int id = -1;
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno


			String sql2 = "SELECT * FROM public.user WHERE nome = '"+user.getNome()+"' and cognome = '"+user.getCognome()+"'"
					+ "and email = '"+user.getEmail()+"';";
			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql2); // faccio la query su uno statement
				while (rs.next() == true) {
					id = rs.getInt("iduser");
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


	//	INSERIMENTO USER NEL DATABASE
	public void setUser(API_2 user) throws SQLException {
		System.out.println("SALVATAGGIO USER");
		Statement stmt = null;
		Connection conn = null;
		System.out.println("test1");
		try {

			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno


			String sql = "INSERT INTO public.user(nome, cognome, email, password, nascita, telefono, inserimento)";   
			sql += " VALUES('"
					+ user.getNome()+ "','"
					+ user.getCognome()+ "','"
					+ user.getEmail()+ "','"
					+ user.getPass()+ "','"
					+ user.getNascita()+ "','"
					+ user.getTelefono()+ "','"
					+ user.getInserimento()+ "');";
			System.out.println("INSERT QUERY: "+sql);

			stmt.execute(sql);
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

	//	INSERIMENTO USER NEL DATABASE
	public void setUser(String nome, String cognome, String email, String password, Date nascita, String telefono) throws SQLException {
		System.out.println("SALVATAGGIO USER");
		Statement stmt = null;
		Connection conn = null;
		System.out.println("test1");
		try {

			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno


			String sql = "INSERT INTO public.user(nome, cognome, email, password, nascita, telefono, inserimento)";   
			sql += " VALUES('"
					+ nome + "','"
					+ cognome + "','"
					+ email + "','"
					+ password + "','"
					+ nascita + "','"
					+ telefono + "','"
					+ getInserimento2()+ "');";
			System.out.println("INSERT QUERY: "+sql);

			stmt.execute(sql);
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

	
	


}
