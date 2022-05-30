package API;

import java.time.LocalDate;

public class API_7 {

	public String id_macchinario;
	public String id_polizza;
	public LocalDate data;
	
	
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
	public LocalDate getData() {
		return data;
	}
	public void setData() {
		this.data = LocalDate.now();
	}
}
