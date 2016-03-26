package org.strotmann.tcg;

import java.util.List;

public interface MitgliedPersister {
	
	public void store (Mitglied mitglied);
	
	public List <Mitglied> readMitglieder ();
	
	public Mitglied readMitglied (long l);
	
	public long getMitgliedId (Mitglied m);
	
	public void delete (Mitglied mitglied);

}
