package org.strotmann.tcg;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.strotmann.db.Db4oFactory;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class MitgliedPersisterDB4Objects implements MitgliedPersister
{
	public long getMitgliedId(Mitglied m) {
		return file.ext().getID(m);
	}

	private ObjectContainer file;
	
	public MitgliedPersisterDB4Objects() {
		HttpServletRequest request =
			(HttpServletRequest) FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequest();
		
		this.file = new Db4oFactory().getDb(request);
	}

	public void store(Mitglied mitglied) {
		file.set(mitglied);
		file.commit();
	}
	
	public List <Mitglied> readMitglieder () {
		List <Mitglied> mitgliederListe = new ArrayList <Mitglied>();
		Mitglied proto = new Mitglied();
		ObjectSet<Mitglied> result = file.get(proto);
		while(result.hasNext()) {
			Mitglied mitglied = result.next();
			mitglied.setMitgliedId(file.ext().getID(mitglied));
	        mitgliederListe.add(mitglied);
	    }
		return mitgliederListe;
	}
	
	public Mitglied readMitglied (long mId) {
	    Mitglied m = (Mitglied) file.ext().getByID(mId);
	    file.activate(m, 1);
	    m.setMitgliedId(mId);
		return m;
	}
	
	public void delete(Mitglied mitglied) {
		file.delete(mitglied);
		file.commit();
	}
}
