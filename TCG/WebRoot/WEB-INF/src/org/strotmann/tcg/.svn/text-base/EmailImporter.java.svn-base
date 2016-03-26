package org.strotmann.tcg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class EmailImporter {
	
	private POIFSFileSystem fs = null;
	private HSSFWorkbook wb = null;
	private Map <String, String> eMailMap = new HashMap();
	
	public void importiere() {
		try {
			
			fs = new POIFSFileSystem(new FileInputStream("/home/peter/workspaces/tcg/TCG/altDaten/tcg-email.xls"));
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			System.out.println("tcg-email.xls nicht gefunden");			
		} catch (IOException e) {
			System.out.println("tcg-email.xls nicht gefunden");			
		}
		
		HSSFSheet sheet = wb.getSheetAt(0);
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			String s = row.getCell((short)0).getRichStringCellValue().getString();
			String t = row.getCell((short)1).getRichStringCellValue().getString();
			String zuname = s.split(",")[0].trim();
			String vorname = s.split(",")[1].trim();
			eMailMap.put(zuname+vorname, t);
		}
	}
	
	public String sucheEmail (String zuname, String vorname) {
		
		String key = zuname+vorname;
		return this.eMailMap.get(key);		
	}
	
	public void listEmail () {
		for (String eMail : eMailMap.values()) {
			System.out.println(eMail);
		}
	}
	
	public void removeEntry (String key) {
		this.eMailMap.remove(key);
	}
}
