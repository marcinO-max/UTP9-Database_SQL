package zad1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;



public class Oferta {
	
	String jezyk_kraj;
	String kraj;
	LocalDate date_wyjazdu;
	LocalDate date_powrotu;
	String miejsce;
	double cena;
	String walutaLocale;




	public Oferta(String jezyk_kraj, String kraj, LocalDate date_wyjazdu, LocalDate date_powrotu, String miejsce , double cena, String walutaLocale) {
		this.jezyk_kraj = jezyk_kraj;
		this.kraj = kraj;
		this.date_wyjazdu = date_wyjazdu;
		this.date_powrotu = date_powrotu;
		this.miejsce = miejsce;
		this.cena = cena;
		this.walutaLocale=walutaLocale;

	}

	@Override
	public String toString() {
		return "Oferta{" +
				"jezyk_kraj='" + jezyk_kraj + '\'' +
				", kraj='" + kraj + '\'' +
				", date_wyjazdu=" + date_wyjazdu +
				", date_powrotu=" + date_powrotu +
				", miejsce='" + miejsce + '\'' +
				", cena=" + cena +
				", walutaLocale=" + walutaLocale +
				'}';
	}

	public String getKraj() {
		return String.valueOf(kraj);
	}





	public LocalDate getDate_wyjazdu() {
		return date_wyjazdu;
	}





	public LocalDate getDate_powrotu() {
		return date_powrotu;
	}





	public String getMiejsce() {
		return miejsce;
	}





	public String getCena() {
		return String.valueOf(cena);
	}





	public String getWalutaLocale() {
		return String.valueOf(walutaLocale);
	}


	
	

	
}
