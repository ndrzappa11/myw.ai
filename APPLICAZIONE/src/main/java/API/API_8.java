package API;

import java.time.LocalDate;

public class API_8 {

	public String stato;
	public String descrizione;
	public String doc; //gestire i pdf, mi sa con un altra classe
	public LocalDate data;
	
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData() {
		this.data = LocalDate.now();
	}
	
}
