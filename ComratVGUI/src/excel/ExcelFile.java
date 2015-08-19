package excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import BackEnd.frequencyAndLikelyHoodModel;
import BackEnd.solutionNode;

public class ExcelFile {
	private String fileName = "B:\\eclipseworkspace\\DerbyDemo\\LogFiles\\result0.xls";

	public void write(Map<Integer, solutionNode> solution) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Comrat-V results");

		CellStyle style = workbook.createCellStyle();// Create style
		Font font = workbook.createFont();// Create font
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// Make font bold
		style.setFont(font);
		int j = 0;
		int i = 0;
		Row row;
		Cell cell = null;
		solutionNode solutionN;
		row = sheet.createRow(i);
		String[] headers = { "First Item", "Second Item", "Third Item",
				"Correct Answer", "Comrat-V Answer", "WaWi", "Wa",
				"% WaWi/(3*Wa)", "WbWi", "Wb", "% WbWi/(3*Wb)", "Wc", "WcWi",
				"% WiWc/(3*Wc)", "% Total" };
		for (j = 0; j < 15; j++) {
			row.setRowStyle(style);
			cell = row.createCell(j);
			sheet.autoSizeColumn(j);
			cell.setCellValue(headers[j]);
		}
		++i;
		row = sheet.createRow(i);
		String query[];
		Double[] frequencyAndLikelyHoodToInsert;
		frequencyAndLikelyHoodModel frequencyAndLikeyHoodData;
		int cellNumber = 0;
		for (Entry<Integer, solutionNode> sN : solution.entrySet()) {
			solutionN = sN.getValue();
			query = new String[] { solutionN.firstItem, solutionN.secondItem,
					solutionN.thirdItem, solutionN.correctAnswer };
			for (cellNumber = 0; cellNumber < 4; cellNumber++) {
				cell = row.createCell(cellNumber);
				sheet.autoSizeColumn(cellNumber);
				cell.setCellValue(query[cellNumber]);
			}
			if (solutionN.getConvergedAnswer().keySet().size() < 1) {
				cell = row.createCell(4);
				sheet.autoSizeColumn(4);
				cell.setCellValue("!!!No Convergence!!!");
				++i;
				row = sheet.createRow(i);
			} else {
				for (String answer : solutionN.getConvergedAnswer().keySet()) {
					frequencyAndLikeyHoodData = solutionN
							.getFrequencyAndLikelyHood().get(answer);
					cell = row.createCell(4);
					sheet.autoSizeColumn(4);
					cell.setCellValue(answer);

					frequencyAndLikelyHoodToInsert = new Double[] {
							(double) frequencyAndLikeyHoodData.firstItemFrequency,
							(double) frequencyAndLikeyHoodData.firstItemFrequency,
							frequencyAndLikeyHoodData.firstItemLikelyhood,
							(double) frequencyAndLikeyHoodData.secondItemComratVAnswerFrequency,
							(double) frequencyAndLikeyHoodData.secondItemFrequency,
							frequencyAndLikeyHoodData.secondItemLikelyhood,
							(double) frequencyAndLikeyHoodData.thirdItemComratVAnswerFrequency,
							(double) frequencyAndLikeyHoodData.thirdItemFrequency,
							frequencyAndLikeyHoodData.thirdItemLikelyhood,
							frequencyAndLikeyHoodData.totalLikelyhood };
					for (cellNumber = 5; cellNumber < 15; cellNumber++) {
						cell = row.createCell(cellNumber);
						sheet.autoSizeColumn(cellNumber);
						cell.setCellValue(frequencyAndLikelyHoodToInsert[cellNumber]);
					}
					synchronized (this) {
						++i;
						row = sheet.createRow(i);
					}
				}
			}
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void read(String fileName, int columnNo) { fileName =
	 * "ComratVKB2.xls"; columnNo = 1; String parse = ""; String item = "";
	 * Map<String, Integer> map = null; String[] answers; int rows, cols, tmp;
	 * Row rowq; Cell cellq; String toLower; try { POIFSFileSystem fs = new
	 * POIFSFileSystem(new FileInputStream( fileName)); HSSFWorkbook wb = new
	 * HSSFWorkbook(fs); for (int i = 3; i < 4; i++) { HSSFSheet sheet =
	 * wb.getSheetAt(i); HSSFRow row = null; rows =
	 * sheet.getPhysicalNumberOfRows(); cols = 0; tmp = 0; // This trick ensures
	 * that we get the data properly even if it // doesn't start from first few
	 * rows for for (int j = 0; j < 10 || j < rows; j++) { row =
	 * sheet.getRow(j); if (row != null) { tmp =
	 * sheet.getRow(j).getPhysicalNumberOfCells(); if (tmp > cols) cols = tmp; }
	 * } for (int p = 0; p < 15; p++) { parse = ""; item = ""; columnNo = p; map
	 * = new TreeMap<String, Integer>(); for (int j = 0; j < 9; j++) { rowq =
	 * sheet.getRow(j); if (rowq != null && rowq.getCell(columnNo) != null) {//
	 * column // no // ///////////////////////////////////////////// cellq =
	 * rowq.getCell(columnNo); // ////////////////////////////////////////////
	 * if (!(cellq.toString().trim().isEmpty())) {// get // first parse =
	 * cellq.toString().trim().toLowerCase(); // cell if (j == 0) { item =
	 * parse.split(":")[1]; item = item.toLowerCase().trim(); } else { answers =
	 * parse.split(","); for (int k = 0; k < answers.length; k++) { if
	 * (answers[k].trim().isEmpty() == false) { toLower =
	 * answers[k].toLowerCase(); toLower = toLower.trim(); if
	 * ((map.containsKey(toLower))) { map.put(toLower, map.get(toLower) + 1); }
	 * else { map.put(toLower, 1); }
	 * 
	 * if ((map1.containsKey(toLower))) { map.put(toLower, map.get(toLower) +
	 * 1); } else { map1.put(toLower, 1); }
	 * 
	 * } } }
	 * 
	 * } } }
	 * 
	 * System.out.println(p + "--------------------------------------"); //
	 * System.out.println(item+ " "+map); for (Entry<String, Integer> entry :
	 * map.entrySet()) { System.out.println(item + "=>" + entry.getKey() + "=>"
	 * + entry.getValue()); // database.databaseQuery(item.trim().toLowerCase(),
	 * // entry.getKey().trim().toLowerCase() // , entry.getValue()); }
	 * System.out
	 * .println("--------------------------------------------------");
	 * System.out.println("total####################");
	 * 
	 * System.out.println(map1); System.out.println(map1.size());
	 * 
	 * // toDatabase(item, map); } } wb.close(); } catch (Exception ioe) {
	 * ioe.printStackTrace(); } }
	 */

}
