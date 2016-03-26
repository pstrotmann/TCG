package service;

import java.util.ArrayList;
import java.util.List;

import org.strotmann.tcg.Dta;
import org.strotmann.tcg.Mitglied;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class CheckDta {
	
	public static void main(String[] args) {
		
		List <Mitglied> beitragsliste = new ArrayList();
		ObjectContainer db=Db4o.openFile(args[0]);
		Mitglied proto = new Mitglied();
		ObjectSet<Mitglied> mitglieder = db.get(proto);
		for (Mitglied mitglied : mitglieder) {
			if (mitglied.getBeitragsklasse() > 0 && mitglied.getAmt() > 0)
				beitragsliste.add(mitglied);
		}
		
		Dta dta = new Dta(beitragsliste);
		String dtaName = dta.erzeugeDta();
		System.out.println("Name der DTA-Datei: " + dtaName);
		db.close();
	}
}
