package zad1;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

public class TravelData {
	
	File file;
	Scanner sc;
	StringBuilder sb;
	String [] kolumn;
	List<Oferta> oferty = new ArrayList<Oferta>();
	ArrayList<String> list;
	Map<String, String> miejsceZam = Map.of("sea", "morze",
																		"mountains", "gory",
																		"lake", "jezioro",
																		"morze", "sea",
																		"gory", "mountains",
																		"jezioro", "lake");


	List <Oferta> ofertyList = new ArrayList<>();
	


	public TravelData(File file)  {


		
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
			try {
				file = new File(file.getAbsoluteFile() + "/plik.txt");
			
				sc = new Scanner(file);
				
				while(sc.hasNext()) {
					
																	
					String [] oddziel = sc.nextLine().split("\t");
					
					oferty.add(

							new Oferta(
							String.valueOf(oddziel[0].replace('_', '-')),
							String.valueOf(oddziel[1].replace('_', '-')),
							convertToLocalDateViaInstant(sdf.parse(oddziel[2])),
							convertToLocalDateViaInstant(sdf.parse(oddziel[3])),
							String.valueOf(oddziel[4]),
							Double.parseDouble(oddziel[5].replace(",",".")),
							String.valueOf(oddziel[6])
						));	
				}		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
		
		Locale localDate = new Locale(String.valueOf(locale.replace('_', '-')));
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		list = new ArrayList<String>();



		Locale loc = Locale.forLanguageTag(locale);
	

			for (Oferta oferta : oferty) {
				if (locale.startsWith(oferta.jezyk_kraj.substring(0,2))) {
					String krajString = oferta.kraj;
					Date date_w = Date.from(oferta.date_wyjazdu.atStartOfDay(ZoneId.systemDefault()).toInstant());
					String data_wStr = sdf.format(date_w);

					Date date_p = Date.from(oferta.date_powrotu.atStartOfDay(ZoneId.systemDefault()).toInstant());
					String data_pStr = sdf.format(date_p);

					String miejsce_Str = oferta.miejsce.toString();

					String cena_Str = NumberFormat.getInstance(localDate).format(oferta.cena);
					String waluta_Str=oferta.walutaLocale;

					String wynikString = krajString + " " + data_wStr + " " + data_pStr + " " + miejsce_Str + " " + cena_Str + " " + waluta_Str;

					try {
						ofertyList.add(new Oferta(locale,krajString,convertToLocalDateViaInstant(sdf.parse(data_wStr)),convertToLocalDateViaInstant(sdf.parse(data_pStr)),miejsce_Str,Double.parseDouble(cena_Str.replace(",","")),waluta_Str));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					list.add(wynikString);
			
				}else{
					String krajString= oferta.kraj;
					Locale in = Locale.forLanguageTag(oferta.jezyk_kraj);


					singlesearchloop:
					for(Locale l : Locale.getAvailableLocales()){
						if(l.getDisplayCountry(in).equals(krajString)){
							
							if (krajString.equals(l.getDisplayCountry(loc))){

								loc = Locale.forLanguageTag(locale.replace("_","-"));
								for(Locale lo : Locale.getAvailableLocales()){
									if(lo.getDisplayCountry(in).equals(krajString)){
										

										krajString = lo.getDisplayCountry(loc);
									
										Date date_w = Date.from(oferta.date_wyjazdu.atStartOfDay(ZoneId.systemDefault()).toInstant());
										String data_wStr = sdf.format(date_w);

										Date date_p = Date.from(oferta.date_powrotu.atStartOfDay(ZoneId.systemDefault()).toInstant());
										String data_pStr = sdf.format(date_p);

										String miejsce_Str = miejsceZam.get(oferta.miejsce);

										String cena_Str = NumberFormat.getInstance(lo).format(oferta.cena);

										String waluta_Str = oferta.walutaLocale;

										String wynikString = krajString + " " + data_wStr + " " + data_pStr + " " + miejsce_Str + " " + cena_Str + " " + waluta_Str;
										list.add(wynikString);
										try {
											ofertyList.add(new Oferta(locale,krajString,convertToLocalDateViaInstant(sdf.parse(data_wStr)),convertToLocalDateViaInstant(sdf.parse(data_pStr)),miejsce_Str,Double.parseDouble(cena_Str.replace(",","")),waluta_Str));
										} catch (ParseException e) {
											e.printStackTrace();
										}


										break singlesearchloop;
									}


								}

							}
						
							



							krajString = l.getDisplayCountry(loc);
							
							Date date_w = Date.from(oferta.date_wyjazdu.atStartOfDay(ZoneId.systemDefault()).toInstant());
							String data_wStr = sdf.format(date_w);

							Date date_p = Date.from(oferta.date_powrotu.atStartOfDay(ZoneId.systemDefault()).toInstant());
							String data_pStr = sdf.format(date_p);

							String miejsce_Str = miejsceZam.get(oferta.miejsce);

							String cena_Str = NumberFormat.getInstance(localDate).format(oferta.cena);
							String waluta_Str = oferta.walutaLocale;
							
							String wynikString = krajString + " " + data_wStr + " " + data_pStr + " " + miejsce_Str + " " + cena_Str + " " + waluta_Str;
							list.add(wynikString);
							try {
								ofertyList.add(new Oferta(locale,krajString,convertToLocalDateViaInstant(sdf.parse(data_wStr)),convertToLocalDateViaInstant(sdf.parse(data_pStr)),miejsce_Str,Double.parseDouble(cena_Str.replace(",","")),waluta_Str));
							} catch (ParseException e) {
								e.printStackTrace();
							}


							break;
						}
					}




				}
			}


		return list;
	}
	
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
//	public Locale translateCountryNameToLocale(String nameToTranslate, Locale inputLocale) {
//		for(Locale locale : Locale.getAvailableLocales()) {
//			if(locale.getDisplayCountry(inputLocale).equals(nameToTranslate))
//				return locale;
//		}
//		throw new IllegalArgumentException("No such country!");
//	}

	public ArrayList<String> getResList() {
		return list;
	}
}
