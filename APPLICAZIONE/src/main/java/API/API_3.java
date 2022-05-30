package API;

import java.sql.Date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import db.SaveMySQL;

public class API_3 {

	public int id_macchinario;
	public Date data_inizio;
	public Date data_fine;
	public Double massimale;
	public String selezione;
	public double premio;
	public int id_polizza;
	public String durata;
	public String pag; 
	
	
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

	public int getId_macchinario() {
		return id_macchinario;
	}

	public void setId_macchinario(int id_macchinario) {
		this.id_macchinario = id_macchinario;
	}

	public Date getData_inizio() {
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

	public int getId_polizza() {
		return id_polizza;
	}

	public void setId_polizza(int id_polizza) {
		this.id_polizza = id_polizza;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getPag() {
		return pag;
	}

	public void setPag(String pag) {
		this.pag = pag;
	}
	

	// inserimento di una nuova polizza all'interno del DB
	public void generaPolizza(API_1 api1, String pag, int offerta, API_3 api3, int idU) throws Exception {
		//SaveMySQL savePolizza = new SaveMySQL();
		Statement stmt = null;
		Connection conn = null;
		System.out.println("INSERIMENTO NUOVA POLIZZA ATTIVA");
		conn= SaveMySQL.getDBConnection();
		conn.setAutoCommit(false); // non so cosa sia
		stmt = conn.createStatement(); //nemmeno
		String sql = "INSERT INTO api.polizza(inizio, fine, massimale, premio, pagamento, fk_macchinario, fk_user)";   
		sql += " VALUES('"
				+ api3.getData_inizio()+ "','"
				+ api3.getData_fine()+ "',"
				+ api3.getMassimale(offerta)+ ","
				+ api3.getPremio(offerta)+ ",'"
				+ pag+ "','"
                + api3.getId_macchinario()+ "','"
				+ idU+ "');";
		System.out.println("INSERT QUERY: "+sql);

		stmt.execute(sql);

		conn.commit();
	}
	
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
	
	
	
	/////////////////////////////////////////

}
