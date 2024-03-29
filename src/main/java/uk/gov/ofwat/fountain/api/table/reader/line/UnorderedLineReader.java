package uk.gov.ofwat.fountain.api.table.reader.line;

import java.util.HashMap;
import java.util.Map;

import uk.gov.ofwat.fountain.api.table.reader.Column;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class UnorderedLineReader extends LineReader {
	
	public void read(GroupEntry groupEntry, Company company) {
		
		Map<String, DataDto> dataKeyedOnItemCode = new HashMap<String, DataDto>();
		for (DataDto dto: dataTable.getDataList().values()) {
			dataKeyedOnItemCode.put(dto.getItem().getCode(), dto);
		}
		
		Map<String, DataDto> dataKeyedOnItemCodeAndIntervalId = new HashMap<String, DataDto>();
		for (DataDto dto: dataTable.getDataList().values()) {
			dataKeyedOnItemCodeAndIntervalId.put(dto.getItem().getCode() + "-" + dto.getIntervalDto().getId(), dto);
		}
		
		String rowItemCode = null;
		int colIdx = 0;
		for(FormDisplayCell cell: cells){
//			log.info("Row: " + row.getRowNum()+1 + "    Col: " + colIdx);
			DataDto dto;
			String newValue; 
			if (null != cell) {
	    		switch(cell.getCellType()){
	    		case EMPTY:
	    			colIdx++;
	    			break;
	    		case ROW_HEADING_NON_REPEAT:
	    			colIdx++;
	    			break;
	    		case COL_HEADING:
	    			colIdx++;
	    			break;
	    		case ROW_HEADING:
	    			if (section.getItemCodeColumn() == null) {
						metaData.addError(row.getRowNum(), colIdx, "ItemCodeColumn is not set in an unordered section.");
	    			}
	    			else if (colIdx+1 == section.getItemCodeColumn()) {
		    			String headerText = readHeaderCell(row, colIdx);
		    			if (null == headerText ||
		    				headerText.isEmpty()) {
		    				log.info("Row: " + (row.getRowNum()+1) + "    Col: " + colIdx + " No code present.");
		    			}
		    			else if (dataKeyedOnItemCode.containsKey(headerText)) {
		    				rowItemCode = headerText;
		    			}
		    			else {
							metaData.addError(row.getRowNum(), colIdx, "Code : '" + headerText + "' cannot be found. Is this a new code?");
		    			}
	    			}
	    			colIdx++;
	    			break;
	    		case CALC:
	    			if (null == rowItemCode) {
	    				continue;
	    			}
	    			setupColumn(colIdx, cell, rowItemCode);
	    			colIdx++;
	    			break;
	    		case COPYCELL:
	    			if (null == rowItemCode) {
	    				continue;
	    			}
	    			setupColumn(colIdx, cell, rowItemCode);
	    			dto = dataKeyedOnItemCodeAndIntervalId.get(itemCodeAndIntervalIdKey(rowItemCode, colIdx));
	    			if (null == dto) {
	    				reportMissingItemCodeAndInterval(rowItemCode, colIdx);
	    			}
	    			else {
	    				newValue = readDataCell(row, colIdx, dto, cell.getCellType());
	    				if (metaData.isAllowHistoricData()) {
	        				updateDtoValue(dto, cell.getCellType(), newValue);
	    				}
	    				else {
	    					reportInvalidChange(row.getRowNum(), colIdx, dto, cell.getCellType(), newValue);
	    				}
    				}
	    			colIdx++;
	    			break;
	    		case INPUT:
	    			if (null == rowItemCode) {
	    				continue;
	    			}
	    			setupColumn(colIdx, cell, rowItemCode);
	    			dto = dataKeyedOnItemCodeAndIntervalId.get(itemCodeAndIntervalIdKey(rowItemCode, colIdx));
	    			if (null == dto) {
	    				reportMissingItemCodeAndInterval(rowItemCode, colIdx);
	    			}
	    			else {
	    				readDataCell(row, colIdx, dto, cell.getCellType());
	    				newValue = readDataCell(row, colIdx, dto, cell.getCellType());
	    				updateDtoValue(dto, cell.getCellType(), newValue);
	    			}
	    			colIdx++;
	    			break;
	    		case GROUP_ROW_HEADING:
	    			if(!groupEntry.getGroup().getName().equals("NONE")){
		    			colIdx++;
	    			}
	    			break;
	    		}
			}
    	}
	}

	private String itemCodeAndIntervalIdKey(String rowItemCode, int colIdx) {
		return rowItemCode + "-" + section.getColumn(colIdx).getIntervalId();
	}

	private void setupColumn(int colIdx, FormDisplayCell cell, String rowItemCode) {
		if (null == section.getColumn(colIdx)) {
			String keyString = cell.getCellContents();
			DataKey key = new DataKey(keyString);
			
			Column column = new Column(key.getIntervalIdInteger(), rowItemCode); 
			section.addColumn(colIdx, column);
		}
	}
}
