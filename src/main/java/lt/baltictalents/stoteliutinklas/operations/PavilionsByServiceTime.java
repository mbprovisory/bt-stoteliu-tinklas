package lt.baltictalents.stoteliutinklas.operations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

import lt.baltictalents.stoteliutinklas.data.beans.Station;
import lt.baltictalents.stoteliutinklas.data.database.DatabaseOperationsMangirdas;

/* sprint 3 7)	Galimybė gauti sąrašą per ilgai neatnaujintų paviljonų sąrašą:
 *	a.	Vartotojas gali įvesti limitą (pvz. 30D reikštų 30 dienų arba 1M reikštų vieną mėnesį)
 *	b.	Vartotojui parodomos tik tos stotelės, kuriose darbai buvo atlikti prieš daugiau laiko nei 
 *	limitas (skaičiuojant nuo šiandienos).
 *  d. Jei darbai niekada nebuvo atlikti, paviljonas irgi yra įtraukiamas į sąrašą
 */

public class PavilionsByServiceTime {
	
	/**
	 * @param d
	 * @return
	 */
	public List<Station> get(int d){                  // time elapsed
		
		LocalDate dabar = LocalDate.now();
		LocalDate segment = dabar.minusDays(d);       // last service date
		
		List<Station> data1 = 
				DatabaseOperationsMangirdas.getPavilionsByOldestDate();
		
		List<Station> oldest = new LinkedList<Station>();
		for(Station old: data1) {
			if(old.getDate() == null) {               // never service
				oldest.add(old);
			}else {
//			    LocalDate lDate = old.getDate()       // 1 variant convert
//					     .toInstant()
//					     .atZone(ZoneId.systemDefault())
//					     .toLocalDate();
				LocalDate lDate = 
						new  java.sql.Date(old.getDate()
								.getTime()).toLocalDate();  // 2 variant convert
				if(lDate.isBefore(segment))
			
				oldest.add(old);
			}
		}
		return oldest;
	}
}

