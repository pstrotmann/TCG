package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.strotmann.tcg.Mitglied;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Sepa {
	
	private static POIFSFileSystem fs = null;
	private static HSSFWorkbook wb = null;

	public static void main(String[] args) {
		
		ObjectContainer db=Db4o.openFile(args[0]);
		Mitglied proto = new Mitglied();
		ObjectSet<Mitglied> mitglieder = db.get(proto);
		try {		
			fs = new POIFSFileSystem(new FileInputStream("/vol/BLZ_BIC.xls"));
			wb = new HSSFWorkbook(fs);
			} catch (FileNotFoundException e) {
				System.out.println("BLZ_BIC.xlsx nicht gefunden");			
			} catch (IOException e) {
				System.out.println("BLZ_BIC.xlsx nicht gefunden");			
			}
		for (Mitglied m : mitglieder) {
			String IBAN = getIBAN(m.getBankleitzahl(), m.getKontonummer());
			m.setIBAN(IBAN);
			String BIC = getBIC(m.getBankleitzahl());
			m.setBIC(BIC);
			db.set(m);
			db.commit();
			System.out.println(m.getZuname()+","+m.getVorname()+" "+m.getIBAN()+" "+m.getBIC());
		}
		db.close();
	}
	
	public static String getIBAN(long bankleitzahl, long kontonummer) {
		String	 pz = "",
				blzString = StringUtils.leftPad(((Long) bankleitzahl).toString(), 8, '0'),
				ktoString = StringUtils.leftPad(((Long) kontonummer).toString(), 10, '0');
		
		try {	
			if (bankleitzahl > 0 && kontonummer > 0) {
				pz = new IBANCheckDigit().calculate("DE00" + blzString
						+ ktoString);
			} else
				pz = "";	
		} catch (CheckDigitException e) {
			e.printStackTrace();
		}
		
		if (pz.equals("")) {
			return pz;
		} else
			return "DE" + pz + blzString + ktoString;
	}
	
	@SuppressWarnings("deprecation")
	public static String getBIC (long bankleitzahl) {
	
		HSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			String blz = row.getCell(0).getStringCellValue();
			if (blz.equals(((Long)bankleitzahl).toString())) {
				return row.getCell(4).getStringCellValue();
			}
		}
		return "";
	}
}
