package com.yuriel.customView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.yuriel.domain.UserVO;

@Component(value="usersXlsx")
public class UsersXlsxView extends AbstractXlsxView {
	@Override
	protected void buildExcelDocument(
				Map<String, Object> model,
				Workbook workbook,
				HttpServletRequest request,
				HttpServletResponse response
			) throws Exception {
		// 1. 시트 만들기
		Sheet sheet = createFirstSheet(workbook);
		// 2. 라벨 만들기
		createColumnLabel(sheet);
		// 3. 각 행의 셀 만들기
		@SuppressWarnings("unchecked")
		List<UserVO> users = (ArrayList<UserVO>) model.get("result");
		int rowNum = 1;
		for(UserVO user : users) {
			createPageRankRow(sheet, user, rowNum++);
		}
		
		String userAgent = request.getHeader("User-Agent");
		boolean ie = (userAgent.indexOf("MSIE") > -1) || (userAgent.indexOf("rv") > -1);
					// 해당 request가 IE(Internet Explorer) 브라우저에서 왔는지 확인한다 -> IE11 이전은 "MSIE", IE11은 "rv"
		String name = "UserList.xlsx";
		String fileName = null;
		
		if(ie) { // MSIE일 때 처리
			fileName = URLEncoder.encode(name, "UTF-8");
		} else { // MSIE 외 브라우저일 때 처리
			fileName = new String(name.getBytes("UTF-8"), "ISO-8859-1");
						// 만약 UTF-8 사용이 불가하다면 ISO-8859-1으로 대신 사용하도록 지정한다.
		}
		
		response.setHeader("Content-Disposition",
						   "attachment; filename=\"" + fileName + "\";");
	}
	
	private Sheet createFirstSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "회원 명단");
		sheet.setColumnWidth(1, 256 * 20);
		return sheet;
	}
	
	private void createColumnLabel(Sheet sheet) {
		Row firstRow = sheet.createRow(0);
		Cell cell = firstRow.createCell(0);
		cell.setCellValue("회원 번호");
		
		cell = firstRow.createCell(1);
		cell.setCellValue("회원 이메일");
		
		cell = firstRow.createCell(2);
		cell.setCellValue("회원 이름");
	}
	
	private void createPageRankRow(Sheet sheet, UserVO user, int rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(0);
		cell.setCellValue(user.getId());
		
		cell = row.createCell(1);
		cell.setCellValue(user.getEmail());
		
		cell = row.createCell(2);
		cell.setCellValue(user.getName());
	}	
}